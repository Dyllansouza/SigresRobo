package InventoryUnconfigureExecution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import InventoryUnconfigureExecution.Logs;

public class Robo {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

		String user = System.getProperty("user.name");
		InetAddress host = InetAddress.getLocalHost();

		Logger logs = Logs.Logger();
		logs.info("[InventoryUnconfigureExecution] Inicializando logger...");
		logs.info("[InventoryUnconfigureExecution] Acessado por host " + host);
		logs.info("[InventoryUnconfigureExecution] Usuário " + user);

		ArrayList<String> lista = Execucoes.readFile();
//		BrowserRadioButton.setBrowser();
//		
//		Thread.sleep(4000);

//		String browser = JOptionPane.showInputDialog("Escolha entre Firefox, Chrome ou Edge: ");
		if(!lista.isEmpty()) {
		for (String numero : lista) {
//			Thread.sleep(2000);
			try {

				/**
				 * 
				 * Instanciate the webdriver with the browser selected.
				 * 
				 */
				WebDriver driver = Driver.webDriver("Chrome");
				driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				/**
				 * setUrlBase: Set the main URL used for this robot. navigateToPesquisaInstacia:
				 * Metod that navigate to the button 'Pesquisa Instancia'. searchNrc: Search an
				 * 'Instancia' by the current NRC code. setFocus: Set the focus on the new tab
				 * IBM WebSphere MQ Workflow. efetuaLogin: Apply the login and password required
				 * for this page.
				 */
				Driver.setUrlBase();
				Driver.navigateToPesquisaInstacia();
				Driver.searchNrc(numero);
				logs.info("[InventoryUnconfigureExecution] Executando NRC " + numero);
				Execucoes.setFocus();
//				Thread.sleep(1000);
				Execucoes.efetuaLogin();

				/**
				 * 
				 * Begin the current cenario procedure. Click in element 'LiberarRecursos' and
				 * Wait page load
				 * 
				 */
				WebElement map1 = driver.findElement(By.cssSelector("map"));
				WebElement area1 = map1.findElement(By.xpath("/html/body/map/area[53]"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", area1);
//				Thread.sleep(2000);

				/**
				 * 
				 * Click in 'Subprocesso' icon
				 * 
				 */
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td[3]/a/img")).click();

				/**
				 * 
				 * Click in 'InventoryDeactivateExeution' element and wait page load
				 * 
				 */
				WebElement map2 = driver.findElement(By.cssSelector("map"));
				WebElement area2 = map2.findElement(By.xpath("/html/body/map/area[8]"));
				executor.executeScript("arguments[0].click();", area2);
//				Thread.sleep(2000);

				/**
				 * 
				 * Click in 'Forçar a conclusão da Atividade' icon
				 * 
				 */
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td[4]/a[3]/img")).click();

				/**
				 * 
				 * Fill in the field 'STATUS' with the value '0'
				 * 
				 */
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr[524]/td[3]/input")).clear();

				/**
				 * 
				 * Fill in the field 'OLD_PROVISION.ERROR.ERROR_CODE' with null
				 * 
				 */
				driver.findElement(By.xpath("/html/body/form/table/tbody/tr[444]/td[3]/input")).clear();

				/**
				 * 
				 * Click in 'Forçar a conclusão da Atividade' button
				 * 
				 */
				driver.findElement(By.xpath("/html/body/form/input[3]")).click();
				
				/**
				 * 
				 * Click in Logout button
				 */
				
				driver.findElement(By.xpath("/html/body/table/tbody/tr/td[9]/a/img")).click();
//				Thread.sleep(1000);

				/**
				 * Quit and close the webdriver
				 */
				InventoryUnconfigureExecution.Logs.sucesso.add(numero);
				driver.quit();
				logs.info("[InventoryUnconfigureExecution] Sucesso na execução do NRC " + numero);
			} catch (Exception e) {
				InventoryUnconfigureExecution.Logs.erro.add(numero);
				logs.info("[InventoryUnconfigureExecution] Erro ao executar NRC " + numero);
				logs.info(e.getMessage());
				Driver.getWebDriver().quit();
				continue;
			}
			if (!InventoryUnconfigureExecution.Logs.erro.isEmpty()) {
				InventoryUnconfigureExecution.Logs.gravaErro();
			}
			if (!InventoryUnconfigureExecution.Logs.sucesso.isEmpty()) {
				InventoryUnconfigureExecution.Logs.gravaSucesso();
			}
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "Lista esta vazia, favor inserir ao menos um NRC");
			Logs.close();
			
		}
		Logs.close();
	}
}
