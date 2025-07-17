package workingwithseleniumandconcepts.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

public class Order extends ReusableComponents{

	WebDriver driver;
	
	public Order(WebDriver driver) {
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//td[@class='col name']/strong")
	WebElement prodName;
	
	public String returnProdName() throws InterruptedException {
		
		Thread.sleep(5000);
		return prodName.getText();
	}
	
}
