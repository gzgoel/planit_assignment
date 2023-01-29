package JupitorToys.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import JupitorToys.abstractcomponents.AbstractComponents;

public class ProductSelectionPage extends AbstractComponents {

	WebDriver driver;
	public ProductSelectionPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".products")
	List<WebElement> products;
	

	@FindBy(css=".icon-shopping-cart")
	WebElement carticon;
	
	By productsby=By.cssSelector(".products");
	public  List<WebElement> getproductslist() {
		
		waitforelementtoappear(productsby);
		return products;
	}
	
	public void addproduct(String product) {
		
		List<WebElement> products=driver.findElements(By.cssSelector(".product"));
		System.out.println(products.size());
		WebElement myproduct= products.stream().filter(product1->product1.findElement(By.cssSelector(".product-title")).getText().equals(product)).findFirst().orElse(null);
		myproduct.findElement(By.cssSelector(".btn-success:last-of-type")).click();
		
	}
	
	
	public void gotocart() {
		carticon.click();
	}
	

}
