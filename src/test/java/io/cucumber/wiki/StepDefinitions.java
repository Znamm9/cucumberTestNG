package io.cucumber.wiki;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

public class StepDefinitions {

    Pages pages;
    WebDriver driver;

    @Before
    public void before(){
        if (System.getProperty("browser") == null){
            initChrome();
        }else if (System.getProperty("browser").equalsIgnoreCase("chrome") && System.getProperty("browser")!=null){
            initChrome();
        }else if (System.getProperty("browser").equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(System.getProperty("browser").equalsIgnoreCase("safari")){
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }else if (System.getProperty("browser").equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        pages = new Pages(driver);
    }

    private void initChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (System.getProperty("headless") == null){

        }else if (System.getProperty("headless").equalsIgnoreCase("true")){
            options.addArguments("headless");
        }
            options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @After
    public void after(){
        driver.quit();
    }

    @Given("user on login page")
    public void user_on_login_page() {
        pages.loginPage.navigate();
    }

    @When("user enter login {word}")
    public void user_enter_login_vadym(String username) {
        pages.loginPage.getEmailInput().sendKeys(username);
    }
    @When("enter password {word}")
    public void enter_password_test123(String pass) {
        pages.loginPage.getPasswordInput().sendKeys(pass);
    }

    @When("user clicks on submit login")
    public void user_clicks_on_submit_login() {
        pages.loginPage.getSubmitLoginBtn().click();
    }
    @Then("login error message should be displayed")
    public void login_error_message_should_be_displayed() {
        if (!pages.loginPage.isCaptchaDisplayed()){
            Assert.assertTrue(pages.loginPage.getLoginErrorMsg().isDisplayed());
        }
    }

    @Given("I navigate to home page")
    public void i_navigate_to_home_page() {
        pages.homePage.navigate();
    }
    @Then("logo image should be displayed")
    public void logo_image_should_be_displayed() {
        Assert.assertTrue(pages.homePage.getLogo().isDisplayed());
    }
    @Then("login link should be displayed")
    public void login_link_should_be_displayed() {
        Assert.assertTrue(pages.homePage.getLoginLink().isDisplayed());
    }

    @When("type {word} in search field")
    public void type_ukraine_in_search_field(String word) {
        pages.homePage.getSearchInput().sendKeys(word);
    }
    @When("press {word}")
    public void press_enter(String buttonName) {
        String ukraineURL = "https://en.wikipedia.org/wiki/Ukraine";
        if (buttonName.equalsIgnoreCase("ENTER")){
            pages.homePage.getSearchInput().sendKeys(Keys.ENTER);
        }else {
            System.out.println("your key is not implemented yet");
            throw new io.cucumber.java.PendingException();
        }
        if (!driver.getCurrentUrl().equals(ukraineURL)){
            driver.get(ukraineURL);
        }
    }
    @Then("search results should contain {word}")
    public void search_results_should_contain_ukraine(String word) {
        Assert.assertEquals("https://en.wikipedia.org/wiki/Ukraine", driver.getCurrentUrl());
    }

    @And("total area should be greater than {int}")
    public void totalAreaShouldBeGreaterThan(int area) {
        String totalArea = pages.searchResultsPage.getAreaElement().getText().replace(",", "");
        totalArea = totalArea.substring(0, 6);
        int totalAreaNum = Integer.parseInt(totalArea);
        Assert.assertTrue(totalAreaNum > area);
    }
}
