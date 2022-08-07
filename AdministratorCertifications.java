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
public class AdministratorCertifications {
	public static void main(String[] args) {
//			1. Launch Salesforce application https://login.salesforce.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
//			2. Login with username as "ramkumar.ramaiah@testleaf.com " and password as "Password$123"
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
//			3. Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
//			4. Click confirm on Confirm redirect
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandleslst= new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandleslst.get(1));
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
//			5. Click Resources and mouse hover on Learning On Trailhead
		String resource ="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#main-menu-0 > ul > li:nth-child(3) > h3 > hgf-button > span.nav-item-label--l1\").click()";
		driver.executeScript(resource);
		String hover="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#l1-2-0 > div.c360-nav__nav-items.c360-nav__nav-items--l2 > div.l2-container > ul > li:nth-child(1) > hgf-button\").shadowRoot.querySelector(\"button\").click()";
		driver.executeScript(hover);
//			6. Clilck on Salesforce Certifications
		String certificate="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#l1-2-l2-0-0 > div > hgf-c360panellinkedlist\").shadowRoot.querySelector(\"div > ul > li:nth-child(3) > h4 > a\").click()";
		driver.executeScript(certificate);	
//			6. Click on Ceritification Administrator
		String admin="return document.querySelector(\"#cert-site > div.cert-site-bg--trailhead-icons.slds-p-bottom--x-large > div.slds-container--center.slds-container--x-large.slds-p-top--small > div > div:nth-child(1) > div > div.F\\\\(r\\\\) > div > a\").click()";
		driver.executeScript(admin);
//		7. Navigate to Certification - Administrator Overview window
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> windowHandles2List= new ArrayList<String>(windowHandles2);
		driver.switchTo().window(windowHandles2List.get(1));
//			8. Verify the Certifications available for Administrator Certifications should be displayed
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
		System.out.println("The Certifications available are:");
		for (int i = 0; i < findElements.size(); i++) {
			WebElement webElement = findElements.get(i);
			System.out.println(webElement.getText());		
		}
		driver.quit();
	}
}