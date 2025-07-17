package workingwithseleniumandconcepts.testclasses;


import org.testng.Assert;
import org.testng.annotations.Test;

import workingwithseleniumandconcepts.basetestclass.BaseTest;
import workingwithseleniumandconcepts.pageobject.LoginPage;

public class TestFail extends BaseTest {
	@Test(groups= {"Wrong"})
	public void loginUsingWrongPassword() {           
		
		String error = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		newLoginPage.loginAction("Overwhelming@gmail.com","password@123");
		String message = newLoginPage.wrongCredentials();
		Assert.assertFalse(message.contains(error));
	}

	@Test(groups={"Wrong"})
	public void loginUsingWrongEmail() {
		
		String error = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
		newLoginPage.loginAction("Underwhelming@gmail.com","password@123");
		String message = newLoginPage.wrongCredentials();
		Assert.assertTrue(message.contains(error));
	}	
}
