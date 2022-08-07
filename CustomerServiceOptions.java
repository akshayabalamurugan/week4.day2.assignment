package week4.day2.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomerServiceOptions {

	public static void main(String[] args) {
//			1. Launch Salesforce application https://login.salesforce.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
//			2. Login with Provided Credentials
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
//			3. Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
//			4. Clilck on Products and Mousehover on Service
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandleslst= new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandleslst.get(1));
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		String products ="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#main-menu-0 > ul > li:nth-child(1) > h3 > hgf-button > span.nav-item-label--l1\").click()";
		driver.executeScript(products);
		String service="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#l1-0-0 > div.c360-nav__nav-items.c360-nav__nav-items--l2 > div.l2-container > ul > li:nth-child(3) > hgf-button > div > span.nav-item-label--l2\").click()";
		driver.executeScript(service);
//			5. Click on Customer Services
		String customerService="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#l1-0-l2-2-0 > div > hgf-c360panellinkedlist\").shadowRoot.querySelector(\"div > ul > li:nth-child(1) > h4 > a\").click()";
		driver.executeScript(customerService);
//			6. Get the names Of Services Available
		List<WebElement> serviceele = driver.findElements(By.xpath("//div[@class='caption']//h2/span"));
		System.out.println("The services available are: ");
		for (int i = 0; i < serviceele.size(); i++) {
			String services = serviceele.get(i).getText();
			services= services.replaceAll(" ", "");
			System.out.println(services);
		}
//			7. Verify the title
		String title = driver.getTitle();
		System.out.println("The title of current page is "+title);

	}

}
