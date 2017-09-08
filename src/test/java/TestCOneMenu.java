import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestCOneMenu {
	final Logger logger = Logger.getLogger(TestCOneMenu.class);
	private WebDriver driver;
	private COneHomePage homePage;
	private COneLoginPage loginPage;
	private ReadPropFile propFile;
	
	@DataProvider(name="ComodoOneMenuParameters")
	public static String[][] MenuPages(){
		return new String[][] {{"dashboard","overview","0"},{"dashboard","notifications","0"},{"dashboard","reports","0"},{"management","customer","0"},
			{"management","staff","0"},{"management","roles","0"},{"management","account","0"},{"management","billing","0"},{"store","","0"},{"tools","","0"},
			{"it and security manager","","1"},{"patch management","","1"},{"device management","","1"},{"rmm","","1"},{"service desk","","1"},{"procedures","","1"},
			{"all applications","","1"}};
	}
	
  @BeforeClass
  public void beforeClass() {
	  propFile=new ReadPropFile();
	  logger.info("Begining of the Test");
	  System.setProperty("webdriver.chrome.driver", propFile.getChromeDriverLocation());
	  driver=new ChromeDriver();
	  logger.info("Chrome driver setup");
	  driver.get("https://staging.one.comodo.com/app/login");
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
	  logger.info("End of the test");
  }
  
  @Test(dataProvider="ComodoOneMenuParameters",description="Checks all Comodo One menus and submenus.")
  public void testCOneLoginandMenu(String menuItem,String listItem,String info){
	  if(driver.getCurrentUrl().equals("https://staging.one.comodo.com/app/login") && info.equals("0")) {
		  loginPage=new COneLoginPage(driver);
		  loginPage.login(propFile.getEmail(), propFile.getPassword());
		  
		  homePage=new COneHomePage(driver);
		  //to control home page is load or not
		  Assert.assertTrue(homePage.isPageLoaded()); 
		  //to control the user is login successfully
		  Assert.assertTrue(homePage.checkUserEmail(propFile.getEmail()));
	  
		  Assert.assertTrue(homePage.checkMenu(menuItem, listItem));
	  }
	  else if(!driver.getCurrentUrl().equals("https://staging.one.comodo.com/app/login") && info.equals("0")) Assert.assertTrue(homePage.checkMenu(menuItem, listItem));
	  else if(info.equals("1")) {
		  driver.get("https://staging.one.comodo.com/app/#/dashboard/overview");
		  new WebDriverWait(driver, 10).until(AdditionalConditions.angularHasFinishedProcessing());
		  Assert.assertTrue(homePage.checkAppMenu(menuItem));
	  }
  }
  
}
