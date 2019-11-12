package webassist.regSearch;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.regSearch.RegSearchCBStudent;
import webassist.applicationflow.regSearch.RegSearchReg;
import webassist.data.GetTestData;

public class RegSearchCBStudentTest extends BaseTest{

	private RegSearchCBStudent regSearchCBStudent;
	private String path = "testdata/Reg Search/RegSearchCBStudent.properties";
	private String ID = GetTestData.getOutputTestData(path,"ID");

	@Test(description="Reg SEARCH CB Student to verify Clear Button is Working")
	private void RegSearchTestMethodCBStudentClear() throws InterruptedException {
		regSearchCBStudent = new RegSearchCBStudent(wd);
		Assert.assertTrue(regSearchCBStudent.clearDetails(ID), 
				"Unable to Clear CB Student Details");
	}

	@Test(description="Reg SEARCH with Invalid CB Student ID",
			dependsOnMethods="RegSearchTestMethodCBStudentClear") 
	private void RegSearchTestMethodCBStudentInvalid() throws InterruptedException {
		regSearchCBStudent.RegSearchCBStudent("1234");
		Assert.assertTrue(regSearchCBStudent.validateWrongID(), 
				"CB Student Validation Not Working");
	}

	@Test(description="Reg SEARCH with Invalid CB Student ID Length",
			dependsOnMethods="RegSearchTestMethodCBStudentInvalid") 
	private void RegSearchTestMethodCBStudentInvalidLength() throws InterruptedException {
		regSearchCBStudent.RegSearchCBStudent("1234567891011");
		Assert.assertTrue(regSearchCBStudent.validateStudentIDLength("1234567891011"), 
				"CB Student Length Validation Not Working");
	}

	@Test(description="Reg SEARCH with Valid CB Student ID",
			dependsOnMethods="RegSearchTestMethodCBStudentInvalidLength")
	private void RegSearchTestMethodCBStudentCorrect() throws InterruptedException {
		Assert.assertTrue(regSearchCBStudent.RegSearchCBStudent(ID), 
				"Unable to Search using CB Student ID");
	}
}
