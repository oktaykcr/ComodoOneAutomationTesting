import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class COneLoginPage {
	final Logger logger = Logger.getLogger(COneLoginPage.class);
	private WebDriver driver;
	private final By loc_emailField=By.id("email");
	private final By loc_passField=By.id("password");
	private final By loc_loginButton=By.className("btn-primary");
	private final By loc_forgotPass=By.className("pull-right");
	private final By loc_forgotPass_val=By.className("panel-title");
	private final By loc_appStore=By.className("pull-left");
	private final By loc_appStore_val=By.id("title");
	private final By loc_androidStore=By.xpath("/html/body/div[2]/div/div/form/div[6]/div[2]/a");
	private final By loc_androidStore_val=By.className("id-app-title");
	
	
	/**
	 * The constructor of the login page.It takes WebDriver parameter.
	 * @param driver the reference of the WebDriver.
	 * 		  		 Must not be <code>null</code>.
	 * 
	 * @see <a href="https://sites.google.com/a/chromium.org/chromedriver/">Chrome Driver</a>
	 */
	public COneLoginPage(WebDriver driver) {
		this.driver=driver;
		if(driver!=null) logger.info("Driver installation successful.");
		else logger.info("Driver installation failed!");
	}
	
	/**
	 * This method sends email value in the parameter to <i>Comodo One</i> login page email or login field.
	 * @param email the email of the <i>Comodo One</i> user.
	 * 				Must not be <code>null</code>.
	 * @see <a href="https://staging.one.comodo.com/app/login">Comodo One Login Page</a>
	 */
	public void setEmail(String email) {
		driver.findElement(loc_emailField).sendKeys(email);
	}
	
	/**
	 * This method sends pass value in the parameter to <i>Comodo One</i> login page password field.
	 * @param pass the password of the <i>Comodo One</i> user.
	 * 			   Must not be <code>null</code>.
	 * @see <a href="https://staging.one.comodo.com/app/login">Comodo One Login Page</a>
	 */
	public void setPass(String pass) {
		driver.findElement(loc_passField).sendKeys(pass);
	}
	
	/**
	 * This method finds the login button of the <i>Comodo One</i> login page and clicks on it. 
	 */
	public void clickLogin() {
		driver.findElement(loc_loginButton).click();
	}
	
	/**
	 * This method finds the forgot password link on the login page and clicks on it. 
	 */
	public void clickForgotPassword() {
		driver.findElement(loc_forgotPass).click();
	}
	
	/**
	 * This method finds the App Store button of the <i>Comodo One</i> login page and clicks on it.
	 */
	public void clickAppStore() {
		driver.findElement(loc_appStore).click();
	}
	
	/**
	 * This method finds the Android Store button of the <i>Comodo One</i> login page and clicks on it.
	 */
	public void clickAndroidStore() {
		driver.findElement(loc_androidStore).click();
	}
	
	/**
	 * This method checks the login page successfully opened or not.
	 * @return <code>true</code> if the login page successfully opened; 
     *         <code>false</code> otherwise.
	 */
	public boolean isPageLoaded() {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable((loc_loginButton)));
			logger.info("Login page successfully opened!");
			return true;
		} catch (Exception e) {
			logger.info("Login page could not be opened!");
			return false;
		}
	}
	
	/**
	 * This method sends values of the parameter to <i>Comodo One</i> login page and it fills in the required fields automatically.
	 * @param email the email of the <i>Comodo One</i> user.
	 * 				Must not be <code>null</code>.
	 * @param pass the password of the <i>Comodo One</i> user.
	 * 			    Must not be <code>null</code>.
	 */			  
	public void login(String email,String pass){
		if(isPageLoaded()) {
			logger.info("entering the login information...");
			//EMAIL
			setEmail(email);
			
			//PASSWORD
			setPass(pass);
			//LOGIN BUTTON
			clickLogin();
		}
	}
	
	/**
	 * This method checks forgot password page works properly or not.
	 * @return <code>true</code> if the forgot password page successfully opened;
	 *         <code>false</code> otherwise. 
	 */		   
	public boolean forgotPassword() {
		clickForgotPassword();
		logger.info("Current URL: "+driver.getCurrentUrl());
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(loc_forgotPass_val));
		String text=driver.findElement(loc_forgotPass_val).getText().toLowerCase();
		logger.info("Forgot Password page unique text: "+text);
		if(text.equals("password reset request")) {
			logger.info("Forgot password page successfully opened!");
			return true;
		}
		else {
			logger.info("Forgot password page could not be opened!");
			return false;
		}
	}
	
	/**
	 * This methods checks <i>App Store</i> page works properly or not.
	 * @return <code>true</code> if the App Store page successfully opened;
	 * 		   <code>false</code> otherwise.
	 */
	public boolean appStorePage() {
		clickAppStore();
		for(String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
		}
		logger.info("Current URL: "+driver.getCurrentUrl());
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(loc_appStore_val));
		String text=driver.findElement(loc_appStore_val).getText().toLowerCase();
		logger.info("App Store unique text: "+text);
		if(text.contains("comodo one mobile")) {
			logger.info("App Store page successfully opened!");
			driver.close();//
			for(String window : driver.getWindowHandles()) {
				driver.switchTo().window(window);
			}
			return true;
		}
		else {
			logger.info("App Store page could not be opened!");
			return false;
		}
	}
	
	/**
	 * This methods checks <i>Android Store</i> page works properly or not. 
	 * @return <code>true</code>if the android store page successfully opened;
	 * 	       <code>false</code> otherwise.
	 */
	public boolean androidStorePage(){
		clickAndroidStore();
		for(String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
		}
		logger.info("Current URL: "+driver.getCurrentUrl());
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(loc_androidStore_val));
		String text=driver.findElement(loc_androidStore_val).getText().toLowerCase();
		logger.info("Android Store unique text: "+text);
		if(text.contains("comodo one mobile")) {
			logger.info("Android Store page successfully opened!");
			driver.close();
			for(String window : driver.getWindowHandles()) {
				driver.switchTo().window(window);
			}
			return true;
		}
		else {
			logger.info("Android Store page could not be opened!");
			return false;
		}
	}
	
}
