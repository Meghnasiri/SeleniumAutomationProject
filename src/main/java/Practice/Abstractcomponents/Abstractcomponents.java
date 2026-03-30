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

public class Abstractcomponents {

	WebDriver driver;
	WebDriverWait wait;

	public Abstractcomponents(WebDriver driver) {
		this.driver = driver;// TODO Auto-generated constructor stub
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	public void waitforElementtoAppear(By FindBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForElementToBeCilckable(WebElement FindBy) {
		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}
	
	public void waitforElementtoBeVisible(WebElement FindBy) {

		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}

	@FindBy(css = "button[routerlink*='/dashboard/cart']")
	WebElement cartHeader;
	@FindBy(css = "button[routerlink*='/dashboard/myorders']")
	WebElement orderHeader;
	
	public Cartpage goToCartPage() {

		cartHeader.click();
		Cartpage cartPage =new Cartpage(driver);
		 return cartPage;

	}

	public Orderpage goToOrderPage() {

		orderHeader.click();
		Orderpage orderPage =new Orderpage(driver);
		 return orderPage;

	}
	public void waitforElementToDisappear(WebElement FindBy) throws InterruptedException {

		Thread.sleep(2000);
		// wait.until(ExpectedConditions.invisibilityOf(FindBy));
	}

	
}
