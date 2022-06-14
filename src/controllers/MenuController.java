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
	

	@FXML//MUDA OS NOMES DESSES BOT�ES E DESSES EVENTOS CARA
    private Button btnUser, btnProvider, btnSales, btnCostumer, btnItens, btnProduct;
	
	@FXML
	//MUDA OS NOMES DESSES BOT�ES E DESSES EVENTOS CARA
	private void eventShowUserScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
		
		System.out.println("Usuario");
		//Main.scenes("guser");
	}
	
	@FXML//MUDA OS NOMES DESSES BOT�ES E DESSES EVENTOS CARA
	private void eventProductShowScene(ActionEvent e) {
		System.out.println("Produto");
		Main.scenes("gproduct");
	}
	
	@FXML//MUDA OS NOMES DESSES BOT�ES E DESSES EVENTOS CARA
	private void eventCostumerShowScene(ActionEvent e) {
		System.out.println("Clientes");
		Main.scenes("gclientes");
	}
	
	@FXML//MUDA OS NOMES DESSES BOT�ES E DESSES EVENTOS CARA
	private void eventShowProviderScene(ActionEvent e) {
		System.out.println("Fornecedores");
		Main.scenes("gfornecedores");
	}
	//MUDA OS NOMES DESSES BOT�ES E DESSES EVENTOS CARA
	@FXML
	private void eventItensShowScene(ActionEvent e) {
		System.out.println("Cardapio");
		Main.scenes("gcardapio");
	}
	
	@FXML//MUDA OS NOMES DESSES BOT�ES E DESSES EVENTOS CARA
	private void eventShowSalesScene(ActionEvent e) {
		System.out.println("Vendas");
		Main.scenes("gvendas");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnUser.setCursor(Cursor.HAND);
		btnProvider.setCursor(Cursor.HAND);
		btnSales.setCursor(Cursor.HAND);
		btnCostumer.setCursor(Cursor.HAND);
		btnItens.setCursor(Cursor.HAND);
		btnProduct.setCursor(Cursor.HAND);
	}
	
}
