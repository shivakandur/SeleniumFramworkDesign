package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productname = "ZARA COAT 3";

		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		driver.get("https://rahulshettyacademy.com/client");
	//LandingPage landingpage=new LandingPage(driver);
	driver.findElement(By.id("userEmail")).sendKeys("shiva.mahesh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mahesh@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// .ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
		driver.findElement(By.cssSelector("[class*='ta-item']:nth-of-type(2)")).click();
		//a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click();
		//Thread.sleep(2000);
	WebElement orderbtn=	driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"));
//		//wait.until(ExpectedConditions.elementToBeClickable(orderbtn));
//		//orderbtn.click();
//		//Thread.sleep(2000);
//		//div[aria-label='Order Placed Successfully']
//		//id attribute is not available for this element
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", orderbtn );
		
		//scrollIntoView(true)
		
		//Thread.sleep(2000);		
		
		String confirmMessage=driver.findElement(By.cssSelector("h1[style*='text-transform']")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();

	}

}
