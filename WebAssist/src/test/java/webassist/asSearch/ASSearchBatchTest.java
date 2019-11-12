package webassist.asSearch;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchBatch;
import webassist.data.GetTestData;
import webassist.util.GetEnvironmentData;

public class ASSearchBatchTest extends BaseTest {

	private ASSearchBatch asSearchBatch;
	
	@Test(description="AS SEARCH Batch to verify Clear Button is Working")
	@Parameters({"searchType","tcID","adminID","regID","last","first","batchID"}) 
	private void ASSearchTestMethodBatchClear(String searchType,String adminID,String tcID,String regID,String last,String first,String batchID) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		Assert.assertTrue(asSearchBatch.clearDetails(searchType,adminID,tcID,regID,last,first,batchID), 
				"Unable to Clear TC Details");
	}
	
	@Test(description="AS SEARCH Batch with valid Admin Details only",
			dependsOnMethods="ASSearchTestMethodBatchClear")
	@Parameters({"searchType","adminID"}) 
	private void ASSearchTestMethodBatchAdmin(String searchType,String adminID) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		asSearchBatch.ASSearchBatch(searchType,adminID,"","","","","");
		Assert.assertTrue(asSearchBatch.verifyError(),
				"Admin Validation not Working");
	}
	
	@Test(description="AS SEARCH Batch with Invalid Admin Details",
			dependsOnMethods="ASSearchTestMethodBatchAdmin")
	@Parameters({"searchType","tcID"}) 
	private void ASSearchTestMethodBatchInvalidAdmin(String searchType,String tcID) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		asSearchBatch.ASSearchBatch(searchType,"1234",tcID,"","","","");
		Assert.assertTrue(asSearchBatch.validateWrongID(),
				"Admin Validation not Working");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin and Invalid TC Details",
			dependsOnMethods="ASSearchTestMethodBatchInvalidAdmin")
	@Parameters({"searchType","adminID"}) 
	private void ASSearchTestMethodBatchAdminInvalidTC(String searchType,String adminID) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		asSearchBatch.ASSearchBatch(searchType,adminID,"1234","","","","");
		Assert.assertTrue(asSearchBatch.validateWrongID(),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin and TC Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminInvalidTC")
	@Parameters({"searchType","adminID","tcID"}) 
	private void ASSearchTestMethodBatchAdminTC(String searchType,String adminID,String tcID) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		asSearchBatch.ASSearchBatch(searchType,adminID,tcID,"","","","");
		Assert.assertTrue(asSearchBatch.verifysearchResultsPage(),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin,TC,Reg Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminTC")
	@Parameters({"searchType","adminID","tcID","regID"}) 
	private void ASSearchTestMethodBatchAdminTCReg(String searchType,String adminID,String tcID,String regID) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		Assert.assertTrue(asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,"","",""),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin,TC,Reg,Last Name Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminTCReg")
	@Parameters({"searchType","adminID","tcID","regID","last"}) 
	private void ASSearchTestMethodBatchAdminTCRegLName(String searchType,String adminID,String tcID,String regID,String last) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		Assert.assertTrue(asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,last,"",""),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with Valid Admin,TC,Reg,Last Name,First Name Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminTCRegLName")
	@Parameters({"searchType","adminID","tcID","regID","last","first"}) 
	private void ASSearchTestMethodBatchAdminTCRegLNameFName(String searchType,String adminID,String tcID,String regID,String last,String first) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		Assert.assertTrue(asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,last,first,""),
				"Unable to Search using Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with All Valid Details",
			dependsOnMethods="ASSearchTestMethodBatchAdminTCRegLNameFName")
	@Parameters({"searchType","adminID","tcID","regID","last","first","batchID"}) 
	private void ASSearchTestMethodBatch(String searchType,String adminID,String tcID,String regID,String last,String first,String batchID) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);		
		Assert.assertTrue(asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,last,first,batchID),
				"Unable to Search using All valid Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with All Valid Details and Validate Output Details",
			dataProvider = "ASSearchOutput",
			dataProviderClass = GetTestData.class,
			dependsOnMethods="ASSearchTestMethodBatch")
	private void ASSearchTestMethodBatchOutputValidation(String field,String value) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		Assert.assertTrue(asSearchBatch.verifyASDetailsPage(field,value),
				"Unable to get Output using All valid Details of Batch");
	}
	
	@Test(description="AS SEARCH Batch with All Valid Details and post Comment",
			dependsOnMethods="ASSearchTestMethodBatchOutputValidation")
	@Parameters({"searchType","adminID","tcID","regID","last","first","batchID"}) 
	private void ASSearchTestMethodBatchPageResults(String searchType,String adminID,String tcID,String regID,String last,String first,String batchID) throws InterruptedException {
		asSearchBatch = new ASSearchBatch(wd);
		asSearchBatch.ASSearchBatch(searchType,adminID,tcID,regID,last,first,batchID);
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm").format(Calendar.getInstance().getTime());
		String testComment = "Automation Test";
		Assert.assertTrue(asSearchBatch.pageResults(testComment+" "+timeStamp),
				"Unable to Post Comment");
		 
	}
}
	
	