package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class FormItensController {


    @FXML
    private Button btnBack, btnSave;

    @FXML
    private TextField inputMedida, inputName, inputValue;
    
    @FXML
    void eventBack(ActionEvent event) throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }

    @FXML
    void eventSave(ActionEvent event) {

    }
}
