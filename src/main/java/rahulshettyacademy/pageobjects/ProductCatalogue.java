package rahulshettyacademy.pageobjects;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".la-ball-scale-multiple.ng-star-inserted")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.xpath("//button[text()=' Add To Cart']");
	By toastMessage = By.cssSelector("#toast-container");


	//action methods
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public MyCart addProductToCart(String productName) {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissappear(spinner);	
		
		MyCart myCart =  new MyCart(driver);
		return myCart;
		
	}
	

	

}
