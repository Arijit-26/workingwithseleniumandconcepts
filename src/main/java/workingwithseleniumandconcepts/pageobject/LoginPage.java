package workingwithseleniumandconcepts.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import workingwithseleniumandconcepts.reusablecodes.ReusableComponents;

public class LoginPage extends ReusableComponents {

	WebDriver driver;                         //This is the local driver for this particular page object class. This is the page object class for the login page
	
	//Constructor to create object of this class in the main Test class(We have used 'TestClass1' as the main test class) , where we write the test method. The 'driver' passed as the argument to the constructor  should be  the driver created in the test class 'TestClass1'
	public LoginPage(WebDriver driver) {
		super(driver);					   //Here we are assigning the driver from 'TestClass1' to the local driver present in the parent class 'ReusableComponents' by calling its constructor using the 'super' keyword.
		this.driver = driver;              //Here we assign the driver from the 'TestClass1' to this local driver.The local driver will perform all the actions necessary for the login            
		PageFactory.initElements(driver, this);   //PageFactory.initElements(driver, this) takes two arguments as we can see, one argument is driver and the other is the keyword 'this'. This means that the local driver for this page object class(LoginPage) will be used to locate and initialize the web elements present in the login page.
		//This method is called in the constructor so that  all the web elements are initialized,as soon as the 'LoginPage' constructor is called from the 'TestClass1'.
		
	}
	
	//Locating web elements using @FindBy' of PageFactory class. This is called PageFactory design
	
	@FindBy(id="email")				//As argument to @FindBy(), provide the locator of the web element we need. Here we provide the locator 'id="email"'. We can also provide 'xpath', 'css' etc 
	WebElement emailField;         	//Here we have declared 'WebElement emailField'.
	//This is equivalent to 'WebElement emailField = driver.findElement(By.id("email"));'.Writing this shortened code here means at runtime the code 'WebElement emailField = driver.findElement(By.id("email"));' will be used to locate the element
	//The driver to be used here is passed as an argument to the PageFactory.initElements() method in the constructor. 
	//This method is needed in order to specify which driver to use for locating web elements. Because in page factory design we do not mention the driver for locating web element. We just mention the locator and the 'WebELement' object in which the web element is to be stored.
	@FindBy(id="pass")
	WebElement pwdField;
	
	@FindBy(id="send2")
	WebElement loginButton;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement errorMessage;
	
	//This action method loads the url of the login page
	public void goTo()
	{
		driver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/");
	}
	
	
	//This action method is used for providing email address and password and then logging in. The email and password are provided dynamically as arguments to the method
	//The last action method which wll lead to the next page/URL, create and return an object for the next page object class, so that in the main test class we can avoid the overhead of creating the object for the next page object class. Our code in the actual test class will look good
	public ProductCatalogue loginAction(String email,String password) {
		scrollbypixels();
		emailField.sendKeys(email);
		pwdField.sendKeys(password);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	//This action method returns the error message generated if we try to sign in using wrong credentials
	public String wrongCredentials() {
		waitTillVisible(errorMessage);
		return errorMessage.getText();
	}
}
