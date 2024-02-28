package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver); // pass this to parent, define constructor in parent - AbstractComponent to catch the child
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement login;
	
	//for error msg while entering wrong user email
	@FindBy(xpath="//div[@role='alert']")
	WebElement errorMsg;
	
	By errorMsgBy = By.xpath("//div[@role='alert']");
	
	//action methods
	
	public ProductCatalogue loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

	}
	
	public String getErrorMsg() {
		waitForElementToAppear(errorMsgBy);
		return errorMsg.getText();
	}
	
	

}
