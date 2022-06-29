package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementProducts;
import model.Product;

public class FormProductsController implements Initializable {


	@FXML
    private ComboBox<String> inputMedida;
	
	private String[] lista = {"L","Kg" , "g", "mL"};

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
	private void eventSave(ActionEvent event) throws IOException{
		boolean aux = true;
		//LocalDate aa= LocalDate.now();
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//now = now.parse("2019-11-05");
		//inputValidity.setValue(aa);
		// Verifica n�o existe um id selecionado
		if (Main.getIdSelected() == -1) {
			try {
				createProduct();
				aux = false;
			} catch (NegativePriceEntity e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Valor negativo");
				alert.setHeaderText("Valor do produto negativo");
				alert.setContentText("Usuário por favor preencher com valores acima de 0");
		    	alert.show();
			} catch (InsufficientQuantityProducts e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Quantidade insuficiente");
				alert.setHeaderText("Quantidade de produtos insuficiente");
				alert.setContentText("Usuário por favor preencher com quantidade de produtos suficiente");
		    	alert.show();
			} catch (DateInvalid e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Validade invalida");
				alert.setHeaderText("Data de validade anterior a atual");
				alert.setContentText("Usuário por favor preencher com data posterior a atual");
		    	alert.show();
			}
		// Caso tenha o id selecionado
		} else {
			try {
				editProduct();
				aux = false;
			} catch (DateInvalid e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Validade invalida");
				alert.setHeaderText("Data de validade anterior a atual");
				alert.setContentText("Usuário por favor preencher com data posterior a atual");
		    	alert.show();
			} catch (InsufficientQuantityProducts e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Quantidade insuficiente");
				alert.setHeaderText("Quantidade de produtos insuficiente");
				alert.setContentText("Usuário por favor preencher com quantidade de produtos suficiente");
		    	alert.show();
			} catch (NegativePriceEntity e) {
				System.out.println("Valor negativo é inválido");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Valor negativo");
				alert.setHeaderText("Valor do produto negativo");
				alert.setContentText("Usuário por favor preencher com valores acima de 0");
		    	alert.show();
			}
		}
    	// Se passar pelas etapas sem receber uma exce��o
    	if(aux==false) {
    		backToProduct();
    	}
	}
	private void createProduct() throws NegativePriceEntity, InsufficientQuantityProducts, DateInvalid{
		// Fazer os testes de erros de convers�o e gerar alerts
		Product p = new Product();
		p.setName(inputName.getText());
		p.setMedida(inputMedida.getPromptText());
		p.setPrice(Float.parseFloat(inputValue.getText()));
		p.setQtd(Integer.parseInt(inputQtd.getText()));
		p.setValidity(inputValidity.getValue());

		ManagementProducts.addProduct(p);
	}
	
	private void editProduct() throws DateInvalid, InsufficientQuantityProducts, NegativePriceEntity {
		// Fazer os testes de erros de convers�o e gerar alerts
		Product p = new Product();
		p.setName(inputName.getText());
		p.setMedida(inputMedida.getPromptText());
		p.setPrice(Float.parseFloat(inputValue.getText()));
		p.setQtd(Integer.parseInt(inputQtd.getText()));
		p.setValidity(inputValidity.getValue());
		
		ManagementProducts.update(Main.getIdSelected(), p);	
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		inputMedida.getItems().setAll(lista);
		
		inputQtd.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("[0123456789]*")) 
		        return null;
		    else
		        return c;
		    }
		));
		
		inputValue.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("[0123456789.]*")) 
		        return null;
		    else
		        return c;
		    }
		));
		
		if (Main.getIdSelected() != -1) {
			Product p = ManagementProducts.getOne(Main.getIdSelected());
			inputName.setText(p.getName());
			inputMedida.setPromptText(p.getMedida());
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