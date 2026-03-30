package Practice.Pageobjectclasses;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.Abstractcomponents.Abstractcomponents;

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

	public Productcatalogue loginApplication(String email, String password) {
		username.sendKeys(email);
		//waitforElementtoBeVisible(passwordle);
		passwordle.sendKeys(password);
		waitForElementToBeCilckable(submit);
		submit.click();
		 Productcatalogue productCatalogue = new Productcatalogue(driver);
		 return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitforElementtoBeVisible(errormessage);
		return errormessage.getText();
		
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

}
