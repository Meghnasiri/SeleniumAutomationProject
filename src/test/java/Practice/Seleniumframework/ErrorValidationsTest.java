package Practice.Seleniumframework;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Practice.Pageobjectclasses.Cartpage;
import Practice.Pageobjectclasses.CheckoutPage;
import Practice.Pageobjectclasses.Confirmationpage;
import Practice.Pageobjectclasses.Landingpage;
import Practice.Pageobjectclasses.Productcatalogue;
import Practice.TestComponents.BaseTest;
import Practice.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest{

	    @Test(groups = {"Error validation"},retryAnalyzer =Retry.class)
        public void loginErrorValidation() throws IOException, InterruptedException {
		
		landingpage.loginApplication("meghnasiri07@gmail.com", "Lekshana@97");	
	   	Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}
	    

	    @Test
        public void productErrorValidation() throws IOException, InterruptedException {
		String productname = "ZARA COAT 3";
		String countryName = "India";
		Productcatalogue productCatalogue = landingpage.loginApplication("meghnasiri07@gmail.com", "Lekshana@#097");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
		Cartpage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 344");
		Assert.assertFalse(match);

	}
}