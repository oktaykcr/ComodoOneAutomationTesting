import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class COneHomePage {
	final Logger logger = Logger.getLogger(COneHomePage.class);
	private WebDriver driver;
	private HomePage_Menu menu;
	private COneApplicationsPage appPage;
	
	private final By loc_userText=By.id("c1-user-text");
	private final By loc_logoutButton=By.id("c1-menu-logout");
	
	/**
	 * The constructor of the home page.It takes WebDriver parameter.
	 * @param driver the reference of the WebDriver.
	 * 		  		 Must not be <code>null</code>.
	 * 
	 * @see <a href="https://sites.google.com/a/chromium.org/chromedriver/">Chrome Driver</a>
	 */
	public COneHomePage(WebDriver driver) {
		this.driver=driver;
		if(driver!=null) logger.info("Driver installation successful.");
		else logger.info("Driver installation failed!");
	}
	
	/**
	 * The logout method of the homepage.
	 * @return <code>true<code> if logout process succeed;
	 * 		   <code>false</code> otherwise.
	 */
	public boolean logout() {
		try {
			driver.findElement(loc_userText).click();
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(loc_logoutButton));
			driver.findElement(loc_logoutButton).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(loc_userText));
			logger.info("Logout process succeed!");
			return true;
		} catch (Exception e) {
			logger.info("Logout process failed!");
			return false;
		}
	}
	
	/**
	 * Get user email address on the homepage.
	 * @return the email address of the 'Comodo One' user.
	 */
	public String getDashboardUserEmail() {
		return driver.findElement(loc_userText).getAttribute("title");
	}
	
	/**
	 * This method checks home page successfully opened or not.
	 * @return <code>true</code> if the home page successfully opened; 
     *         <code>false</code> otherwise.
	 */
	public boolean isPageLoaded() {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated((loc_userText)));
			logger.info("Homepage successfully opened!");
			return true;
		} catch (Exception e) {
			logger.info("Homepage could not be opened!");
			return false;
		}
	}
	
	/**
	 * This method checks user mail address equals homepage mail address or not.
	 * @param email the email address of the user.
	 * @return <code>true</code> if the user mail address check succeed.
	 * 		   <code>false</code> otherwise.
	 */
	public boolean checkUserEmail(String email) {
		try {
			if(this.getDashboardUserEmail().contains(email)) {
				logger.info("User mail check succeed!");
				return true;
			}
			else throw new Exception();
		} catch (Exception e) {
			logger.info("User mail check failed!");
			return false;
		}
	}
	
	/**
	 * This method checks <i>Comodo One</i> menu pages successfully opened or not.
	 * This method <b>does not check applications page</b>.
	 * @param menuElement the name of the menu element.
	 * 				   Must not be <code>null</code>.
	 * @param subElement the name of the sub menu element.
	 * 		  		   May be <code>null</code>
	 * @return <code>true</code> if the desired menu and submenu successfully opened;
	 * 		   <code>false</code> otherwise.	
	 */
	public boolean checkMenu(String menuElement,String subElement) {
		try {
			menu=new HomePage_Menu(driver,menuElement);
			menu.click();
			menu.getElements(false);
			if(menu.getListSize()>1) {
				WebElement button=this.driver.findElement(By.id("c1-menu-"+subElement));
				button.click();
				new WebDriverWait(driver, 5).until(AdditionalConditions.angularHasFinishedProcessing());
				//to read the breadcrumb text
				String pageText=this.driver.findElement(By.className("breadcrumb")).getText().toLowerCase();
				logger.info(subElement+" unique text: "+pageText);
				
				if(subElement.equals("billing")) {//MANAGEMENT-APPLICATIONS
					if(pageText.contains("applications")) {
						logger.info("Applications page successfully opened!");
						return true;
					}
				}
				if(pageText.contains(subElement)) {
					logger.info(subElement+" page successfully opened!");
					return true;
				}
				else throw new Exception();
			}
			else {
				new WebDriverWait(driver, 5).until(AdditionalConditions.angularHasFinishedProcessing());
				String pageText=this.driver.findElement(By.className("breadcrumb")).getText().toLowerCase();
				logger.info("DEBUG: "+pageText);
				if(pageText.contains(menuElement)) {
					logger.info(menuElement+" page successfully opened!");
					return true;
				}
				else throw new Exception();
			}
			
		} catch (Exception e) {
			logger.info(subElement+" page could not be opened!");
			return false;
		}
	}
	
	/**
	 * This method checks <i>Comodo One</i> applications menu successfully opened or not.
	 * @param appElement the name of submenu of the applications menu.
	 * 				  <p><b>The applications submenu elements:</b></p>
	 * 				  ----------------------------------<br>
	 * 			      <i>it and security manager</i><br>
	 * 				  <i>patch management</i><br>
	 * 				  <i>rmm</i><br>
	 * 				  <i>device management</i><br>
	 * 				  <i>procedures</i><br>
	 * 				  <i>service desk</i><br>
	 * 				  <i>all applications</i><br>
	 * 				  ----------------------------------<br>
	 * 				  Must not be <code>null<code>.
	 * @return <code>true</code> if the submenu of the applications menu successfully opened;
	 * 		   <code>false</code> otherwise.
	 */
	public boolean checkAppMenu(String appElement) {
		boolean info=false;
		menu=new HomePage_Menu(driver,"applications");
		menu.click();
		appPage=new COneApplicationsPage(driver);
		try {
			switch (appElement) {
			case "it and security manager":
				if(appPage.getItsmText().contains("security manager")) info=true;
				break;
			case "patch management":
				if(appPage.getPatchManagementText().contains("patch management")) info=true;
				break;
			case "rmm":
				if(appPage.getRmmText().contains("device list")) info=true;
				break;
			case "device management":
				if(appPage.getDeviceManagementText().contains("device list")) info=true;
				break;
			case "procedures":
				if(appPage.getProceduresText().contains("procedures")) info=true;
				break;
			case "service desk":
				if(appPage.getServiceDeskText().contains("service desk")) info=true;
				break;
			case "all applications":
				if(appPage.getAllApplicationsText().contains("applications")) info=true;
				break;
			default:
				logger.info(appElement+" page could not be opened!");
				break;
			}
			logger.info(appElement+" page status info:"+info);
			if(info) {
				logger.info(appElement+" page successfully opened!");
				return true;
			}
			else throw new Exception();
		} catch (Exception e) {
			logger.info(appElement+" page could not be opened!");
			return false;
		}
	}
	
}
