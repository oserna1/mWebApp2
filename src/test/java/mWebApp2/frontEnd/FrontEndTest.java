package mWebApp2.frontEnd;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FrontEndTest {
	
	private WebDriver driver = null;
	private WebDriverWait wait = null;
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\oscar.a.serna\\Documents\\dev\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		
	}

	@Test(priority=1)
	public void openApp() {
		
		driver.get("http://localhost:8080/mWebApp2/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homePageHeader")));
		Assert.assertEquals(driver.findElement(By.id("homePageHeader")).getText(), "Mood Tracker");
		
	}

	@Test(priority=2)
	public void login() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginLink")));	
		driver.findElement(By.id("loginLink")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginHeader")));
		Assert.assertEquals(driver.findElement(By.id("loginHeader")).getText(), "Login");
		
		driver.findElement(By.id("exampleDropdownFormUsername")).sendKeys("exampleUser");
		driver.findElement(By.id("exampleDropdownFormEmail1")).sendKeys("example@email.com");
		driver.findElement(By.id("exampleDropdownFormPassword1")).sendKeys("12345");
		driver.findElement(By.id("loginSubmitButton")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("trackPageHeader")));
		Assert.assertEquals(driver.findElement(By.id("trackPageHeader")).getText(), "Track");
		
	}
	
	@Test(priority=3)
	public void selectUser() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userDropdownButton")));	
		driver.findElement(By.id("userDropdownButton")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("u-0")));	
		String usernameToSelect = driver.findElement(By.id("u-0")).getText();
		driver.findElement(By.id("u-0")).click();	
		
		Assert.assertTrue(driver.findElement(By.id("selectedUserHeader")).getText().contains(usernameToSelect));
	}
	
	@Test(priority=4)
	public void addMood() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moodRowsm-0")));
		List<WebElement> moodRows = driver.findElements(By.xpath("//*[starts-with(@id,'moodRowsm')]"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moodRangeInput")));
		driver.findElement(By.id("moodRangeInput")).sendKeys("25");
		driver.findElement(By.id("descriptionInput")).sendKeys("I feel okay");
		driver.findElement(By.id("submitButton")).click();
		
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[starts-with(@id,'moodRowsm')]"), moodRows.size() + 1));
	}
	
	@Test(priority=5)
	public void updateMood() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moodIdm-0")));
		String moodId = driver.findElement(By.id("moodIdm-0")).getText();
		String moodRange = "30";
		String moodDescription = "DEPRESSED";
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("editButtonm-0")));
		driver.findElement(By.id("editButtonm-0")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moodRangeInput")));
		driver.findElement(By.id("moodRangeInput")).clear();
		driver.findElement(By.id("moodRangeInput")).sendKeys(moodRange);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("descriptionInput")));
		driver.findElement(By.id("descriptionInput")).clear();
		driver.findElement(By.id("descriptionInput")).sendKeys(moodDescription);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitButton")));
		driver.findElement(By.id("submitButton")).click();
		
		wait.until(ExpectedConditions.textToBe(By.id("moodRangem-0"), moodRange));
		Assert.assertEquals(driver.findElement(By.id("moodIdm-0")).getText(), moodId);
		Assert.assertEquals(driver.findElement(By.id("moodRangem-0")).getText(), moodRange);
		Assert.assertEquals(driver.findElement(By.id("moodDescriptionm-0")).getText(), moodDescription);
				
	}
	
	@Test(priority=6)
	public void deleteMood() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moodIdm-0")));
		String moodIdToBeDeleted = driver.findElement(By.id("moodIdm-0")).getText();
		WebElement rowToBeDeleted = driver.findElement(By.xpath("//*[starts-with(@id,'moodRowsm')]"));
		driver.findElement(By.id("deleteButtonm-0")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(rowToBeDeleted));
		List<WebElement>moodIdElements = driver.findElements(By.xpath("//*[starts-with(@id,'moodIdm')]"));
		List<String> moodIds = new ArrayList<String>();
		for(WebElement element : moodIdElements) {
			moodIds.add(element.getText());
		}
		Assert.assertFalse(moodIds.contains(moodIdToBeDeleted));
		
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}


}
