package workingwithseleniumandconcepts.testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import workingwithseleniumandconcepts.basetestclass.BaseTest;
import workingwithseleniumandconcepts.pageobject.CartPage;
import workingwithseleniumandconcepts.pageobject.MyAccountPage;
import workingwithseleniumandconcepts.pageobject.MyOrderPage;
import workingwithseleniumandconcepts.pageobject.Order;
import workingwithseleniumandconcepts.pageobject.PlaceOrderPage;
import workingwithseleniumandconcepts.pageobject.ProductCatalogue;
import workingwithseleniumandconcepts.pageobject.ProductCatalogueMen;
import workingwithseleniumandconcepts.pageobject.ShippingPage;
import workingwithseleniumandconcepts.pageobject.SuccessfulOrderPage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClassForLoadTesting extends BaseTest{
	
	@Test(dataProvider="dataProvidingForParallel",threadPoolSize = 3, invocationCount = 3)
	public void shopping(String username,String password, String product,String comapnyName,String line1,String line2, String line3, String cityName, String regionName, String postCode, String countryName,String phone) throws InterruptedException, IOException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(username,password);
		ProductCatalogueMen products = productCataloguePage.clickon("https://magento.softwaretestingboard.com/");
		CartPage cartPage = products.clickOnRequiredProduct(product);
		cartPage.selectFeaturesAndAddToCart();
		Assert.assertTrue(cartPage.checkInCart(product));
		ShippingPage shippingPage = cartPage.checkOut();
		PlaceOrderPage placeOrderPage = shippingPage.ship(comapnyName,line1,line2,line3,cityName,regionName,postCode,countryName,phone);
		SuccessfulOrderPage successfulOrderPage = placeOrderPage.placeOrder();
		Assert.assertTrue(successfulOrderPage.successMessageCheck("Thank you for your purchase!"));
		
	}
	
	@Test(dependsOnMethods = { "shopping" },dataProvider="dataProvidingForParallel",threadPoolSize = 3, invocationCount = 3)                                                 //The test 'checkMyOrders' depends on the method 'shopping()'. So when we run this test, first 'shopping()' will be executed and only then will 'checkMyOrders()' be executed, because 'checkMyOrders()' won't run until 'shopping()' is completed
	
	public void checkMyOrders(String username,String password, String product,String comapnyName,String line1,String line2, String line3, String cityName, String regionName, String postCode, String countryName,String phone) throws InterruptedException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(username,password);
		MyAccountPage myAccountPage = productCataloguePage.clickOnMyAccount();
		MyOrderPage myOrderPage=myAccountPage.clickOnMyOrders();
		Order order = myOrderPage.checkOrder();
		String productName = order.returnProdName();
		Assert.assertTrue(productName.equalsIgnoreCase(product));
		
	}
}