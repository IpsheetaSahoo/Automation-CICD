package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderConfirmation extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderConfirmation(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[contains(text(),'Place Order ')]")
	WebElement a_placeOrder;
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement h1_successMsg;
	
	By successText = By.xpath("//h1[@class='hero-primary']");
	
	
	
	public void placeOrder() {
		
		a_placeOrder.click();
	}
	
	public boolean verifyOrderConfirmed(String successMsg) {
		
		waitForElementToAppear(successText);
		boolean success = (h1_successMsg.getText()).equalsIgnoreCase(successMsg);
		return success;
	}

}
