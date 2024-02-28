package rahulshettyacademy.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

import java.util.stream.Collectors;



import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();//driver manager after adding maven dependency
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage (driver);
		driver.findElement(By.id("userEmail")).sendKeys("reema.nayak@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abcd@1234");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); //concept of limiting scope of driver
		//if filter returns multiple values, select the first one or else return null
		
		prod.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		
		
		//add explicit waits
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".la-ball-scale-multiple.ng-star-inserted")));
		
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.cart ul h3"));
		Boolean match = cartProducts.stream().anyMatch(product -> 
		product.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector("li.totalRow button.btn-primary")).click();
//		//div.form-group
//		driver.findElement(By.cssSelector("li.totalRow button.btn-primary")).sendKeys("Ind");)
//		List<WebElement> countries = driver.findElements(By.cssSelector("section.ta-results span"));
//		WebElement countrySelect = countries.stream().filter(country -> country.getText().equals(" India")).findFirst().orElse(null);
//		
//		countrySelect.click();
		
		
		Actions a = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results span")));
		driver.findElement(By.xpath("(//button//span)[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Place Order ')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='hero-primary']")));
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText().equalsIgnoreCase("Thankyou for the order."));
		
		
		

	}

}
