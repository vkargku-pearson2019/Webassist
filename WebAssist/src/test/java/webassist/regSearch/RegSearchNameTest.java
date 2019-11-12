package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchTC;
import webassist.applicationflow.regSearch.RegSearchName;
import webassist.data.GetTestData;

public class RegSearchNameTest extends BaseTest{

	private RegSearchName regSearchName;
	private String path = "testdata/Reg Search/RegSearchName.properties";
	private String last = GetTestData.getOutputTestData(path,"lastName");
	private String first = GetTestData.getOutputTestData(path,"firstName");

	@Test(description="Reg SEARCH with LastName input only")
	private void RegSearchTestMethodLastName() throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		regSearchName.RegSearchName(last,"");
		Assert.assertTrue(regSearchName.verifyFirstNameLength(""), 
				"FirstName Validation Not Working");
	}

	@Test(description="Reg SEARCH Name to verify Clear Button is Working",
			dependsOnMethods="RegSearchTestMethodLastName")
	private void RegSearchTestMethodNameClear() throws InterruptedException {
		Assert.assertTrue(regSearchName.clearDetails(last,first), 
				"Unable to Clear Name Details"); 
	}
	//change	
	@Test(description="Reg SEARCH with FirstName input only",
			dependsOnMethods="RegSearchTestMethodNameClear")
	private void RegSearchTestMethodFirstName() throws InterruptedException {
		regSearchName.RegSearchName("",first);
		Assert.assertTrue(regSearchName.verifyLastNameMinLength(""), 
				"LastName Validation Not Working");
	}

	//change
	@Test(description="Reg SEARCH with Invalid LastName",
			dependsOnMethods="RegSearchTestMethodFirstName")
	private void RegSearchTestMethodInvalidLastName() throws InterruptedException {
		regSearchName.RegSearchName("abcd",first);
		Assert.assertTrue(regSearchName.validateWrongID(), 
				"LastName Validation Not Working");
	}

	@Test(description="Reg SEARCH with Invalid FirstName",
			dependsOnMethods="RegSearchTestMethodInvalidLastName")
	private void RegSearchTestMethodInvalidFirstName() throws InterruptedException {
		regSearchName.RegSearchName(last,"abcd");
		Assert.assertTrue(regSearchName.validateWrongID(), 
				"FirstName Validation Not Working");
	}

	@Test(description="Reg SEARCH with LastName having Invalid Length",
			dependsOnMethods="RegSearchTestMethodInvalidFirstName")
	private void RegSearchTestMethodLongLastName() throws InterruptedException {
		regSearchName.RegSearchName("12345678912345678",first);
		Assert.assertTrue(regSearchName.verifyLastNameMaxLength("12345678912345678"), 
				"LastName Length Validation Not Working");
	}

	@Test(description="Reg SEARCH with FirstName having Invalid Length",
			dependsOnMethods="RegSearchTestMethodLongLastName")
	private void RegSearchTestMethodLongFirstName() throws InterruptedException {
		regSearchName.RegSearchName(last,"12345678912345678");
		Assert.assertTrue(regSearchName.verifyFirstNameLength("12345678912345678"), 
				"FirstName Length Validation Not Working");
	}

	@Test(description="Reg SEARCH Name with all Valid inputs",
			dependsOnMethods="RegSearchTestMethodLongFirstName")
	private void RegSearchTestMethodNameCorrect() throws InterruptedException {
		Assert.assertTrue(regSearchName.RegSearchName(last,first), 
				"Unable to Search using Name ");
	}
}
