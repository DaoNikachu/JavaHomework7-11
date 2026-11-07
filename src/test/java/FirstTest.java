import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class FirstTest {
    private WebDriver driver;
    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void searchBoxTest(){
        driver.get("https://www.mts.by/");
        WebElement searchBox = driver.findElement(By.cssSelector(".pay__wrapper h2"));
        String actual = searchBox.getText();
        Assert.assertEquals(actual, "ОНЛАЙН ПОПОЛНЕНИЕ\nБЕЗ КОМИССИИ");}
    @Test
    public void paymentLogosTest() {
        driver.get("https://www.mts.by/");

        List<WebElement> logos = driver.findElements(
                By.cssSelector("img[src*='/pay/']")
        );

        Assert.assertEquals(
                logos.size(),
                5,
                "Количество логотипов не соответствует ожидаемому"
        );

        for (WebElement logo : logos) {
            Assert.assertTrue(logo.isDisplayed());
        }
    }
    @Test
    public void paymentServiceLinkTest() {
        driver.get("https://www.mts.by/");
        driver.findElement(By.cssSelector(".cookie__cancel")).click();
        WebElement link = driver.findElement(
                By.linkText("Подробнее о сервисе")
        );

        link.click();

        Assert.assertTrue(
                driver.getCurrentUrl().contains(
                        "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"
                ),
                "Ссылка ведёт на неправильную страницу"
        );
    }
    @Test
    public void paymentContinueButtonTest() {
        driver.get("https://www.mts.by/");

        // Закрываем cookie
        driver.findElement(
                By.cssSelector(".cookie__cancel")
        ).click();

        // Вводим номер телефона
        driver.findElement(
                By.id("connection-phone")
        ).sendKeys("297777777");

        // Вводим сумму
        driver.findElement(
                By.id("connection-sum")
        ).sendKeys("10");

        // Вводим email
        driver.findElement(
                By.id("connection-email")
        ).sendKeys("test@example.com");

        // Нажимаем "Продолжить"
        driver.findElement(
                By.cssSelector("#pay-connection button[type='submit']")
        ).click();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        WebElement paymentWindow = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".payment-widget-iframe")
                )
        );

        Assert.assertTrue(
                paymentWindow.isDisplayed(),
                "Окно оплаты не открылось"
        );
    }
    @AfterMethod(alwaysRun = true)
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }