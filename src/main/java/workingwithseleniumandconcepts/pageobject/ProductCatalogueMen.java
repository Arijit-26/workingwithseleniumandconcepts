package workingwithseleniumandconcepts.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

public class ProductCatalogueMen extends ReusableComponents{

	WebDriver driver;
	
	public  ProductCatalogueMen(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".block-products-list")          //@FindBy(css=" ") means we are using the locator 'cssSelector'
	WebElement prodBlock;
	
	@FindBy(xpath="//strong[@class='product-item-name']")     /*Equivalent to 'List<WebElement> productName = driver.findElements(By.xpath("//strong[@class='product-item-name']"));'*/
	List<WebElement> productName; 													
	
	@FindBy(css=".tocart")
	List<WebElement> atcButtons;
	
	
	public CartPage clickOnRequiredProduct(String prodName) {
		scroll(prodBlock);                                           //javascript scroll is used as a reusable component
		for(int i = 0;i<productName.size();i++)
		{
			
			WebElement product =productName.get(i).findElement(By.tagName("a"));     //  Here inside the action class we are using a limited driver. On limited driver we cannot use page factory design. So we have to use the whole code 'productName.get(i).findElement(By.tagName("a"))', instead of using the '@FindBy()'
			if(product.getText().equalsIgnoreCase(prodName))
			{
				Actions a = new Actions(driver);
				a.moveToElement(product).build().perform();
				jsClick(atcButtons.get(i));                          //javascript click is used as a reusable component
				break;
			}
		}
		
	CartPage cart = new CartPage(driver);
	return cart;
	}
}
