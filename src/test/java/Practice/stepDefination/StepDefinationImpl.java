package Practice.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Practice.Pageobjectclasses.Cartpage;
import Practice.Pageobjectclasses.CheckoutPage;
import Practice.Pageobjectclasses.Confirmationpage;
import Practice.Pageobjectclasses.Landingpage;
import Practice.Pageobjectclasses.Productcatalogue;
import Practice.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest {
	
	public Landingpage landingpage;
	public Productcatalogue productCatalogue;
	public Cartpage cartPage ;
	public CheckoutPage checkoutPage;
	public Confirmationpage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {	
		landingpage  = launchingBrowser();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_password(String username, String password) {
		
		productCatalogue= landingpage.loginApplication(username, password);
	}
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);	
	}
	
    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) {
    	
    	cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
    }
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void confirmation_message(String string ) {
    	String confrimMessage = confirmationPage.confirmationMessage();
		Assert.assertTrue(confrimMessage.equalsIgnoreCase(string));
        driver.close();
    }
    
    @Then("{string} message is displayed")
    public void some_message_is_displayed(String string) {
    	
     	Assert.assertEquals(string, landingpage.getErrorMessage());
     	driver.close();
    }
    
    
}