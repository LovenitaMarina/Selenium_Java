import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ScopeOfDriver {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://www.qaclickacademy.com/practice.php");
		//Print the count of links in the website
		System.out.println(driver.findElements(By.tagName("a")).size());

		WebElement footerDriver = driver.findElement(By.id("gf-BIG")); 
		//Print the count of links in the footer
		System.out.println(footerDriver.findElements(By.tagName("a")).size()); //limited Webdriver Scope
		//Print the count of links in the first column of the footer
		footerDriver = driver.findElement(By.cssSelector("div.footer_top_agile_w3ls table tbody tr td:first-child"));
		System.out.println(footerDriver.findElements(By.tagName("a")).size()); 

		String currentwin = driver.getWindowHandle();	
		
		
		List<WebElement> linksToBeClicked = footerDriver.findElements(By.tagName("a"));
		Actions a = new Actions(driver);
		
		for(int i = 1; i<linksToBeClicked.size();i++)
		{
			a.moveToElement(linksToBeClicked.get(i)).keyDown(Keys.LEFT_CONTROL).click().keyUp(Keys.LEFT_CONTROL).build().perform();
			//linksToBeClicked.get(i).click();
			//driver.switchTo().window(currentwin);
		}
		
		/*for(int i = 1; i<linksToBeClicked.size();i++)
		{
			
			linksToBeClicked.get(i).click();
			driver.navigate().back();
			Thread.sleep(3000);
		}*/
		Set <String> allTabs = driver.getWindowHandles() ;
		 Iterator<String> it = allTabs.iterator();
		 while(it.hasNext()) {
			 driver.switchTo().window(it.next());
			 System.out.println(driver.getTitle());
		 }
		
		
		//driver.close();
	}

}
