package webassist.srf;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.srf.SRF;
import webassist.data.GetTestData;
import webassist.util.Util;

public class SRFTest extends BaseTest{

	private SRF srf;
	private Util util= new Util(wd);
	private final String timeStamp = util.getTime();
	private final String testComment = "Automation Test";
	private final String updatedComment = "Updated";
	private String path = "testdata/SRF/SRFTest.properties";
	private String adminID = GetTestData.getOutputTestData(path,"admin");
	private String tcID = GetTestData.getOutputTestData(path,"testCenterCode");
	private String uin = GetTestData.getOutputTestData(path,"uin");
	
	/**
	 * SRF Search to verify the functionality of Clear Button
	 */

	@Test(description="SRF Search to verify Clear Button is Working")
	private void SRFSearchTestClearDetails() throws InterruptedException{ 
		srf = new SRF(wd);
		Assert.assertTrue(srf.clearDetails(adminID,uin,tcID),
				"Unable to Clear Details");
	}
	
	/**
	 * SRF Search keeping all the input fields blank
	 */

	@Test(description="SRF Search without any details",
			dependsOnMethods="SRFSearchTestClearDetails") 
	private void SRFSearchTestNoDetails() throws InterruptedException {
		srf.SRFsearch("","","");
		Assert.assertTrue(srf.verifyError(), 
				"Not Working");
	}
	
	/**
	 * SRF Search with invalid Admin ID
	 */

	@Test(description="SRF Search with Invalid Admin details",
			dependsOnMethods="SRFSearchTestNoDetails")
	private void SRFSearchTestInvalidAdminDate() throws InterruptedException {
		srf.SRFsearch("123456",uin,tcID);
		Assert.assertTrue(srf.validateWrongID(), 
				"Not working");
	}
	
	/**
	 * SRF Search with invalid UIN
	 */

	@Test(description="SRF Search with Invalid UIN Details",
			dependsOnMethods="SRFSearchTestInvalidAdminDate")
	private void SRFSearchTestInvalidUIN() throws InterruptedException {
		srf.SRFsearch(adminID,"1234567890",tcID);
		Assert.assertTrue(srf.validateWrongID(), 
				"Not working");
	}
	
	/**
	 * SRF Search with invalid Test Centre Code
	 */

	@Test(description="SRF Search with Invalid Test Centre Details",
			dependsOnMethods="SRFSearchTestInvalidUIN")
	private void SRFSearchTestInvalidTC() throws InterruptedException {
		srf.SRFsearch(adminID,uin,"1234");
		Assert.assertTrue(srf.validateWrongID(), 
				"Not working");
	}
	
	/**
	 * SRF Search with all Valid Input Details 
	 */

	@Test(description="SRF Search with all Valid Details",
			dependsOnMethods="SRFSearchTestInvalidTC")
	private void SRFSearchTestCorrect() throws InterruptedException {
		Assert.assertTrue(srf.SRFsearch(adminID,uin,tcID), 
				"Unable to Search using valid Details");
	}
	
	/**
	 * Output Page Data Validation
	 */
	
	@Test(description="SRF Search to validate Output",
			dataProvider="SRFOutputFields",
			dependsOnMethods="SRFSearchTestCorrect")
	private void SRFSearchTestOutputValidation(String field,String value) throws InterruptedException {
		String ansSheetField = GetTestData.getOutputTestData(path,field);
		String ansSheetValue = GetTestData.getOutputTestData(path,value);
		Assert.assertTrue(srf.verifyViewResult(ansSheetField, ansSheetValue), 
				"Unable to get Output using All valid Details of SRF");
	}
	
	/**
	 * Post Comment 
	 */

	@Test(description="SRF Search to post Comment",
			dependsOnMethods="SRFSearchTestOutputValidation")
	private void SRFSearchTestPostComment() throws InterruptedException {
		Assert.assertTrue(srf.postCommentSRF(testComment+" "+timeStamp), 
				"Unable to Post Comment");
	}
	
	/**
	 * Update Comment 
	 */

	@Test(description="SRF Search to update Comment",
			dependsOnMethods="SRFSearchTestPostComment")
	private void SRFSearchTestUpdateComment() throws InterruptedException {
		Assert.assertTrue(srf.updateCommentSRF(testComment+" "+timeStamp,updatedComment+" "+testComment+" "+timeStamp), 
				"Unable to update Comment");
	}
	
	/**
	 * Delete Comment 
	 */
	
	@Test(description="SRF Search to delete Comment",
			dependsOnMethods="SRFSearchTestUpdateComment") 
	private void SRFSearchTestDeleteComment() throws InterruptedException {
		Assert.assertTrue(srf.deleteCommentSRF(testComment+" "+timeStamp,updatedComment+" "+testComment+" "+timeStamp), 
				"Unable to delete Comment");
	}
	
	@DataProvider(name = "SRFOutputFields") 
	public static Object[][] outputFields() {
		return new Object[][] { {"uinField","uin"},{"pasField","pas"},
			{"testCenterField","testCenterCode"},
			{"adminField","admin"},{"regField","reg"},
			{"lastNameField","lastName"},
			{"firstNameField","firstName"},
			{"dobField","dob"} };
	}
}
