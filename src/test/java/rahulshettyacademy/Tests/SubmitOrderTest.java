package rahulshettyacademy.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.MyCart;
import rahulshettyacademy.pageobjects.OrderConfirmation;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";

@Test(dataProvider="getData", groups= {"Purchase"})
public void submitOrder(HashMap<String,String> input) throws IOException{
		

		String country = "India";
		String successMsg = "Thankyou for the order.";
		// we are launching the app @BeforeTest in BaseTest class, parent method is also extended
		//and since [public LandingPage landingPage;]is also extended, no longer required to define here
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List <WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("productName"));
		MyCart myCart = productCatalogue.addProductToCart(input.get("productName"));
		productCatalogue.goToCartPage();
		Boolean match = myCart.checkProducts(input.get("productName"));;
		Assert.assertTrue(match);
		//All assertions can stay inside the test case
		//No assertions should be present inside the page objects
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToChacekoutPage();
		checkoutPage.selectCountry(country);
		OrderConfirmation orderConfirmation = new OrderConfirmation(driver);
		orderConfirmation.placeOrder();
		Boolean success = orderConfirmation.verifyOrderConfirmed(successMsg);
		Assert.assertTrue(success);
		
		
	}
	

@Test(dependsOnMethods = {"submitOrder"})
public void OrderHistoryTest() {
	
	ProductCatalogue productCatalogue = landingPage.loginApplication("reema.nayak@gmail.com", "Abcd@1234");
	OrderPage orderPage = productCatalogue.goToOrderPage();
	Assert.assertTrue(orderPage.checkOrders(productName));
}

//we will use data provider annotation to provide data to our test - user email, pasword and productName
//--------------way 1--------------------
//@DataProvider
//public Object[][] getData() {
//	
//	HashMap <String,String> map = new HashMap<String,String>();
//	map.put("email", "reema.nayak@gmail.com");
//	map.put("password","Abcd@1234");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap <String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "reema.nayak12@gmail.com");
//	map1.put("password","Abcde@1234");
//	map1.put("productName", "ADIDAS ORIGINAL");
//	
//	
//	return new Object [][] {{map},{map1}};
//}
//-------------------------------way 2----------------------------

//@DataProvider
//public Object[][] getData() {
//	
//		return new Object [][] {{"reema.nayak@gmail.com", "Abcd@1234","ZARA COAT 3"},{"reema.nayak12@gmail.com","Abcde@1234","ADIDAS ORIGINAL"}};
//}
//---------------------------way 3--------------------------------------


//extent reports
@DataProvider
public Object[][] getData() throws IOException {
	
	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
	return new Object [][] {{data.get(0)},{data.get(1)}};
}


}
