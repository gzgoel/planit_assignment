package JupitorToys.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import JupitorToys.abstractcomponents.AbstractComponents;

public class ContactForm extends AbstractComponents {
	
	WebDriver driver;
	
	public ContactForm(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".btn-contact")
	WebElement submitbtn;
	
	@FindBy(xpath="//span[@id='forename-err']")
	WebElement fornameerrormessage;
	
	@FindBy(xpath="//span[@id='email-err']")
	WebElement emailerrormessage;
	
	@FindBy(xpath="//span[@id='message-err']")
	WebElement messageerror;
	
	@FindBy(css="input#forename")
	WebElement name;
	
	@FindBy(css="input#email")
	WebElement email;
	
	@FindBy(css="textarea#message")
	WebElement message;
	

	
	public void submitbtnclick() {
		
		submitbtn.click();
	}
	
	public String validatenameerror() {
		return fornameerrormessage.getText();
		}
	
	public String validateemailerrorm() {
		return emailerrormessage.getText();
	}
	
	public String messageerror() {
		return messageerror.getText();
	}
	
	public void fillform(String nameinput, String emailinput, String messageinput) {
		
			name.sendKeys(nameinput);
			email.sendKeys(emailinput);
			message.sendKeys(messageinput);
			submitbtn.click();
			
			
	}
	
	public void verifyunsuccesfulmessagegone(String message)
	{
		if ( driver.getPageSource().contains(message)){
			Assert.assertEquals(message+ " message is present", message+" should not have been present");
				      }
	}
	
	public void verifysuccessfulmessage(String message) {
		if(driver.getPageSource().contains(message)) {
			Assert.assertTrue(driver.getPageSource().contains(message));
		}
	}

}
