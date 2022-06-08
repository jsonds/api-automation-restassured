package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentSparkReporter htmlReporter;
    private static ExtentReports extentReports;
    private static final String reportsPath = "target/reports/api-Report.html";

    public static ExtentReports createInstance() {

        htmlReporter = new ExtentSparkReporter(reportsPath);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("API");
        htmlReporter.config().setReportName("API Test Results Report");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.setSystemInfo("Organization", "Restassured");
        extentReports.attachReporter(htmlReporter);

        return extentReports;
    }
}
