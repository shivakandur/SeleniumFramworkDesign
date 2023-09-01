package rahulshettyacademy.TestCompmonents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;


public class BaseTest {

	public LandingPage landingpage;
	public WebDriver driver;
	public WebDriver initializedriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\Global.properties")	;	
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			 driver = WebDriverManager.chromedriver().create();
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			 driver = WebDriverManager.firefoxdriver().create();
			
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			 driver = WebDriverManager.edgedriver().create();
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonToMap(String string) throws IOException
	{
		//get json to string
		String JsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacacademy\\Data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		//String to hashmap
		ObjectMapper mapper=new ObjectMapper();
				List<HashMap<String, String>> data=		mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>() {});
				return data;
				//{{map},{map}}
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializedriver();
		 landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();
	}
}
