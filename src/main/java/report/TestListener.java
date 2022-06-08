package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import framework.LogLevels;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import util.LoggerUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

public class TestListener implements ITestListener {

    private static ExtentReports extentReports = ExtentReportManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        ExtentTest extentTest = extentReports.createTest(iTestResult.getTestClass().getName()
                + " :: " + iTestResult.getMethod().getDescription());
        test.set(extentTest);
        LoggerUtil.log("ExtentReport initialized:" + iTestResult.getMethod().getDescription(), LogLevels.INFO);

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Markup markup = MarkupHelper.createLabel(iTestResult.getMethod().getDescription() + " successfull.", ExtentColor.GREEN);
        test.get().log(Status.PASS, markup);
        LoggerUtil.log("Test Passed:" + iTestResult.getMethod().getDescription(), LogLevels.INFO);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String errorMessage = iTestResult.getThrowable().getMessage();
        String exceptionMessage = Arrays.toString(iTestResult.getThrowable().getStackTrace());
        test.get().fail("Test failed. Error message:  " + errorMessage);
        test.get().fail("Test failed. Stacktrace:  " + exceptionMessage);
        LoggerUtil.log("Test failed:" + iTestResult.getMethod().getDescription(), LogLevels.ERROR);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Markup markup = MarkupHelper.createLabel(iTestResult.getMethod().getDescription() + " skipped.", ExtentColor.GREY);
        test.get().log(Status.SKIP, markup);
        LoggerUtil.log("Test skipped:" + iTestResult.getMethod().getDescription(), LogLevels.ERROR);

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
