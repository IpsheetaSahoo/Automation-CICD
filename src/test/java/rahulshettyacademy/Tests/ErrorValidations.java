package rahulshettyacademy.Tests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.MyCart;
import rahulshettyacademy.pageobjects.OrderConfirmation;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	
	@Test(groups={"ErrorHandling"}, retryAnalyzer= Retry.class)
	public void loginErrorValidation() {
	
	String productName = "ZARA COAT 3";
	ProductCatalogue productCatalogue = landingPage.loginApplication("reema.nyak@gmail", "Abcd@1234");
	//Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMsg());
	//to fail intentioannly to check for listeners
	Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMsg());
	}
	
	@Test
	public void productErrorValidation() {
	
	String productName = "ZARA COAT 3";

	ProductCatalogue productCatalogue = landingPage.loginApplication("reema.nayak@gmail.com", "Abcd@1234");
	List <WebElement> products = productCatalogue.getProductList();
	productCatalogue.getProductByName(productName);
	MyCart myCart = productCatalogue.addProductToCart(productName);
	productCatalogue.goToCartPage();
	Boolean match = myCart.checkProducts("ZARA COAT 33");
	Assert.assertFalse(match);

	
	}
}
