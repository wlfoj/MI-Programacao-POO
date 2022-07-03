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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import main.Main;
	
public class MenuController implements Initializable {
	
	@FXML
    private Button btnUser, btnProvider, btnSales, btnCostumer, btnItens, btnProduct, btnLogout;
	
	@FXML
	private void eventShowUserScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	@FXML
	private void eventProductShowScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	@FXML
	private void eventCostumerShowScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorCostume.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	@FXML
	private void eventShowProviderScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}

	@FXML
	private void eventItensShowScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	@FXML
	private void eventShowSalesScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Main.setIdSelected(-1);
		btnUser.setCursor(Cursor.HAND);
		btnProvider.setCursor(Cursor.HAND);
		btnSales.setCursor(Cursor.HAND);
		btnCostumer.setCursor(Cursor.HAND);
		btnItens.setCursor(Cursor.HAND);
		btnProduct.setCursor(Cursor.HAND);
		btnLogout.setCursor(Cursor.HAND);
		logoutSystem();
	}
	
	public void logoutSystem() {
		btnLogout.setOnAction(e-> {
			Alert logoutExe = new Alert(Alert.AlertType.CONFIRMATION);
			
			ButtonType btnOk = new ButtonType("Sair");
			ButtonType btnCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
			
			logoutExe.initOwner(btnLogout.getScene().getWindow());
			logoutExe.setTitle("Sair");
			logoutExe.setHeaderText("Deseja realmente sair?");
			logoutExe.setContentText("Ao sair o usuário terá que logar novamente no sistema");
			logoutExe.getButtonTypes().setAll(btnOk,btnCancel);
			logoutExe.showAndWait().ifPresent(a -> {
				if (a == btnOk) {
					try {
						loginScene();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
		});
	}
	public void loginScene() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
}
