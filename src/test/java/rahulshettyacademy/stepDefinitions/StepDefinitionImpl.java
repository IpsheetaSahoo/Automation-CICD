package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.MyCart;
import rahulshettyacademy.pageobjects.OrderConfirmation;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public MyCart myCart;
	public CheckoutPage checkoutPage;
	String country = "India";
//	String successMsg = "Thankyou for the order.";
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		
		landingPage = launchApplication();
		
	}
	
	@Given("^logged in with username (.+) and password (.+) $") // regex now tied to method arg
	public void logged_in_with_username_and_password(String username, String password) {
		
		productCatalogue = landingPage.loginApplication(username,password);
		
	}

	@When ("^ add product (.+) to cart $")
	public void add_product_to_cart(String productName) {
		
		List <WebElement> products = productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);
		myCart = productCatalogue.addProductToCart(productName);
		
	}
	
	@And("^ checkout the product(.+) and submit the order $")
	public void checkout_the_product_and_submit_the_order(String productName) {
		
		productCatalogue.goToCartPage();
		Boolean match = myCart.checkProducts(productName);
		Assert.assertTrue(match);
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.goToChacekoutPage();
		checkoutPage.selectCountry(country);
		
	}
	
	
	@Then("verify message {String} is displayed on confirmation page")
	public void verify_thankyou_message_isDisplayed_on_confirmation_page(String successMsg) {
		
		OrderConfirmation orderConfirmation = new OrderConfirmation(driver);
		orderConfirmation.placeOrder();
		Boolean success = orderConfirmation.verifyOrderConfirmed(successMsg);
		Assert.assertTrue(success);
		
	}

}
