package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchReg;
import webassist.data.GetTestData;
import webassist.util.Util;

public class RegSearchRegTest extends BaseTest{
	
private RegSearchReg regSearchReg;
private Util util = new Util(wd);
private String path = "testdata/Reg Search/RegSearchReg.properties";
private String REG = GetTestData.getOutputTestData(path,"reg");
private String searchType = GetTestData.getOutputTestData(path,"searchType");
private final String fieldsName = "Reg #";

	
	@Test(description="Reg SEARCH REG to verify Clear Button is Working")
	private void RegSearchTestMethodRegClear() throws InterruptedException {
		regSearchReg = new RegSearchReg(wd);
		Assert.assertTrue(regSearchReg.clearDetails(searchType,REG), 
				"Unable to Clear REG Details");
	}

	@Test(description="Reg SEARCH with Invalid REG Details",
			dependsOnMethods="RegSearchTestMethodRegClear") 
	private void RegSearchTestMethodRegInvalid() throws InterruptedException {
		regSearchReg.RegSearchReg("1234");
		Assert.assertTrue(regSearchReg.validateWrongID(), 
				"REG Validation Not Working");
	}
	
	@Test(description="Reg SEARCH with REG Details having Invalid Length ",
			dependsOnMethods="RegSearchTestMethodRegInvalid") 
	private void RegSearchTestMethodRegInvalidLength() throws InterruptedException {
		regSearchReg.RegSearchReg("1234567891011");
		Assert.assertTrue(regSearchReg.validateRegLength("1234567891011"), 
				"REG Length Validation Not Working");
	}
	
	
	@Test(description="Reg SEARCH REG with all Valid Details",
		dependsOnMethods="RegSearchTestMethodRegInvalidLength")
	private void RegSearchTestMethodRegCorrect() throws InterruptedException {
		Assert.assertTrue(regSearchReg.RegSearchReg(REG), 
				"Unable to Search using REG ");
	}
	
	@Test(description="REG SEARCH & Validate Output Details",
			dataProvider = "RegSearchOutputFields",
			dependsOnMethods="RegSearchTestMethodRegCorrect")
	private void REGSearchTestMethodREGOutputValidation(String field,String value) throws InterruptedException {
		String regSheetField = GetTestData.getOutputTestData(path,field);
		String regSheetValue = GetTestData.getOutputTestData(path,value);
		Assert.assertTrue(regSearchReg.verifyREGDetailsPage(regSheetField,regSheetValue),
				"Unable to get Output using All valid Details of REG");
		extentTest.log(Status.INFO,"Verify field value of : " + regSheetField + ". Value : " + regSheetValue );
		
	}
	
	@DataProvider(name = "RegSearchOutputFields") 
	public static Object[][] outputFields() {
		return new Object[][] { {"adminField","admindate"},{"SSDField","SSDCode"},
			         {"lastNameField","lastName"}, {"firstNameField",
				"firstName"},{"dobField","dob"} };
	}
	
	
}
