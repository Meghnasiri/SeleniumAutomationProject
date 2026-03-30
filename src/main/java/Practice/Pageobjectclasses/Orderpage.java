package Practice.Pageobjectclasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.Abstractcomponents.Abstractcomponents;

/**
 * Page Object Model for the Order page.
 * Handles order history and order verification actions.
 */

//page object doesn't hold any data
public class Orderpage extends Abstractcomponents {

	WebDriver driver;

	public Orderpage(WebDriver driver) {
		super(driver);
		this.driver = driver;// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	// pagefactory
	// Initialization of webelement
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderProductNames;

	// Action Method of webelements
	public boolean verifyOrderDisplay(String productname) {

		boolean match = orderProductNames.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		return match;

	}

	// Add JavaDoc for each public method and key inline comments for clarity.
}
