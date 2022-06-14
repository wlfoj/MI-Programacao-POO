package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementUsers;
import model.User;

public class UserController implements Initializable {
	
	private Integer idSelected;
	
	ObservableList<User> observableListaUsuarios; 
	
	@FXML
    private TableColumn<User, Integer> idColuna;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> tipoColuna;
    
    @FXML
    private TextField textFild1;
    
    @FXML
    private TableColumn<User, String> userColuna;

	@FXML
	//ESQUECEU DE MUDAR ESSES AQUI
    private Button btnBack, btnAdd, btnEdit, btnDelete;
	
	@FXML
	private void eventCreate(ActionEvent e) throws IOException {
		// Indicando que n�o selecionou ningu�m
		Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
		// Para qu� isso? manda mensagem explicando
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
	}
	
    @FXML
    void eventEdit(ActionEvent event) throws IOException {
    	// Indicando quem selecionou
    	Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
		// Para qu� isso? manda mensagem explicando
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
    }

	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
		// Para qu� isso? manda mensagem explicando
		btnEdit.setDisable(true);
		btnDelete.setDisable(true);
	}
	
    @FXML
    void deleteAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	//VERIFICAR QUANDO PRESSIONAR OK!
    	alert.setTitle("Deletar");
    	alert.setHeaderText("Realmente deseja excluir?");
    	alert.setContentText("Ao apagar as informações não serão mais recuperadas");
    	alert.show();
    	ManagementUsers.delete(idSelected);
    	refreshTableView();
    }
    

	
    @FXML
    void clickLine(MouseEvent event) {
    	User p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected =p.getId();
    	}
    	
    	btnEdit.setDisable(false);
    	btnDelete.setDisable(false);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnAdd.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		refreshTableView();
	}
	
	public void refreshTableView() {
		observableListaUsuarios = FXCollections.observableArrayList(ManagementUsers.listAllUsers());
		tableView.setItems(observableListaUsuarios);

		idColuna.setCellValueFactory(new PropertyValueFactory<>("id"));
		tipoColuna.setCellValueFactory(new PropertyValueFactory<>("name"));
		userColuna.setCellValueFactory(new PropertyValueFactory<>("login"));
	}
}