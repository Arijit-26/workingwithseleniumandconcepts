package workingwithseleniumandconcepts.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

public class CartPage extends ReusableComponents {

WebDriver driver;
	
	public  CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	String URL="https://magento.softwaretestingboard.com/hero-hoodie.html";
	
	@FindBy(xpath="//div[@class='swatch-option text']")
	List<WebElement> sizes;
	
	@FindBy(css=".swatch-option.color")
	List<WebElement> colors;
	
	@FindBy(id="product-addtocart-button")
	WebElement addToCartButton;
	
	@FindBy(css=".action.showcart")
	WebElement showCart;
	
	@FindBy(css=".block-minicart")
	WebElement blockMinicart;
	
	@FindBy(xpath="//strong[@class='product-item-name']/a")
	WebElement itemNameInCart;
	
	@FindBy(id="top-cart-btn-checkout")
	WebElement checkoutButton;
	
	public void selectFeaturesAndAddToCart() throws InterruptedException
	{
		scrollbypixels();
		waitForURLToMatch(URL);
		for(WebElement size:sizes)
		{
			if(size.getText().equalsIgnoreCase("l"))
			{
				size.click();
				break;
			}
		}
		
		for(WebElement color:colors)
		{
			if(color.getAttribute("option-label").equalsIgnoreCase("green"))
			{
				color.click();
				break;
			}
		}
		
		addToCartButton.click();
		Thread.sleep(8000);
	}
	
	public boolean checkInCart(String prodName)
	{
		showCart.click();
		waitTillVisible(blockMinicart);
		String productName =  itemNameInCart.getText();
		return productName.equalsIgnoreCase(prodName);
	}
	
	public ShippingPage checkOut()
	{
		checkoutButton.click();
		ShippingPage shippingPage = new ShippingPage(driver);
		return shippingPage;
	}
	
	
	
}
