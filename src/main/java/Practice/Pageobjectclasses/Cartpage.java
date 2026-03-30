package Practice.Pageobjectclasses;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Practice.Abstractcomponents.Abstractcomponents;

/**
 * Page Object Model for the Cart page.
 * Handles cart actions such as verifying product display and navigating to checkout.
 */

//page object doesn't hold any data

public class Cartpage extends Abstractcomponents{

	WebDriver driver;

	public Cartpage(WebDriver driver) {
        super(driver);
		this.driver = driver;// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	// pagefactory
	// Initialization of webelement 
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = "div[class*='subtotal'] button")
	WebElement checkOut;

	

	// Action Method of webelements
   public boolean verifyProductDisplay(String productname) {
	   
	   boolean match = cartProducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
	
	   return match;
		
   }
   
   public CheckoutPage goToCheckout(){
	   
	   checkOut.click();
	   CheckoutPage checkoutPage = new CheckoutPage(driver);
	   return checkoutPage;
   }
   
   // Add JavaDoc for each public method and key inline comments for clarity.
   }




