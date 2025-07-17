package workingwithseleniumandconcepts.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

//This page object class is for the page 'https://magento.softwaretestingboard.com/'
public class ProductCatalogue extends ReusableComponents {

	WebDriver driver;
	
	//Constructor for 'ProductCatalogue' class
	public ProductCatalogue(WebDriver driver) {
		super(driver);          //Calling the constructor of the parent class 'ReusableComponents'. The driver from 'TestClass1' will be assigned to the local driver of the class 'ReusableComponents'               
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#ui-id-5")
	WebElement productPage;
	
	@FindBy(xpath="//button[@data-action='customer-menu-toggle']")
	WebElement customerMenuToggle;
	
	@FindBy(css=".header.links:nth-child(1)")
	WebElement headerLinks;
	
	@FindBy(xpath="//a[text()='My Account']")
	WebElement myAccountButton;
	
	//This action class will lead to the page for men's product catalogue for which we have the page object class 'ProductCatalogueMen'. So in this method we create and return an object for 'ProductCatalogueMen'
	public ProductCatalogueMen clickon(String URL)
	{
		
		waitForURLToMatch(URL);		//Using the method 'waitForURLToMatch()' from 'ReusableComponents' class
		scrollbypixels();
		productPage.click();
		ProductCatalogueMen productCatalogueMen = new ProductCatalogueMen(driver);
		return productCatalogueMen;
	}
	
	public MyAccountPage clickOnMyAccount() {
		
		customerMenuToggle.click();
		waitTillVisible(headerLinks);
		myAccountButton.click();
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		return myAccountPage;
	}
}
