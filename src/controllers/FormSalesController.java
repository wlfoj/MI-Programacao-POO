package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class FormSalesController {

	@FXML
	private Button btnBack, btnSave;

    @FXML
    void actionBack(ActionEvent event) throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }

    @FXML
    void actionSave(ActionEvent event) {

    }
	
}
