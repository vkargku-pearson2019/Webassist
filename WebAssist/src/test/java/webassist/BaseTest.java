package webassist;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import webassist.extendreport.ExtentReportsManager;
import webassist.extendreport.ExtentTestManager;
import webassist.util.Base;
import webassist.util.CaptureScreenshot;
import webassist.util.GetEnvironmentData;
import webassist.util.Util;

import java.io.IOException;

public class BaseTest {

	protected static WebDriver wd;
	private Base base;
	private Util util;
	protected static String userName;
	private String annotatedTestName;
	protected static ExtentTest extentTest;
	//private String annotatedDescription;
	private static String browserType;
	private ExtentReports extentReports;

	@BeforeSuite
	protected void beforeSuiteSetup(ITestContext iTestContext) {
		//Get Suite Name
		String suiteName = iTestContext.getCurrentXmlTest().getSuite().getName();
		browserType = GetEnvironmentData.getAppEnvProperty("browserType");

		//initiate ExtentReportsManager
		extentReports = ExtentReportsManager.getReporter(suiteName,browserType);
	}

	@BeforeTest
	protected void beforeTestSetup(final ITestContext iTestContext){
		String testName = iTestContext.getName();
		String testDescription = iTestContext.getCurrentXmlTest().getParameter("testDescription");
		extentTest = ExtentTestManager.startTest(testName, testDescription);

		String url =  GetEnvironmentData.getAppEnvProperty("url");
		extentTest.log(Status.INFO,url);
		extentTest.log(Status.INFO,"Browser : " + browserType);
		base = new Base();
		wd = base.launchBrowser(browserType);
		util = new Util(wd);
		util.navigateTo(url);
	}

	@AfterMethod
	protected void fetchMostRecentTestResult(ITestResult testResult) {
		int status = testResult.getStatus();
		ITestNGMethod method = testResult.getMethod();

		switch (status) {
		case ITestResult.SUCCESS:
			System.out.println(method.getDescription() + " : PASS ");
			extentTest.log(Status.PASS, method.getDescription() + " - " + method.getMethodName());

			break;
		case ITestResult.FAILURE:
			System.out.println(method.getDescription() + " : FAIL ");
			String filePath = new CaptureScreenshot(wd).takeFullScreenshot();
			try {
				extentTest.log(Status.FAIL, method.getDescription() + " - " + method.getMethodName());
				extentTest.createNode( method.getDescription() + " - " +
						method.getMethodName()).fail(String.valueOf(testResult.getThrowable()))
				.addScreenCaptureFromPath(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;
		case ITestResult.SKIP:
			System.out.println(method.getDescription() + " : SKIP BLOCKED ");
			extentTest.log(Status.SKIP, method.getDescription() + " - " + method.getMethodName());
			extentTest.createNode(method.getDescription() + " - " + method.getMethodName()).fail(String.valueOf(testResult.getThrowable()));

			break;
		default:

		}

	}


	@AfterTest
	protected void closeAll() {
		wd.quit();
		ExtentTestManager.endTest();
	}


}
