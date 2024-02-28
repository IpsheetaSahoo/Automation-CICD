package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class MyCart extends AbstractComponent{
	
	WebDriver driver;
	
	public MyCart(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	

	
	@FindBy(css="div.cart ul h3")
	List<WebElement> cartProduct;
	
	By productsInCart = By.cssSelector("div.cart ul h3");
	
	
	
	
	//action methods

	
	public boolean checkProducts(String productName) {
		
		waitForElementToAppear(productsInCart);
		List<WebElement> cartProducts = cartProduct;
		Boolean match = cartProducts.stream().anyMatch(product -> 
		product.getText().equalsIgnoreCase(productName));
		
		return match;
		
		
		
	}

}
