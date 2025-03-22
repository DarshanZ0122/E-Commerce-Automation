package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    // Take screenshot and save it to the folder
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Create folder if not exists
            String folderPath = "screenshots/";
            Files.createDirectories(Paths.get(folderPath));

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = folderPath + screenshotName + ".png";
            Files.copy(screenshot.toPath(), Paths.get(filePath));

            System.out.println("Screenshot saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to capture screenshot");
        }
    }
}
