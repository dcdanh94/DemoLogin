package loginTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://www.demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC01_loginBlankField()
	{
		driver.findElement(By.xpath("//input[@name='uid']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Alert alert = driver.switchTo().alert();
		String messInvalid = alert.getText();
		Assert.assertEquals(messInvalid, "User or Password is not valid");
		alert.accept();
	}
	@Test
	public void TC02_loginSuccess()
	{
		driver.findElement(By.xpath("//input[@name='uid']")).clear();
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr321004");
		
		driver.findElement(By.xpath("//input[@name='password']")).clear();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mUmAnav");
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();	
		Assert.assertEquals(driver.getCurrentUrl(), "http://www.demo.guru99.com/v4/manager/Managerhomepage.php");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
