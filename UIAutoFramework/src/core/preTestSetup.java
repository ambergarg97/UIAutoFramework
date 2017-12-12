package core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class preTestSetup {
	private WebDriver webdriver = null;
	private String rootDir = null;// path variable for root dir :
									// "workspace project"
	private Properties prop = null;
	private String browser = null;
	private String baseUrl = "";

	// css selectors
	private static final By userNameTextBox = By.id("identifierId");
	private static final By passwordTextBox = By
			.cssSelector("input[name='password']");
	private static final By Login_Button = By.cssSelector("span.RveJvd");

	// default block will be initialized at first
	{
		rootDir = System.getProperty("user.dir");
		prop = new Properties();

	}

	public String getBaseURL() {

		try {
			InputStream input = new FileInputStream(rootDir
					+ "\\src\\resources\\testExecution.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		baseUrl=prop.getProperty("UITest.URL").toString();
		return baseUrl;
	}

	String getBrowserType() {
		try {
			InputStream input = new FileInputStream(rootDir
					+ "\\src\\resources\\testExecution.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prop.getProperty("UITest.Browser").toString();
	}

	public WebDriver getWebDriver() {

		browser = getBrowserType();
		if (browser != null) {
			switch (browser) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", rootDir
						+ "\\src\\drivers\\geckodriver.exe");
				webdriver = new FirefoxDriver();
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", rootDir
						+ "\\src\\drivers\\chromedriver.exe");
				webdriver = new ChromeDriver();
				break;
			default:
				throw new IllegalArgumentException("The given browser "
						+ browser + " is not known or supported ");
			}
		}

		return webdriver;
	}

	public void adminLogin() {
		try {

			webdriver.get(getBaseURL());
			webdriver.findElement(userNameTextBox).click();
			webdriver.findElement(userNameTextBox).clear();
			webdriver.findElement(userNameTextBox).sendKeys(
					prop.getProperty("UITest.User").toString());
			webdriver.findElement(userNameTextBox).click();
			webdriver.findElement(userNameTextBox).sendKeys("\n");
			Thread.sleep(3000);

			webdriver.findElement(passwordTextBox).click();
			webdriver.findElement(passwordTextBox).clear();
			webdriver.findElement(passwordTextBox).sendKeys(
					prop.getProperty("UITest.Password").toString());
			Thread.sleep(2000);
			webdriver.findElement(Login_Button).click();
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
