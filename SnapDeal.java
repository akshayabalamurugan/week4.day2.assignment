package week4.day2.assignment;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import com.google.common.collect.Ordering;
import io.github.bonigarcia.wdm.WebDriverManager;
public class SnapDeal {
	public static void main(String[] args) throws InterruptedException, IOException {
//			1. Launch https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
//			2. Go to Mens Fashion
 		WebElement mensFashion = driver.findElement(By.xpath("(//div[text()='Top Categories']//following::span)[2]"));
		Actions builder= new Actions(driver);
		builder.moveToElement(mensFashion).perform();
//			3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
//			4. Get the count of the sports shoes
		WebElement shoesCount = driver.findElement(By.xpath("//h1[contains(text(),'Sports Shoes for Men')]//following::span"));
		String shoesCountStr = shoesCount.getText();
		shoesCountStr=shoesCountStr.replaceAll("[^0-9]", "");
		System.out.println("Total count of shoes available: "+shoesCountStr);
//			5. Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
//			6. Sort by Low to High
		driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
		driver.findElement(By.xpath("//li[@class='search-li']")).click();
//			7. Check if the items displayed are sorted correctly
		Thread.sleep(5000);
		List<WebElement> findElements = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> priceList= new ArrayList<Integer>();
		for (int i = 0; i < findElements.size(); i++) {
			String text = findElements.get(i).getText();
			text= text.replaceAll("[^0-9]", "");
			priceList.add(Integer.parseInt(text));	
		}
		System.out.println(priceList);
		boolean ordered = Ordering.natural().isOrdered(priceList);
		if (ordered==true) {
			System.out.println("The items are sorted");
		} else {
			System.out.println("The items are not sorted");
		}
//			8.Select the price range (900-1200)
		driver.findElement(By.xpath("//div[@class='price-text-box']/input")).clear();
		driver.findElement(By.xpath("//div[@class='price-text-box']/input")).sendKeys("400");
		driver.findElement(By.xpath("(//div[@class='price-text-box']/input)[2]")).clear();
		driver.findElement(By.xpath("(//div[@class='price-text-box']/input)[2]")).sendKeys("600");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
//			9.Filter with color Navy
		Thread.sleep(5000);
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
//			10 verify the all applied filters
		Thread.sleep(3000);
		List<WebElement> filters = driver.findElements(By.xpath("//div[@class='navFiltersPill']/a"));
		System.out.println("The filters applied are: ");
		for (int i = 0; i < filters.size(); i++) {
			String filtersText = filters.get(i).getText();
			System.out.println(filtersText);
		}
//			11. Mouse Hover on first resulting Training shoes
		WebElement firstResult = driver.findElement(By.xpath("(//p[@class='product-title'])[1]"));
		builder.moveToElement(firstResult).perform();
//			12. click QuickView button
		driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]")).click();
//			13. Print the cost and the discount percentage
		String rs = driver.findElement(By.xpath("//span[@class='strikee ']")).getText();
		System.out.println("The price is: "+rs);
		String ds = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("The discounted price is: "+ds);
//			14. Take the snapshot of the shoes
		File source = driver.getScreenshotAs(OutputType.FILE);
		 File target= new File("./shoes"+".png");
		 FileUtils.copyFile(source, target);
//			15. Close the current window
		 driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
//			16. Close the main window
		 Thread.sleep(5000);
		 driver.quit();

	}
}