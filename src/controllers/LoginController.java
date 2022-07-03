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
    
    /**Metodo para realizar o login
     * 
     * @param event - evento disparado ao clicar login
     * @throws IOException
     */
    @FXML
    void clickedLogin(MouseEvent event) throws IOException {
    	// Jogando o User recebido em session
    	Main.setSession(ManagementUsers.auth(user.getText(), password.getText()));
    	if(Main.getSession() != null) {
    		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
			Scene cena = new Scene(anchor);
    		Main.setScene(cena);
    	} else {
    		lbl.setText("Login ou senha incorretos tente novamente");
    	}
    }
   

    /**Metodo executa ao inicializar o componente
     * 
     */
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttonLogin.setCursor(Cursor.HAND);	
	}

}
