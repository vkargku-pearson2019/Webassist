package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchAdmin;
import webassist.data.GetTestData;

public class RegSearchAdminTest extends BaseTest{
	
	private RegSearchAdmin regSearchAdmin;
	private String path = "testdata/Reg Search/RegSearchAdmin.properties";
	private String ID = GetTestData.getOutputTestData(path,"ID");
	
	@Test(description="Reg SEARCH Admin to verify Clear Button is Working")
	private void RegSearchTestMethodAdminClear() throws InterruptedException {
		regSearchAdmin = new RegSearchAdmin(wd);
		Assert.assertTrue(regSearchAdmin.clearDetails(ID), 
				"Unable to Clear Admin Details");
	}

	@Test(description="Reg SEARCH with Invalid Admin Details",
			dependsOnMethods="RegSearchTestMethodAdminClear") 
	private void RegSearchTestMethodAdminInvalid() throws InterruptedException {
		regSearchAdmin.RegSearchAdmin("1234");
		Assert.assertTrue(regSearchAdmin.validateWrongID(), 
				"Admin Validation Not Working");
	}
	
	@Test(description="Reg SEARCH with Valid Admin Details",
			dependsOnMethods="RegSearchTestMethodAdminInvalid")
	private void RegSearchTestMethodAdminCorrect() throws InterruptedException {
		Assert.assertTrue(regSearchAdmin.RegSearchAdmin(ID), 
				"Unable to Search using Admin ");
	}

	@DataProvider(name = "RegSearchOutputFields") 
	public static Object[][] outputFields() {
		return new Object[][] { {"adminField","admindate"},{"testCenterField","testCenterCode"},
			           {"regField","reg"},{"lastNameField","lastName"}, {"firstNameField",
				"firstName"},{"dobField","dob"} };
	}
}
