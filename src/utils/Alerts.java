package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/** Classe responsavel pelo  Alertas de excecoes
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class Alerts {

	/** Metodo que gera alertas de erro
	 * 
	 * @param msgTitle para o titulo do alerta
	 * @param msgDescript para descricao do alerta
	 * @param msgDescriptDetail para descricao detalhada do alerta
	 */
	public static void alertError(String msgTitle ,String msgDescript ,String msgDescriptDetail) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(msgTitle);
		alert.setHeaderText(msgDescript);
		alert.setContentText(msgDescriptDetail);
    	alert.show();
	}
	
	
	/** Metodo que gera alertas de informacao
	 * 
	 * @param msgTitle para o titulo do alerta
	 * @param msgDescript para descricao do alerta
	 * @param msgDescriptDetail para descricao detalhada do alerta
	 */
	public static void alertInformation(String msgTitle ,String msgDescript ,String msgDescriptDetail) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(msgTitle);
		alert.setHeaderText(msgDescript);
		alert.setContentText(msgDescriptDetail);
    	alert.show();
	}
}
