package DeactivateIPs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.openqa.selenium.WebDriver;

public class BrowserRadioButton {

	
	
	private static String browser;

	public static String getBrowser() {
		return browser;
	}

	public static void setBrowser(String browser) {
		BrowserRadioButton.browser = browser;
	}

	public static void setBrowser() {
		JFrame frame = new JFrame("Select your browser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel(new GridLayout(1, 0));

		ButtonGroup group = new ButtonGroup();
		JRadioButton aRadioButton = new JRadioButton("Chrome");
		JRadioButton bRadioButton = new JRadioButton("Firefox");
		JRadioButton cRadioButton = new JRadioButton("Edge");

		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent.getSource();
				browser = (aButton.getText());
				frame.dispose();
			}
		};

		panel.add(aRadioButton);
		group.add(aRadioButton);
		panel.add(bRadioButton);
		group.add(bRadioButton);
		panel.add(cRadioButton);
		group.add(cRadioButton);

		aRadioButton.addActionListener(sliceActionListener);
		bRadioButton.addActionListener(sliceActionListener);
		cRadioButton.addActionListener(sliceActionListener);

		frame.add(panel);
		frame.setSize(300, 100);
		frame.setVisible(true);
	}

	
}