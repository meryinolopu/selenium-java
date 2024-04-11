package com.nopcommerce.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class LoginTest extends BaseTest{
    @Test (groups = {"Regression", "Integration"})
    public void loginWithValidInformation() {
        setUp();
        String expected_title = "Welcome to our store";
        homePage.goToLoginOption();
        loginPage.fillOutLoginForm();
        loginPage.clickLogInButton();

        WebElement labelAccount = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='topic-block-title']/h2")));
        Assert.assertEquals(labelAccount.getText(),expected_title);

        tearDown();
    }

    @Test (groups = "Functional")
    public void loginWithWrongPassword() {
        setUp();
        homePage.goToLoginOption();
        loginPage.setLoginFieldValues("susanalopez@test.com","Super123456");
        loginPage.clickLogInButton();

        Assert.assertEquals("Login was unsuccessful. Please correct the errors and try again.\n" +
                        "The credentials provided are incorrect",
                loginPage.getLoginErrorMessageText());

        tearDown();
    }

    @Test (groups = "Functional")
    public void loginWithEmptyFields() {
        setUp();
        homePage.goToLoginOption();
        loginPage.setLoginFieldValues("","");
        loginPage.clickLogInButton();

        Assert.assertEquals("Please enter your email",
                loginPage.getEmptyLoginErrorMessageText());

        tearDown();
    }

    @Test (groups = "Regression")
    public void loginWithNonExistingEmail() {
        setUp();
        homePage.goToLoginOption();
        loginPage.setLoginFieldValues("susana@test.com","SuperPass123");
        loginPage.clickLogInButton();

        Assert.assertEquals("Login was unsuccessful. Please correct the errors and try again.\n" +
                        "No customer account found",
                loginPage.getLoginErrorMessageText());

        tearDown();
    }

    @Test (groups = {"Functional", "Integration"})
    public void loginForgotPassword() throws InterruptedException{
        setUp();
        homePage.goToLoginOption();
        loginPage.clickForgotPassword();
        loginPage.fillOutForgotPasswordForm("susanalopez@test.com");
        loginPage.clickRecover();
        Thread.sleep(1000);

        Assert.assertEquals("Email with instructions has been sent to you.",
                loginPage.getForgotPasswordMessageText());

        tearDown();
    }

}
