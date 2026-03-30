package Practice.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Practice.Pageobjectclasses.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	public Landingpage landingpage;

	public WebDriver initializeDriver() throws IOException {

		// Properties class can read the global properties file to decide at runtime on
		// which browser to run.
		Properties pro = new Properties();
		FileInputStream file = new FileInputStream(
		System.getProperty("user.dir") + "\\src\\main\\java\\Practice\\Resources\\GlobalData.properties");
		pro.load(file);
		String browsername = System.getProperty("browser")!=null ? System.getProperty("browser") :pro.getProperty("browser");
		

		if (browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			options.addArguments("--window-size=1920,1080");
			//driver.manage().window().setSize(new Dimension(1440,900));//Full Screen
		}
		else if (browsername.equalsIgnoreCase("firefox")) {
		driver= new FirefoxDriver();	
		}

		else if (browsername.equalsIgnoreCase("edge")) {
		driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	public List<HashMap<String, String>> getjsonDataToString(String filename) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);

		// String to HashMap using Jackson DataBind Dependency
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public String getScreenShot(String testcasename,WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public Landingpage launchingBrowser() throws IOException {
		driver = initializeDriver();
		landingpage = new Landingpage(driver);
		landingpage.goTo();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
