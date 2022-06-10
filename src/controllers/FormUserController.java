package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import main.Main;

public class FormUserController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;
    private String[] lista = {"Administrador","Fucionário"};
    
    @FXML
    private Button btt1, btt2;
    
	@FXML
	private void eventoOne(ActionEvent e) {
		System.out.println("Voltar Formulário");
		Main.scenes("backgUser");
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		
		comboBox.getItems().setAll(lista);
	}
	
	
}
