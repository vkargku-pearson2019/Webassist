package DemoBDDkk.testfilesphp;

public class basetestphp {
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
		protected void beforeSuiteSetup() {
			//Get Suite Name
			browserType = GetEnvironmentData.getAppEnvProperty("browserType");

			//initiate ExtentReportsManager
			extentReports = ExtentReportsManager.getReporter(suiteName,browserType);
		}

		@BeforeTest
		protected void beforeTestSetup(final ITestContext iTestContext){
			extentTest = ExtentTestManager.startTest(testName, testDescription);

			String url =  GetEnvironmentData.getAppEnvProperty("url");
			extentTest.log(Status.INFO,url);
			extentTest.log(Status.INFO,"Browser : " + browserType);
			base = new Base();
			wd = base.launchBrowser(browserType);
			util = new Util(wd);
			util.navigateTo(url);
		}

		

		}


		@AfterTest
		protected void closeAll() {
			wd.quit();
			ExtentTestManager.endTest();
		}


	}

}
