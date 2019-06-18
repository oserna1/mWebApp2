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
		Assert.assertEquals(getElement(By.id("homePageHeader")).getText(), "Mood Tracker");
		
	}

	@Test(priority=2)
	public void login() {

		getElement(By.id("loginLink")).click();
		Assert.assertEquals(getElement(By.id("loginHeader")).getText(), "Login");
		
		getElement(By.id("exampleDropdownFormUsername")).sendKeys("exampleUser");
		getElement(By.id("exampleDropdownFormEmail1")).sendKeys("example@email.com");
		getElement(By.id("exampleDropdownFormPassword1")).sendKeys("12345");
		getElement(By.id("loginSubmitButton")).click();
		
		Assert.assertEquals(getElement(By.id("trackPageHeader")).getText(), "Track");
		
	}
	
	@Test(priority=3)
	public void selectUser() {
		
		getElement(By.id("userDropdownButton")).click();	
		String usernameToSelect = getElement(By.id("u-0")).getText();
		getElement(By.id("u-0")).click();	
		
		Assert.assertTrue(getElement(By.id("selectedUserHeader")).getText().contains(usernameToSelect));
	}
	
	@Test(priority=4)
	public void addMood() {
		
		List<WebElement> moodRows = getElements(By.xpath("//*[starts-with(@id,'moodRowsm')]"));
		getElement(By.id("moodRangeInput")).sendKeys("25");
		getElement(By.id("descriptionInput")).sendKeys("I feel okay");
		getElement(By.id("submitButton")).click();
		
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[starts-with(@id,'moodRowsm')]"), moodRows.size() + 1));
	}
	
	@Test(priority=5)
	public void updateMood() {
	
		String moodId = getElement(By.id("moodIdm-0")).getText();
		String moodRange = "30";
		String moodDescription = "DEPRESSED";

		getElement(By.id("editButtonm-0")).click();
		getElement(By.id("moodRangeInput")).clear();
		getElement(By.id("moodRangeInput")).sendKeys(moodRange);
		getElement(By.id("descriptionInput")).clear();
		getElement(By.id("descriptionInput")).sendKeys(moodDescription);
		getElement(By.id("submitButton")).click();
		
		Assert.assertTrue(wait.until(ExpectedConditions.textToBe(By.id("moodIdm-0"), moodId)));
		Assert.assertTrue(wait.until(ExpectedConditions.textToBe(By.id("moodRangem-0"), moodRange)));
		Assert.assertTrue(wait.until(ExpectedConditions.textToBe(By.id("moodDescriptionm-0"), moodDescription)));
				
	}
	
	@Test(priority=6)
	public void deleteMood() {

		String moodIdToBeDeleted = getElement(By.id("moodIdm-0")).getText();
		WebElement rowToBeDeleted = getElement(By.xpath("//*[starts-with(@id,'moodRowsm')]"));
		getElement(By.id("deleteButtonm-0")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(rowToBeDeleted));
		List<WebElement>moodIdElements = getElements(By.xpath("//*[starts-with(@id,'moodIdm')]"));
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
	
	public WebElement getElement(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return driver.findElement(by);
	}
	
	public List<WebElement> getElements(By by) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		return driver.findElements(by);
	}


}
