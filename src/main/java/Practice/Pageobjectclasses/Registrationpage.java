package Practice.Pageobjectclasses;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Practice.Abstractcomponents.Abstractcomponents;

/**
 * Page Object Model for the Registration page.
 * Handles user registration actions and element interactions.
 */
public class Registrationpage extends Abstractcomponents {

	WebDriver driver;

	/**
	 * Constructor for Registrationpage.
	 * @param driver WebDriver instance
	 */
	public Registrationpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory: Initialization of web elements for registration form
	@FindBy(id = "firstName")
	WebElement firstName;
	@FindBy(id = "lastName")
	WebElement lastName;
	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userMobile")
	WebElement userMobile;
	@FindBy(css = "select[formcontrolname='occupation']")
	WebElement occupationSelect;
	@FindBy(css = "input[value='Male']")
	WebElement maleRadio;
	@FindBy(css = "input[value='Female']")
	WebElement femaleRadio;
	@FindBy(id = "userPassword")
	WebElement userPassword;
	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	@FindBy(css = "input[formcontrolname='required']")
	WebElement ageCheckbox;
	@FindBy(id = "login")
	WebElement registerButton;
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	/**
	 * Registers a user using individual string parameters for each field.
	 * @param fname First name
	 * @param lname Last name
	 * @param email Email address
	 * @param mobile Mobile number
	 * @param occupation Occupation (dropdown)
	 * @param gender Gender (radio)
	 * @param password Password
	 * @param confirmPass Confirm password
	 * @return Landingpage instance after registration
	 */
	public Landingpage registerApplication(String fname, String lname, String email, String mobile, String occupation, String gender, String password, String confirmPass) {
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		userEmail.sendKeys(email);
		userMobile.sendKeys(mobile);
		Select select = new Select(occupationSelect);
		select.selectByVisibleText(occupation);
		if (gender.equals("Male")) {
			maleRadio.click();
		} else {
			femaleRadio.click();
		}
		userPassword.sendKeys(password);
		confirmPassword.sendKeys(confirmPass);
		ageCheckbox.click();
		waitForElementToBeCilckable(registerButton);
		registerButton.click();
		Landingpage landingPage = new Landingpage(driver);
		return landingPage;
	}

	/**
	 * Registers a user using a HashMap of registration fields (for data-driven tests).
	 * @param input HashMap with keys: firstName, lastName, userEmail, userMobile, occupation, gender, userPassword, confirmPassword
	 * @return Landingpage instance after registration
	 */
	public Landingpage registerApplication(HashMap<String, String> input) {
		firstName.sendKeys(input.get("firstName"));
		lastName.sendKeys(input.get("lastName"));
		userEmail.sendKeys(input.get("email"));
		userMobile.sendKeys(input.get("userMobile"));
		Select select = new Select(occupationSelect);
		select.selectByVisibleText(input.get("occupation"));
		if (input.get("gender").equals("Male")) {
			maleRadio.click();
		} else {
			femaleRadio.click();
		}
		userPassword.sendKeys(input.get("password"));
		confirmPassword.sendKeys(input.get("confirmPassword"));
		ageCheckbox.click();
		waitForElementToBeCilckable(registerButton);
		registerButton.click();
		Landingpage landingPage = new Landingpage(driver);
		return landingPage;
	}

	/**
	 * Gets the error message displayed on registration failure.
	 * @return Error message text
	 */
	public String getErrorMessage() {
		waitforElementtoBeVisible(errorMessage);
		return errorMessage.getText();
	}

	/**
	 * Navigates to the registration page URL.
	 */
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/register");
	}

}
