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
import model.ManagementUsers;
	
public class MenuController implements Initializable {
	
	@FXML
    private Button btnUser, btnProvider, btnSales, btnCostumer, btnItens, btnProduct, btnLogout;
	
	/**Metodo para trocar de tela para gerenciamento de usuario
	 * 
	 * @param evento disparado ao clicar no botao usuario
	 * @throws IOException
	 */
	@FXML
	private void actionShowUserScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}

	/**Metodo para trocar de tela para gerenciamento de produtos
	 * 
	 * @param e evento disparado ao clicar no botao produtos
	 * @throws IOException
	 */
	@FXML
	private void actionProductShowScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	/**Metodo para trocar de tela para gerenciamento de clientes
	 * 
	 * @param evento disparado ao clicar no botao clientes
	 * @throws IOException
	 */
	@FXML
	private void actionCostumerShowScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorCostume.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	/**Metodo para trocar de tela para gerenciamento de fornecedor
	 * 
	 * @param evento disparado ao clicar no botao fornecedores
	 * @throws IOException
	 */
	@FXML
	private void actionShowProviderScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}

	/**Metodo para trocar de tela para gerenciamento de cardapio
	 * 
	 * @param evento disparado ao clicar no botao cardapio
	 * @throws IOException
	 */
	@FXML
	private void actionItensShowScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	/**Metodo para trocar de tela para gerenciamento de vendas
	 * 
	 * @param evento disparado ao clicar no botao vendas
	 * @throws IOException
	 */
	@FXML
	private void actionShowSalesScene(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	/**Metodo executa ao inicializar o componente
	 * 
	 */
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
		if (logoutSystem() != false) {
			ManagementUsers.auth(null, null);
		}
	}
	
	/**Metodo que realiza o logout do sistema
	 * 
	 * @return
	 */
	public boolean logoutSystem() {
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
		return false;
	}
	
	/**Metodo que retorna para a cena de login
	 * 
	 * @throws IOException
	 */
	public void loginScene() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
}
