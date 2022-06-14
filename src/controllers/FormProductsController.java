package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import main.Main;

public class FormProductsController implements Initializable {


    
    @FXML
    private Button btnBack, btnSave;
    
	@FXML
	private void eventBack(ActionEvent e) {
		System.out.println("Voltar Formulario Produto para Gerenciamento Produto");
		Main.scenes("backProducts");
	}
	
	//AO SALVAR RETORNAR AO GERENCIAMENTO DE PRODUTOS
	@FXML
	private void eventSave(ActionEvent event) {

	    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		
	}
	
	
}