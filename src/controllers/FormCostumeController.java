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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.Costumer;
import model.ManagementCostumer;
import model.ManagementUsers;
import model.User;

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
    

	
    @FXML
    void eventSave(ActionEvent event) throws IOException {
    	if(Main.getIdSelected() == -1) {
    		//Cria
    		createCostumer();
    	}
    	else {
    		//edita
    		editCostumer();
    	}
    	backToCostumer();
    }
	
    private void createCostumer() {
    	String name = inputName.getText();
    	String cpf = inputCpf.getText();
    	String email = inputEmail.getText();
    	String phone = inputPhone.getText();
    	
    	Costumer c = new Costumer();
    	c.setCpf(cpf);
    	c.setEmail(email);
    	c.setName(name);
    	c.setTelefone(phone);
    	
    	try {
			ManagementCostumer.addCostumer(c);
		} catch (NullFieldException e) {
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Erro");
	    	alert.setHeaderText("Campos vazios");
	    	alert.setContentText("Para prosseguir, preencha corretamente os campos");
	    	alert.show();
		} catch (ObjectRegistred e) {
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Erro");
	    	alert.setHeaderText("CPF registrado");
	    	alert.setContentText("Para prosseguir, insira outro cpf");
	    	alert.show();
		}
    }
    
    private void editCostumer() {
    	String name = inputName.getText();
    	String cpf = inputCpf.getText();
    	String email = inputEmail.getText();
    	String phone = inputPhone.getText();
    	
    	Costumer c = new Costumer();
    	c.setCpf(cpf);
    	c.setEmail(email);
    	c.setName(name);
    	c.setTelefone(phone);
    	
    	try {
			ManagementCostumer.update(Main.getIdSelected(), c);
		} catch (NullFieldException e) {
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Erro");
	    	alert.setHeaderText("Campos vazios");
	    	alert.setContentText("Para prosseguir, preencha corretamente os campos");
	    	alert.show();
		}
    	
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		//
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
	
	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		backToCostumer();
	}
	
	private void backToCostumer() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorCostume.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	
}
