/*
 * This Selenium WebDriver program automates a login process by first acquiring the password
 * needed to login from another page. Then TestNG is used to assert that we have successfully
 * logged in. The program finishes by logging out and closing WebDriver.
 */

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Locators2 {

	public static void main(String[] args) throws InterruptedException {
		final String NAME = "Joey";
		String actual;
		String expected;

		System.setProperty("webdriver.chrome.driver", "C:/Users/Joey/Documents/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String password = getPassword(driver);
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
		//Enter user name and password
		driver.findElement(By.id("inputUsername")).sendKeys(NAME);
		driver.findElement(By.name("inputPassword")).sendKeys(password);
		
		//Sign in
		driver.findElement(By.className("signInBtn")).click();
		
		//Wait for page to load
		Thread.sleep(1000);
		
		//Print success message
		System.out.println(driver.findElement(By.tagName("p")).getText());
		
		//Assert that login was successful
		actual = driver.findElement(By.tagName("p")).getText();
		expected = "You are successfully logged in.";
		Assert.assertEquals(actual, expected);
		
		//Assert welcome message
		actual = driver.findElement(By.cssSelector("div.login-container h2")).getText();
		expected = "Hello " + NAME + ",";
		Assert.assertEquals(actual, expected);
		
		//Logout
		driver.findElement(By.xpath("//*[text()='Log Out']")).click();

		driver.close();
	}

	//Acquire password from reset password page
	public static String getPassword(WebDriver driver) throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.linkText("Forgot your password?")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		Thread.sleep(1000);
		String passwordText = driver.findElement(By.cssSelector("p.infoMsg")).getText();
		return passwordText.split("'")[1];
	}
}
