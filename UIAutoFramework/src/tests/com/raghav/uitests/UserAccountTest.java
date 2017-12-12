package tests.com.raghav.uitests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import core.preTestSetup;

public class UserAccountTest extends preTestSetup {
	private WebDriver wd = null;

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		wd = getWebDriver();
	}

	@Test(enabled = true, description = "test is for user login to Gmail !", groups = { "UserAccount" })
	public void testUserLogin() {
		adminLogin();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		wd.close();
		wd.quit();
	}
}