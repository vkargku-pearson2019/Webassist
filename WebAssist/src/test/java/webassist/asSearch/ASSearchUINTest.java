package webassist.asSearch;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.asSearch.ASSearchBatch;
import webassist.applicationflow.asSearch.ASSearchUIN;
import webassist.data.GetTestData;

public class ASSearchUINTest extends BaseTest {
	
	private ASSearchUIN asSearchUIN;
	
	@Test(description="AS SEARCH UIN to verify Clear Button is Working",
			dataProvider = "ASSearchInputUIN",
			dataProviderClass = GetTestData.class)
	private void ASSearchTestMethodUINClear(String searchType,String ID) throws InterruptedException {
		asSearchUIN = new ASSearchUIN(wd);
		Assert.assertTrue(asSearchUIN.clearDetails(searchType,ID), 
				"Unable to Clear UIN Details");
	}
	
	@Test(description="AS SEARCH with Invalid UIN ",
			dataProvider = "ASSearchInputUIN",
			dataProviderClass = GetTestData.class,
			dependsOnMethods="ASSearchTestMethodUINClear")
	private void ASSearchTestMethodUINInvalid(String searchType,String ID) throws InterruptedException {
		asSearchUIN = new ASSearchUIN(wd);
		asSearchUIN.ASSearchUIN(searchType,"1234");
		Assert.assertTrue(asSearchUIN.validateWrongID(), 
				"UIN Validation Not Working");
	}
	
	@Test(description="AS SEARCH with valid UIN ",
			dataProvider = "ASSearchInputUIN",
			dataProviderClass = GetTestData.class,
			dependsOnMethods="ASSearchTestMethodUINInvalid")
	private void ASSearchTestMethodUINCorrect(String searchType,String ID) throws InterruptedException {
		asSearchUIN = new ASSearchUIN(wd);		
		Assert.assertTrue(asSearchUIN.ASSearchUIN(searchType,ID), 
				"Unable to Search using UIN ");
	}
	
	@Test(description="AS SEARCH UIN with All Valid Details and Validate Output Details",
			dataProvider = "ASSearchOutput",
			dataProviderClass = GetTestData.class,
			dependsOnMethods="ASSearchTestMethodUINCorrect")
	private void ASSearchTestMethodUINOutputValidation(String field,String value) throws InterruptedException {
		asSearchUIN = new ASSearchUIN(wd);
		Assert.assertTrue(asSearchUIN.verifyASDetailsPage(field,value),
				"Unable to get Output using All valid Details of UIN");
	}
	
	@Test(description="AS SEARCH with valid UIN and post Comment",
			dependsOnMethods="ASSearchTestMethodUINOutputValidation")
	private void ASSearchTestMethodUINPageResults() throws InterruptedException {
		asSearchUIN = new ASSearchUIN(wd);
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm").format(Calendar.getInstance().getTime());
		String testComment = "Automation Test";
		Assert.assertTrue(asSearchUIN.pageResults(testComment+" "+timeStamp), 
				"Unable to Post Comment");
	}

}
