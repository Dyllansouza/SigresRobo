package DeactivateIPs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Driver {
	
	
	private static WebDriver webDriver;
		
	public static Actions action;
	
	public static Actions getAction() {
		return action;
	}



	public static void setAction(Actions actions) {
		Driver.action = action;
	}



	public static WebDriver getWebDriver() {
		return webDriver;
	}



	public static void setWebDriver(WebDriver webDriver) {
		Driver.webDriver = webDriver;
	}

	/**
	 * 
	 * Method that set the web browser of choise.
	 * Choose between Chrome, Firefox or Edge
	 * 
	 */
	public static WebDriver webDriver(String browser) {
				
		switch (browser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "C:/Automatizacao/Sigres/Drivers/chromedriver.exe");
			 WebDriver driverChrome = new ChromeDriver();
			 Driver.setWebDriver(driverChrome);
			 setWebDriver(driverChrome);
			 return driverChrome;

		case "Firefox":
			System.setProperty("webdriver.gecko.driver", "C:/Automatizacao/Sigres/Drivers/geckodriver.exe");
			 WebDriver driverFirefox = new FirefoxDriver();
			 Driver.setWebDriver(driverFirefox);
			 setWebDriver(driverFirefox);
			 return driverFirefox;

		case "Edge":
			System.setProperty("webdriver.edge.driver", "C:/Automatizacao/Sigres/Drivers/MicrosoftWebDriver.exe");
			 WebDriver driverEdge = new EdgeDriver();
			 Driver.setWebDriver(driverEdge);
			 setWebDriver(driverEdge);
			 return driverEdge;
			 
		default:
			return null;
		}
	}
	
	/**
	 * 
	 * Method that set the main URL used for this robot
	 * 
	 */
	public static void setUrlBase() {
		webDriver.get("http://192.168.236.128/reports/quicker/index.htm");								
		webDriver.manage().window().maximize();	
	}
	/**
	 * 
	 * Method that navigate to the button 'Pesquisa Instancia'
	 * 
	 */
	public static void navigateToPesquisaInstacia() {
		Actions action = new Actions(webDriver);
		Driver.setAction(action);
		WebElement hoverHere = webDriver.findElement(By.xpath("/html/body/div[1]/ul/li[6]/a/b"));				// Hover to 'Gestão Workflow' 
		action.moveToElement(hoverHere).perform();                                             				    // Click on 'Gestão Workflow' 				
		webDriver.findElement(By.xpath("/html/body/div[1]/ul/li[6]/ul/li[1]/a")).click(); 					    // Click on 'Pesquisa Instancia'	
	}
	
	/**
	 * 
	 * Method that search an 'Instancia' by the current NRC code. 
	 * 
	 */
	public static void searchNrc(String numero) {
		webDriver.switchTo().frame(webDriver.findElement(By.xpath("/html/body/div[2]/div/iframe")));				 // Switch to the first iFrame 'principal' 		
		webDriver.switchTo().frame(webDriver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/iframe")));	 // Switch to the second iFrame 'I1'					  
		webDriver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/center/table/tbody/tr[1]/td[2]/input")).sendKeys(numero);// Print the number on the input field
		webDriver.findElement(By.xpath("/html/body/table/tbody/tr/td/form/center/table/tbody/tr[3]/td[3]/input[2]")).click(); 	   // Click on 'enviar' button
		webDriver.findElement(By.xpath("/html/body/table/tbody/tr/td/table[2]/tbody/tr/td[3]/a[1]")).click();   // Click on 'monitor' button
	
	}
}
