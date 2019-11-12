package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchTC;
import webassist.data.GetTestData;

public class RegSearchTCTest extends BaseTest{
	
	private RegSearchTC regSearchTC;
	private String path = "testdata/Reg Search/RegSearchSSD.properties";
	private String ID = GetTestData.getOutputTestData(path,"ID");
	private String searchType = GetTestData.getOutputTestData(path,"searchType");
	
	@Test(description="Reg SEARCH TC to verify Clear Button is Working")
	private void RegSearchTestMethodTCClear() throws InterruptedException {
		regSearchTC = new RegSearchTC(wd);
		Assert.assertTrue(regSearchTC.clearDetails(searchType,ID), 
				"Unable to Clear TC Details");
	}
	
	@Test(description="Reg SEARCH with Valid TC ID only",
			dependsOnMethods="RegSearchTestMethodTCClear")
	private void RegSearchTestMethodTCID() throws InterruptedException {
		regSearchTC.RegSearchTCID(ID);
		Assert.assertTrue(regSearchTC.validateTCLookupButton(), 
				"TC Dropdown Validation Not Working");
	}
	
	@Test(description="Reg SEARCH TC with Valid Test Centre Type only",
			dependsOnMethods="RegSearchTestMethodTCID")
	private void RegSearchTestMethodTCDropdown() throws InterruptedException {
		regSearchTC.clickClearButton();
		regSearchTC.RegSearchTC(searchType,"");
		Assert.assertTrue(regSearchTC.validateTCLookupButton(), 
				"TC ID Validation Not Working");
	}
	
	@Test(description="Reg SEARCH TC with all Valid details",
			dependsOnMethods="RegSearchTestMethodTCDropdown")
	private void RegSearchTestMethodTCCorrect() throws InterruptedException {
		Assert.assertTrue(regSearchTC.RegSearchTC(searchType,ID), 
				"Unable to Search using TC");
	}

}
