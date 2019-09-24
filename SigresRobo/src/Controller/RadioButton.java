package Controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.openqa.selenium.WebDriver;

public class RadioButton {

	private static String execucao;

	public static String getExecucao() {
		return execucao;
	}

	public static void setExecucao(String execucao) {
		RadioButton.execucao = execucao;
	}

	public static void setRobo() {

		JFrame frame = new JFrame("Selecione a execução");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(2, 2,3,3));		

		ButtonGroup group = new ButtonGroup();
		JRadioButton aRadioButton = new JRadioButton("RetrieveDados");
		JRadioButton bRadioButton = new JRadioButton("NetworkDeactivationExecution");
		JRadioButton cRadioButton = new JRadioButton("AlterProfileServiceRollback");
		JRadioButton dRadioButton = new JRadioButton("DeactivateIPs");
		JRadioButton eRadioButton = new JRadioButton("InventoryUnconfigureExecution");

		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent.getSource();
				execucao = (aButton.getText());
				frame.dispose();
			}
		};

		panel.add(aRadioButton);
		group.add(aRadioButton);
		panel.add(bRadioButton);
		group.add(bRadioButton);
		panel.add(cRadioButton);
		group.add(cRadioButton);
		panel.add(dRadioButton);
		group.add(dRadioButton);
		panel.add(eRadioButton);
		group.add(eRadioButton);

		aRadioButton.addActionListener(sliceActionListener);
		bRadioButton.addActionListener(sliceActionListener);
		cRadioButton.addActionListener(sliceActionListener);
		dRadioButton.addActionListener(sliceActionListener);
		eRadioButton.addActionListener(sliceActionListener);

		frame.add(panel);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
}