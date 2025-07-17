package workingwithseleniumandconcepts.testclasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import workingwithseleniumandconcepts.basetestclass.BaseTest;
import workingwithseleniumandconcepts.pageobject.CartPage;
import workingwithseleniumandconcepts.pageobject.PlaceOrderPage;
import workingwithseleniumandconcepts.pageobject.ProductCatalogue;
import workingwithseleniumandconcepts.pageobject.ProductCatalogueMen;
import workingwithseleniumandconcepts.pageobject.ShippingPage;
import workingwithseleniumandconcepts.pageobject.SuccessfulOrderPage;

public class DataProviderForShopping extends BaseTest {

	@Test(dataProvider="getData")                //The two sets of data are fed to the test method using (dataProvider) attribute. So the test method will run 2 times
	
	public void shopping(String email,String password,String product,String comapnyName,String line1,String line2, String line3, String cityName, String regionName, String postCode, String countryName,String phone) throws InterruptedException, IOException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(email,password);
		ProductCatalogueMen products = productCataloguePage.clickon("https://magento.softwaretestingboard.com/");
		CartPage cartPage = products.clickOnRequiredProduct(product);
		cartPage.selectFeaturesAndAddToCart();
		Assert.assertTrue(cartPage.checkInCart(product));
		ShippingPage shippingPage = cartPage.checkOut();
		PlaceOrderPage placeOrderPage = shippingPage.ship(comapnyName,line1,line2,line3,cityName,regionName,postCode,countryName,phone);
		SuccessfulOrderPage successfulOrderPage = placeOrderPage.placeOrder();
		Assert.assertTrue(successfulOrderPage.successMessageCheck("Thank you for your purchase!"));
}
	@DataProvider                               //Using this method with @DataProvider attribute, we feed sets of data into the test method
	public Object[][] getData() {
		
		return new Object[][] {{"Overwhelming@gmail.com","Password@123","Hero Hoodie","NewCompany","123","456","789","Kolkata","Washington","700034","United States","8420167352"
},{"Rbhai@gmail.com","Rbhai#123","Hero Hoodie",
			"Comp1","Turk 1","Turk 2","Turk 3","Kazak","stan","700123","Turkmenistan","8970654321"}};
		
		//Each set contains three data-> email , password, product we want to buy
		//So there are 2 sets and 3 data in each sets , so the data matrix will be a 2x3 matrix.
		//The data type is 'Object'. This is a primitive data type.Our data can be a mixture of int, string. So we cannot declare the data matrix as int or string. The data type for the matrix is 'Object', so that one data in a set can be int , another data in the same set can be string, like that
				
	}
}
