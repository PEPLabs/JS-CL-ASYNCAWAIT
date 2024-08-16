import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");

        // Get file
        File file = new File("src/main/AsyncAwait.html");
        String path = "file://" + file.getAbsolutePath();

        // Create a new ChromeDriver instance
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(options);

        // Open the HTML file
        webDriver.get(path);
    }


    @Test
    public void testInitialState() {
        WebElement text = webDriver.findElement(By.id("text"));
        assertEquals("click the button", text.getText());       
    }

    @Test
    public void testAsyncAwait() {
        WebElement text = webDriver.findElement(By.id("text"));
        assertEquals("click the button", text.getText());    
        
        WebElement button = webDriver.findElement(By.id("button"));
        // click the button
        button.click();

        // wait 10 seconds or until the text changes:
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        // ensure that the new text is "Success!":
        wait.until(ExpectedConditions.textToBePresentInElement(text, "Success!"));

    }


    @After
    public void tearDown() {
        // Close the browser
        webDriver.quit();
    }
}
