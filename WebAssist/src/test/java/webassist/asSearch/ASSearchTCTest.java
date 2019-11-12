package webassist.asSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchTC;

public class ASSearchTCTest extends BaseTest{
	
	private ASSearchTC asSearchTC;
	
	@Test(description="AS SEARCH TC to verify Clear Button is Working")
	@Parameters({"searchType","tcID","adminID"}) 
	private void ASSearchTestMethodTCClear(String searchType,String tcID,String adminID) throws InterruptedException {
		asSearchTC = new ASSearchTC(wd);
		Assert.assertTrue(asSearchTC.clearDetails(searchType,tcID,adminID), 
				"Unable to Clear TC Details");
	}

	
	@Test(description="AS SEARCH with Invalid TC"
			,dependsOnMethods="ASSearchTestMethodTCClear")
	@Parameters({"searchType","adminID"}) 
	private void ASSearchTestMethodTCInvalid(String searchType,String adminID) throws InterruptedException {
		asSearchTC = new ASSearchTC(wd);
		asSearchTC.ASSearchTC(searchType,"1234",adminID);
		Assert.assertTrue(asSearchTC.validateWrongID(), 
				"TC Validation Not Working");
	}
	
	@Test(description="AS SEARCH with Invalid Admin ID"
			,dependsOnMethods="ASSearchTestMethodTCInvalid")
	@Parameters({"searchType","tcID"}) 
	private void ASSearchTestMethodAdminInvalid(String searchType,String tcID) throws InterruptedException {
		asSearchTC = new ASSearchTC(wd);
		asSearchTC.ASSearchTC(searchType,tcID,"1234");
		Assert.assertTrue(asSearchTC.validateWrongID(), 
				"Admin Validation Not Working");
	}
	
	@Test(description="AS SEARCH TC with Valid Details"
			,dependsOnMethods="ASSearchTestMethodAdminInvalid")
	@Parameters({"searchType","tcID","adminID"}) 
	private void ASSearchTestMethodTCCorrect(String searchType,String tcID,String adminID) throws InterruptedException {
		asSearchTC = new ASSearchTC(wd);
		Assert.assertTrue(asSearchTC.ASSearchTC(searchType,tcID,adminID), 
				"Unable to Search using TC ");
	}

}
