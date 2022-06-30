package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertsController {
		
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
