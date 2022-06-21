package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
	private void eventSave(ActionEvent event) throws IOException, DateInvalid, InsufficientQuantityProducts, NegativePriceEntity {
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
	
	private void createProduct() throws DateInvalid, InsufficientQuantityProducts, NegativePriceEntity{
		// Fazer os testes de erros de convers�o e gerar alerts
		Product p = new Product();
		p.setName(inputName.getText());
		p.setMedida(inputMedida.getText());
		p.setPrice(Float.parseFloat(inputValue.getText()));
		p.setQtd(Integer.parseInt(inputQtd.getText()));
		p.setValidity(inputValidity.getValue());
		
		try {
			ManagementProducts.addProduct(p);
		} catch (NegativePriceEntity e) {
			System.out.println("Valor negativo é inválido");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Valor negativo");
			alert.setHeaderText("Valor do produto negativo");
			alert.setContentText("Usuário por favor preencher com valores acima de 0");
	    	alert.show();
		} catch (InsufficientQuantityProducts e) {
			System.out.println("Quantidades de produtos insuficientes");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Quantidade insuficiente");
			alert.setHeaderText("Quantidade de produtos insuficiente");
			alert.setContentText("Usuário por favor preencher com quantidade de produtos suficiente");
	    	alert.show();
		} catch (DateInvalid e) {
			System.out.println("Data de validade invalida");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Validade invalida");
			alert.setHeaderText("Data de validade anterior a atual");
			alert.setContentText("Usuário por favor preencher com data posterior a atual");
	    	alert.show();
		}
		
	}
	
	private void editProduct() throws DateInvalid, InsufficientQuantityProducts {
		// Fazer os testes de erros de convers�o e gerar alerts
		Product p = new Product();
		p.setName(inputName.getText());
		p.setMedida(inputMedida.getText());
		p.setPrice(Float.parseFloat(inputValue.getText()));
		p.setQtd(Integer.parseInt(inputQtd.getText()));
		p.setValidity(inputValidity.getValue());
		
		try {
			ManagementProducts.update(Main.getIdSelected(), p);
		} catch (NegativePriceEntity e) {
			System.out.println("Valor negativo é inválido");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Valor negativo");
			alert.setHeaderText("Valor do produto negativo");
			alert.setContentText("Usuário por favor preencher com valores acima de 0");
	    	alert.show();
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