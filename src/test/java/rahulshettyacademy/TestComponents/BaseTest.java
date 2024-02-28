package rahulshettyacademy.TestComponents;


import java.io.File;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		//setting up global properties for the browsers,i.e. whatever browswer the tester wants to use, 
		//that will be invoked
		
		
		//properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.prperties");//this takes file path as para
		prop.load(fis);   //but load method takes fileinput stream as parameter hence,
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser"): prop.getProperty("browser");
		//use ternary operator to check if command line fetteches the browser if not, look in the file
		//String browserName = prop.getProperty("browser");
		
		//system.getproperty --- C:\\Users\\Ipsheeta\\eclipse-workspace\\SeleniumFrameworkDesgn\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.prperties
		//[C:\\Users\\Ipsheeta\\eclipse-workspace\\SeleniumFrameworkDesgn] -- this is project internal path - retrieved via code -- System.getProperty(user.dir)
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			
			WebDriverManager.chromedriver().setup();//driver manager after adding maven dependency
			
			if(browserName.contains("headless")) { // no browser will open as it is headless
			options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); // to avoid flackiness in system nd run in full screen

			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			
			//firefox
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			
			//edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}	
		
		//after initializing the browser, we have to launch the application
	
	@BeforeMethod  // because we have to launch application before any @Test
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver(); // as this method above returns a WebDriver driver
		landingPage = new LandingPage (driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	

public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir" + "//reports//" + testCaseName + ".png"));
	FileUtils.copyFile(source, file);
	return System.getProperty("user.dir" + "//reports//" + testCaseName + ".png");
	
}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException{
		
	//read json to string
	String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);

	//string to hashmap jackson databind through maven dependency
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	
	return data; // {map},{map}
	
	}

	

}
