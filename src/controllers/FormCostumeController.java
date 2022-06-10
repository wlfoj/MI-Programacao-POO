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
    private Button btt1, btt2;
    
	@FXML
	private void eventoOne(ActionEvent e) {
		System.out.println("Voltar Formulario Cliente para Gerenciamento Clientes");
		Main.scenes("backClientes");
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		
	}
	
	
}
