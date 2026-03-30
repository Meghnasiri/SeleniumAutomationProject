package Practice.Seleniumframework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practice.Pageobjectclasses.Cartpage;
import Practice.Pageobjectclasses.CheckoutPage;
import Practice.Pageobjectclasses.Confirmationpage;
import Practice.Pageobjectclasses.Landingpage;
import Practice.Pageobjectclasses.Orderpage;
import Practice.Pageobjectclasses.Productcatalogue;
import Practice.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitorderTest extends BaseTest {
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		String countryName = "India";
		Productcatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productname"));
		Cartpage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("productname"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		Confirmationpage confirmationPage = checkoutPage.submitOrder();
		String confrimMessage = confirmationPage.confirmationMessage();
		Assert.assertTrue(confrimMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistorytest() {
		Productcatalogue productCatalogue = landingpage.loginApplication("meghnasiri07@gmail.com", "Lekshana@#097");
		Orderpage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productname));
	}


	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> inputdata = getjsonDataToString(
				System.getProperty("user.dir") + "//src//test//java//Practice//TestData//PurchaseOrder.json");

		return new Object[][] { { inputdata.get(0) }, { inputdata.get(1) } };

	}

}

// @DataProvider
// public Object[][] getData() {
// return new Object[][] {{"meghnasiri07@gmail.com","Lekshana@#097","ZARA COAT
// 3"},{"meghanacharagundla07@gmail.com","Multiply34,"ADIDAS ORIGINAL"}};
// }

/*
 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
 * "meghnasiri07@gmail.com"); map.put("password", "Lekshana@#097");
 * map.put("productname", "ZARA COAT 3"); HashMap<String,String> map1 = new
 * HashMap<String,String>(); map1.put("email","meghanacharagundla07@gmail.com");
 * map1.put("password","Multiply34"); map1.put("productname","ADIDAS ORIGINAL");
 * }
 */