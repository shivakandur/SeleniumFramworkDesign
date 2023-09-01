package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;	
		PageFactory.initElements(driver, this);
	}
	//driver.findElement(By.cssSelector("[placeholder='Select Country']")
	@FindBy (css="[placeholder='Select Country']")
	WebElement selectcountry;
	
	//driver.findElement(By.cssSelector("[class*='ta-item']:nth-of-type(2)")).click();
	@FindBy (css="[class*='ta-item']:nth-of-type(2)")
	WebElement selectedcountry;
	
	//driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"))
	@FindBy (css=".btnn.action__submit.ng-star-inserted")
	WebElement orderbtn;
	
	By selectcountryBy=By.cssSelector("[class*='ta-results']");
	
	public void countryNameClick(String countryname)
	{
		//Actions a = new Actions(driver);
		//a.sendKeys(selectcountry, "india").build().perform();
		JavascriptExecutor js=(JavascriptExecutor)driver;		
		js.executeScript("arguments[0].click()", "India" );
		waitForElementToAppear(selectcountryBy);
		
		
		
	}
	public ConfirmationPage submitOrder()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollintoview(true)", orderbtn );
		return new ConfirmationPage(driver);
	}
}
