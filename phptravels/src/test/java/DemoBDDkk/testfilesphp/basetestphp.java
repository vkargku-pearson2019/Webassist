package DemoBDDkk.testfilesphp;

/*	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;*/

import resourcesphp.Getenvironmentphp;
import resourcesphp.basephp;
import resourcesphp.phputilfile;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
//import webassist.extendreport.ExtentReportsManager;
//import webassist.extendreport.ExtentTestManager;

import resourcesphp.capturescreenshotphp;



import java.io.IOException;

public class basetestphp {
	protected static WebDriver wd;
	private basephp base;
	private phputilfile util;
	protected static String userName;
	//private String annotatedTestName;
	//protected static ExtentTest extentTest;
	//private String annotatedDescription;
	private static String browserType;
	//private ExtentReports extentReports;

	@BeforeSuite
	protected void beforeSuiteSetup() {
		//Get Suite Name
		browserType = Getenvironmentphp.getAppEnvProperty("browserType");

		//initiate ExtentReportsManager
		//extentReports = ExtentReportsManager.getReporter(suiteName,browserType);
	}

	@BeforeTest
//	protected void beforeTestSetup(final ITestContext iTestContext){
	protected void beforeTestSetup(){
		//extentTest = ExtentTestManager.startTest(testName, testDescription);

		String url =  Getenvironmentphp.getAppEnvProperty("url");
		//extentTest.log(Status.INFO,url);
		//extentTest.log(Status.INFO,"Browser : " + browserType);
		base = new basephp();
		wd = base.launchBrowser(browserType);
		util = new phputilfile(wd);
		util.navigateTo(url);
	}

/*
	@AfterTest
	protected void closeAll() {
		wd.quit();
		//ExtentTestManager.endTest();
	}

*/
}

