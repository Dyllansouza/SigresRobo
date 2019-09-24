package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import NetworkDeactivateExecution.*;
import RetrieveDados.Robo;

public class Controller {
	public static void main(String[] args) throws FileNotFoundException, InterruptedException, IOException {

		RadioButton.setRobo();

		while ((RadioButton.getExecucao() == null)) {
			Thread.sleep(1000);
		}

		switch (RadioButton.getExecucao()) {
		case "RetrieveDados":
			RetrieveDados.Robo.main(args);
			break;

		case "NetworkDeactivationExecution":
			NetworkDeactivateExecution.Robo.main(args);
			break;

		case "AlterProfileServiceRollback":
			AlterProfileServiceRollback.Robo.main(args);
			break;
			
		case "DeactivateIPs":
			DeactivateIPs.Robo.main(args);
			break;
			
		case "InventoryUnconfigureExecution":
			InventoryUnconfigureExecution.Robo.main(args);
			break;
		default:
			break;

		}
	}
}
