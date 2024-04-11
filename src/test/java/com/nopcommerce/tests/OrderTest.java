package com.nopcommerce.tests;

import org.junit.Assert;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest{

    @Test (groups = "Regression")
    public void orderAddProducts(){
        setUp();
        homePage.goToLoginOption();
        loginPage.fillOutLoginForm();
        loginPage.clickLogInButton();
        orderPage.clickBooksProduct();
        orderPage.clickAddCart("3","First Prize Pies");

        Assert.assertEquals("The product has been added to your shopping cart",
                orderPage.getAddProductMessageText());

        tearDown();
    }

    @Test (groups = "Regression")
    public void orderRemoveProducts() throws InterruptedException{
        setUp();
        homePage.goToLoginOption();
        loginPage.fillOutLoginForm();
        loginPage.clickLogInButton();
        orderPage.clickBooksProduct();
        Thread.sleep(3000);
        // obtenemos la cantidad en texto despues de agregar productos
        String cartText = orderPage.getCartQuantityText();
        orderPage.clickRemoveProducts();

        // obtenemos la cantidad en texto despues de eliminar productos
        String newCartText = orderPage.getCartQuantityText();

        // si es diferente es por que se ha eliminado un producto
        Assert.assertNotEquals(cartText, newCartText);

        tearDown();
    }


    @Test (groups = {"Functional","Integration"})
    public void orderUpdateQuantityProducts() throws InterruptedException{
        setUp();
        homePage.goToLoginOption();
        loginPage.fillOutLoginForm();
        loginPage.clickLogInButton();
        orderPage.clickBooksProduct();
        Thread.sleep(3000);
        orderPage.clickGoShoppingCart();
        String productTotal = orderPage.getTotalShoppingCart();
        Thread.sleep(1000);
        orderPage.clickUpdateShoppingCart("2");

        String newProductTotal = orderPage.getTotalShoppingCart();

        Assert.assertNotEquals(productTotal, newProductTotal);

        tearDown();
    }

    @Test (groups = "Regression")
    public void createNewOrderWithThreeProducts() throws InterruptedException {
        setUp();
        homePage.goToLoginOption();
        loginPage.fillOutLoginForm();
        loginPage.clickLogInButton();
        orderPage.clickBooksProduct();
        orderPage.clickAddCart("5", "Pride and Prejudice");
        Thread.sleep(6000);
        orderPage.clickBooksProduct();
        orderPage.clickAddCart("3", "First Prize Pies");
        Thread.sleep(6000);
        orderPage.clickDigitalDownloadsProduct();
        orderPage.clickAddCart("2", "If You Wait (donation)");
        Thread.sleep(6000);
        orderPage.createOrder("United States","California","Sacramento","Test 123","10011",
                "20202020","Test name","4111111111111111","2026","123");

        Assert.assertEquals("Your order has been successfully processed!",
                orderPage.getSuccessOrderMessageText());

        tearDown();
    }
}
