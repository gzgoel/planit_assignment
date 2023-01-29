package JupitorToys.abstractcomponents;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		
	}
	
	
	public void waitforelementtoappear(By findby) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		System.out.println("I was done");
	}
	
	
	public void waituntilelementinvisibility(By findby) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.invisibilityOfElementLocated((findby)));
	}
	
}
