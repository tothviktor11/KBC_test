import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KBCTest {

    public static EdgeOptions edgeOptions;
    public static WebDriver driver;

    @BeforeTest
    public static void SetUp() throws InterruptedException {

        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/src/test/resources/msedgedriver.exe");

        edgeOptions = new EdgeOptions();
        edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        edgeOptions.addArguments("--remote-allow-origins=*");
        edgeOptions.setCapability("ignore-certificate-errors", true);

        driver = new EdgeDriver(edgeOptions);
        driver.manage().deleteAllCookies();
        driver.get("https://automationexercise.com");
    }

    @Test
    void testCase2() {

        WebElement homeButton = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[1]/a"));

        Assert.assertEquals(driver.getTitle(), "Automation Exercise","Incorrect title.");
        Assert.assertEquals(homeButton.getText(), "Home","Incorrect title.");
        Assert.assertEquals(homeButton.getCssValue("color"), "rgba(255, 165, 0, 1)", "Home screen is not selected.");

        WebElement btnSignupOrLogin = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a"));
        btnSignupOrLogin.click();

        WebElement lblLoginTitle = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2"));
        Assert.assertEquals(lblLoginTitle.getText(), "Login to your account", "Incorrect Login title.");

        WebElement txtEmailAddress = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]"));
        txtEmailAddress.sendKeys("tothviktor11@gmail.com");
        WebElement pwdPassword = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]"));
        pwdPassword.sendKeys("KBC_Is_Num.1");
        WebElement btnLogin = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button"));
        btnLogin.click();

        WebElement btnLoggedUser = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a"));
        Assert.assertEquals(btnLoggedUser.getText(), "Logged in as Viktor","Incorrect user");

        WebElement btnDeleteAccount = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a"));
        btnDeleteAccount.click();

        WebElement lblAccountDeleted = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
        Assert.assertEquals(lblAccountDeleted.getText(), "ACCOUNT DELETED!");

        driver.quit();
    }
}

