package webassist.asSearch;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchBatch;
import webassist.data.GetTestData;
import webassist.util.Util;

public class ASSearchBatchTest extends BaseTest {

	private ASSearchBatch asSearchBatch;
	private Util util = new Util(wd);
	private String path = "testdata/ASSearch/ASSearchBatchTest.properties";
	private String testComment = "Automation Test";
	private String searchType = GetTestData.getOutputTestData(path,"searchType");
	private String adminID = GetTestData.getOutputTestData(path,"admin");
	private String tcID = GetTestData.getOutputTestData(path,"testCenterCode");
	private String regID = GetTestData.getOutputTestData(path,"reg");
	private String last = GetTestData.getOutputTestData(path,"lastName");
	private String first = GetTestData.getOutputTestData(path,"firstName");
	private String batchID = GetTestData.getOutputTestData(path,"batch");
	
	@Test(description="AS SEARCH Batch to verify Clear Button is Working")
	private void ASSearchTestMethodBatchClear() throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		Assert.assertTrue(asSearchBatch.clearDetails(searchType,adminID,tcID,regID,last,first,batchID), 
				"Unable to Clear TC Details");
	}
	
	@Test(description="AS SEARCH Batch with valid Admin Details only",
			dependsOnMethods="ASSearchTestMethodBatchClear")
	private void ASSearchTestMethodBatchAdmin() throws InterruptedException {
		asSearchBatch.ASSearchBatch(searchType,adminID,"","","","","");
		Assert.assertTrue(asSearchBatch.verifyError(),
				"Admin Validation not Working");
	}
	
	@Test(description="AS SEARCH Batch with Invalid Admin and Valid TC Details",
			dependsOnMethods="ASSearchTestMethodBatchAdmin")
	private void ASSearchTestMethodBatchInvalidAdmin() throws InterruptedException {
		asSearchBatch.ASSearchBatch(searchType,"1234",tcID,"","","","");
		Assert.assertTrue(asSearchBatch.validateWrongID(),
				"Admin Validation not Working");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin and Invalid TC Details",
			dependsOnMethods="ASSearchTestMethodBatchInvalidAdmin")
	private void ASSearchTestMethodBatchAdminInvalidTC() throws InterruptedException {
		asSearchBatch.ASSearchBatch(searchType,adminID,"1234","","","","");
		Assert.assertTrue(asSearchBatch.validateWrongID(),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin and TC Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminInvalidTC")
	private void ASSearchTestMethodBatchAdminTC() throws InterruptedException {
		asSearchBatch.ASSearchBatch(searchType,adminID,tcID,"","","","");
		Assert.assertTrue(asSearchBatch.verifysearchResultsPage(),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin,TC,Reg Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminTC")
	private void ASSearchTestMethodBatchAdminTCReg() throws InterruptedException {
		Assert.assertTrue(asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,"","",""),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin,TC,Reg,Last Name Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminTCReg")
	private void ASSearchTestMethodBatchAdminTCRegLName() throws InterruptedException {
		Assert.assertTrue(asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,last,"",""),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin,TC,Reg,Last Name,First Name Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminTCRegLName")
	private void ASSearchTestMethodBatchAdminTCRegLNameFName() throws InterruptedException {
		Assert.assertTrue(asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,last,first,""),
				"Unable to Search using Details of Batch");
	}
	/*
	@Test(description="AS SEARCH Batch with All Valid Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminTCRegLNameFName")
	private void ASSearchTestMethodBatch() throws InterruptedException {	
		Assert.assertTrue(asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,last,first,batchID),
				"Unable to Search using All valid Details of Batch");
	}
	*/
	@Test(description="AS SEARCH Batch with All Valid Details and Validate Output Details",
			dataProvider = "ASSearchOutputFields",
			dependsOnMethods="ASSearchTestMethodBatchAdminTCRegLNameFName")
	private void ASSearchTestMethodBatchOutputValidation(String field,String value) throws InterruptedException {
		String ansSheetField = GetTestData.getOutputTestData(path,field);
		String ansSheetValue = GetTestData.getOutputTestData(path,value);
		Assert.assertTrue(asSearchBatch.verifyASDetailsPage(ansSheetField,ansSheetValue),
				"Unable to get Output using All valid Details of Batch");
		extentTest.log(Status.INFO,"Verify field value of : " + ansSheetField + ". Value : " + ansSheetValue );
	}
	
	@Test(description="AS SEARCH Batch with All Valid Details and post Comment",
			dependsOnMethods="ASSearchTestMethodBatchOutputValidation")
	private void ASSearchTestMethodBatchPageResults() throws InterruptedException {
		String timeStamp = util.getTime();
		Assert.assertTrue(asSearchBatch.pageResults(testComment+" "+timeStamp),
				"Unable to Post Comment"); 
	}
	
	@DataProvider(name = "ASSearchOutputFields") 
	public static Object[][] outputFields() {
		return new Object[][] { {"uinField","uin"},{"pasField","pas"},
			{"testCenterField","testCenterCode"},
			{"adminField","admin"},{"regField","reg"},
			{"lastNameField","lastName"},
			{"firstNameField","firstName"},
			{"dobField","dob"} };
	}
}
	
	