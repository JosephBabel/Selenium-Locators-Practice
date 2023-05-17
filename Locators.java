/*
 * This Selenium WebDriver program navigates to 'https://rahulshettyacademy.com/locatorspractice/'
 * and automates a reset password and login process using a variety of locators for practice.
 */
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Joey/Documents/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
		//Enter user name and password
		driver.findElement(By.id("inputUsername")).sendKeys("Joey");
		driver.findElement(By.name("inputPassword")).sendKeys("password");
		
		//Attempt to sign in
		driver.findElement(By.className("signInBtn")).click();
		
		//Print failure
		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
		
		//Reset password
		driver.findElement(By.linkText("Forgot your password?")).click();
		
		//Fill password reset form
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Joey");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("GenericEmail@EmailProvider.com");
		driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("ActualEmail@EmailProvider.com");
		driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("ActualActualEmail@EmailProvider.com");
		driver.findElement(By.xpath("//form/input[3]")).sendKeys("1111111111");
		
		//Send password reset form
		//Important Note: Implicit waiting is bad for a variety of reasons as it could include
		//unnecessary wait times or in case the web page takes longer to load we could run into
		//the same click intercept issue.
		Thread.sleep(1000); //Wait for animation to complete
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		
		System.out.println(driver.findElement(By.cssSelector("form p")).getText());
		
		//Go back to login
		driver.findElement(By.cssSelector(".go-to-login-btn")).click();
		Thread.sleep(1000);
		
		//Fill login form
		driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
		driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");
		
		//Remember user name
		driver.findElement(By.id("chkboxOne")).click();
		
		//Send login form
		driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
	}

}
