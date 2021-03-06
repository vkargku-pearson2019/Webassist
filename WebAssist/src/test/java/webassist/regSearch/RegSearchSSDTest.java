package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchSSD;
import webassist.data.GetTestData;

public class RegSearchSSDTest extends BaseTest{
	
private RegSearchSSD regSearchSSD;
private String path = "testdata/Reg Search/RegSearchSSD.properties";
private String ID = GetTestData.getOutputTestData(path,"ID");
	
	@Test(description="Reg SEARCH SSD to verify Clear Button is Working")
	private void RegSearchTestMethodSSDClear() throws InterruptedException {
		regSearchSSD = new RegSearchSSD(wd);
		Assert.assertTrue(regSearchSSD.clearDetails(ID), 
				"Unable to Clear SSD Details");
	}
	
	@Test(description="Reg SEARCH with Invalid SSD Details",
			dependsOnMethods="RegSearchTestMethodSSDClear")
	private void RegSearchTestMethodSSDInvalid() throws InterruptedException {
		regSearchSSD.RegSearchSSD("1234");
		Assert.assertTrue(regSearchSSD.validateWrongID(), 
				"Invalid SSD Validation Not Working");
	}
	
	@Test(description="Reg SEARCH with Valid SSD Details",
			dependsOnMethods="RegSearchTestMethodSSDInvalid")
	private void RegSearchTestMethodSSDCorrect() throws InterruptedException {
		Assert.assertTrue(regSearchSSD.RegSearchSSD(ID), 
				"SSD Search Not Working");
	}
}
