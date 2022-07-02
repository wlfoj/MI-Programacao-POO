package controllers;

import java.io.IOException;
import java.net.URL;
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
import utils.AlertsController;

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
    private ComboBox<String> boxType; // Combobox para selecionar os tipos de usuário
    
    // Opções de tipos de usuarios para selecionar no Combobox
    private String[] lista = {"Administrador","Fucionario"};
    
    /**Metodo para salvar/criar um usuário com os valores digitados nos campos
     * 
     * @param event - Evento disparado ao clicar no botão de salvar
     * @throws IOException
     */
    @FXML
    void actionSave(ActionEvent event) throws IOException  {
    	boolean aux = true;
    	// Verifica não existe um id selecionado
    	if(Main.getIdSelected() == -1) {
    		try {
				createNewUser();
				aux = false;
			} catch (NullFieldException e) {
				AlertsController.alertErrorDate(e.getMessage(), "Campos vazios","Preencha todos os campos" );
			} catch (ObjectRegistred e) {
				AlertsController.alertErrorDate(e.getMessage(), "Login registrado","Já existe um usuário com este login" );
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
    	// Se passar pelas etapas sem receber uma exceção, retorna para tela anterior
    	if (aux == false) {
    		backToUser();
    	}
    	
    }
   
    /**Metodo para criar um novo usuário
     * 
     * @throws NullFieldException - Exceção disparada caso não tenha campos preenchidos
     * @throws ObjectRegistred - Exceção disparada caso o login já tenha sido registrado
     */
    private void createNewUser() throws NullFieldException, ObjectRegistred {
    	String name = inputName.getText();
    	String password = inputPassword.getText();
    	String login = inputLogin.getText();
    	String type = boxType.getValue();
		FacedeManagement.addUser(name, login, password, type);
    }
    
    /**Metodo para editar um usuário
     * 
     * @throws NullFieldException - Exceção disparada caso não tenha campos preenchidos
     */
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
    
    /**Metodo para retornar a tela anterior
     * 
     * @param e - Evento disparado ao clicar no botão de voltar
     * @throws IOException
     */
	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		backToUser();
	}
	
	/**Metodo para retornar a tela de usuários
	 * 
	 * @throws IOException
	 */
	private void backToUser() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorUser.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	/**Metodo executado ao inicializar o componente
	 * 
	 */
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
