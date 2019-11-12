package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchAdmin;

public class RegSearchAdminTest extends BaseTest{
	
	private RegSearchAdmin regSearchAdmin;
	
	@Test(description="Reg SEARCH Admin to verify Clear Button is Working")
	@Parameters({"ID"}) 
	private void RegSearchTestMethodAdminClear(String ID) throws InterruptedException {
		regSearchAdmin = new RegSearchAdmin(wd);
		Assert.assertTrue(regSearchAdmin.clearDetails(ID), 
				"Unable to Clear Admin Details");
	}

	@Test(description="Reg SEARCH with Invalid Admin Details"
			,dependsOnMethods="RegSearchTestMethodAdminClear") 
	private void RegSearchTestMethodAdminInvalid() throws InterruptedException {
		regSearchAdmin = new RegSearchAdmin(wd);
		regSearchAdmin.RegSearchAdmin("1234");
		Assert.assertTrue(regSearchAdmin.validateWrongID(), 
				"Admin Validation Not Working");
	}
	
	@Test(description="Reg SEARCH with Valid Admin Details"
			,dependsOnMethods="RegSearchTestMethodAdminInvalid")
	@Parameters({"ID"}) 
	private void RegSearchTestMethodAdminCorrect(String ID) throws InterruptedException {
		regSearchAdmin = new RegSearchAdmin(wd);
		Assert.assertTrue(regSearchAdmin.RegSearchAdmin(ID), 
				"Unable to Search using Admin ");
	}

}
