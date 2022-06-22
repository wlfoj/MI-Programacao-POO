package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import facede.FacedeManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.Administrator;
import model.Employee;
import model.ManagementUsers;
import model.User;

public class FormUserController implements Initializable {

    @FXML
    private TextField inputLogin;
	
 	@FXML
    private TextField inputName;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Button btnBack, btnSave;
    
    @FXML
    private ComboBox<String> boxType;
    
    // Opções de tipos de usuarios para selecionar
    private String[] lista = {"Administrador","Fucionario"};
    
    @FXML
    void actionSave(ActionEvent event) throws IOException  {
    	boolean aux = true;
    	// Verifica não existe um id selecionado
    	if(Main.getIdSelected() == -1) {
    		try {
				createNewUser();
				aux = false;
			} catch (NullFieldException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Campos vazios");
				alert.setContentText("Preencha todos os campos");
				alert.show();
			} catch (ObjectRegistred e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Login registrado");
				alert.setContentText("Já existe um usuário com este login");
				alert.show();
			}
    	}
    	// Caso tenha o id selecionado
    	else {
    		try {
				editUser();
				aux = false;
			} catch (NullFieldException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Campos vazios");
				alert.setContentText("Preencha todos os campos");
				alert.show();
			}
    	}
    	// Se passar pelas etapas sem receber uma exceção
    	if (aux == false) {
    		backToUser();
    	}
    	
    }
   
    private void createNewUser() throws NullFieldException, ObjectRegistred {
    	String name = inputName.getText();
    	String password = inputPassword.getText();
    	String login = inputLogin.getText();
    	String type = boxType.getValue();
    	boxType.setValue("");
		FacedeManagement.addUser(name, login, password, type);
    }
    
    private void editUser() throws NullFieldException {
    	String name = inputName.getText();
    	String password = inputPassword.getText();
    	String login = inputLogin.getText();
    	String type = boxType.getValue();
    	if (type == "Administrador") {
    		Administrator u = new Administrator();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(password);
			ManagementUsers.update(Main.getIdSelected(), u);
    	}
    	else {
    		Employee u = new Employee();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(password);
			ManagementUsers.update(Main.getIdSelected(), u);
    	}
    }
    
	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		backToUser();
	}
	
	private void backToUser() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		//INICIALIZACOES PARA CRIAR UM NOVO USUARIO
		if (Main.getIdSelected() == -1) {
			boxType.getItems().setAll(lista);
			boxType.setValue("Fucionario");
		}else {
			//INICIALIZACOES PARA EDITAR UM NOVO USUARIO
			User u = new User();
			u = ManagementUsers.getOne(Main.getIdSelected());
			inputName.setText(u.getName());
			inputLogin.setText(u.getLogin());
			inputPassword.setText(u.getPass());
			if (u instanceof Employee) {
				boxType.setValue("Fucionario");
			}
			else {
				boxType.setValue("Administrador");
			}
		}
	}	
}
