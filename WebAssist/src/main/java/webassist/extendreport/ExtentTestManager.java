package webassist.extendreport;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager extends ExtentReportsManager{

    public static synchronized ExtentTest startTest(String testName, String disc) {
        ExtentTest test = extentReports.createTest(testName, disc);
        return test;
    }

    public static synchronized void endTest() {
        extentReports.flush();
    }


}
