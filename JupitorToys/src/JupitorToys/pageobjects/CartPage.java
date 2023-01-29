package JupitorToys.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import JupitorToys.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
	
	
	
	
	public String verifyproductquantity(String Productname) {
		List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cart-item"));
		String value = null;
		for(WebElement we: CartProducts) {
			System.out.println(we.findElement(By.cssSelector(".ng-valid-number")).getAttribute("value"));
			String cartitem=we.findElement(By.cssSelector(".ng-binding:first-of-type")).getText();
			if(cartitem.equalsIgnoreCase(Productname)) {
				value=we.findElement(By.cssSelector(".ng-valid-number")).getAttribute("value");
				
			
			}
			
			
		}
		return value;
	}
	
	
	public String verifyproductprice(String Productname) {
		List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cart-item"));
		String value = null;
		for(WebElement we: CartProducts) {
			System.out.println(we.findElement(By.cssSelector(".ng-valid-number")).getAttribute("value"));
			String cartitem=we.findElement(By.cssSelector(".ng-binding:first-of-type")).getText();
			if(cartitem.equalsIgnoreCase(Productname)) {
				value=we.findElement(By.cssSelector(".ng-binding:nth-child(2)")).getText();
			
			}
		}
		return value;
	}
	
	
	public String verifyproductsubtotal(String Productname) {
		List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cart-item"));
		String value = null;
		for(WebElement we: CartProducts) {
			System.out.println(we.findElement(By.cssSelector(".ng-valid-number")).getAttribute("value"));
			String cartitem=we.findElement(By.cssSelector(".ng-binding:first-of-type")).getText();
			if(cartitem.equalsIgnoreCase(Productname)) {
				value=we.findElement(By.cssSelector(".ng-binding:nth-child(4)")).getText();
			
			}
		}
		return value;
	}

	
	public float verifysubtotal(float fbprice, float fbquantity) {
			return Math.round((fbprice)*(fbquantity));
	}






}
