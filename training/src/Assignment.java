import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class Assignment {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		assignment8(driver);

		// driver.close();

	}

	public static void assignment8(WebDriver driver) throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		Actions a = new Actions(driver);

		a.moveToElement(driver.findElement(By.id("autocomplete"))).click().build().perform();
		driver.findElement(By.id("autocomplete")).sendKeys("ba");

		Thread.sleep(1000);
		List<WebElement> options = driver.findElements(By.cssSelector(".ui-menu-item-wrapper"));

		for (WebElement option : options) {

			if (option.getText().equalsIgnoreCase("Bahrain"))
				a.moveToElement(option).click().build().perform();

		}

	}

	public static void assignment7(WebDriver driver) {
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		int noOfRows = driver.findElements(By.cssSelector(".table-display tr")).size();
		int noOfColumns = driver.findElements(By.cssSelector(".table-display tr th")).size();
		List<WebElement> secondRow = driver.findElements(By.cssSelector(".table-display tr:nth-child(3)"));
		String details = "";

		for (WebElement detail : secondRow) {
			details = details + detail.getText() + " ";
		}

		System.out.println("number of Rows in the table = " + noOfRows + "\nnumber of coumns = " + noOfColumns);
		System.out.println(details);
	}

	public static void assignment6(WebDriver driver) throws InterruptedException {
		/*
		 * select any checkbox, and grab the label for the selected checkbox Select an
		 * option in dropdown. Here option to select should come from step2
		 */
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		Random id = new Random();

		driver.findElement(By.id("checkBoxOption" + id.nextInt(1, 4))).click();
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		for (WebElement checkbox : checkboxes) {
			// System.out.println(checkbox.getAttribute("value")+ " enabled "
			// +checkbox.isEnabled() +" selected " + checkbox.isSelected());
			if (checkbox.isSelected()) {
				String selection = checkbox.getAttribute("value");
				Select x = new Select(driver.findElement(By.id("dropdown-class-example")));
				x.selectByValue(selection);
				driver.findElement(By.id("name")).sendKeys(selection);
				Thread.sleep(1000);
				driver.findElement(By.id("alertbtn")).click();
				if (driver.switchTo().alert().getText().contains(selection)) {
					Thread.sleep(1000);
					System.out.println("inside 2nd if");
					driver.switchTo().alert().accept();
				}
			}

		}

	}

	public static void assignment5(WebDriver driver) {

		driver.get("https://the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Nested Frames")).click();

		// driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		driver.switchTo().frame("frame-top");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-middle']")));
		System.out.println(driver.findElement(By.cssSelector("div#content")).getText());
		driver.switchTo().defaultContent();

	}

	public static void assignment4(WebDriver driver) {

		driver.get("https://the-internet.herokuapp.com/");

		driver.findElement(By.xpath("//a[contains ( text(),\"Multiple Windows\")]")).click();
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//a[contains(text(),\"Click Here\")]"))).click().build().perform();

		Set<String> windowsOpened = driver.getWindowHandles();
		Iterator<String> it = windowsOpened.iterator();
		String window1 = it.next();
		String window2 = it.next();
		driver.switchTo().window(window2);
		System.out.println(driver.findElement(By.cssSelector("h3")).getText());
		driver.switchTo().window(window1);
		System.out.println(driver.findElement(By.cssSelector("h3")).getText());

	}

	public static void assignment3(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
		driver.findElement(By.id("password")).sendKeys("learning");
		driver.findElement(By.xpath("//div/label[2]/span[@class='checkmark']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
		driver.findElement(By.id("okayBtn")).click();

		Select option = new Select(driver.findElement(By.cssSelector("select.form-control")));
		option.selectByIndex(2);

		driver.findElement(By.id("terms")).click();
		Assert.assertTrue(driver.findElement(By.id("terms")).isEnabled());

		driver.findElement(By.id("signInBtn")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-card")));

		List<WebElement> items = driver.findElements(By.xpath("//app-card"));
		int count = 0;

		for (WebElement item : items) {
			count++;
			driver.findElement(By.xpath("//app-card-list[@class='row']/app-card[" + count + "]/div/div/button"))
					.click();
		}
		driver.findElement(By.cssSelector("a.nav-link.btn")).click();
	}
}
