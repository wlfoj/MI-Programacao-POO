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
    private TableColumn<User, String> userColuna;

	@FXML
    private Button btt1, btt2, btnAdd, btt4, btnDelete;
	
	@FXML
	private void eventCreate(ActionEvent e) {
		System.out.println("Formulario Usuario");
		Main.scenes("formUser");
		btt4.setDisable(true);
		btnDelete.setDisable(true);
	}

    @FXML
    private TextField textFild1;
	
    @FXML
    void deleteAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	//VERIFICAR QUANDO PRESSIONAR OK!
    	alert.show();
    	alert.setTitle("Deletar");
    	alert.setHeaderText("Realmente deseja excluir?");
    	alert.setContentText("Ao apagar as informações não serão mais recuperadas");
    	ManagementUsers.delete(idSelected);
    	refreshTableView();
    }
    
	@FXML
	//VOLTAR
	private void eventBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
		btt4.setDisable(true);
		btnDelete.setDisable(true);
	}
	
    @FXML
    //formuilario editar
    void eventEdit(ActionEvent event) throws IOException {
		//Main.scenes("formUserEdit");
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
		btt4.setDisable(true);
		btnDelete.setDisable(true);
    }
	
    @FXML
    void clickLine(MouseEvent event) {
    	User p = tableView.getSelectionModel().getSelectedItem();
    	if(p == null) {
    		
    	} else {	
    		idSelected =p.getId();
    		System.out.println(idSelected);
    	}
    	
    	btt4.setDisable(false);
    	btnDelete.setDisable(false);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btnAdd.setCursor(Cursor.HAND);
		btt4.setCursor(Cursor.HAND);
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