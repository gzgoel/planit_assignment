package JupitorToys.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import JupitorToys.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//a[normalize-space()='Contact']")
	WebElement contactlink;
	
	@FindBy(css=".btn-large")
	WebElement startShoppingbtn;
	
	@FindBy(css=".brand")
	WebElement Brand;
	
	public void gotoContact() {
		contactlink.click();
	}
	
	public void gotohomepage() {
		Brand.click();
	}
	
	public void startshopping() {
		startShoppingbtn.click();
	}
}
