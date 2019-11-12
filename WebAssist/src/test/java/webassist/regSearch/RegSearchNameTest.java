package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchTC;
import webassist.applicationflow.regSearch.RegSearchName;

public class RegSearchNameTest extends BaseTest{

	private RegSearchName regSearchName;

	@Test(description="Reg SEARCH with LastName input only")
	@Parameters({"last"}) 
	private void RegSearchTestMethodLastName(String last) throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		regSearchName.RegSearchName(last,"");
		Assert.assertTrue(regSearchName.verifyFirstNameLength(""), 
				"FirstName Validation Not Working");
	}

	@Test(description="Reg SEARCH Name to verify Clear Button is Working"
			,dependsOnMethods="RegSearchTestMethodLastName")
	@Parameters({"last","first"}) 
	private void RegSearchTestMethodNameClear(String last,String first) throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		Assert.assertTrue(regSearchName.clearDetails(last,first), 
				"Unable to Clear Name Details"); 
	}
	//change	
	@Test(description="Reg SEARCH with FirstName input only"
			,dependsOnMethods="RegSearchTestMethodNameClear")
	@Parameters({"first"}) 
	private void RegSearchTestMethodFirstName(String first) throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		regSearchName.RegSearchName("",first);
		Assert.assertTrue(regSearchName.verifyLastNameMinLength(""), 
				"LastName Validation Not Working");
	}

	//change
	@Test(description="Reg SEARCH with Invalid LastName"
			,dependsOnMethods="RegSearchTestMethodFirstName")
	@Parameters({"first"}) 
	private void RegSearchTestMethodInvalidLastName(String first) throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		regSearchName.RegSearchName("abcd",first);
		Assert.assertTrue(regSearchName.validateWrongID(), 
				"LastName Validation Not Working");
	}

	@Test(description="Reg SEARCH with Invalid FirstName"
			,dependsOnMethods="RegSearchTestMethodInvalidLastName")
	@Parameters({"last"}) 
	private void RegSearchTestMethodInvalidFirstName(String last) throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		regSearchName.RegSearchName(last,"abcd");
		Assert.assertTrue(regSearchName.validateWrongID(), 
				"FirstName Validation Not Working");
	}

	@Test(description="Reg SEARCH with LastName having Invalid Length"
			,dependsOnMethods="RegSearchTestMethodInvalidFirstName")
	@Parameters({"first"}) 
	private void RegSearchTestMethodLongLastName(String first) throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		regSearchName.RegSearchName("12345678912345678",first);
		Assert.assertTrue(regSearchName.verifyLastNameMaxLength("12345678912345678"), 
				"LastName Length Validation Not Working");
	}

	@Test(description="Reg SEARCH with FirstName having Invalid Length"
			,dependsOnMethods="RegSearchTestMethodLongLastName")
	@Parameters({"last"}) 
	private void RegSearchTestMethodLongFirstName(String last) throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		regSearchName.RegSearchName(last,"12345678912345678");
		Assert.assertTrue(regSearchName.verifyFirstNameLength("12345678912345678"), 
				"FirstName Length Validation Not Working");
	}

	@Test(description="Reg SEARCH Name with all Valid inputs"
			,dependsOnMethods="RegSearchTestMethodLongFirstName")
	@Parameters({"last","first"}) 
	private void RegSearchTestMethodNameCorrect(String last,String first) throws InterruptedException {
		regSearchName = new RegSearchName(wd);
		Assert.assertTrue(regSearchName.RegSearchName(last,first), 
				"Unable to Search using Name ");
	}

}
