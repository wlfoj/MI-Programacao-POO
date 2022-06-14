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
import model.Product;

public class FormProviderController implements Initializable {

    @FXML
    private Button btnAddProduct,btnBack,btnDeleteProduct,btnSave;

    @FXML
    private ComboBox<Product> comboProducts;

    @FXML
    private TextField inputAdress, inputName, inputCnpj;
    
    @FXML
    private TableView<?> tableProducts;

    @FXML
    void actionAddProduct(ActionEvent event) {

    }

    @FXML
    void actionBack(ActionEvent event) throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }

    @FXML
    void actionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void clickLine(MouseEvent event) {

    }

    @FXML
    void actionSave(ActionEvent event) {

    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Product p = new Product();
		p.setName("pao");
		p.setId(0);
		comboProducts.;
	}
}
