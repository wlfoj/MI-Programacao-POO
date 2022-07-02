package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/** Classe responsavel pelo Controller de Alertas de excecoes
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class AlertsController {
	/** Classe responsavel pelos alertas nas excecoes
	 * 
	 * @param msg1 para o titulo do alerta
	 * @param msg2 para descricao do alerta
	 * @param msg3 para descricao detalhada do alerta
	 */
	public static void alertErrorDate(String msg1 ,String msg2 ,String msg3) {
		Alert alert = new Alert(AlertType.ERROR);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("iconapp.png"));
		alert.setTitle(msg1);
		alert.setHeaderText(msg2);
		alert.setContentText(msg3);
    	alert.show();
	}
}
