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
	
public class MenuController implements Initializable {
	

	@FXML//MUDA OS NOMES DESSES BOTÕES E DESSES EVENTOS CARA
    private Button btt1, btt2, btt3, btt4, btt5, btt6;
	
	@FXML
	//MUDA OS NOMES DESSES BOTÕES E DESSES EVENTOS CARA
	private void eventoOne(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
		
		System.out.println("Usuario");
		//Main.scenes("guser");
	}
	
	@FXML//MUDA OS NOMES DESSES BOTÕES E DESSES EVENTOS CARA
	private void eventoTwo(ActionEvent e) {
		System.out.println("Produto");
		Main.scenes("gproduct");
	}
	
	@FXML//MUDA OS NOMES DESSES BOTÕES E DESSES EVENTOS CARA
	private void eventoThree(ActionEvent e) {
		System.out.println("Clientes");
		Main.scenes("gclientes");
	}
	
	@FXML//MUDA OS NOMES DESSES BOTÕES E DESSES EVENTOS CARA
	private void eventoFour(ActionEvent e) {
		System.out.println("Fornecedores");
		Main.scenes("gfornecedores");
	}
	//MUDA OS NOMES DESSES BOTÕES E DESSES EVENTOS CARA
	@FXML
	private void eventoFive(ActionEvent e) {
		System.out.println("Cardapio");
		Main.scenes("gcardapio");
	}
	
	@FXML//MUDA OS NOMES DESSES BOTÕES E DESSES EVENTOS CARA
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
