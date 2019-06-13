package mWebApp2.frontEnd;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class FrontEndTest {
	
	WebDriver driver = null;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\oscar.a.serna\\Documents\\dev\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}

	@BeforeTest
	public void beforeTest() {
	}

	@Test(priority=1)
	public void openApp() {
		driver.get("http://localhost:8080/mWebApp2/");
	}
	
	@Test(priority=2)
	public void login() {
		driver.findElement(By.cssSelector("#loginLink")).click();
		driver.findElement(By.cssSelector("#exampleDropdownFormUsername")).sendKeys("exampleUser");
		driver.findElement(By.cssSelector("#exampleDropdownFormEmail1")).sendKeys("example@email.com");
		driver.findElement(By.cssSelector("#exampleDropdownFormPassword1")).sendKeys("12345");
		driver.findElement(By.cssSelector("#loginSubmitButton")).click();
		
	}
	
	@Test(priority=3)
	public void selectUser() {
		driver.findElement(By.cssSelector("#userDropdownButton")).click();
		driver.findElement(By.cssSelector("#u-0")).click();	
	}
	
	@Test(priority=4)
	public void addMood() {
		driver.findElement(By.cssSelector("#moodRangeInput")).sendKeys("25");
		driver.findElement(By.cssSelector("#descriptionInput")).sendKeys("I feel okay");
		driver.findElement(By.cssSelector("#submitButton")).click();
	}
	
	@Test(priority=5)
	public void updateMood() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#editButtonm-0"))).click();
		driver.findElement(By.cssSelector("#moodRangeInput")).clear();
		driver.findElement(By.cssSelector("#moodRangeInput")).sendKeys("30");
		driver.findElement(By.cssSelector("#descriptionInput")).clear();
		driver.findElement(By.cssSelector("#descriptionInput")).sendKeys("...i am depressed");
		driver.findElement(By.cssSelector("#submitButton")).click();
		
	}
	
	@Test(priority=6)
	public void deleteMood() {
		driver.findElement(By.cssSelector("#deleteButtonm-0")).click();
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@AfterClass
	public void afterClass() {
	}



}
