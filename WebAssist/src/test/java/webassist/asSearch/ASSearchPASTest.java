package webassist.asSearch;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchPAS;
import webassist.data.GetTestData;
import webassist.util.Util;

public class ASSearchPASTest extends BaseTest {
	
private ASSearchPAS asSearchPAS;
private Util util = new Util(wd);

private String path = "testdata/ASSearch/ASSearchPASTest.properties";
private String testComment = "Automation Test";
	
	@Test(description="AS SEARCH PAS to verify Clear Button is Working")
	private void ASSearchTestMethodPASClear() throws InterruptedException {
		asSearchPAS = new ASSearchPAS(wd);
		String searchType = GetTestData.getOutputTestData(path,"searchType");
		String ID = GetTestData.getOutputTestData(path, "pas");
		Assert.assertTrue(asSearchPAS.clearDetails(searchType,ID), 
				"Unable to Clear PAS Details");
	}

	
	@Test(description="AS SEARCH with Invalid PAS"
			,dependsOnMethods="ASSearchTestMethodPASClear")
	private void ASSearchTestMethodPASInvalid() throws InterruptedException {
		String searchType = GetTestData.getOutputTestData(path,"searchType");
		asSearchPAS.ASSearchPAS(searchType,"1234");
		Assert.assertTrue(asSearchPAS.validateWrongID(), 
				"PAS Validation Not Working");
	}
	
	@Test(description="AS SEARCH with Valid PAS"
			,dependsOnMethods="ASSearchTestMethodPASInvalid")
	private void ASSearchTestMethodPASCorrect() throws InterruptedException {
		String searchType = GetTestData.getOutputTestData(path,"searchType");
		String ID = GetTestData.getOutputTestData(path,"pas");
		Assert.assertTrue(asSearchPAS.ASSearchPAS(searchType,ID), 
				"Unable to Search using PAS");
	}
	
	@Test(description="AS SEARCH PAS with All Valid Details and Validate Output Details",
			dataProvider = "ASSearchOutputFields",
			dependsOnMethods="ASSearchTestMethodPASCorrect")
	private void ASSearchTestMethodPASOutputValidation(String field,String value) throws InterruptedException {
		String ansSheetField = GetTestData.getOutputTestData(path,field);
		String ansSheetValue = GetTestData.getOutputTestData(path,value);
		Assert.assertTrue(asSearchPAS.verifyASDetailsPage(ansSheetField,ansSheetValue),
				"Unable to get Output using All valid Details of PAS");
		extentTest.log(Status.INFO,"Verify field value of : " + ansSheetField + ". Value : " + ansSheetValue );
		
	}
	
	@Test(description="AS SEARCH with Valid PAS and post Comment"
			,dependsOnMethods="ASSearchTestMethodPASCorrect")
	private void ASSearchTestMethodPASPageResults() throws InterruptedException {
		String timeStamp = util.getTime();
		Assert.assertTrue(asSearchPAS.pageResults(testComment+" "+timeStamp), 
				"Unable to Post Comment");
	}
	
	@DataProvider(name = "ASSearchOutputFields") 
	public static Object[][] outputFields() {
		return new Object[][] { {"uinField","uin"},{"pasField","pas"},
			{"testCenterField","testCenterCode"},
			{"adminField","admin"},{"regField","reg"},
			{"lastNameField","lastName"},{"firstNameField",
				"firstName"},{"dobField","dob"} };
	}

}
