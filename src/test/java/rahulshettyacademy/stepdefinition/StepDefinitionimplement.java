package rahulshettyacademy.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestCompmonents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionimplement extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	@Given("^i logged in with username (.+) and password (.+)$")
	public void i_logged_in_with_username_and_password(String username, String password)
	{
		landingpage.loginApplication(username, password);
	 productCatalogue = new ProductCatalogue(driver);
	}
	
	 @When ("^I added product (.+) to cart$")
	 public void i_added_product_to_cart(String productName)
	 {
		 List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
	 }
	 
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_thr_order(String productName)
	{
		productCatalogue.goToCartButton();
		CartPage cartpage = new CartPage(driver);
		boolean match = cartpage.verifyProductToDispaly(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartpage.goToCheckout();
		checkoutPage.countryNameClick("india");
		 confirmationPage=checkoutPage.submitOrder();
	}
	// Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page
	 @Then ("{String} message is displayed on confirmation page")
	 public void message_is_displayed_on_confirmation_page(String string)
	 {
		 String confirmMessage =confirmationPage. getConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			driver.close();
	 }
}
