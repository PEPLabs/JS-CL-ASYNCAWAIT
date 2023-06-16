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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // Create a new ChromeDriver instance
        driver = new ChromeDriver();
        File file = new File("AsyncAwait.html");
        // Open the HTML file
        driver.get(file.getAbsolutePath());
    }
    @Test
    public void testInitialState() {
        WebElement text = driver.findElement(By.id("text"));
        assertEquals("click the button", text.getText());       
    }

    @Test
    public void testAsyncAwait() {
        WebElement text = driver.findElement(By.id("text"));
        assertEquals("click the button", text.getText());    
        
        WebElement button = driver.findElement(By.id("button"));
        // click the button
        button.click();

        // wait 10 seconds or until the text changes:
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // ensure that the new text is "Success!":
        wait.until(ExpectedConditions.textToBePresentInElement(text, "Success!"));

    }


    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}