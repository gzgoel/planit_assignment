import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import JupitorToys.pageobjects.CartPage;
import JupitorToys.pageobjects.ContactForm;
import JupitorToys.pageobjects.LandingPage;
import JupitorToys.pageobjects.ProductSelectionPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class JupitorAppTest {



	@Test(invocationCount=1)
	public void TestCase1() {
		
		
		//From the home page go to contact page
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		
		LandingPage landingpage=new LandingPage(driver);
		landingpage.gotoContact();
		
		//Click submit button
		ContactForm cf=new ContactForm(driver);
		By submitbtn = By.cssSelector(".btn-contact ");
		cf.waitforelementtoappear(submitbtn);
		cf.submitbtnclick();
		
		
		//Validate errors
		//Validating for name error message
		String Forenameerroemessage = cf.validatenameerror();
		cf.verifysuccessfulmessage(Forenameerroemessage);

		//Validating Email error message
		String emailerroemessage=cf.validateemailerrorm();
		cf.verifysuccessfulmessage(emailerroemessage);
		
		//Validating Message error message
		String Messageerroemessage=cf.messageerror();
		cf.verifysuccessfulmessage(Messageerroemessage);
		
		//Populate mandatory fields

		cf.fillform("JohnDoe", "John.doe@Planit.com", "Hey there");
		By feedbackbar=By.cssSelector(".popup");
		cf.waituntilelementinvisibility(feedbackbar);
	
		//Validate errors are gone
		
			
		cf.verifyunsuccesfulmessagegone("Forename is required");
		cf.verifyunsuccesfulmessagegone("Email is required");
		cf.verifyunsuccesfulmessagegone("Message is required");

		//validating successful message
		cf.verifysuccessfulmessage("we appreciate your feedback");
	
		
		//******************************************
		//starting to shop
		landingpage.gotohomepage();
		landingpage.startshopping();
	

		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products")));
		By myproducts=By.cssSelector(".products");
		ProductSelectionPage PS=new ProductSelectionPage(driver);
		PS.waitforelementtoappear(myproducts);
		PS.addproduct("Funny Cow");
		PS.addproduct("Funny Cow");

		
		
		//move to cart
		driver.findElement(By.cssSelector(".icon-shopping-cart")).click();
		
		
		CartPage CP=new CartPage(driver);
		By allcartitem= By.cssSelector(".cart-item");
		CP.waitforelementtoappear(allcartitem);
		
		
		//verify if correct quantity was added
		//List<WebElement> CartProducts = driver.findElements(By.cssSelector(".cart-item"));
	    System.out.println("product quantity is "+CP.verifyproductquantity("Funny Cow"));	
		

			
		}
	
	@Test(invocationCount=5)
	public void TestCase2() {
		
		//From the home page go to contact page
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		LandingPage LP=new LandingPage(driver);
		LP.gotoContact();
		
		
		//Populate mandatory fields
		//Click submit button
		ContactForm CF=new ContactForm(driver);
		By submitbtn = By.cssSelector(".btn-contact ");
		CF.waitforelementtoappear(submitbtn);
		CF.fillform("TestCase2", "TestCase2@TC2.com", "This is TC2");
		CF.verifysuccessfulmessage("we appreciate your feedback");
		
		
		
	}
		
	@Test(invocationCount=0)
	public void TestCase3() {
		
		//From the home page go to contact page
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		LandingPage LP=new LandingPage(driver);
		LP.gotohomepage();

		LP.startshopping();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products")));
		By myproducts=By.cssSelector(".products");
		ProductSelectionPage PS=new ProductSelectionPage(driver);
		PS.waitforelementtoappear(myproducts);
		
		//Click buy button 2 times on “Funny Cow”
		PS.addproduct("Funny Cow");
		PS.addproduct("Funny Cow");
		
		//Click buy button 1 time on “Fluffy Bunny”
		PS.addproduct("Fluffy Bunny");
		
		//goto cart page
		PS.gotocart();
		
		
		//Verify the items are in the cart
		CartPage CP=new CartPage(driver);
		By allcartitem= By.cssSelector(".cart-item");
		CP.waitforelementtoappear(allcartitem);
		
		Assert.assertEquals(CP.verifyproductquantity("Fluffy Bunny"), "1");
		Assert.assertEquals(CP.verifyproductquantity("Funny Cow"), "2");

		
		
	}
	
	@Test(invocationCount=1)
	public void TestCase4() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		LandingPage LP=new LandingPage(driver);
		LP.gotohomepage();
		LP.startshopping();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products")));
		By myproducts=By.cssSelector(".products");
		ProductSelectionPage PS=new ProductSelectionPage(driver);
		PS.waitforelementtoappear(myproducts);
		
		//Click buy button 2 times on “Stuffed Frog”
		PS.addproduct("Stuffed Frog");
		PS.addproduct("Stuffed Frog");
		
		PS.waitforelementtoappear(myproducts);
		
		//Click by button 5 times to buy Fluffy Bunny
		PS.addproduct("Fluffy Bunny");
		PS.addproduct("Fluffy Bunny");
		PS.addproduct("Fluffy Bunny");
		PS.addproduct("Fluffy Bunny");
		PS.addproduct("Fluffy Bunny");
		
		PS.waitforelementtoappear(myproducts);
		//Click to by button 3 times to buy Valentine Bear
		
		PS.addproduct("Valentine Bear");
		PS.addproduct("Valentine Bear");
		PS.addproduct("Valentine Bear");
		
		PS.gotocart();
		
		CartPage CP=new CartPage(driver);
		By allcartitem= By.cssSelector(".cart-item");
		CP.waitforelementtoappear(allcartitem);
		System.out.println("Product Price is"+CP.verifyproductprice("Valentine Bear"));
		
		//Verify product price of Stuffed Dog
		Assert.assertEquals(CP.verifyproductprice("Stuffed Frog"), "$10.99");
		
		//Verify product price of Fluffy Bunny
		Assert.assertEquals(CP.verifyproductprice("Fluffy Bunny"), "$9.99");
		
		//Verify product price of Valentine Bear
		Assert.assertEquals(CP.verifyproductprice("Valentine Bear"), "$14.99");
		
		//Subtotal
        System.out.println("Price for fluffy bunny is "+CP.verifyproductprice("Fluffy Bunny"));
		System.out.println("Subtotal for fluffy bunny is"+CP.verifyproductsubtotal("Fluffy Bunny"));
		System.out.println("Quantity for flulfy bunny is"+CP.verifyproductquantity("Fluffy Bunny"));
		
		//verify total price of fluffy bunny
		float FBSubtotal=Float.parseFloat(CP.verifyproductsubtotal("Fluffy Bunny").replace("$", ""));
		float FBPrice=Float.parseFloat(CP.verifyproductprice("Fluffy Bunny").replace("$", ""));
		float FBQuantity=Float.parseFloat(CP.verifyproductquantity("Fluffy Bunny"));
		
		Assert.assertEquals(CP.verifysubtotal(FBPrice, FBQuantity), FBSubtotal);
		
		
		//verify total price of Stuffed Frog
				float SFSubtotal=Float.parseFloat(CP.verifyproductsubtotal("Stuffed Frog").replace("$", ""));
				float SFPrice=Float.parseFloat(CP.verifyproductprice("Stuffed Frog").replace("$", ""));
				float SFQuantity=Float.parseFloat(CP.verifyproductquantity("Stuffed Frog"));
				
				Assert.assertEquals(CP.verifysubtotal(SFPrice, SFQuantity), SFSubtotal);
				
				
				//verify total price of Valentine Bear
				float VBSubtotal=Float.parseFloat(CP.verifyproductsubtotal("Valentine Bear").replace("$", ""));
				float VBPrice=Float.parseFloat(CP.verifyproductprice("Valentine Bear").replace("$", ""));
				float VBQuantity=Float.parseFloat(CP.verifyproductquantity("Valentine Bear"));
				
				Assert.assertEquals(CP.verifysubtotal(VBPrice, VBQuantity), VBSubtotal);
		
	}
				
		

		
	
}
