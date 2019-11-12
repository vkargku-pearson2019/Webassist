package webassist.extendreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import webassist.util.DateTimeFormat;

public class ExtentReportsManager {

    private static String DEFAULT_REPORT_LOCATION = "./reports/html/WebAssist_" + DateTimeFormat.getFormattedDateTime() + ".html";
    protected static ExtentReports extentReports;

    public synchronized static ExtentReports getReporter(String suiteName,String browserType){
        // initialize the HtmlReporter
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(DEFAULT_REPORT_LOCATION);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("WebAssist - Automation");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss a");

        // initialize ExtentReports and attach the HtmlReporter
        extentReports = new ExtentReports();
        // attach only HtmlReporter
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Application","WebAssist");
        extentReports.setSystemInfo("QA","Automation Test");
        extentReports.setSystemInfo("Project Name","WebAssist");
        extentReports.setSystemInfo("Suite",suiteName);
        extentReports.setSystemInfo("Browser",browserType);

        return extentReports;
    }

}
