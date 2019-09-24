package DeactivateIPs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logs {

	public static HashSet<String> sucesso = new HashSet<>();
	public static HashSet<String> erro = new HashSet<>();
	final static String sucessoPath = "C:/Automatizacao/Sigres/Log/AlteracaoPedido/DeactivateIPs/SUCESSO/";
	final static String erroPath = "C:/Automatizacao/Sigres/Log/AlteracaoPedido/DeactivateIPs/ERRO/";
	public static FileHandler fh;

	public static java.util.logging.Logger Logger() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		Logger logger = Logger.getLogger("MyLog");

		try {

			// This block configure the logger with handler and formatter

			fh = new FileHandler(
					"C:/Automatizacao/Sigres/Log/AlteracaoPedido/DeactivateIPs/DeactivateIPs@" + Logs.Data() + ".txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// the following statement is used to log any messages

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return logger;

	}

	public static String Data() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Calendar data = Calendar.getInstance();
		return sdf.format(data.getTime());
	}

	public static void close() {
		fh.close();
	}

	public static void gravaSucesso() throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(sucessoPath + Logs.Data() + ".txt"))) {

			for (String nrc : Logs.sucesso) {
				bw.write(nrc + ";");
				bw.newLine();
			}

		}
	}

	public static void gravaErro() throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(erroPath + Logs.Data() + ".txt"))) {

			for (String nrc : Logs.erro) {
				bw.write(nrc + ";");
				bw.newLine();
			}

		}
	}
}
