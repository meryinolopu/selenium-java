package com.nopcommerce.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {
    WebDriver driver;

    By radioButtonGenderMale = By.id("gender-male");
    By radioButtonGenderFemale = By.id("gender-female");
    By inputFirstName = By.id("FirstName");
    By inputLastName  = By.id("LastName");
    By listDayOfBirth = By.name("DateOfBirthDay");
    By listMonthOfBirth = By.name("DateOfBirthMonth");
    By listYearOfBirth = By.name("DateOfBirthYear");
    By inputEmail = By.id("Email");
    By inputCompanyName = By.id("Company");
    By inputPassword = By.id("Password");
    By inputConfirmPassword = By.id("ConfirmPassword");
    By buttonRegister = By.id("register-button");
    By errorMessageFirstName = By.id("FirstName-error");
    By errorMessageLastName = By.id("LastName-error");
    By errorMessageEmail = By.id("Email-error");
    By errorMessagePassword = By.id("Password-error");
    By errorMessageConfirmationPassword = By.id("ConfirmPassword-error");
    By errorMessageExistingEmail = By.className("message-error");
    By bodyMessage = By.className("page-body");

    public RegisterPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void createNewAccount(String dayOfBirth, String monthOfBirth, String yearOfBirth){
        type(inputFirstName,"Susana");
        type(inputLastName,"Lopez");
        selectFromDropdown(listDayOfBirth, dayOfBirth);
        selectFromDropdown(listMonthOfBirth, monthOfBirth);
        selectFromDropdown(listYearOfBirth, yearOfBirth);
        type(inputEmail,"susanalopez@test.com");
        type(inputCompanyName,"Test company");
        type(inputPassword,"SuperPass123");
        type(inputConfirmPassword,"SuperPass123");
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Female")) {
            driver.findElement(radioButtonGenderFemale).click();
        }
        if (gender.equalsIgnoreCase("Male")) {
            driver.findElement(radioButtonGenderMale).click();
        }
    }
    private void selectFromDropdown(By dropdownLocator, String value) {
        Select dropdown = new Select(driver.findElement(dropdownLocator));
        dropdown.selectByVisibleText(value);
    }

    public void clickRegisterButton(){
        submit(buttonRegister);
    }

    public void setFieldValues(String firstname, String lastname, String email, String companyName, String password, String confirmPassword){
        type(inputFirstName,firstname);
        type(inputLastName,lastname);
        type(inputEmail,email);
        type(inputCompanyName,companyName);
        type(inputPassword,password);
        type(inputConfirmPassword,confirmPassword);
    }

    public String getFirstNameErrorMessageText() {
        WebElement element = driver.findElement(errorMessageFirstName);
        return element.getText();
    }

    public String getLastNameErrorMessageText() {
        WebElement element = driver.findElement(errorMessageLastName);
        return element.getText();
    }

    public String  getEmailErrorMessageText() {
        WebElement element = driver.findElement(errorMessageEmail);
        return element.getText();
    }

    public String  getPasswordErrorMessageText() {
        WebElement element = driver.findElement(errorMessagePassword);
        return element.getText();
    }

    public String  getConfirmationPasswordErrorMessageText() {
        WebElement element = driver.findElement(errorMessageConfirmationPassword);
        return element.getText();
    }

    public String  getExistingEmailErrorMessageText() {
        WebElement element = driver.findElement(errorMessageExistingEmail);
        return element.getText();
    }

    public String  getRegisterCompleteMessageText() {
        WebElement element = driver.findElement(bodyMessage);
        WebElement message = element.findElement(By.className("result"));
        return message.getText();
    }
}
