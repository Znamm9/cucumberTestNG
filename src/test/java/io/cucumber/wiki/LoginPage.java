package io.cucumber.wiki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.get("https://en.wikipedia.org/w/index.php?title=Special:UserLogin&returnto=Ukraine");
    }

    public WebElement getEmailInput() {
        return getElementByXpath("//input[@id='wpName1']");
    }

    public WebElement getPasswordInput() {
        return getElementByXpath("//input[@id='wpPassword1']");
    }

    public WebElement getSubmitLoginBtn() {
        return getElementByXpath("//button[@id='wpLoginAttempt']");
    }

    public WebElement getLoginErrorMsg() {
        return getElementByXpath("//div[@id='userloginForm']//div[contains(@class, 'box-error')]");
    }

    public boolean isCaptchaDisplayed() {
        sleep(500);
        return driver.findElements(By.xpath("//div[contains(@class, 'fancycaptcha-image-container')]")).size() > 0;
    }
}
