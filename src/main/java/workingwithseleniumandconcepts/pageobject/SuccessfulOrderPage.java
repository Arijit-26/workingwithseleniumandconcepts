package workingwithseleniumandconcepts.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

public class SuccessfulOrderPage extends ReusableComponents {

	WebDriver driver;
	public SuccessfulOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}

	String URL = "https://magento.softwaretestingboard.com/checkout/onepage/success/";
	
	@FindBy(xpath="//h1/span")
	WebElement successMessage;
	
	public boolean successMessageCheck(String realMessage) {
		
		waitForURLToMatch(URL);
		String message = successMessage.getText();
		System.out.println(message);
		return message.equalsIgnoreCase(realMessage);
	}
}
