import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		allBrokenLinks(driver);

	}

	public static void allBrokenLinks(WebDriver driver) throws Exception {

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		WebElement selection = driver.findElement(By.className("gf-t"));
		List<WebElement> links = selection.findElements(By.tagName("a"));
		SoftAssert a = new SoftAssert();

		for (WebElement link : links) {

			URL url = new URL(link.getDomProperty("href"));

			HttpURLConnection connect = (HttpURLConnection) url.openConnection();
			connect.setRequestMethod("HEAD");
			connect.connect();

			int resCode = connect.getResponseCode();
			System.out.print(resCode);
			System.out.print("...." + link.getText());
			System.out.println("......" + link.getDomProperty("href"));

			
			a.assertTrue(resCode < 400, "The URL returned BAD response" + resCode + "...." + link.getText());
 
		}
		a.assertAll();

	}

	public void brokenLink(WebDriver driver) throws Exception {

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		// String url =
		// driver.findElement(By.cssSelector("a[href*='soapui']")).getDomProperty("href");
		String url = driver.findElement(By.cssSelector("a[href*='broken']")).getDomProperty("href");

		URL content = new URL(url);
		HttpURLConnection connect = (HttpURLConnection) content.openConnection();

		connect.setRequestMethod("HEAD");
		connect.connect();

		int resCode = connect.getResponseCode();
		Assert.assertTrue(resCode < 400, "The URL returned BAD response");

		// driver.close();
	}

}
