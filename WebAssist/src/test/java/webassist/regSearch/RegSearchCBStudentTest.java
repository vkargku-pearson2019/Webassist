package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchCBStudent;
import webassist.applicationflow.regSearch.RegSearchReg;

public class RegSearchCBStudentTest extends BaseTest{

	private RegSearchCBStudent regSearchCBStudent;

	@Test(description="Reg SEARCH CB Student to verify Clear Button is Working")
	@Parameters({"ID"}) 
	private void RegSearchTestMethodCBStudentClear(String ID) throws InterruptedException {
		regSearchCBStudent = new RegSearchCBStudent(wd);
		Assert.assertTrue(regSearchCBStudent.clearDetails(ID), 
				"Unable to Clear CB Student Details");
	}

	@Test(description="Reg SEARCH with Invalid CB Student ID "
			,dependsOnMethods="RegSearchTestMethodCBStudentClear") 
	private void RegSearchTestMethodCBStudentInvalid() throws InterruptedException {
		regSearchCBStudent = new RegSearchCBStudent(wd);
		regSearchCBStudent.RegSearchCBStudent("1234");
		Assert.assertTrue(regSearchCBStudent.validateWrongID(), 
				"CB Student Validation Not Working");
	}

	@Test(description="Reg SEARCH with Invalid CB Student ID Length "
			,dependsOnMethods="RegSearchTestMethodCBStudentInvalid") 
	private void RegSearchTestMethodCBStudentInvalidLength() throws InterruptedException {
		regSearchCBStudent = new RegSearchCBStudent(wd);
		regSearchCBStudent.RegSearchCBStudent("1234567891011");
		Assert.assertTrue(regSearchCBStudent.validateStudentIDLength("1234567891011"), 
				"CB Student Length Validation Not Working");
	}

	@Test(description="Reg SEARCH with Valid CB Student ID"
			,dependsOnMethods="RegSearchTestMethodCBStudentInvalidLength")
	@Parameters({"ID"}) 
	private void RegSearchTestMethodCBStudentCorrect(String ID) throws InterruptedException {
		regSearchCBStudent = new RegSearchCBStudent(wd);
		Assert.assertTrue(regSearchCBStudent.RegSearchCBStudent(ID), 
				"Unable to Search using CB Student ID");
	}


}
