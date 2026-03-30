package Practice.Seleniumframework;

import io.github.bonigarcia.wdm.WebDriverManager;

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

import Practice.Pageobjectclasses.Landingpage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		Landingpage landingpage = new Landingpage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("meghnasiri07@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Lekshana@#097");
		driver.findElement(By.id("login")).click();

		String productname = "ZARA COAT 3";
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class *='mb-3']")));

		List<WebElement> products = driver.findElements(By.cssSelector("div[class *='mb-3']"));

		WebElement pro = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);

		pro.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		/*
		 * for(int i=0;i<products.size();i++) { String p1=
		 * products.get(i).findElement(By.xpath(".//b")).getText();
		 * System.out.println(p1); if(p1.equals("ZARA COAT 3")) {
		 * products.get(i).findElement(By.xpath(".//button[contains(.,' Add To Cart')]")
		 * ).click(); break; } }
		 */
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink*='/dashboard/cart']")).click();

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector("div[class*='subtotal'] button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder ='Select Country']")), "India").build()
				.perform();
		// .ta-results :nth-child(3)

		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));

		driver.findElement(By.cssSelector(".ta-results :nth-child(3)")).click();
		driver.findElement(By.cssSelector(".action__submit ")).click();

		String confrimMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confrimMessage.equalsIgnoreCase("Thankyou for the order."));

	}
}