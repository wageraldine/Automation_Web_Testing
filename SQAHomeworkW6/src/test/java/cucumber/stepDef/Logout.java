package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Logout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user is on system")
    public void user_is_on_system(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
    @When("user click icon menu button")
    public void user_click_icon_menu_button(){
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
    }
    @And("user click logout button")
    public void user_click_logout_button(){
        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
    }
    @Then("user verify logout process")
    public void user_verify_logout_process(){
        String accepted_username = driver.findElement(By.xpath("//h4[contains(text(), 'Accepted usernames are:')]")).getText();
        Assert.assertEquals(accepted_username, "Accepted usernames are:");
    }
}
