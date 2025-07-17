package workingwithseleniumandconcepts.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

public class ShippingPage extends ReusableComponents {

	WebDriver driver;

	public ShippingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@name='company']")
	WebElement company;

	@FindBy(xpath = "//input[@name='street[0]']")
	WebElement addressLine1;

	@FindBy(xpath = "//input[@name='street[1]']")
	WebElement adressLine2;

	@FindBy(xpath = "//input[@name='street[2]']")
	WebElement adressLine3;

	@FindBy(xpath = "//input[@name='city']")
	WebElement city;

	@FindBy(xpath = "//select[@name='region_id']")
	WebElement region;
	
	@FindBy(xpath = "//input[@name='region']")
	WebElement regiontext;

	@FindBy(xpath = "//input[@name='postcode']")
	WebElement postcode;

	@FindBy(xpath = "//select[@name='country_id']")
	WebElement country;

	@FindBy(xpath = "//input[@name='telephone']")
	WebElement telephone;

	@FindBy(xpath = "//input[@name='ko_unique_1']")
	WebElement checkBox;

	@FindBy(css = ".continue")
	WebElement continueButton;

	String URL = "https://magento.softwaretestingboard.com/checkout/#shipping";

	// Here I am not using page factory, as there are too many web elements

	public PlaceOrderPage ship(String comapnyName, String line1, String line2, String line3, String cityName,
			String regionName, String postCode, String countryName, String phone) throws InterruptedException {
		waitForURLToMatch(URL);
		Thread.sleep(5000);
		try {
			company.sendKeys(comapnyName);
			addressLine1.sendKeys(line1);
			adressLine2.sendKeys(line2);
			adressLine3.sendKeys(line3);
			city.sendKeys(cityName);
			if(countryName.equalsIgnoreCase("United States"))
			{
			Select regionDropdown = new Select(region);
			regionDropdown.selectByVisibleText(regionName);
			}
			else
			{
				
			}
			postcode.sendKeys(postCode);
			Select countryDropdown = new Select(country);
			countryDropdown.selectByVisibleText(countryName);
			telephone.sendKeys(phone);
			if (countryName.equalsIgnoreCase("United States")) {
				checkBox.click();
				Thread.sleep(5000);
				continueButton.click();
				}
			else {
				regiontext.sendKeys(regionName);
				Thread.sleep(8000);
				continueButton.click();
			}
		} catch (Exception e) {
			if (countryName.equalsIgnoreCase("United States")) {
				checkBox.click();
				Thread.sleep(5000);
				continueButton.click();
				}
			else {
				Thread.sleep(8000);
				continueButton.click();
			}
		}
		PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver);
		return placeOrderPage;
	}

}
