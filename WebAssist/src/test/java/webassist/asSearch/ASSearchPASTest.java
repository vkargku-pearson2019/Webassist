package webassist.asSearch;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchPAS;

public class ASSearchPASTest extends BaseTest {
	
private ASSearchPAS asSearchPAS;
	
	@Test(description="AS SEARCH PAS to verify Clear Button is Working")
	@Parameters({"searchType","ID"}) 
	private void ASSearchTestMethodPASClear(String searchType,String ID) throws InterruptedException {
		asSearchPAS = new ASSearchPAS(wd);
		Assert.assertTrue(asSearchPAS.clearDetails(searchType,ID), 
				"Unable to Clear PAS Details");
	}

	
	@Test(description="AS SEARCH with Invalid PAS"
			,dependsOnMethods="ASSearchTestMethodPASClear")
	@Parameters({"searchType"}) 
	private void ASSearchTestMethodPASInvalid(String searchType) throws InterruptedException {
		asSearchPAS = new ASSearchPAS(wd);
		asSearchPAS.ASSearchPAS(searchType,"1234");
		Assert.assertTrue(asSearchPAS.validateWrongID(), 
				"PAS Validation Not Working");
	}
	
	@Test(description="AS SEARCH with Valid PAS"
			,dependsOnMethods="ASSearchTestMethodPASInvalid")
	@Parameters({"searchType","ID","ansSheetField","ansSheetFieldValue"}) 
	private void ASSearchTestMethodPASCorrect(String searchType,String ID,String ansSheetField,String ansSheetFieldValue) throws InterruptedException {
		asSearchPAS = new ASSearchPAS(wd);
		asSearchPAS.ASSearchPAS(searchType,ID);
		Assert.assertTrue(asSearchPAS.verifyASDetailsPage(ansSheetField,ansSheetFieldValue), 
				"Unable to Search using PAS ");
	}
	
	@Test(description="AS SEARCH with Valid PAS and post Comment"
			,dependsOnMethods="ASSearchTestMethodPASCorrect")
	@Parameters({"searchType","ID"}) 
	private void ASSearchTestMethodPASPageResults(String searchType,String ID) throws InterruptedException {
		asSearchPAS = new ASSearchPAS(wd);
		asSearchPAS.ASSearchPAS(searchType,ID);
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm").format(Calendar.getInstance().getTime());
		String testComment = "Automation Test";
		Assert.assertTrue(asSearchPAS.pageResults(testComment+" "+timeStamp), 
				"Unable to Post Comment");
	}

}
