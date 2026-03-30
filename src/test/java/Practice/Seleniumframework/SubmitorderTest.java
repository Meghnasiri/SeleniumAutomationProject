package Practice.Seleniumframework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practice.Pageobjectclasses.Cartpage;
import Practice.Pageobjectclasses.CheckoutPage;
import Practice.Pageobjectclasses.Confirmationpage;
import Practice.Pageobjectclasses.Landingpage;
import Practice.Pageobjectclasses.Orderpage;
import Practice.Pageobjectclasses.Productcatalogue;
import Practice.Pageobjectclasses.Registrationpage;
import Practice.TestComponents.BaseTest;

/**
 * Test class for end-to-end purchase flow including dynamic registration.
 * Reads registration and purchase data from RegistrationData.json.
 *
 * Test Flow:
 * 1. Register a new user with data from JSON
 * 2. Login with the registered user
 * 3. Add product to cart and complete purchase
 * 4. Optionally, verify order history
 */
public class SubmitorderTest extends BaseTest {
	/**
	 * Product name used for order history verification.
	 */
	String productname = "ZARA COAT 3";

	/**
	 * Registers a user using the Registrationpage page object.
	 * 
	 * @param regData HashMap containing all registration fields (from JSON)
	 */
	private void performRegistration(HashMap<String, String> regData) {
		Registrationpage registrationPage = new Registrationpage(driver);
		registrationPage.goTo();
		landingpage = registrationPage.registerApplication(regData);
	}

	/**
	 * End-to-end test for registration, login, add to cart, and purchase.
	 * Data-driven from RegistrationData.json.
	 * 
	 * @param input HashMap with registration and purchase fields
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// Register the user first using all registration fields from JSON
		performRegistration(input);

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

	/**
	 * Verifies that the ordered product appears in the user's order history.
	 * Depends on submitOrder test.
	 */
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistorytest() {
		Productcatalogue productCatalogue = landingpage.loginApplication("meghnasiri07@gmail.com", "Lekshana@#097");
		Orderpage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productname));
	}

	/**
	 * Data provider for registration and purchase tests.
	 * Reads data from RegistrationData.json.
	 * 
	 * @return Object[][] for TestNG data-driven tests
	 * @throws IOException if file read fails
	 */
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> inputdata = getjsonDataToString(
				System.getProperty("user.dir") + "//src//test//java//Practice//TestData//RegistrationData.json");

		return new Object[][] { { inputdata.get(0) }, { inputdata.get(1) } };

	}

}
