package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Automation Results");
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static com.aventstack.extentreports.ExtentTest startTest(String testName) {
        return getInstance().createTest(testName);
    }

    public static void endTest() {
        if (extent != null) {
            extent.flush();
        }
    }
}
