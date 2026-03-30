package Practice.Pageobjectclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.Abstractcomponents.Abstractcomponents;

/**
 * Page Object Model for the Confirmation page.
 * Handles actions and verifications after order submission.
 */
public class Confirmationpage extends Abstractcomponents {

	WebDriver driver;

	public Confirmationpage(WebDriver driver) {
		super(driver);
		this.driver = driver;// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
	/**
	 * Retrieves the confirmation message displayed on the confirmation page.
	 * @return String confirmation message
	 */
	public String confirmationMessage() {
		
	String confrimMessage = message.getText();
	return confrimMessage;
	
	}
}
// Add JavaDoc for each public method and key inline comments for clarity.
