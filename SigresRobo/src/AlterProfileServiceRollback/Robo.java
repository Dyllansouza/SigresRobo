package AlterProfileServiceRollback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Robo {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {

		String user = System.getProperty("user.name");
		InetAddress host = InetAddress.getLocalHost();

		Logger logs = Logs.Logger();
		logs.info("[AlterProfileServiceRollback] Inicializando logger...");
		logs.info("[AlterProfileServiceRollback] Acessado por host " + host);
		logs.info("[AlterProfileServiceRollback] Usuário " + user);

		@SuppressWarnings("unchecked")
		ArrayList<String> lista = Execucoes.readFile();
		if(!lista.isEmpty()) {
		for (String numero : lista) {
			Thread.sleep(2000);
			try {

				/**
				######################################################
				##		Inicializa o webdriver desejado.			##				
				######################################################						
				*/
				WebDriver driver = Driver.webDriver("Chrome");
				driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				/**
				######################################################
				##	Inicializa a URL base para o robô e realiza 	## 
				##	a navegação principal até a pesquisa do NRC		##				
				######################################################						
				*/
				Driver.setUrlBase();
				Driver.navigateToPesquisaInstacia();
				Driver.searchNrc(numero);
				logs.info("[AlterProfileServiceRollback] Executando NRC " + numero);
				Execucoes.setFocus();
//				Thread.sleep(1000);
				Execucoes.efetuaLogin();
				
				/**
				######################################################
				##		Realiza a tentativa do  botão 				##
				##		AlterProfileServiceRollBack	com xpath 45	##				
				######################################################						
				*/
				
				
				if (!validaXpath45("45"))				 {

					/**
					######################################################
					##				Clica no botão de voltar.			##				
					######################################################						
					*/
					driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/a/img")).click();
//					Thread.sleep(3000);
										
					/**
					######################################################
					##		Realiza a tentativa do  botão 				##
					##		AlterProfileServiceRollBack	com xpath 42	##				
					######################################################						
					*/
					
					if (!validaXpath45("42")) {
						

						/**
						######################################################
						##				Clica no botão de voltar.			##				
						######################################################						
						*/
						driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/a/img")).click();
//						Thread.sleep(3000);
						
						/**
						######################################################
						##		Realiza a tentativa do  botão 				##
						##		AlterProfileServiceRollBack	com xpath 43	##				
						######################################################						
						*/
						
						if (!validaXpath45("43"))	{
							AlterProfileServiceRollback.Logs.erro.add(numero);
							logs.info("[AlterProfileServiceRollback] Erro ao executar NRC " + numero);
							Driver.getWebDriver().quit();
							continue;

							}else {
								
								/**
								######################################################
								##		Realiza todo o fluxo até preencher			##
								##		 o formulário e	clicar em Forçar Tarefa		##		
								######################################################						
								*/
								forcaAtividade(numero);
								logs.info("[AlterProfileServiceRollback] Sucesso na execução do NRC " + numero);

						}
						
					}else {
						
						/**
						######################################################
						##		Realiza todo o fluxo até preencher			##
						##		 o formulário e	clicar em Forçar Tarefa		##		
						######################################################						
						*/
						
						forcaAtividade(numero);
						logs.info("[AlterProfileServiceRollback] Sucesso na execução do NRC " + numero);
					}
					

				} else {
					
					/**
					######################################################
					##		Realiza todo o fluxo até preencher			##
					##		 o formulário e	clicar em Forçar Tarefa		##		
					######################################################						
					*/
					
					forcaAtividade(numero);
					logs.info("[AlterProfileServiceRollback] Sucesso na execução do NRC " + numero);
					
				}

			} catch (Exception e) {
				
				/**
				######################################################
				##		Realiza o Quit e Close do browser.		##				
				######################################################						
				*/
				AlterProfileServiceRollback.Logs.erro.add(numero);
				logs.info(e.getMessage());
				logs.info("[AlterProfileServiceRollback] Erro ao executar NRC " + numero);
					Driver.getWebDriver().quit();
				continue;
			}

		}
		}

		if (!AlterProfileServiceRollback.Logs.erro.isEmpty()) {
			AlterProfileServiceRollback.Logs.gravaErro();
		}
		if (!AlterProfileServiceRollback.Logs.sucesso.isEmpty()) {
			AlterProfileServiceRollback.Logs.gravaSucesso();
		}
		else {
			JOptionPane.showMessageDialog(null, "Lista esta vazia, favor inserir ao menos um NRC");
			Logs.close();
			
		}
		Logs.close();
	}
	
	static boolean validaXpath45(String id) throws InterruptedException {
		String xpath = "/html/body/map/area[" + id + "]"; 
		
		WebElement map1 = Driver.getWebDriver().findElement(By.cssSelector("map"));
		WebElement area1 = map1.findElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor) Driver.getWebDriver();
		executor.executeScript("arguments[0].click();", area1);
		Thread.sleep(2000);
		Driver.getWebDriver().findElement(By.xpath("//*[@id=\"Head3\"]/span/a/nobr")).click();

		String valida = Driver.getWebDriver().findElement(By.xpath("//*[@id=\"DataStructures\"]/table/tbody/tr[1]/td[2]")).getText();
		
		return (valida.equals("ALTER_PROFILE_MSG"));
	}
	
	static void forcaAtividade(String nrc) throws InterruptedException {
		/**
		######################################################
		##				Clica no ícone 'Forçar Tarefa'		##				
		######################################################						
		*/
		Driver.getWebDriver().findElement(By.xpath("/html/body/table/tbody/tr/td[5]/a[3]/img")).click();
		/**
		######################################################
		##		Preenche o campo 'ERROR.ERROR_CODE' 		##
		##					com o valor '0'					##				
		######################################################						
		*/
		Driver.getWebDriver().findElement(By.xpath("/html/body/form/table/tbody/tr[73]/td[3]/input")).clear();
		Driver.getWebDriver().findElement(By.xpath("/html/body/form/table/tbody/tr[73]/td[3]/input")).sendKeys("0");

		/**
		######################################################
		##		Preenche o campo 'ERROR.ERROR_TYPE' 		##
		##					com o null						##				
		######################################################						
		*/
		Driver.getWebDriver().findElement(By.xpath("/html/body/form/table/tbody/tr[74]/td[3]/input")).clear();

		/**
		######################################################
		##		Preenche o campo 'ERROR.ERROR_INFO' 		##
		##					com o valor OK					##				
		######################################################						
		*/
		Driver.getWebDriver().findElement(By.xpath("/html/body/form/table/tbody/tr[75]/td[3]/input")).clear();
		Driver.getWebDriver().findElement(By.xpath("/html/body/form/table/tbody/tr[75]/td[3]/input")).sendKeys("OK");

		/**
		######################################################
		##		Preenche o campo 'ERROR.CODE_CALLBACK' 		##
		##					com o valor '0'					##				
		######################################################						
		*/
	WebElement searchCode_CallBack = Driver.getWebDriver()
			.findElement(By.xpath("/html/body/form/table/tbody/tr[76]/td[3]/input"));
	if (searchCode_CallBack != null) {
		Driver.getWebDriver().findElement(By.xpath("/html/body/form/table/tbody/tr[76]/td[3]/input")).clear();
		Driver.getWebDriver().findElement(By.xpath("/html/body/form/table/tbody/tr[76]/td[3]/input")).sendKeys("0");
	}else {
		/**
		######################################################
		##		Preenche o campo 'ERROR.INFO_CALLBACK' 		##
		##					com o null						##				
		######################################################						
		*/
		WebElement searchInfo_CallBack = Driver.getWebDriver()
				.findElement(By.xpath("/html/body/form/table/tbody/tr[77]/td[3]/input"));
		if (searchInfo_CallBack != null)
			Driver.getWebDriver().findElement(By.xpath("/html/body/form/table/tbody/tr[77]/td[3]/input")).clear();
		}
	/**
	######################################################
	##			Clica no botão 'Forçar Tarefa'			##				
	######################################################						
	*/
	Driver.getWebDriver().findElement(By.xpath("/html/body/form/input[3]")).click();
	
	/**
	######################################################
	##				Clica no botão de Logout			##				
	######################################################						
	*/
	Driver.getWebDriver().findElement(By.xpath("/html/body/table/tbody/tr/td[10]/a/img")).click();
	Thread.sleep(1000);

	/**
	######################################################
	##		Realiza o Quit e Close do browser.		##				
	######################################################						
	*/
	AlterProfileServiceRollback.Logs.sucesso.add(nrc);
	Driver.getWebDriver().quit();
	}
}
