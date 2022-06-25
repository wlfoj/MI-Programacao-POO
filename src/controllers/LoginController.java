package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementUsers;

public class LoginController implements Initializable { 

    @FXML
    private Button buttonLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;
    
    @FXML
    private Label lbl;
    	
    @FXML
    void clickedLogin(MouseEvent event) throws IOException {
    	if(verificationLogin(user.getText(), new String (password.getText()))) {
    		System.out.println("Acesso Liberado!");
    		//OLHE ESSA L�GICA AQUI
    		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
			Scene cena = new Scene(anchor);
    		Main.setScene(cena);
    		//FIM DA L�GICA
    	} else {
    		System.out.println("Acesso Negado!");
    		lbl.setText("Login ou senha incorretos tente novamente");
    	}
    }
    
    //@FXML
    public boolean verificationLogin(String name, String pass) {
    	if(ManagementUsers.auth(name, pass) == null) {
    		return false;
    	}
    	return true;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttonLogin.setCursor(Cursor.HAND);	
	}

}
