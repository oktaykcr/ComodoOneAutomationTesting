import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage_Menu {
	final Logger logger = Logger.getLogger(HomePage_Menu.class);
	private WebDriver driver;
	private By menuButton_loc;//=By.id("c1-menu-dashboard");
	private int menuState=0;
	private List<WebElement> submenuList;
	private int listSize;
	
	/**
	 * This constructor clicks  the menu button according to menuItem parameter.
	 * @param driver the reference value of WebDriver
	 * 				 Must not be <code>null</code>.
	 * @param menuElement the name of the menu button.
	 * 				      <p><b>The menu elements:</b></p>
	 * 				  	  ----------------------------------<br>
	 * 			      	  <i>dashboard</i><br>
	 * 				  	  <i>applications</i><br>
	 * 				  	  <i>management</i><br>
	 * 				  	  <i>store</i><br>
	 * 				  	  <i>tools</i><br>
	 * 				      ----------------------------------<br>
	 * 				  	  Must not be <code>null</code>.
	 */
	public HomePage_Menu(WebDriver driver,String menuElement) {
		this.driver=driver;
		switch(menuElement) {
			case "dashboard":
				menuState=1;
				this.menuButton_loc=By.id("c1-menu-"+menuElement);
				break;
			case "applications":
				menuState=2;
				this.menuButton_loc=By.id("c1-menu-"+"licensed-applications");
				break;
			case "management":
				menuState=3;
				this.menuButton_loc=By.id("c1-menu-"+menuElement);
				break;
			case "store":
				menuState=4;
				this.menuButton_loc=By.id("c1-menu-"+menuElement);
				break;
			case "tools":
				menuState=5;
				this.menuButton_loc=By.id("c1-menu-"+menuElement);
				break;
			default:
				logger.info("There is no such menu selection("+menuElement+")");
				break;
		}
	}
	
	/**
	 * This method finds the menuButton according to initializing of the constructor and clicks the button.
	 * 
	 * @see {@link #HomePage_Menu(WebDriver, String)}
	 */
	public void click() {
		this.driver.findElement(menuButton_loc).click();
	}
	
	/**
	 * This method finds how many submenus the Menu elements has.
	 * @param displayElements <code>true</code> display submenus of the menu element.
	 * 						  <code>false</code> otherwise.
	 * @return submenus list of the menu element.
	 */
	public List<WebElement> getElements(boolean displayElements) {
		//*[@id="c1-main-menu"]/li[1]DASHBOARD
		//*[@id="c1-main-menu"]/li[2]APPLICATIONS
		//*[@id="c1-main-menu"]/li[3]MANAGEMENT
		//*[@id="c1-main-menu"]/li[4]STORE
		//*[@id="c1-main-menu"]/li[5]TOOLS
		if(menuState!=0) {
			submenuList=this.driver.findElements(By.xpath("//*[@id='c1-main-menu']/li["+menuState+"]"));
			String[] list=submenuList.get(0).getText().split("\n");
			this.listSize=list.length;
		}
		if(displayElements) displayElementList();
		return submenuList;
	}
	
	/**
	 * display the names of the submenus of the menu element.
	 */
	private void displayElementList() {
		if(!submenuList.isEmpty()) {
			String[] list=submenuList.get(0).getText().split("\n");
			for(int i=0;i<list.length;i++) {
				logger.info("Element "+i+" :"+list[i]);
			}
			logger.info("Size: "+list.length);
		}
		else logger.info("List is empty!");
	}
	
	/**
	 * Get the size of submenus of the menu element.
	 * @return the size of submenus of the menu element.
	 */
	public int getListSize() {
		return this.listSize;
	}
}
