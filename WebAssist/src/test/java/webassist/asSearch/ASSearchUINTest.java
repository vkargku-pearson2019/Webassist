package webassist.asSearch;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchUIN;
import webassist.data.GetTestData;
import webassist.util.Util;

public class ASSearchUINTest extends BaseTest {
	
	private ASSearchUIN asSearchUIN;
	private Util util = new Util(wd);
	private String path = "testdata/ASSearch/ASSearchUINTest.properties";
	private String testComment = "Automation Test";
	private String searchType = GetTestData.getOutputTestData(path,"searchType");
	private String ID = GetTestData.getOutputTestData(path,"uin");
	
	/**
	 * 
	 * @param searchType
	 * @param ID
	 * @throws InterruptedException
	 */
	@Test(description="AS SEARCH UIN to verify Clear Button is Working")
	private void ASSearchTestMethodUINClear() throws InterruptedException {
		asSearchUIN = new ASSearchUIN(wd);
		Assert.assertTrue(asSearchUIN.clearDetails(searchType,ID), 
				"Unable to Clear UIN Details");
	}
	
	/**
	 * 
	 * @param searchType
	 * @param ID
	 * @throws InterruptedException
	 */
	@Test(description="AS SEARCH with Invalid UIN",
			dependsOnMethods="ASSearchTestMethodUINClear")
	private void ASSearchTestMethodUINInvalid() throws InterruptedException {
		asSearchUIN.ASSearchUIN(searchType,"1234");
		Assert.assertTrue(asSearchUIN.validateWrongID(), 
				"UIN Validation Not Working");
	}
	
	@Test(description="AS SEARCH with valid UIN ",
			dependsOnMethods="ASSearchTestMethodUINInvalid")
	private void ASSearchTestMethodUINCorrect() throws InterruptedException {
		Assert.assertTrue(asSearchUIN.ASSearchUIN(searchType,ID), 
				"Unable to Search using UIN ");
	}
	
	@Test(description="AS SEARCH UIN with All Valid Details and Validate Output Details",
			dataProvider = "ASSearchOutputFields",
			dependsOnMethods="ASSearchTestMethodUINCorrect")
	private void ASSearchTestMethodUINOutputValidation(String field,String value) throws InterruptedException {
		String ansSheetField = GetTestData.getOutputTestData(path,field);
		String ansSheetValue = GetTestData.getOutputTestData(path,value);
		Assert.assertTrue(asSearchUIN.verifyASDetailsPage(ansSheetField,ansSheetValue),
				"Unable to get Output using All valid Details of UIN");
		extentTest.log(Status.INFO,"Verify field value of : " + ansSheetField + ". Value : " + ansSheetValue );
		
	}
	
	@Test(description="AS SEARCH with valid UIN and post Comment",
			dependsOnMethods="ASSearchTestMethodUINOutputValidation")
	private void ASSearchTestMethodUINPageResults() throws InterruptedException {
		String timeStamp = util.getTime();
		String comment=testComment+" "+timeStamp;
		Assert.assertTrue(asSearchUIN.pageResults(comment), 
				"Unable to Post Comment");
		extentTest.log(Status.INFO, "Check that the comment is:" +comment+ "");
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
