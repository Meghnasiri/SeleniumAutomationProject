package Practice.Pageobjectclasses;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.Abstractcomponents.Abstractcomponents;

/**
 * Page Object Model for the Landing (Login) page.
 * Handles login actions and error message retrieval.
 */

//page object doesn't hold any data

public class Landingpage extends Abstractcomponents{

	WebDriver driver;

	public Landingpage(WebDriver driver) {
        super(driver);
		this.driver = driver;// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	// pagefactory
	// Initialization of webelements
	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement passwordle;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errormessage;

	// Action Method of webelements

	/**
	 * Logs into the application using provided email and password.
	 * @param email - User's email
	 * @param password - User's password
	 * @return Productcatalogue - Instance of Productcatalogue class
	 */
	public Productcatalogue loginApplication(String email, String password) {
		username.sendKeys(email);
		//waitforElementtoBeVisible(passwordle);
		passwordle.sendKeys(password);
		waitForElementToBeCilckable(submit);
		submit.click();
		 Productcatalogue productCatalogue = new Productcatalogue(driver);
		 return productCatalogue;
	}
	
	/**
	 * Retrieves the error message displayed on the login page.
	 * @return String - Error message text
	 */
	public String getErrorMessage() {
		waitforElementtoBeVisible(errormessage);
		return errormessage.getText();
		
	}

	/**
	 * Navigates to the login page URL.
	 */
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

}
