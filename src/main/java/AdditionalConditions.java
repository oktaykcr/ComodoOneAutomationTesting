import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class AdditionalConditions {
	 /**
	  * This method waits for the angularJS process to finish.
	  * @return <code>true</code> if the process is finished;
	  * 	    <code>false</code> otherwise.
	  */
	public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
	        return new ExpectedCondition<Boolean>() {
	            @Override
	            public Boolean apply(WebDriver driver) {
	                return Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
	            }
	        };
	    }
}
