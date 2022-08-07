package week4.day2.assignment;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Nykaa {
	public static void main(String[] args) throws InterruptedException {
//			1) Go to https://www.nykaa.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(" https://www.nykaa.com/");
		driver.manage().window().maximize();
//			2) Mouseover on Brands and Search L'Oreal Paris
		WebElement brand = driver.findElement(By.xpath("(//a[@class='css-1mavm7h'])[2]"));
		Actions builder= new Actions(driver);
		builder.moveToElement(brand).perform();
//			3) Click L'Oreal Paris
		driver.findElement(By.linkText("L'Oreal Paris")).click();
//			4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		System.out.println("The title of page is "+title);
		if (title.contains("L'Oreal Paris")) {
			System.out.println("The page title contains L'Oreal Paris");	
		}
//			5) Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[text()='Sort By : popularity']")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
//			6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
//			7) Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
//			8)check whether the Filter is applied with Shampoo
		String filter = driver.findElement(By.xpath("//span[text()='Filters Applied']//following::span")).getText();
		if (filter.contains("Shampoo")) {
			System.out.println("The filter is applied with shampoo");
		}
//			9) Click on L'Oreal Paris Colour Protect Shampoo
		WebElement shampoo = driver.findElement(By.xpath("//div[@class='css-xrzmfa']"));
		wait.until(ExpectedConditions.elementToBeClickable(shampoo));
		shampoo.click();
//			10) GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList= new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
//			11) Print the MRP of the product
		WebElement findElement = driver.findElement(By.xpath("//select[@class='css-2t5nwu']"));
		Select ml= new Select(findElement);
		ml.selectByVisibleText("175ml");
//			12) Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='Add to Bag']")).click();
//			13) Go to Shopping Bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
//			14) Print the Grand Total amount
		driver.switchTo().frame(0);
		String grandTotal = driver.findElement(By.xpath("//span[text()='Grand Total']//following::div")).getText();
		grandTotal = grandTotal.replaceAll("[^0-9]", "");
		System.out.println("The grandtotal of cart is "+grandTotal);
//			15) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
//			16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
//			17) Check if this grand total is the same in step 14
		String grandTotal1 = driver.findElement(By.xpath("//div[text()='Grand Total']//following::span")).getText();
		if (grandTotal.equals(grandTotal1)) {
			System.out.println("The price is same in cart and checkout page");		
		}
//			18) Close all windows
		try {
			driver.quit();
		} catch (Exception e) {
			
		}
	}
}