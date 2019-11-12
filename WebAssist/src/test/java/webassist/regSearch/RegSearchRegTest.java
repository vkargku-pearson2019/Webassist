package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchReg;

public class RegSearchRegTest extends BaseTest{
	
private RegSearchReg regSearchReg;
	
	@Test(description="Reg SEARCH REG to verify Clear Button is Working")
	@Parameters({"ID"}) 
	private void RegSearchTestMethodRegClear(String ID) throws InterruptedException {
		regSearchReg = new RegSearchReg(wd);
		Assert.assertTrue(regSearchReg.clearDetails(ID), 
				"Unable to Clear REG Details");
	}

	@Test(description="Reg SEARCH with Invalid REG Details"
			,dependsOnMethods="RegSearchTestMethodRegClear") 
	private void RegSearchTestMethodRegInvalid() throws InterruptedException {
		regSearchReg = new RegSearchReg(wd);
		regSearchReg.RegSearchReg("1234");
		Assert.assertTrue(regSearchReg.validateWrongID(), 
				"REG Validation Not Working");
	}
	
	@Test(description="Reg SEARCH with REG Details having Invalid Length "
			,dependsOnMethods="RegSearchTestMethodRegInvalid") 
	private void RegSearchTestMethodRegInvalidLength() throws InterruptedException {
		regSearchReg = new RegSearchReg(wd);
		regSearchReg.RegSearchReg("1234567891011");
		Assert.assertTrue(regSearchReg.validateRegLength("1234567891011"), 
				"REG Length Validation Not Working");
	}
	
	@Test(description="Reg SEARCH REG with all Valid Details"
			,dependsOnMethods="RegSearchTestMethodRegInvalidLength")
	@Parameters({"ID"}) 
	private void RegSearchTestMethodRegCorrect(String ID) throws InterruptedException {
		regSearchReg = new RegSearchReg(wd);
		Assert.assertTrue(regSearchReg.RegSearchReg(ID), 
				"Unable to Search using REG ");
	}

}
