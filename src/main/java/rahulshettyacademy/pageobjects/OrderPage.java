package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//tbody/tr//td[2]")
	List<WebElement> orderProduct;
	
	By productsInOrder = By.xpath("//tbody/tr//td[2]");
	
	public boolean checkOrders(String productName) {
		
		waitForElementToAppear(productsInOrder);
		List<WebElement> orderProducts = orderProduct;
		Boolean match = orderProducts.stream().anyMatch(product -> 
		product.getText().equalsIgnoreCase(productName));
		
		return match;
	}

}
