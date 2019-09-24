package NetworkDeactivateExecution;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class Execucoes {
	
	
	/**
	 * 
	 * Method that set the focus on the new tab IBM WebSphere MQ Workflow
	 * 
	 */
	public static void setFocus() {
		Set<String> handles = Driver.getWebDriver().getWindowHandles();
		String currentHandle = Driver.getWebDriver().getWindowHandle();
		for (String handle : handles) {

			if (!handle.equals(currentHandle)) {
				Driver.getWebDriver().switchTo().window(handle);
			}
		}
	}
	
	/**
	 * 
	 * Method that apply the login and password required for this page
	 * Here we have two possible actions the sistem will take:
	 * 1st: It will search the elements by ID.
	 * 2nd: In case the ID fails, it'll search the elements by xpath.
	 *   
	 */
	public static void efetuaLogin() {
		try {
			Driver.getWebDriver().findElement(By.id("userid")).sendKeys("admin");
			Driver.getWebDriver().findElement(By.id("password")).sendKeys("password");
			Driver.getWebDriver().findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr/td[1]/table/tbody/tr[7]/td[2]/input[1]")).click();
		}catch(NoSuchElementException e){
			Driver.getWebDriver().findElement(By.xpath("//*[@id=\"userid\"]")).sendKeys("admin");
			Driver.getWebDriver().findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("password");
			Driver.getWebDriver().findElement(By.xpath("/html/body/div[2]/form/table/tbody/tr/td[1]/table/tbody/tr[7]/td[2]/input[1]")).click();
		}
	}
	
	/**
	 * 
	 * Method that read the NRC.txt file, wich contains the NRC codes 
	 * necessaries for the execution.
	 * 
	 */
	
	public static ArrayList readFile() throws FileNotFoundException, IOException {
		String csvFile = "C:/Automatizacao/Sigres/NRC/NetworkDeactivateExecution.txt";
		BufferedReader br = null;
		String line ="";
		ArrayList<String> alist = new ArrayList<String>();
		try {
		    br = new BufferedReader(new FileReader(csvFile));

		   while((line = br.readLine()) != null)
		   {
		        String StoringArray[] = line.split(";");
		        for (String i : StoringArray){
		         alist.add(i);                     
		         }
		   } 
		} finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		return alist;
		}
}
