package com.nopcommerce.pages;

import com.nopcommerce.utils.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    WebDriver driver;
    By linkRegister = By.linkText("Register");
    By linkLogin = By.linkText("Log in");


    public HomePage(WebDriver driver){
        super(driver);
    }

    public void goToRegisterOption(){
        click(linkRegister);
    }

    public void goToLoginOption(){
        click(linkLogin);
    }

}
