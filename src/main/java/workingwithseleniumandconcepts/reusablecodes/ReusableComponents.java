package workingwithseleniumandconcepts.reusablecodes;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This is the parent class that contains all reusable codes like waits and switching window handles.
//This class contains all the reusable codes as methods, which are to be used by the page object classes wherever required
//All page object classes need to inherit this class
public class ReusableComponents {
		WebDriver driver;
		
		
		
		
		//The constructor for 'ReusableComponents' 
		//The first step in the constructors of the page object classes inheriting this 'ReusableComponenents' class, is to call the constructor of this class using the 'super' keyword
		public ReusableComponents(WebDriver driver)
		{
			this.driver = driver;
		}
		
		//This method will be used to reuse the code wait.until(ExpectedConditions.urlMatches(URL));
		public void waitForURLToMatch(String URL)
		{
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
			w.until(ExpectedConditions.urlMatches(URL));
		}
		
		//This method takes locator as an argument. For this we use the 'By' class. Object of a 'By' class can store a locator
		//e.g By locator =  By.xpath();
		public void waitForVisibilityOfElement(By locator) {
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
			w.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		//This method will reuse the code for javascript scroll
		public void scroll(WebElement e)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();",e);
		}
		
		public void scrollbypixels()
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)","");
		}
		
		//This method will reuse the code for javascript click
		public void jsClick(WebElement e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();",e);
		}
		
		public void waitTillVisible(WebElement e) {
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
			w.until(ExpectedConditions.visibilityOf(e));
		}
		
		public void elementIsPresent(By locator) {
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
			w.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		
		public void elementIsClickable(By locator) {
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
			w.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		public void elementIsInvisible(By locator) {
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
			w.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}
		
		public void readFromExcel(String path) {
			
		}
}
