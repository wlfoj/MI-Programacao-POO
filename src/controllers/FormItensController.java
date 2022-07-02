package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementProducts;
import model.Product;

public class FormItensController implements Initializable {
	ObservableList<Product> observableListaProduct; 

	@FXML
    void eventBack(ActionEvent event) throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }

    
    @FXML
    private Button btnAdicionar, btnSave, btnBack, btnDelete;

    @FXML
    private ComboBox<?> comboBoxProdutos;

    @FXML
    private TextField inputCategory, inputName, inputQuality, inputValue, inputQuatity;

    @FXML
    private TextArea inputDescription;

    @FXML
    private Label labelUnd;

    @FXML
    private TableView<Product> tableProductes;
    
    @FXML
    private TableColumn<Product, String> tableMedida;
    
    @FXML
    private TableColumn<Product, String> tableProduct;

    @FXML
    private TableColumn<Product, Integer> tableQtd;
    
    @FXML
    void clickLine(MouseEvent event) {
    	btnDelete.setDisable(false);
    }

    @FXML
    void eventAdicionar(ActionEvent event) {

    }

    @FXML
    void eventDelete(ActionEvent event) {

    }

    @FXML
    void eventSave(ActionEvent event) {

    }
    
    @FXML
    void clickComboProduct(MouseEvent event) {
    	inputQuatity.setDisable(false);
    }
    
    public void refreshTableViewProducts() {
		observableListaProduct = FXCollections.observableArrayList(ManagementProducts.listAllProducts());
		tableProductes.setItems(observableListaProduct);
		
		tableMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
		tableProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refreshTableViewProducts();
		
	}

}

