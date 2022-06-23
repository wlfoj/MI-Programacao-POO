package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class FormSalesController implements Initializable {

	@FXML
	private Button btnBack, btnSave, btnAdicionar, btnDelete;

    @FXML
    void actionBack(ActionEvent event) throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }

    @FXML
    private ComboBox<?> comboBoxPratos;
    

    @FXML
    private TextField inputTotalValue;
    
    @FXML
    private ComboBox<String> comboBoxPaymentMethod;
    private String[] lista = {"Avista","Pix","Cartão de débito", "Cartão de crédito"};
    
    @FXML
    private TableView<?> tableView;

    @FXML
    void actionAdicionar(ActionEvent event) {

    }

    @FXML
    void actionDelete(ActionEvent event) {

    }

    @FXML
    void actionSave(ActionEvent event) {

    }

    @FXML
    void clickLine(MouseEvent event) {
    	btnDelete.setDisable(false);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboBoxPaymentMethod.getItems().setAll(lista);
		
	}
	
}
