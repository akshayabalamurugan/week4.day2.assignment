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
public class ArchitectCertifications {
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
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandleslst= new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandleslst.get(1));
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
//			4. Click  On Resources
		String resource ="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#main-menu-0 > ul > li:nth-child(3) > h3 > hgf-button > span.nav-item-label--l1\").click()";
		driver.executeScript(resource);
		String hover="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#l1-2-0 > div.c360-nav__nav-items.c360-nav__nav-items--l2 > div.l2-container > ul > li:nth-child(1) > hgf-button\").shadowRoot.querySelector(\"button\").click()";
		driver.executeScript(hover);
//			5. Select SalesForce Certification Under Learnings
		String certificate="return document.querySelector(\"#c360-hgf-nav\").shadowRoot.querySelector(\"#l1-2-l2-0-0 > div > hgf-c360panellinkedlist\").shadowRoot.querySelector(\"div > ul > li:nth-child(3) > h4 > a\").click()";
		driver.executeScript(certificate);	
//			6. Choose Your Role as Salesforce Architect
		driver.findElement(By.xpath("(//img[@class='roleMenu-item_img'])[2]")).click();
//			7. Get the Text(Summary) of Salesforce Architect
		String salesforceArchitects = driver.findElement(By.xpath("//div[contains(@class,'cert-site_text slds-text-align--center')]")).getText();
		System.out.println(salesforceArchitects);
//			8. Get the List of Salesforce Architect Certifications Available
		List<WebElement> certificates = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
		System.out.println("The certificates available for Salesforce Architect are: ");
		for (int i = 0; i < certificates.size(); i++) {
			String certificateName = certificates.get(i).getText();
			System.out.println(certificateName);
		}
//			9. Click on Application Architect
		driver.findElement(By.linkText("Application Architect")).click();
//			10.Get the List of Certifications available
		List<WebElement> certificates1 = driver.findElements(By.xpath("(//div[@class='slds-grid slds-wrap slds-grid--align-center slds-grid--pull-padded slds-grid--vertical-stretch'])[1]/div/div/div/a"));
		System.out.println("The certificates available for Application Architect are: ");
		for (int i = 0; i < certificates1.size(); i++) {
			String certificateName1 = certificates1.get(i).getText();
			System.out.println(certificateName1);
		}
	}
}