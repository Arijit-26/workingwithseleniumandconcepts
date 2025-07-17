package workingwithseleniumandconcepts.basetestclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import workingwithseleniumandconcepts.pageobject.LoginPage;

public class BaseTest {
	
     public WebDriver driver; //Declare the WebDriver object outside all methods inside the class. The object should be public
     public LoginPage newLoginPage;    //Declare a public 'LoginPage' object, so that once created , it can be directly used in the child class, without returning it

	//This method initializes the browser and maximizes it 
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();                   //'Properties' class will help us to parse the global properties file and retrieve the value of a key
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\workingwithseleniumandconcepts\\globalproperty\\GlobalData.properties"); // Using the FileInputStream class we convert our global properties file to a stream. As argument to the constructor 'FileInputStream()' we have provided the whole path to our global properties file
		prop.load(file);                        //While trying to load the file, the 'prop' object of the 'Properties' class wants the file as a stream. So in the previous step we converted our 'GlobalData.properties' into stream
		
		String browserName = prop.getProperty("browser");//prop.getProperty(key) returns the value associated with the key we have provided as an argument. Here we have given prop.getProperty("browser"), i.e we want the value associated with the key 'browser'
		System.out.println(browserName);
		if(browserName.contains("chrome"))
		{
			/*ChromeOptions cpo = new ChromeOptions();
			cpo.addExtensions(new File(System.getProperty("user.dir")+"\\AdBlock — block ads across the web - Chrome Web Store 6.24.0.0.crx"));
			//cpo.addArguments("load-extension="+System.getProperty("user.dir")+"\\AdBlock — block ads across the web - Chrome Web Store 6.24.0.0.crx");*/
			this.driver = new ChromeDriver();
			/*driver.get("https://chromewebstore.google.com/detail/adblock-%E2%80%94-block-ads-acros/gighmmpiobklfepjocnamgkkbiglidom?hl=en-GB&utm_source=ext_sidebar");
			driver.findElement(By.xpath("//span[text()='Add to Chrome']")).click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.switchTo().alert().accept();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));*/
	 		}
		else if(browserName.contains("firefox"))
		{
			this.driver = new FirefoxDriver();
		}
		
		else if(browserName.contains("edge"))
		{
			this.driver = new EdgeDriver();
		}
		
