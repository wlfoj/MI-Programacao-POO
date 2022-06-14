package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.NullFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
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
    
    private String[] lista = {"Administrador","Fucionario"};
    
    @FXML
    void actionSave(ActionEvent event) throws IOException  {
    	if(Main.getIdSelected() == -1) {
    		//Cria
    		createNewUser();
    	}
    	else {
    		//edita
    		editUser();
    	}
    	backToUser();
    }
   
    private void createNewUser() {
    	String name = inputName.getText();
    	String password = inputPassword.getText();
    	String login = inputLogin.getText();
    	String type = boxType.getValue();
    	
    	if (type == "Administrador") {
    		Administrator u = new Administrator();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(password);
    		try {
				ManagementUsers.addUser(u);
			} catch (NullFieldException e) {
				// FAZER LÃ“GICA PARA TRATAMENTO DE ERRO
				e.printStackTrace();
			}
    	} else {
    		Employee u = new Employee();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(password);
    		try {
				ManagementUsers.addUser(u);
			} catch (NullFieldException e) {
				// FAZER LÃ“GICA PARA TRATAMENTO DE ERRO
				e.printStackTrace();
			}
    	}
    }
    
    private void editUser() {
    	String name = inputName.getText();
    	String password = inputPassword.getText();
    	String login = inputLogin.getText();
    	String type = boxType.getValue();
    	if (type == "Administrador") {
    		Administrator u = new Administrator();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(password);
    		try {
				ManagementUsers.update(Main.getIdSelected(), u);
			} catch (NullFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else {
    		Employee u = new Employee();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(password);
    		try {
				ManagementUsers.update(Main.getIdSelected(), u);
			} catch (NullFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			System.out.println(u.getName());
		}
	}	
}
