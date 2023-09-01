package rahulshettyacademy.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestCompmonents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		String productname = "ZARA COAT 3";
		
		
		landingpage.loginApplication("shiva.maesh@gmail.com", "Mahesmh@123");
		//Assert.assertEquals("Incorrect emai or password.",landingpage.getErrorMessage()) ;
		//Assert.assertEquals("Incorrect emai or password.",landingpage.getErrorMessage() );
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage() );
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		String productname = "ZARA COAT 3";
		
		
		landingpage.loginApplication("shiva.kandur@gmail.com", "Cgsm@123");
		rahulshettyacademy.pageobjects.ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
		productCatalogue.goToCartButton();
		CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.verifyProductToDispaly("ZARA COAT 33");
		Assert.assertFalse(match);
		
		

		
	}
	

}