		this.driver.manage().window().maximize(); //This can be written outside the if else block, as this is common for all kinds of browsers
		return this.driver;                       //The driver should be returned . If we declare a web driver in the main test class, and call this method
	}
	
	
	//We write this method to read the json file containing data and store them in hashmap, inside the 'BaseTest'. We do not write this method in a new class, so that this method can be called from the test class without the hassle of creating a new object
	public List<HashMap<String,String>> readJsonFile(String filePath) throws IOException {  
		
		File file1 = new File(filePath);
		String jsContent= FileUtils.readFileToString(file1,StandardCharsets.UTF_8);//readFileToString(String Filepath, Charset char) is used to read and convert the json file into a string. The first parameter is the path to the json file, and the second parameter is the encoding format in which we want to wrute the string. In our case we shall use UTF8.
		ObjectMapper mapper = new ObjectMapper();       //ObjectMapper class is used to to read a string and convert it to hashmap
		List<HashMap<String,String>> data= mapper.readValue(jsContent, new TypeReference<List<HashMap<String,String>>>(){}); //readValue() methid of ObjectMapper actually reads the string and converts it to hashmap. It takes two parameters. The first parameter is the string that was created by converting the json file, and the second prameter tells how do we want to convert the string. In our case we have converted it and stored it in a list of hashmaps.(Since there are two sets of data in the json file, so once converted there will be two hashmaps , that is why we store it in a list of hashmaps)
		return data;   //We return the list of hashmaps
		
	}
	
	//This methood is specifically written to take screenshot where the test fails and attach the screenshot to our extent report
	//We need to pass the driver of the test that failed as an argument to this method, because control of that driver is present at the step where the test failed and hence take the screenshot where the test method failed
	public String getScreenShot(String testName,WebDriver driver) throws IOException {
		TakesScreenshot ss= (TakesScreenshot) driver;                 //casting our driver with 'TakesScreenshot' class
		File source =ss.getScreenshotAs(OutputType.FILE);             //Screenshot is taken and saved as a 'File' . 
		File destinationPath = new File(System.getProperty("user.dir")+"//ScreenShots//"+testName+".png");  //When we want to take a path which is in string format(e.g. 'src//main//java//test//image.png' is a path in string format) and convert it to a File format , we use 'new File(path in string format)'. Here we convert the path to the destination directory(Which is in string format) in our pc to a 'File' format .
		FileUtils.copyFile(source, destinationPath);                     //this method copyFile() takes the source(in File format) and the path to the destination directory(in File format) as arguments. That is why we converted the path from string format to file format in the previous step.
		return System.getProperty("user.dir")+"//ScreenShots//"+testName+".png";   //This method returns the path to destination directory in string format
	} 
	
	//This method will call the initializeDriver() method to initialize driver and launch browser and then create a new 'LoginPage' object, called 'newloginPage'(Which is declared earlier in this class). Then it goes to the login page
	@BeforeMethod(alwaysRun=true)                             //We are providing '@BeforeMethod' annotation to this method, so that before any method present in the child classes, this method will be executed. Because whatever be the test on the shopping application we need these actions to take place in the beginning(initializing driver, creating LoginPage object and going to the login page)
	public void launchBrowser() throws IOException {                
		
		WebDriver driver1= initializeDriver();
		newLoginPage = new LoginPage(driver1);
		newLoginPage.goTo();
		//Since the object newLoginPage is public , so we do not need to return it to the test class. It can be diretly used in the test class
	}	
	
	 @DataProvider
	    public Object[][] dataProvidingThroughExcel() throws IOException {
	    	
	    	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\UserIds.xlsx");
	    	XSSFWorkbook workBook = new XSSFWorkbook(fis);
	    	XSSFSheet sheet = workBook.getSheetAt(0);
	    	int rowNumber= sheet.getPhysicalNumberOfRows();                               //Getting the number of rows in the sheet
	    	Row r = sheet.getRow(0);												      //This another way of getting into a row 
	    	int cellNumber= r.getPhysicalNumberOfCells();								  //Getting the number of cells in a row. Number of columns = number of cells in a row						   
	    	Object arr[][] = new Object[rowNumber-1][cellNumber];						  //Number of rows in the data array will be rowNumber-1 beacuse the first row in the excel is not a set of data. It contains headers
	    	Iterator<Row> row = sheet.iterator();
	    	//Row r1=row.next();
	    	row.next();
	    	//Getting into each row and fetching the data from each of its cells and storing in the multidimensional array
	    	for(int i = 1;i<rowNumber;i++)
	    	{
	    		Row r2 = row.next();
	    		Iterator<Cell> cell = r2.cellIterator();
	    		for(int j=0;j<cellNumber;j++)
	    		{
	    			Cell cv = cell.next();
					if(cv.getCellType()==CellType.STRING)         
					{
						arr[i-1][j] = cv.getStringCellValue();         
					}
					else {
						arr[i-1][j] = NumberToTextConverter.toText(cv.getNumericCellValue());         
					}
	    		}
	    	}
	    	
	    	return arr;                                                 //Feeding the multidimensional array of data into the test method from the dataprovider method
	    }
	
	 @DataProvider//(parallel = true)
	    public Object[][] dataProvidingForParallel() throws IOException {
	    	
	    	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\UserIds.xlsx");
	    	XSSFWorkbook workBook = new XSSFWorkbook(fis);
	    	XSSFSheet sheet = workBook.getSheetAt(0);
	    	int rowNumber= sheet.getPhysicalNumberOfRows();                               //Getting the number of rows in the sheet
	    	Row r = sheet.getRow(0);												      //This another way of getting into a row 
	    	int cellNumber= r.getPhysicalNumberOfCells();								  //Getting the number of cells in a row. Number of columns = number of cells in a row						   
	    	Object array[][] = new Object[rowNumber-1][cellNumber];						  //Number of rows in the data array will be rowNumber-1 beacuse the first row in the excel is not a set of data. It contains headers
	    	Iterator<Row> row = sheet.iterator();
	    	//Row r1=row.next();
	    	row.next();
	    	//Getting into each row and fetching the data from each of its cells and storing in the multidimensional array
	    	for(int i = 1;i<rowNumber;i++)
	    	{
	    		Row r2 = row.next();
	    		Iterator<Cell> cell = r2.cellIterator();
	    		for(int j=0;j<cellNumber;j++)
	    		{
	    			Cell cv = cell.next();
					if(cv.getCellType()==CellType.STRING)         
					{
						array[i-1][j] = cv.getStringCellValue();         
					}
					else {
						array[i-1][j] = NumberToTextConverter.toText(cv.getNumericCellValue());         
					}
	    		}
	    	}
	    	
	    	return array;                                                 //Feeding the multidimensional array of data into the test method from the dataprovider method
	    }
	

	
	@AfterMethod(alwaysRun=true)                            
	public void tearDown() {
		driver.close();
	}
	//The method launchBrowser() and tearDown() needs to run before every method and after every method respectively. But in 'testnggroup.xml' we have mentioned that we need to run methods belonging to group 'Wrong'. In that case this two methods will not run and if this two methods don't run(Especially 'launchBrowser()'), no method in the test suite will be executed. So we add 'alwaysRun=true' to these two methods. This will ensure that these two methods are executed even if they are not part of the group , whose methods we want to execute
	//We could have annotated 'initializeDriver()' with @BeforeMethod and then we could have continued to use the driver in the test class,but that would include lot on instructions in the test class. So , instead we created a method 'launchBrowser()' and added the '@BeforeMethod' annotation to it
}
