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
	
	WebDriver driver = null;
	WebDriverWait wait = null;
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\oscar.a.serna\\Documents\\dev\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		
	}

	@Test(priority=1)
	public void openApp() {
		
		driver.get("http://localhost:8080/mWebApp2/");
		String homePageHeader = driver.findElement(By.cssSelector("#homePageHeader")).getText();
		Assert.assertEquals(homePageHeader, "Mood Tracker");
		
	}
	
	@Test(priority=2)
	public void login() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#loginLink")));	
		driver.findElement(By.cssSelector("#loginLink")).click();
		
		String loginHeader = driver.findElement(By.cssSelector("#loginHeader")).getText();
		Assert.assertEquals(loginHeader, "Login");
		
		driver.findElement(By.cssSelector("#exampleDropdownFormUsername")).sendKeys("exampleUser");
		driver.findElement(By.cssSelector("#exampleDropdownFormEmail1")).sendKeys("example@email.com");
		driver.findElement(By.cssSelector("#exampleDropdownFormPassword1")).sendKeys("12345");
		driver.findElement(By.cssSelector("#loginSubmitButton")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/mWebApp2/track/"));
		
	}
	
	@Test(priority=3)
	public void selectUser() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#userDropdownButton")));	
		driver.findElement(By.cssSelector("#userDropdownButton")).click();
		String usernameToSelect = driver.findElement(By.cssSelector("#u-0")).getText();
		driver.findElement(By.cssSelector("#u-0")).click();	
		
		Assert.assertTrue(driver.findElement(By.cssSelector("#selectedUserHeader")).getText().contains(usernameToSelect));
	}
	
	@Test(priority=4)
	public void addMood() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[4]/div[2]/table/tbody/tr")));
		List<WebElement> moodRows = driver.findElements(By.xpath("/html/body/div/div[4]/div[2]/table/tbody/tr"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#moodRangeInput")));
		driver.findElement(By.cssSelector("#moodRangeInput")).sendKeys("25");
		driver.findElement(By.cssSelector("#descriptionInput")).sendKeys("I feel okay");
		driver.findElement(By.cssSelector("#submitButton")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tablecontainer")));
		List<WebElement>moodRowsAfterAddition = driver.findElements(By.xpath("/html/body/div/div[4]/div[2]/table/tbody/tr"));
		Assert.assertEquals(moodRows.size() + 1, moodRowsAfterAddition.size());
	}
	
	@Test(priority=5)
	public void updateMood() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#moodIdm-0")));
		String moodId = driver.findElement(By.cssSelector("#moodIdm-0")).getText();
		String moodRange = "30";
		String moodDescription = "DEPRESSED";
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#editButtonm-0")));
		driver.findElement(By.cssSelector("#editButtonm-0")).click();
		driver.findElement(By.cssSelector("#moodRangeInput")).clear();
		driver.findElement(By.cssSelector("#moodRangeInput")).sendKeys(moodRange);
		driver.findElement(By.cssSelector("#descriptionInput")).clear();
		driver.findElement(By.cssSelector("#descriptionInput")).sendKeys(moodDescription);
		driver.findElement(By.cssSelector("#submitButton")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#moodIdm-0")));
		Assert.assertEquals(driver.findElement(By.cssSelector("#moodIdm-0")).getText(), moodId);
		Assert.assertEquals(driver.findElement(By.cssSelector("#moodRangem-0")).getText(), moodRange);
		Assert.assertEquals(driver.findElement(By.cssSelector("#moodDescriptionm-0")).getText(), moodDescription);
				
	}
	
	@Test(priority=6)
	public void deleteMood() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#deleteButtonm-0")));
		String moodIdToBeDeleted = driver.findElement(By.cssSelector("#moodIdm-0")).getText();
		driver.findElement(By.cssSelector("#deleteButtonm-0")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#deleteButtonm-0")));
		List<WebElement>moodIdElements = driver.findElements(By.xpath("/html/body/div/div[4]/div[2]/table/tbody/tr/td[1]"));
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
