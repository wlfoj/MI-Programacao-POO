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

	private Integer idReceived=-1;
	
    @FXML
    private TextField inputLogin;
	
 	@FXML
    private TextField inputName;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private ComboBox<String> boxType;
    private String[] lista = {"Administrador","FucionÃ¡rio"};
    
    @FXML
    void actionSave(ActionEvent event)  {
    	String name = inputName.getText();
    	String password = inputPassword.getText();
    	String login = inputLogin.getText();
    	String type = boxType.getValue();
    	System.out.println(type);
    	
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
    	System.out.println(ManagementUsers.listAllUsers());
    }
    
    @FXML
    private Button btnBack, btnSave;
    
	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if (idReceived == -1) {
			//EXCECUÃ‡Ã•ES PARA CRIAR UM NOVO USUÃ�RIO
			btnBack.setCursor(Cursor.HAND);
			btnSave.setCursor(Cursor.HAND);
			
			boxType.getItems().setAll(lista);
			boxType.setValue("FucionÃ¡rio");
		}else {
			//EXCECUÃ‡Ã•ES PARA EDITAR UM NOVO USUÃ�RIO
			User u = new User();
			u = ManagementUsers.getOne(idReceived);
			inputName.setText(u.getName());
			inputLogin.setText(u.getLogin());
			inputPassword.setText(u.getPass());
			System.out.println(u.getName());
		}
	}	
}
