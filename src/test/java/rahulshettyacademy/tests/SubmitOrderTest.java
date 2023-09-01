package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestCompmonents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest {

	String productname = "ZARA COAT 3";
	@Test(dataProvider="getdata",groups= {"purchaseorder"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		
		
		landingpage.loginApplication(input.get("email") , input.get("password"));
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		productCatalogue.goToCartButton();
		CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.verifyProductToDispaly(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartpage.goToCheckout();
		checkoutPage.countryNameClick("india");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		
		String confirmMessage =confirmationPage. getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	
	}
		
//@Test(dependsOnMethods= {"submitOrder"})
//public void orderHistoryPage()
//{
	//landingpage.loginApplication("shiva.mahesh@gmail.com", "Mahesh@123");
	//OrderPage orderPage=landingpage.goToOrdersButton();
	//boolean match =orderPage.verifyOrderToDispaly(productname);
	//Assert.assertTrue(match);
//}
		
@DataProvider
public Object[][] getdata() throws IOException
{
	
	List<HashMap<String, String>> data=getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacacademy\\Data\\PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
	
			
	//return new Object[][] {{"shiva.mahesh@gmail.com", "shiva.mahesh@gmail.com","ZARA COAT 3"}, {"shiva.kandur@gmail.com", "Cgsm@3721", "ADIDAS ORIGINAL"}};
}
//HashMap<String, String> map=new HashMap<String, String>();
	//map.put("email", "shiva.mahesh@gmail.com");
	//map.put("password", "shiva.mahesh@gmail.com");
	//map.put("product", "ZARA COAT 3");
	
	//HashMap<String, String> map1=new HashMap<String, String>();
	//map1.put("email", "shiva.kandur@gmail.com");
	//map1.put("password", "Cgsm@3721");
	//map1.put("product", "ADIDAS ORIGINAL");
	

}
