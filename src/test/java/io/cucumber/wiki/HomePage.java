package io.cucumber.wiki;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    public WebElement getLogo() {
        return getElementByXpath("//div[@id='p-logo']");
    }

    public WebElement getLoginLink() {
        return getElementByXpath("//li[@id='pt-login']/a");
    }

    public WebElement getSearchInput() {
        return getElementByXpath("//input[@id='searchInput']");
    }
}
