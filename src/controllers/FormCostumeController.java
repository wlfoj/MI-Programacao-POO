package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.Costumer;
import model.ManagementCostumer;
import utils.Alerts;

/** Classe responsavel pelo controle do formulario de clientes
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class FormCostumeController implements Initializable {
    
    @FXML
    private Button btnBack, btnSave;
    
    @FXML
    private TextField inputCpf;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPhone;
    

	/**Metodo para salvar os dados do formulario no cliente
	 * 
	 * @param event - Evento disparado ao pressionar o botao de salvar
	 * @throws IOException
	 */
    @FXML
    void actionSave(ActionEvent event) throws IOException {
    	boolean aux = true;
    	// Verifica n�o existe um id selecionado
    	if(Main.getIdSelected() == -1) {
    		try {
				createCostumer();
				aux = false;
			} catch (NullFieldException e) {
				Alerts.alertError(e.getMessage(), "Campos vazios","Preencha todos os campos" );
			} catch (ObjectRegistred e) {
				Alerts.alertError(e.getMessage(), "Login registrado","J� existe um usu�rio com este cpf" );
			}
    	}
    	// Caso tenha o id selecionado
    	else {
    		try {
				editCostumer();
				aux = false;
			} catch (NullFieldException e) {
				Alerts.alertError(e.getMessage(), "Campos vazios","Preencha todos os campos" );
			}
    	}
    	
    	// Se passar pelas etapas sem receber uma exce��o
    	if(aux==false) {
    		backToCostumer();
    	}
    	
    }
	
    
    /** Metodo para criar um novo cliente
     * 
     * @throws NullFieldException - Excecao para caso tenha certos campos vazios
     * @throws ObjectRegistred - Excecao caso o CPF ja esteja registrado
     */
    private void createCostumer() throws NullFieldException, ObjectRegistred { 	
    	Costumer c = new Costumer();
    	c.setCpf(inputCpf.getText());
    	c.setEmail(inputEmail.getText());
    	c.setName(inputName.getText());
    	c.setTelefone(inputPhone.getText());
    	
		ManagementCostumer.addCostumer(c);
    }
    
    
    /**Metodo para editar um cliente
     * 
     * @throws NullFieldException - Excecao para caso tenha certos campos vazios
     */
    private void editCostumer() throws NullFieldException {
    	Costumer c = new Costumer();
    	c.setCpf(inputCpf.getText());
    	c.setEmail(inputEmail.getText());
    	c.setName(inputName.getText());
    	c.setTelefone(inputPhone.getText());
    	
		ManagementCostumer.update(Main.getIdSelected(), c);
    }
    
    
    /**Metodo executa ao inicializar o componente
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Inteiros
		inputCpf.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("[0123456789]*")) 
		        return null;
		    else
		        return c;
		    }
		));
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Inteiros
		inputPhone.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("[0123456789-]*")) 
		        return null;
		    else
		        return c;
		    }
		));
		
		if(Main.getIdSelected()!=-1) {
			Costumer c = new Costumer();
			c = ManagementCostumer.getOne(Main.getIdSelected());
			//
			inputName.setText(c.getName());
			inputCpf.setText(c.getCpf());
			inputEmail.setText(c.getEmail());
			inputPhone.setText(c.getTelefone());
		}
	}
	
	
	/**Metodo para retornar a tela anterior
	 * 
	 * @param e - Evento disparado ao pressionar botao de voltar
	 * @throws IOException
	 */
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		backToCostumer();
	}
	
	
	/**Metodo que retorna a tela anterior
	 * 
	 * @throws IOException
	 */
	private void backToCostumer() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorCostume.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
}
