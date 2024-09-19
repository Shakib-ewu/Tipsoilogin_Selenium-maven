package allTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class login {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Correctly initialize the class-level variable
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void getTitle() throws InterruptedException {
        driver.get("https://test.clients.inovacetech.com/login");
        driver.findElement(By.id("inputEmail3")).sendKeys("ariful@gmail.com");
        driver.findElement(By.id("inputPassword3")).sendKeys("Ariful@123");

        Actions actions = new Actions(driver);
        List<WebElement> buttons = driver.findElements(By.className("ml-negative-5"));
        actions.click(buttons.get(0)).perform();

        Thread.sleep(4000);

        WebElement manualEntryLink = driver.findElement(By.xpath("//span[text()='Manual Entry']"));
        manualEntryLink.click();

        Select select = new Select(driver.findElement(By.id("manualEntryTypeSelect")));
        select.selectByVisibleText("Manual Entry");

        WebElement entryTimeField = driver.findElement(By.cssSelector("div[date-range-picker]"));
        entryTimeField.click();

        WebElement day = driver.findElement(By.xpath("//td[contains(@class, 'available') and text()='4']"));
        day.click();

        WebElement hourDropdown = driver.findElement(By.cssSelector("select.hourselect"));
        Select selectHour = new Select(hourDropdown);
        selectHour.selectByVisibleText("10");

        WebElement minuteDropdown = driver.findElement(By.cssSelector("select.minuteselect"));
        Select selectMinute = new Select(minuteDropdown);
        selectMinute.selectByVisibleText("00");

        WebElement amPmDropdown = driver.findElement(By.cssSelector("select.ampmselect"));
        Select selectAmPm = new Select(amPmDropdown);
        selectAmPm.selectByVisibleText("PM");

        Thread.sleep(5000);

        WebElement applyButton = driver.findElement(By.xpath("//div[@class='daterangepicker dropdown-menu ltr single opensright show-calendar']//button[@class='applyBtn btn btn-sm btn-success' and text()='Apply']"));
        applyButton.click();

        WebElement exitTimeField = driver.findElement(By.xpath("//div[@ng-model='vm.manualEntryTo']"));
        exitTimeField.click();
    }

    @AfterAll
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
