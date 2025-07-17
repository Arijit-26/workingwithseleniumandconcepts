package workingwithseleniumandconcepts.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

public class MyAccountPage extends ReusableComponents {

	WebDriver driver;
	public MyAccountPage(WebDriver driver) {
	
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	String URL="https://magento.softwaretestingboard.com/customer/account/";
	
	@FindBy(xpath="//a[text()='My Orders']")
	WebElement myOrdersButton;
	

	public MyOrderPage clickOnMyOrders() {
		
		waitForURLToMatch(URL);
		myOrdersButton.click();
		MyOrderPage myOrderPage = new MyOrderPage(driver);
		return myOrderPage;
	}
	
}

