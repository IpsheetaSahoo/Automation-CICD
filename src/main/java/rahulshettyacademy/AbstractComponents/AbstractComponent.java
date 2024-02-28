package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement btn_cart;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement btn_order;

	public void waitForElementToAppear(By findBy) {
		
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	
	public void waitForElementToDissappear(WebElement ele) {
		
		
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOf(ele));
	
	}
	
	public void goToCartPage() {
		
		btn_cart.click();
	}
	
	public OrderPage goToOrderPage() {
		
		btn_order.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}

}
