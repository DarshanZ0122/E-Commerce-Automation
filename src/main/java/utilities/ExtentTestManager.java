package utilities;
import com.aventstack.extentreports.ExtentTest;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    private static Map<Long, ExtentTest> extentTestMap = new HashMap<>();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = ExtentManager.startTest(testName).assignAuthor("Automation Team").assignCategory("Functional Test");
        test.info(description);
        extentTestMap.put(Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized void endTest() {
        extentTestMap.remove(Thread.currentThread().getId());
    }
}