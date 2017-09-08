import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class COneApplicationsPage {
	final Logger logger = Logger.getLogger(COneApplicationsPage.class);
	
	private WebDriver driver;
	
	private final By loc_itsm=By.id("c1-menu-it-and-security-manager");
	private final By loc_itsm_val=By.className("welcome-msg");
	
	private final By loc_patch_management=By.xpath("//*[@id='c1-main-menu']/li[2]/ul/li[1]/ul/li[1]/a");
	
	private final By loc_active=By.className("active");
	
	private final By loc_rmm=By.xpath("//*[@id='c1-main-menu']/li[2]/ul/li[1]/ul/li[2]/a");
	private final By loc_device_management=By.xpath("//*[@id='c1-main-menu']/li[2]/ul/li[1]/ul/li[3]/a");
	
	private final By loc_procedures=By.xpath("//*[@id='c1-main-menu']/li[2]/ul/li[1]/ul/li[4]/a");
	
	private final By loc_serviceDesk=By.id("c1-menu-service-desk");
	private final By loc_serviceDesk_val=By.className("breadcrumb");
	
	private final By loc_allApplications=By.xpath("//*[@id=\'c1-main-menu\']/li[2]/ul/li[7]/a");
	
	/**
	 * The constructor of the applications page.It takes WebDriver parameter.
	 * @param driver the reference of the WebDriver.
	 * 		  		 Must not be <code>null</code>.
	 * 
	 * @see <a href="https://sites.google.com/a/chromium.org/chromedriver/">Chrome Driver</a>
	 */
	public COneApplicationsPage(WebDriver driver) {
		this.driver=driver;
		if(driver!=null) logger.info("Driver installation successful.");
		else logger.info("Driver installation failed!");
	}
	
	/**
	 * This method changes frame to <i>moduleFrame</i>.
	 */
	public void changeFrame() {
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated((By.id("moduleFrame"))));
		driver.switchTo().frame(this.driver.findElement(By.id("moduleFrame")));
	}
	
	//ITSM
	/**
	 * This method clicks the <i>it and security manager</i> button and gets the unique text of <i>it and security manager</i> to verify.<br>
	 * <b>Important note:</b>The frame of the web driver must not be <i>moduleFrame</i>.To avoid this problem, go back to homepage.<br>
	 * For example: <code>driver.get("https://staging.one.comodo.com/app/#/dashboard/overview");<code>
	 * @return the unique text of the <i>it and security manager</i>
	 * 
	 */
	public String getItsmText(){
		driver.findElement(loc_itsm).click();
		logger.info("Current URL: "+driver.getCurrentUrl());
		changeFrame();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loc_itsm_val));
		String text=driver.findElement(loc_itsm_val).getText().toLowerCase();
		logger.info(text);
		return text;
	}
	
	//PATCH MANAGEMENT
	/**
	 * This method clicks the <i>patch management</i> button and gets the unique text of <i>patch management</i> to verify.<br>
	 * <b>Important note:</b>The frame of the web driver must not be <i>moduleFrame</i>.To avoid this problem, go back to homepage.<br>
	 * For example: <code>driver.get("https://staging.one.comodo.com/app/#/dashboard/overview");<code>
	 * @return the unique text of the <i>patch management</i>
	 */
	public String getPatchManagementText(){
		driver.findElement(loc_patch_management).click();
		logger.info("Current URL: "+driver.getCurrentUrl());
		changeFrame();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loc_active));
		String text=driver.findElement(loc_active).getText().toLowerCase();
		logger.info(text);
		return text;
	}
	
	//RMM
	/**
	 * This method clicks the <i>rmm</i> button and gets the unique text of <i>rmm</i> to verify.<br>
	 * <b>Important note:</b>The frame of the web driver must not be <i>moduleFrame</i>.To avoid this problem, go back to homepage.<br>
	 * For example: <code>driver.get("https://staging.one.comodo.com/app/#/dashboard/overview");<code>
	 * @return the unique text of the <i>rmm</i>
	 */
	public String getRmmText() {
		driver.findElement(loc_rmm).click();
		logger.info("Current URL: "+driver.getCurrentUrl());
		changeFrame();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loc_active));
		String text=driver.findElement(loc_active).getText().toLowerCase();
		logger.info(text);
		return text;
	}
	
	//DEVICE MANAGEMENT
	/**
	 * This method clicks the <i>device management</i> button and gets the unique text of <i>device management</i> to verify.<br>
	 * <b>Important note:</b>The frame of the web driver must not be <i>moduleFrame</i>.To avoid this problem, go back to homepage.<br>
	 * For example: <code>driver.get("https://staging.one.comodo.com/app/#/dashboard/overview");<code>
	 * @return the unique text of the <i>device management</i>
	 */
	public String getDeviceManagementText() {
		driver.findElement(loc_device_management).click();
		logger.info("Current URL: "+driver.getCurrentUrl());
		changeFrame();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loc_active));
		String text=driver.findElement(loc_active).getText().toLowerCase();
		logger.info(text);
		return text;
	}
	
	//PROCEDURES
	/**
	 * This method clicks the <i>procedures</i> button and gets the unique text of <i>procedures</i> to verify.<br>
	 * <b>Important note:</b>The frame of the web driver must not be <i>moduleFrame</i>.To avoid this problem, go back to homepage.<br>
	 * For example: <code>driver.get("https://staging.one.comodo.com/app/#/dashboard/overview");<code>
	 * @return the unique text of the <i>procedures</i>
	 */
	public String getProceduresText() {
		driver.findElement(loc_procedures).click();
		logger.info("Current URL: "+driver.getCurrentUrl());
		changeFrame();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loc_active));
		String text=driver.findElement(loc_active).getText().toLowerCase();
		logger.info(text);
		return text;
	}
	
	//SERVICE DESK
	/**
	 * This method clicks the <i>service desk</i> button and gets the unique text of <i>service desk</i> to verify.<br>
	 * <b>Important note:</b>The frame of the web driver must not be <i>moduleFrame</i>.To avoid this problem, go back to homepage.<br>
	 * For example: <code>driver.get("https://staging.one.comodo.com/app/#/dashboard/overview");<code>
	 * @return the unique text of the <i>service desk</i>
	 */
	public String getServiceDeskText() {
		driver.findElement(loc_serviceDesk).click();
		logger.info("Current URL: "+driver.getCurrentUrl());
		changeFrame();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loc_serviceDesk_val));
		String text=driver.findElement(loc_serviceDesk_val).getText().toLowerCase();
		logger.info(text);
		return text;
	}
	
	//ALL APPLICATIONS
	/**
	 * This method clicks the <i>all applications</i> button and gets the unique text of <i>all applications</i> to verify.<br>
	 * <b>Important note:</b>The frame of the web driver must not be <i>moduleFrame</i>.To avoid this problem, go back to homepage.<br>
	 * For example: <code>driver.get("https://staging.one.comodo.com/app/#/dashboard/overview");<code>
	 * @return the unique text of the <i>all applications</i>
	 */
	public String getAllApplicationsText(){
		driver.findElement(loc_allApplications).click();
		logger.info("Current URL: "+driver.getCurrentUrl());
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'c1-view\']/div/div[2]/h1")));//Applications text
		String text=driver.findElement(By.className("breadcrumb")).getText().toLowerCase();
		logger.info(text);
		return text;
	}
	
}
