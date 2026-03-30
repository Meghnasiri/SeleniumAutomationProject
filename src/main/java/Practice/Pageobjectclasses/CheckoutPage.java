package Practice.Pageobjectclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Practice.Abstractcomponents.Abstractcomponents;

/**
 * Page Object Model for the Checkout page.
 * Handles checkout actions such as selecting country and submitting order.
 */
public class CheckoutPage extends Abstractcomponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder ='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-results :nth-child(3)")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement submit;

	By results = By.cssSelector(".ta-results");

	/**
	 * Selects a country from the dropdown.
	 * @param countryName The name of the country to select.
	 */
	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitforElementtoAppear(results);
		selectCountry.click();

	}

	/**
	 * Submits the order.
	 * @return A Confirmationpage object for the next page.
	 */
	public Confirmationpage submitOrder() {
		submit.click();
		Confirmationpage confirmationPage = new Confirmationpage(driver);
		return confirmationPage;
	}
}

// Add JavaDoc for each public method and key inline comments for clarity.
