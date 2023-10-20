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

public class Checkout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user is on cart page")
    public void user_is_on_cart_page(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }
    @When("user click checkout button")
    public void user_click_checkout_button(){
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }
    @Then("user is on checkout step one page")
    public void user_is_on_checkout_step_one_page(){
        driver.findElement(By.xpath("//span[contains(text(), 'Checkout: Your Information')]")).click();
    }
    @When("user input (.*) as firstname$")
    public void user_input_as_firstname(String firstname){
        driver.findElement(By.id("first-name")).sendKeys(firstname);
    }
    @And("user input (.*) as lastname$")
    public void user_input_as_lastname(String lastname){
        driver.findElement(By.id("last-name")).sendKeys(lastname);
    }
    @And("user input (.*) as zipcode$")
    public void user_input_as_zipcode(String zipcode){
        driver.findElement(By.id("postal-code")).sendKeys(zipcode);
    }
    @And("user click continue button")
    public void user_click_continue_button(){
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }
    @Then("user verify (.*) checkout result$")
    public void user_verify_checkout_result(String status){
        if (status.equals("valid")) {
            String checkout_step_two_page = driver.findElement(By.xpath("//span[contains(text(), 'Checkout: Overview')]")).getText();
            Assert.assertEquals(checkout_step_two_page, "Checkout: Overview");
        } else {
            String errorCheckout = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
            Assert.assertEquals(errorCheckout, "Error: First Name is required");
        }
    }
}
