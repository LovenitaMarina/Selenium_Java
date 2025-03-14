import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollHeadLess {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(1000);
		js.executeScript("document.querySelector(\".tableFixHead\").scrollBy(0, 100)");
		int sum = 0;
		
//	     div.tableFixHead td:nth-child(4)
		List<WebElement> values = driver.findElements(By.cssSelector("div.tableFixHead td:nth-child(4)"));
		for(WebElement value : values) {
			sum += Integer.parseInt(value.getText()) ;
		}
		System.out.print(sum);
		
		Assert.assertEquals(sum, Integer.parseInt(driver.findElement(By.className("totalAmount")).getText().split(":")[1].trim()), "The values don't match");
		System.out.print("finish");
		
	}

}
