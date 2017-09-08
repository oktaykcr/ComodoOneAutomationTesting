import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import io.qameta.allure.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;


public class TestCOneLoginPage {
  final Logger logger=Logger.getLogger(TestCOneLoginPage.class);
  private WebDriver driver;
  private COneLoginPage loginPage;
  private ReadPropFile propFile;
	
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
  
  @Description("In this **Forgot Password Page** test case, we checks the page works properly or not according to _'panel-title'_ locator.")
  @Severity(SeverityLevel.CRITICAL)
  @Test(description="Forgot Password Page ")
  public void testForgotPassword() {
	  driver.get("https://staging.one.comodo.com/app/msp/login");
	  loginPage=new COneLoginPage(driver);
	  
	  AllureAttachmentsFactory.saveDownloadableContent("Downloadable Content", "logging.log");
	  
	  Assert.assertTrue(loginPage.isPageLoaded());
	  Assert.assertTrue(loginPage.forgotPassword());
  }
  
  @Step("THIS IS MY APP Step")
  @Test(description="App Store Page",dependsOnMethods= {"testForgotPassword"})
  public void testAppStore() {
	 driver.get("https://staging.one.comodo.com/app/msp/login");
	 loginPage=new COneLoginPage(driver);
	 
	 Assert.assertTrue(loginPage.isPageLoaded());
	 
	 AllureAttachmentsFactory.saveImageAttach("Comodo One Image File","comodo.png");
	 Assert.assertTrue(loginPage.appStorePage());
  }
  
  @Step("THIS IS MY Android Step")
  @Test(description="Android Store Page",dependsOnMethods= {"testAppStore"})
  public void testAndroidStore() {
	  AllureAttachmentsFactory.saveTextLog("Text File", "this is attach text!");
	  Assert.assertTrue(loginPage.androidStorePage());
  }
  
  
}
