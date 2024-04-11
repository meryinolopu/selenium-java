package com.nopcommerce.pages;

import com.nopcommerce.utils.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    WebDriverWait wait;
    int timeOutSec = 10;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSec));
    }

    public WebElement find(By element){
        return driver.findElement(element);
    }

    public void type(By element, String text){
        find(element).sendKeys(text);
    }
    public void click (By element){
        find(element).click();
    }
    public void submit(By element){
        find(element).submit();
    }

    public boolean isDisplay(By locator){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
