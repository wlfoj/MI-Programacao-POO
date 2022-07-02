package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.ButtonBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementUsers;
import model.User;

/** Classe responsavel pelo controle das ações a serem tomadas com os usuários
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class UserController implements Initializable {
	
	//Id do usuário selecionado
	private Integer idSelected;
	
	ObservableList<User> observableListaUsuarios; 

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> tipoColuna;// Coluna do tipo de usuário
    
    @FXML
    private TableColumn<User, String> userColuna;// Coluna do login do usuário

	@FXML
    private TableColumn<User, Integer> idColuna;// Coluna do id do usuário
	
	@FXML
    private Button btnBack, btnAdd, btnEdit, btnDelete;
	
	 /**Metodo para criar um novo user
     * 
     * @param event - Evento disparado ao clicar no botão de criar um usuário
     * @throws IOException
     */
	@FXML
	private void eventCreate(ActionEvent e) throws IOException {
		// Indicando que nï¿½o selecionou ninguï¿½m
		Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	 /**Metodo para editar o User
     * 
     * @param event - Evento disparado ao clicar no botão de editar o usuário
     * @throws IOException
     */
    @FXML
    void eventEdit(ActionEvent event) throws IOException {
    	// Indicando quem selecionou
    	Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);;
    }

    /**Evento atribuido ao botao de voltar para retornar ao menu
     * 
     * @param e - Evento disparado ao clicar no Botão de voltar
     * @throws IOException
     */
	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}

    /**Metodo para selecionar determinada linha da tabela
	 * 
	 * @param event - Evento disparado ao clicar em uma linha da tabela.
	 */
    @FXML
    void clickLine(MouseEvent event) {
    	// Obtendo o id o usuário selecionado
    	User p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected = p.getId();
    	}
    	// Habilitando os botões de edição e de exclusão
    	btnEdit.setDisable(false);
    	btnDelete.setDisable(false);
    }
	
    /**Metodo para inicializar o gerenciamento e  ativar a visualizacao dos botoes 
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnAdd.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		
		addEventDeleteUser();
		refreshTableView();
	}
	
	/**Metodo para carregar a listView da classe e formatar as celulas
	 * 
	 */
	public void refreshTableView() {
		observableListaUsuarios = FXCollections.observableArrayList(ManagementUsers.listAllUsers());
		tableView.setItems(observableListaUsuarios);

		idColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
		tipoColuna.setCellValueFactory(new PropertyValueFactory<>("name"));
		userColuna.setCellValueFactory(new PropertyValueFactory<>("login"));
	}
	
	/**Metodo atribuindo um evento no botao de deletar para deletar o user da lista
	 * 
	 */
	public void addEventDeleteUser() {
		//Adicionando o evento de deletar e configurando comportamento do alert
		btnDelete.setOnAction(e-> {
			Alert deleteExe = new Alert(Alert.AlertType.CONFIRMATION);
					
			ButtonType btnOk = new ButtonType("Deletar");
			ButtonType btnCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
				
			deleteExe.initOwner(btnDelete.getScene().getWindow());
			deleteExe.setTitle("Deletar");
			deleteExe.setHeaderText("Deseja realmente deletar?");
			deleteExe.setContentText("Ao apagar as informaÃ§Ãµes nÃ£o serÃ£o mais recuperadas");
			deleteExe.getButtonTypes().setAll(btnOk,btnCancel);
			deleteExe.showAndWait().ifPresent(a -> {
				// Se clicar no botão 'ok' deleta o usuário
				if (a == btnOk) {
					ManagementUsers.delete(idSelected);
					refreshTableView();
				} 
			});
					
		});
	}
}