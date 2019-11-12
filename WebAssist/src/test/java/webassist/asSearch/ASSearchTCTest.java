package webassist.asSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchTC;
import webassist.data.GetTestData;

public class ASSearchTCTest extends BaseTest{
	
	private ASSearchTC asSearchTC;
	private String path = "testdata/ASSearch/ASSearchTCTest.properties";
	private String searchType = GetTestData.getOutputTestData(path,"searchType");
	private String adminID = GetTestData.getOutputTestData(path,"admin");
	private String tcID = GetTestData.getOutputTestData(path,"testCenterCode");
	
	@Test(description="AS SEARCH TC to verify Clear Button is Working")
	private void ASSearchTestMethodTCClear() throws InterruptedException {
		asSearchTC = new ASSearchTC(wd);
		Assert.assertTrue(asSearchTC.clearDetails(searchType,tcID,adminID), 
				"Unable to Clear TC Details");
	}
	
	@Test(description="AS SEARCH with Invalid TC Code and valid Admin ID"
			,dependsOnMethods="ASSearchTestMethodTCClear")
	private void ASSearchTestMethodTCInvalid() throws InterruptedException {
		asSearchTC.ASSearchTC(searchType,"1234",adminID);
		Assert.assertTrue(asSearchTC.validateWrongID(), 
				"TC Validation Not Working");
	}
	
	@Test(description="AS SEARCH with Valid TC Code and Invalid Admin ID"
			,dependsOnMethods="ASSearchTestMethodTCInvalid")
	private void ASSearchTestMethodAdminInvalid() throws InterruptedException {
		asSearchTC.ASSearchTC(searchType,tcID,"1234");
		Assert.assertTrue(asSearchTC.validateWrongID(), 
				"Admin Validation Not Working");
	}
	
	@Test(description="AS SEARCH TC with Valid Details"
			,dependsOnMethods="ASSearchTestMethodAdminInvalid")
	private void ASSearchTestMethodTCCorrect() throws InterruptedException {
		Assert.assertTrue(asSearchTC.ASSearchTC(searchType,tcID,adminID), 
				"Unable to Search using TC ");
	}

}
