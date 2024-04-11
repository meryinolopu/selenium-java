package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage extends BasePage{
    WebDriver driver;


    By linkBooksProduct = By.xpath("//a[text()='Books ']");
    By buttonAddCart = By.xpath("//button[text()='Add to cart']");
    By productList = By.className("product-grid");
    By quantityProduct = By.className("qty-input");
    By panelCart = By.className("add-to-cart-panel");
    By closeAlert = By.xpath("//span[@class='close' and @title='Close']");
    By linkShoppingCart = By.xpath("//a[@class='ico-cart']");
    By removeIcon = By.className("remove-btn");
    By cartQuantity = By.className("cart-qty");
    By shoppingCartTable = By.xpath("//table[@class='cart']");
    By buttonUpdateCart = By.id("updatecart");
    By linkDigitalDownloadsProduct = By.xpath("//a[text()='Digital downloads ']");
    By checkTermsOfService = By.id("termsofservice");
    By buttonCheckout = By.id("checkout");
    By listCountry = By.id("BillingNewAddress_CountryId");
    By listState = By.id("BillingNewAddress_StateProvinceId");
    By inputCity = By.id("BillingNewAddress_City");
    By inputAddress = By.id("BillingNewAddress_Address1");
    By inputZipCode = By.id("BillingNewAddress_ZipPostalCode");
    By inputPhoneNumber = By.id("BillingNewAddress_PhoneNumber");
    By checkoutBilling = By.id("opc-billing");
    By checkoutShippingMethod = By.id("opc-shipping_method");
    By checkoutPaymentMethod = By.id("opc-payment_method");
    By checkPaymentMethod = By.id("paymentmethod_1");
    By inputCardholderName = By.id("CardholderName");
    By inputCardNumber = By.id("CardNumber");
    By listYearExpirationDate = By.id("ExpireYear");
    By inputCardCode = By.id("CardCode");
    By checkoutPaymentInformation = By.id("opc-payment_info");
    By checkoutConfirm = By.id("opc-confirm_order");
    By sectionOrderComplete = By.className("order-completed");
    By barNotification = By.className("bar-notification");


    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void clickBooksProduct(){
        click(linkBooksProduct);
    }

    public void clickAddCart(String qtyProduct, String productName){

        WebElement products = driver.findElement(productList);
        WebElement product = products.findElement(By.linkText(productName));

        product.click();
        find(quantityProduct).clear();
        type(quantityProduct,qtyProduct);

        WebElement wpanelCart = driver.findElement(panelCart);
        WebElement buttonAddCartProduct = wpanelCart.findElement(By.className("add-to-cart-button"));
        buttonAddCartProduct.click();
    }

    public void clickRemoveProducts() throws InterruptedException{
        click(buttonAddCart);
        click(closeAlert);
        Thread.sleep(3000);
        click(linkShoppingCart);
        click(removeIcon);
    }

    public String getCartQuantityText() {
        WebElement element = driver.findElement(cartQuantity);
        return element.getText();
    }

    public void clickGoShoppingCart() throws InterruptedException {
        click(buttonAddCart);
        click(closeAlert);
        Thread.sleep(3000);
        click(linkShoppingCart);
    }

    public void clickUpdateShoppingCart(String qtyProduct) throws InterruptedException{

        WebElement tableElement = driver.findElement(shoppingCartTable);
        WebElement bodyElement = tableElement.findElement(By.tagName("tbody"));
        WebElement firstRow = bodyElement.findElements(By.tagName("tr")).get(0);
        WebElement qtyProductTable = firstRow.findElement(By.className("qty-input"));
        String qty = qtyProductTable.getAttribute("value");
        if (qty.isEmpty()) {
            qtyProductTable.clear();
            qtyProductTable.sendKeys(qtyProduct);
        }else{
            Integer qtyInt = Integer.parseInt(qty) + Integer.parseInt(qtyProduct);
            qtyProductTable.clear();
            qtyProductTable.sendKeys(qtyInt.toString());
        }
        Thread.sleep(1000);
        click(buttonUpdateCart);
        Thread.sleep(3000);
    }

    public String getTotalShoppingCart() {

        WebElement tableElement = driver.findElement(shoppingCartTable);
        WebElement bodyElement = tableElement.findElement(By.tagName("tbody"));
        WebElement firstRow = bodyElement.findElements(By.tagName("tr")).get(0);
        WebElement productTotal = firstRow.findElement(By.className("product-subtotal"));

        return productTotal.getText();
    }

    public void clickDigitalDownloadsProduct(){
        click(linkDigitalDownloadsProduct);
    }

    public void createOrder(String country, String state, String city, String address, String zipcode, String phone,
                String cardholderName, String cardNumber, String yearExpirationDate, String cardCode) throws InterruptedException{
        click(linkShoppingCart);
        click(checkTermsOfService);
        click(buttonCheckout);
        Thread.sleep(2000);
        selectFromDropdownCheckout(listCountry, country);
        Thread.sleep(2000);
        selectFromDropdownCheckout(listState, state);
        type(inputCity,city);
        type(inputAddress, address);
        type(inputZipCode,zipcode);
        type(inputPhoneNumber,phone);
        WebElement buttonContinue = getBtnContinue(checkoutBilling,"new-address-next-step-button");
        buttonContinue.click();
        WebElement buttonContinueShipping = getBtnContinue(checkoutShippingMethod,"shipping-method-next-step-button");
        buttonContinueShipping.click();
        click(checkPaymentMethod);
        WebElement buttonContinuePaymentMethod = getBtnContinue(checkoutPaymentMethod,"payment-method-next-step-button");
        buttonContinuePaymentMethod.click();
        type(inputCardholderName,cardholderName);
        type(inputCardNumber, cardNumber);
        selectFromDropdownCheckout(listYearExpirationDate, yearExpirationDate);
        type(inputCardCode,cardCode);
        WebElement buttonContinuePaymentInformation = getBtnContinue(checkoutPaymentInformation,"payment-info-next-step-button");
        buttonContinuePaymentInformation.click();
        //Thread.sleep(1000);
        WebElement buttonConfirm = getBtnContinue(checkoutConfirm,"confirm-order-next-step-button");
        buttonConfirm.click();
    }

    public WebElement getBtnContinue(By sectionElement, String btnClass){
        WebElement element = driver.findElement(sectionElement);
        return  element.findElement(By.className((btnClass)));
    }

    private void selectFromDropdownCheckout(By dropdownLocator, String value) {
        Select dropdown = new Select(driver.findElement(dropdownLocator));
        dropdown.selectByVisibleText(value);
    }

    public String  getSuccessOrderMessageText() {
        WebElement element = driver.findElement(sectionOrderComplete);
        WebElement message = element.findElement(By.className("title"));
        return message.getText();
    }

    public String  getAddProductMessageText() {
        WebElement element = driver.findElement(barNotification);
        WebElement message = element.findElement(By.className("content"));
        return message.getText();
    }
}
