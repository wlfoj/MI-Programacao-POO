package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import model.ManagementUsers;
import model.User;

public class UserController implements Initializable {
	String idEditUser;
	
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
    private Button btt1, btt2, btt3, btt4, btt5;
	
	@FXML
	private void eventoTwo(ActionEvent e) {
		System.out.println("Formulario Usuario");
		Main.scenes("formUser");
		btt4.setDisable(true);
		btt5.setDisable(true);
	}

    @FXML
    private TextField textFild1;
	
    @FXML
    void alertAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.show();
    	alert.setTitle("Deletar");
    	alert.setHeaderText("Realmente deseja excluir?");
    	alert.setContentText("Ao apagar as informações não serão mais recuperadas");
    }
    
	@FXML
	private void eventoOne(ActionEvent e) {
		System.out.println("Voltar Usuario para Menu");
		Main.scenes("backUser");
		btt4.setDisable(true);
		btt5.setDisable(true);
	}
	
    @FXML
    void clickLine(MouseEvent event) {
    	User p = tableView.getSelectionModel().getSelectedItem();
    	if(p == null) {
    		
    	} else {
    		String name=p.getName();
    		String login=p.getLogin();
    		Integer id=p.getId();
    		System.out.println("nome:  "+name+"|  login: "+login+"|  id:  "+id);
    	}
    	
    	btt4.setDisable(false);
    	btt5.setDisable(false);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btt3.setCursor(Cursor.HAND);
		btt4.setCursor(Cursor.HAND);
		btt5.setCursor(Cursor.HAND);
	
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