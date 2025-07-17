package workingwithseleniumandconcepts.testclasses;

import java.io.IOException;
import java.util.HashMap;

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

public class DataProviderUsingHashMap extends BaseTest {

@Test(dataProvider="getData")
public void shopping(HashMap<String,String> hmap) throws InterruptedException, IOException {
//Since the data matrix now contains hashmaps, so in each set , instead of passsing three data to the test method , we will pass one hashmap.
//For the first set , the 'hmap' will recieve the HashMap 'map' and from there we will retrieve the values needed
//For the second set, the 'hmap' will recieve the HashMap 'map1'
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(hmap.get("email"),hmap.get("password"));                           //hmap.get(key) will return the value associated with the key.
		ProductCatalogueMen products = productCataloguePage.clickon("https://magento.softwaretestingboard.com/");
		CartPage cartPage = products.clickOnRequiredProduct(hmap.get("product"));
		cartPage.selectFeaturesAndAddToCart();
		Assert.assertTrue(cartPage.checkInCart(hmap.get("product")));
		ShippingPage shippingPage = cartPage.checkOut();
		PlaceOrderPage placeOrderPage = shippingPage.ship(hmap.get("Companyname"),hmap.get("AdressLinea"),hmap.get("AdressLineb"),hmap.get("AdressLinec"),hmap.get("Cityname"),hmap.get("Regionname"),hmap.get("Postcode"),hmap.get("Countryname"),hmap.get("Phonenumber"));
		SuccessfulOrderPage successfulOrderPage = placeOrderPage.placeOrder();
		Assert.assertTrue(successfulOrderPage.successMessageCheck("Thank you for your purchase!"));
}

@DataProvider                               
public Object[][] getData() {
	
	//We will store each row/set of data in a hashmap and then put it in the data matrix.
	
	HashMap<String,String> map = new HashMap<String,String>();                       //This hashmap contains the first set of data
	map.put("email", "Overwhelming@gmail.com");										 // We enter data using map.put(key,value). The login email adress is stored in the HashMap associated with the key 'email'
	map.put("password", "Password@123");
	map.put("product", "Hero Hoodie");
	map.put("Companyname", "NewCompany");
	map.put("AdressLinea", "123");
	map.put("AdressLineb", "456");
	map.put("AdressLinec", "789");
	map.put("Cityname", "Kolkata");
	map.put("Regionname", "Washington");
	map.put("Postcode", "700034");
	map.put("Countryname", "United States");
	map.put("Phonenumber", "8420167352");
	
	
	HashMap<String,String> map1 = new HashMap<String,String>();						//This hashmap contains the second set of data
	map1.put("email", "addas@gmail.com");
	map1.put("password", "Addas#123");
	map1.put("product", "Hero Hoodie");
	map1.put("Companyname", "Comp1");
	map1.put("AdressLinea", "Syr 1");
	map1.put("AdressLineb", "Syr 2");
	map1.put("AdressLinec", "Syr 3");
	map1.put("Cityname", "Key");
	map1.put("Regionname", "Khaimen");
	map1.put("Postcode", "900123");
	map1.put("Countryname", "Syria");
	map1.put("Phonenumber", "8970694321");
	
	return new Object[][] {{map},{map1}};                                            //This is how we pass the hashmaps into the data matrix
	
	}
}
