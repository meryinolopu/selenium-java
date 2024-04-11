package com.nopcommerce.tests;

import com.nopcommerce.pages.HomePage;
import com.nopcommerce.pages.RegisterPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.junit.Assert;

import java.time.Duration;

import static com.nopcommerce.utils.Variables.BASE_URL;

public class RegisterTest extends BaseTest {

    @Test (groups = "Functional")
    public void registerPageSuccessfully() {
        setUp();
        homePage.goToRegisterOption();
        registerPage.createNewAccount("10", "January", "1990");
        registerPage.selectGender("Female");
        registerPage.clickRegisterButton();

        Assert.assertEquals("Your registration completed",
                registerPage.getRegisterCompleteMessageText());

        tearDown();
    }

    @Test (groups = {"Functional", "Integration"})
    public void registerPageWithoutMandatoryFields() {
        setUp();
        homePage.goToRegisterOption();
        registerPage.setFieldValues("","","carloslopez@test.com","Test company","SuperPass123","SuperPass123");
        registerPage.clickRegisterButton();

        Assert.assertEquals("First name is required.",
                registerPage.getFirstNameErrorMessageText());
        Assert.assertEquals("Last name is required.",
                registerPage.getLastNameErrorMessageText());

        tearDown();
    }

    @Test (groups = {"Functional", "Regression"})
    public void registerPageWithWrongEmail() {
        setUp();
        homePage.goToRegisterOption();
        registerPage.setFieldValues("Bruno","Lopez","c72829s","Test company","SuperPass123","SuperPass123");
        registerPage.clickRegisterButton();

        Assert.assertEquals("Wrong email",
                registerPage.getEmailErrorMessageText());
        tearDown();
    }

    @Test (groups = "Regression")
    public void registerPageWithWrongPasswordFormat() {
        setUp();
        homePage.goToRegisterOption();
        registerPage.setFieldValues("Bruno","Lopez","brunolopez@test.com","Test company","1234","1234");
        registerPage.clickRegisterButton();

        Assert.assertEquals("Password must meet the following rules:\n" +
                        "must have at least 6 characters",
                registerPage.getPasswordErrorMessageText());

        tearDown();
    }

    @Test (groups = {"Functional", "Integration"})
    public void registerPageWithWrongConfirmationPassword() {
        setUp();
        homePage.goToRegisterOption();
        registerPage.setFieldValues("Bruno","Lopez","brunolopez@test.com","Test company","123456","1234aaa");
        registerPage.clickRegisterButton();

        Assert.assertEquals("The password and confirmation password do not match.",
                registerPage.getConfirmationPasswordErrorMessageText());

        tearDown();
    }

    @Test (groups = "Integration")
    public void registerPageWithExistingEmail() {
        setUp();
        homePage.goToRegisterOption();
        registerPage.setFieldValues("Susana","Lopez","susanalopez@test.com","Test company","SuperPass123","SuperPass123");
        registerPage.clickRegisterButton();

        Assert.assertEquals("The specified email already exists",
                registerPage.getExistingEmailErrorMessageText());

        tearDown();
    }
}
