package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button buttonLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;

    @FXML
    void clickedLogin(MouseEvent event) {
    	if(verificationLogin(user.getText(), new String (password.getText()))) {
    		System.out.println("Acesso Liberado!");
    		Main.scenes("Acesso Liberado!");
    	} else {
    		System.out.println("Acesso Negado!");
    	}
    }
    
    //@FXML
    public boolean verificationLogin(String name, String pass) {
    	return name.equals("admin") && pass.equals("1234");
    }

}
