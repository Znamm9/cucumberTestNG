package io.cucumber.wiki;

import org.openqa.selenium.WebDriver;

public class Pages extends BasePage{

    public HomePage homePage;
    public LoginPage loginPage;
    public SearchResultsPage searchResultsPage;


    public Pages(WebDriver driver) {
        super(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }
}
