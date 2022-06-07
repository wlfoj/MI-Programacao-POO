package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    void clickedLogin(MouseEvent event) {
    	if(verificationLogin(user.getText(), new String (password.getText()))) {
    		System.out.println("Acesso Liberado!");
    		Main.scenes("Acesso Liberado!");
    	} else {
    		System.out.println("Acesso Negado!");
    		lbl.setText("Login ou senha incorretos tente novamente");
    	}
    }
    
    //@FXML
    public boolean verificationLogin(String name, String pass) {
    	return name.equals("admin") && pass.equals("1234");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttonLogin.setCursor(Cursor.HAND);
		
	}

}
