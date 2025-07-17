package workingwithseleniumandconcepts.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

public class PlaceOrderPage extends ReusableComponents{

	WebDriver driver;
	public PlaceOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	String URL ="https://magento.softwaretestingboard.com/checkout/#payment";
	
	By locator1 = By.cssSelector(".checkout");
	By locator2 = By.cssSelector(".loading-mask");
	
	@FindBy(css=".checkout")
	WebElement checkOutButton;
	
	public SuccessfulOrderPage placeOrder() {
		
		waitForURLToMatch(URL);
		elementIsPresent(locator1);
		elementIsClickable(locator1);
		elementIsInvisible(locator2);
		checkOutButton.click();
		SuccessfulOrderPage successfulOrderPage = new SuccessfulOrderPage(driver);
		return successfulOrderPage;
	}

}
