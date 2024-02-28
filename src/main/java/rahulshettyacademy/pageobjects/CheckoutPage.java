package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="li.totalRow button.btn-primary")
	WebElement btn_checkout;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement input_selectCountry;
	
	@FindBy(xpath="(//button//span)[2]")
	WebElement btnCountry;
	
	
	By selectTheCountry = By.cssSelector("input[placeholder='Select Country']");
	By selectedCountry = By.cssSelector("section.ta-results span");
	
	
	
	public void goToChacekoutPage() {
		
		btn_checkout.click();
	}
	
	public void selectCountry(String country) {
		
		
		waitForElementToAppear(selectTheCountry);
		Actions a = new Actions(driver);
		a.sendKeys(input_selectCountry, country).build().perform();
		
		waitForElementToAppear(selectedCountry);
		btnCountry.click();
		
	}
	

}
