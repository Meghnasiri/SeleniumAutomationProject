package Practice.Pageobjectclasses;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Practice.Abstractcomponents.Abstractcomponents;

//page object doesn't hold any data

public class Productcatalogue extends Abstractcomponents {

	WebDriver driver;

	public Productcatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class *='mb-3']")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productBy = By.cssSelector("div[class *='mb-3']");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {

		waitforElementtoAppear(productBy);
		return products;
	}

	public WebElement getProductByName(String productname) {

		WebElement pro = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		return pro;
	}

	public void addProductToCart(String productname) throws InterruptedException {

		WebElement pro = getProductByName(productname);
		pro.findElement(addToCart).click();

		waitforElementtoAppear(toastMessage);
		waitforElementToDisappear(spinner);
		 
	}
}
