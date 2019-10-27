package com.kavinschool.zenphoto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(how = How.NAME, using="user")
    @CacheLookup
    private WebElement user;

    @FindBy(how = How.NAME, using="pass")
    @CacheLookup
    private WebElement password;


    @FindBy(how = How.CSS, using="button[type=submit]")
    @CacheLookup
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage typeUserId(final String userId) {
        user.clear();
        user.sendKeys(userId);
        return this;
    }


    public LoginPage typePassword(final String password) {
        this.password.clear();
        this.password.sendKeys(password);
        return this;
    }

    public AdminDashBoardPage clickLoginButtonReturnAdminDashBoardPage() {
        loginButton.click();
        return PageFactory.initElements(driver, AdminDashBoardPage.class);
    }

    public AdminDashBoardPage login(final String userId, final String password) {
        typeUserId(userId).typePassword(password);
        return clickLoginButtonReturnAdminDashBoardPage();
    }

}
