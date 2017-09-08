import org.testng.log4testng.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCOneAccountLogin {
	final Logger logger=Logger.getLogger(TestCOneAccountLogin.class);
	private WebDriver driver;
	private COneLoginPage loginPage;
	private COneHomePage homePage;
	private ReadPropFile propFile;
	
	@DataProvider(name="userAccountInformations")
	public String[][] userAccountInformations(){
		return new String[][] {
			{propFile.getEmail(),propFile.getPassword()},
			{propFile.getEmail(),"password"},
			{"adsada@outlook.com","asdasdasd"},
		};
	}
	
	@BeforeClass
	public void beforeClass() {
		propFile=new ReadPropFile();
		logger.info("Begining of the Test");
		System.setProperty("webdriver.chrome.driver", propFile.getChromeDriverLocation());
		driver=new ChromeDriver();
		logger.info("Chrome driver setup");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		logger.info("End of the test");
	}

	@Test(dataProvider="userAccountInformations")
	public void Login(String email,String pass) {
		driver.get("https://staging.one.comodo.com/app/login"); 
		loginPage=new COneLoginPage(driver); 
		loginPage.login(email, pass);
		
		homePage=new COneHomePage(driver);  
		Assert.assertTrue(homePage.isPageLoaded());
		Assert.assertTrue(homePage.checkUserEmail(email));
		Assert.assertTrue(homePage.logout()); 
	}
	  
}
