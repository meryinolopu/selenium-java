package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    WebDriver driver;

    By inputEmail = By.id("Email");
    By inputPassword = By.id("Password");
    By buttonLogIn = By.className("login-button");
    By errorMessageLogin = By.className("message-error");
    By errorMessageEmptyLogin = By.id("Email-error");
    By linkForgotPassword = By.linkText("Forgot password?");
    By buttonRecoverButton = By.name("send-email");
    By messageForgotPassword = By.className("bar-notification");


    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void fillOutLoginForm(){
        type(inputEmail,"susanalopez@test.com");
        type(inputPassword,"SuperPass123");
    }

    public void clickLogInButton(){
        submit(buttonLogIn);
    }

    public void setLoginFieldValues(String email, String password){
        type(inputEmail,email);
        type(inputPassword,password);
    }

    public String  getLoginErrorMessageText() {
        WebElement element = driver.findElement(errorMessageLogin);
        return element.getText();
    }

    public String  getEmptyLoginErrorMessageText() {
        WebElement element = driver.findElement(errorMessageEmptyLogin);
        return element.getText();
    }

    public void clickForgotPassword(){
        click(linkForgotPassword);
    }

    public void fillOutForgotPasswordForm(String forgotEmail){
        type(inputEmail,forgotEmail);
    }

    public void clickRecover(){
        click(buttonRecoverButton);
    }

    public String  getForgotPasswordMessageText() {
        WebElement element = driver.findElement(messageForgotPassword);
        return element.getText();
    }
}
