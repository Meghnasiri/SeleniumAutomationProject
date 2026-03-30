package Practice.Abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Practice.Pageobjectclasses.Cartpage;
import Practice.Pageobjectclasses.Orderpage;

/**
 * Abstract base class for common Selenium actions and navigation.
 * Provides utility methods for waits and navigation to Cart/Order pages.
 */
public class Abstractcomponents {

	WebDriver driver;
	WebDriverWait wait;

	public Abstractcomponents(WebDriver driver) {
		this.driver = driver;// TODO Auto-generated constructor stub
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	/**
	 * Waits for an element to appear on the web page.
	 * @param FindBy - The locator used to find the element.
	 */
	public void waitforElementtoAppear(By FindBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	/**
	 * Waits for an element to be clickable.
	 * @param FindBy - The web element to wait for.
	 */
	public void waitForElementToBeCilckable(WebElement FindBy) {
		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}
	
	/**
	 * Waits for an element to be visible on the web page.
	 * @param FindBy - The web element to wait for.
	 */
	public void waitforElementtoBeVisible(WebElement FindBy) {

		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}

	@FindBy(css = "button[routerlink*='/dashboard/cart']")
	WebElement cartHeader;
	@FindBy(css = "button[routerlink*='/dashboard/myorders']")
	WebElement orderHeader;
	
	/**
	 * Navigates to the Cart page.
	 * @return An instance of the Cartpage class.
	 */
	public Cartpage goToCartPage() {

		cartHeader.click();
		Cartpage cartPage =new Cartpage(driver);
		 return cartPage;

	}

	/**
	 * Navigates to the Order page.
	 * @return An instance of the Orderpage class.
	 */
	public Orderpage goToOrderPage() {

		orderHeader.click();
		Orderpage orderPage =new Orderpage(driver);
		 return orderPage;

	}
	
	/**
	 * Waits for an element to disappear from the web page.
	 * @param FindBy - The web element to wait for.
	 * @throws InterruptedException
	 */
	public void waitforElementToDisappear(WebElement FindBy) throws InterruptedException {

		Thread.sleep(2000);
		// wait.until(ExpectedConditions.invisibilityOf(FindBy));
	}

	
}
