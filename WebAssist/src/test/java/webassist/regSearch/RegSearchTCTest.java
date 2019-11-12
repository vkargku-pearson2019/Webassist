package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchTC;

public class RegSearchTCTest extends BaseTest{
	
	private RegSearchTC regSearchTC;
	
	@Test(description="Reg SEARCH TC to verify Clear Button is Working")
	@Parameters({"searchType","ID"}) 
	private void RegSearchTestMethodTCClear(String searchType,String ID) throws InterruptedException {
		regSearchTC = new RegSearchTC(wd);
		Assert.assertTrue(regSearchTC.clearDetails(searchType,ID), 
				"Unable to Clear Name Details");
	}
	
	@Test(description="Reg SEARCH with Valid TC ID only",
			dependsOnMethods="RegSearchTestMethodTCClear")
	@Parameters({"ID"}) 
	private void RegSearchTestMethodTCID(String ID) throws InterruptedException {
		regSearchTC = new RegSearchTC(wd);
		regSearchTC.RegSearchTCID(ID);
		Assert.assertTrue(regSearchTC.validateTCLookupButton(), 
				"TC Dropdown Validation Not Working");
	}
	
	@Test(description="Reg SEARCH TC with Valid Test Centre Type only",
			dependsOnMethods="RegSearchTestMethodTCID")
	@Parameters({"searchType"}) 
	private void RegSearchTestMethodTCDropdown(String searchType) throws InterruptedException {
		regSearchTC = new RegSearchTC(wd);
		regSearchTC.clickClearButton();
		regSearchTC.RegSearchTC(searchType,"");
		Assert.assertTrue(regSearchTC.validateTCLookupButton(), 
				"TC ID Validation Not Working");
	}
	
	@Test(description="Reg SEARCH TC with all Valid details",
			dependsOnMethods="RegSearchTestMethodTCDropdown")
	@Parameters({"searchType","ID"}) 
	private void RegSearchTestMethodTCCorrect(String searchType,String ID) throws InterruptedException {
		regSearchTC = new RegSearchTC(wd);
		Assert.assertTrue(regSearchTC.RegSearchTC(searchType, ID), 
				"Unable to Search using TC");
	}

}
