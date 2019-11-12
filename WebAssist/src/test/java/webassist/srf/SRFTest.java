package webassist.srf;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.srf.SRF;

public class SRFTest extends BaseTest{

	private SRF srf;
	private final String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm").format(Calendar.getInstance().getTime());
	private final String testComment = "Automation Test";
	private final String updatedComment = "Updated";

	@Test(description="SRF Search to verify Clear Button is Working")
	@Parameters({"adminDate","uinNumber","centreCode"}) 
	private void SRFSearchTestClearDetails(String adminDate,String uinNumber,String
			centreCode) throws InterruptedException{ 
		srf = new SRF(wd);
		Assert.assertTrue(srf.clearDetails(adminDate,uinNumber,centreCode),
				"Unable to Clear Details");
	}

	@Test(description="SRF Search without any details",
			dependsOnMethods="SRFSearchTestClearDetails") 
	private void SRFSearchTestNoDetails() throws InterruptedException {
		srf = new SRF(wd);
		srf.SRFsearch("","","");
		Assert.assertTrue(srf.verifyError(), 
				"Not Working");
	}

	@Test(description="SRF Search without UIN Details",
			dependsOnMethods="SRFSearchTestNoDetails")
	@Parameters({"adminDate","centreCode"}) 
	private void SRFSearchTestWithoutUIN(String adminDate,String centreCode) throws InterruptedException {
		srf = new SRF(wd);
		srf.SRFsearch(adminDate,"",centreCode);
		Assert.assertTrue(srf.verifyError(), 
				"UIN Details Validation not Working");
	}

	@Test(description="SRF Search with Invalid Admin details",
			dependsOnMethods="SRFSearchTestWithoutUIN")
	@Parameters({"uinNumber","centreCode"}) 
	private void SRFSearchTestInvalidAdminDate(String uinNumber,String centreCode) throws InterruptedException {
		srf = new SRF(wd);
		srf.SRFsearch("123456",uinNumber,centreCode);
		Assert.assertTrue(srf.validateWrongID(), 
				"Not working");
	}

	@Test(description="SRF Search with Invalid UIN Details",
			dependsOnMethods="SRFSearchTestInvalidAdminDate")
	@Parameters({"adminDate","centreCode"}) 
	private void SRFSearchTestInvalidUIN(String adminDate,String centreCode) throws InterruptedException {
		srf = new SRF(wd);
		srf.SRFsearch(adminDate,"1234567890",centreCode);
		Assert.assertTrue(srf.validateWrongID(), 
				"Not working");
	}

	@Test(description="SRF Search with Invalid Test Centre Details",
			dependsOnMethods="SRFSearchTestInvalidUIN")
	@Parameters({"adminDate","uinNumber"}) 
	private void SRFSearchTestInvalidTC(String adminDate,String uinNumber) throws InterruptedException {
		srf = new SRF(wd);
		srf.SRFsearch(adminDate,uinNumber,"1234");
		Assert.assertTrue(srf.validateWrongID(), 
				"Not working");
	}

	@Test(description="SRF Search with all Valid Details",
			dependsOnMethods="SRFSearchTestInvalidTC")
	@Parameters({"adminDate","uinNumber","centreCode","ansSheetField","ansSheetFieldValue"}) 
	private void SRFSearchTestCorrect(String adminDate,String uinNumber,String centreCode,String ansSheetField,String ansSheetFieldValue) throws InterruptedException {
		srf = new SRF(wd);
		srf.SRFsearch(adminDate,uinNumber,centreCode);
		Assert.assertTrue(srf.verifyViewResult(ansSheetField, ansSheetFieldValue), 
				"Unable to Search using valid Details");
	}

	@Test(description="SRF Search to post Comment",
			dependsOnMethods="SRFSearchTestCorrect")
	@Parameters({"adminDate","uinNumber","centreCode"}) 
	private void SRFSearchTestPostComment(String adminDate,String uinNumber,String centreCode) throws InterruptedException {
		srf = new SRF(wd);
		Assert.assertTrue(srf.postCommentSRF(testComment+" "+timeStamp), 
				"Unable to Post Comment");
	}

	@Test(description="SRF Search to update Comment",
			dependsOnMethods="SRFSearchTestPostComment")
	@Parameters({"adminDate","uinNumber","centreCode"}) 
	private void SRFSearchTestUpdateComment(String adminDate,String uinNumber,String centreCode) throws InterruptedException {
		srf = new SRF(wd);
		Assert.assertTrue(srf.updateCommentSRF(testComment+" "+timeStamp,updatedComment+" "+testComment+" "+timeStamp), 
				"Unable to update Comment");
	}
	
	@Test(description="SRF Search to delete Comment",
			dependsOnMethods="SRFSearchTestUpdateComment")
	@Parameters({"adminDate","uinNumber","centreCode"}) 
	private void SRFSearchTestDeleteComment(String adminDate,String uinNumber,String centreCode) throws InterruptedException {
		srf = new SRF(wd);
		Assert.assertTrue(srf.deleteCommentSRF(testComment+" "+timeStamp,updatedComment+" "+testComment+" "+timeStamp), 
				"Unable to delete Comment");
	}





}
