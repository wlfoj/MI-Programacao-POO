package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class FormUserController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;
    private String[] lista = {"Administrador","Fucionário"};
    
    @FXML
    private Button btt1, btt2, btt3;
    
	@FXML
	private void eventoOne(ActionEvent e) {
		System.out.println("Voltar Formulário");
		Main.scenes("backgUser");
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btt3.setCursor(Cursor.HAND);
		
		choiceBox.getItems().setAll(lista);
	}
	
	
}
