package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementProducts;
import model.Product;

public class FormProductsController implements Initializable {


    @FXML
    private TextField inputMedida;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputQtd;

    @FXML
    private DatePicker inputValidity;

    @FXML
    private TextField inputValue;

    @FXML
    private Button btnBack, btnSave;
	

	@FXML
	private void eventSave(ActionEvent event) throws IOException {
		//LocalDate aa= LocalDate.now();
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//now = now.parse("2019-11-05");
		//inputValidity.setValue(aa);
		if (Main.getIdSelected() == -1) {
			//Cria
			createProduct();
		} else {
			//edita
			editProduct();
		}
		backToProduct();

	}
	
	private void createProduct() {
		// Fazer os testes de erros de conversão e gerar alerts
		Product p = new Product();
		p.setName(inputName.getText());
		p.setMedida(inputMedida.getText());
		p.setPrice(Float.parseFloat(inputValue.getText()));
		p.setQtd(Integer.parseInt(inputQtd.getText()));
		p.setValidity(inputValidity.getValue());
		
		try {
			ManagementProducts.addProduct(p);
		} catch (NegativePriceEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsufficientQuantityProducts e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void editProduct() {
		// Fazer os testes de erros de conversão e gerar alerts
		Product p = new Product();
		p.setName(inputName.getText());
		p.setMedida(inputMedida.getText());
		p.setPrice(Float.parseFloat(inputValue.getText()));
		p.setQtd(Integer.parseInt(inputQtd.getText()));
		p.setValidity(inputValidity.getValue());
		
		try {
			ManagementProducts.update(Main.getIdSelected(), p);
		} catch (NullFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NegativePriceEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		if (Main.getIdSelected() != -1) {
			Product p = ManagementProducts.getOne(Main.getIdSelected());
			inputName.setText(p.getName());
			inputMedida.setText(p.getMedida());
			inputQtd.setText(Integer.toString(p.getQtd()));
			inputValue.setText(Float.toString(p.getPrice()));
			inputValidity.setValue(p.getValidity());
		}
		
	}
	
	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		backToProduct();
	}
	
	private void backToProduct() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	
}