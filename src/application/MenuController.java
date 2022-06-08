package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
	
public class MenuController implements Initializable {
	

	@FXML
    private Button btt1, btt2, btt3, btt4, btt5, btt6;
	
	@FXML
	private void eventoOne(ActionEvent e) {
		System.out.println("Usuario");
		Main.scenes("guser");
	}
	
	@FXML
	private void eventoTwo(ActionEvent e) {
		System.out.println("Produto");
		Main.scenes("gproduct");
	}
	
	@FXML
	private void eventoThree(ActionEvent e) {
		System.out.println("Clientes");
		Main.scenes("gclientes");
	}
	
	@FXML
	private void eventoFour(ActionEvent e) {
		System.out.println("Fornecedores");
		Main.scenes("gfornecedores");
	}
	
	@FXML
	private void eventoFive(ActionEvent e) {
		System.out.println("Cardapio");
		Main.scenes("gcardapio");
	}
	
	@FXML
	private void eventoSix(ActionEvent e) {
		System.out.println("Vendas");
		Main.scenes("gvendas");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btt3.setCursor(Cursor.HAND);
		btt4.setCursor(Cursor.HAND);
		btt5.setCursor(Cursor.HAND);
		btt6.setCursor(Cursor.HAND);
	}
	
}
