package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import main.Main;

public class FormCostumeController implements Initializable {


    
    @FXML
    private Button btnBack, btnSave;
    
	@FXML
	private void eventBack(ActionEvent e) {
		System.out.println("Voltar Formulario Cliente para Gerenciamento Clientes");
		Main.scenes("backClientes");
	}
	
    @FXML
    void eventSave(ActionEvent event) {

    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		
	}
	
	
}
