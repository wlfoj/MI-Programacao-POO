package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class ItensController implements Initializable {

	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
    @FXML
    void actionCreate(ActionEvent event) throws IOException {
    	Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
	@FXML
    private Button btnBack, btt2, btt3, btt4, btnCreate, btt6;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btt3.setCursor(Cursor.HAND);
		btt4.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		btt6.setCursor(Cursor.HAND);
	}

}
