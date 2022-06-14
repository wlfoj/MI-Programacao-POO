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
import model.ManagementProducts;
import model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class FormProviderController implements Initializable {

    @FXML
    private Button btnAddProduct,btnBack,btnDeleteProduct,btnSave;

    @FXML
    private ComboBox<String> comboProducts;
    
    private static ArrayList<String> comboNameList;
    
    private static ArrayList<Product> listAllProducts;
    
    private static Map<String, Integer> HashMapProducts = new HashMap<String,Integer>();

    @FXML
    private TextField inputAdress, inputName, inputCnpj;
    
    @FXML
    private TableView<?> tableProducts;

    @FXML
    void actionAddProduct(ActionEvent event) {
    	ArrayList<Integer> listProducts = new ArrayList<Integer>();
    	//Falta fazer o percorrer
    	// Percorre a lista da tabela e em cada linha, cruza o nome do campo com o id e adiciona na lista
    	listProducts.add(HashMapProducts.get(comboProducts.getValue()));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboNameList = new ArrayList<String>();
		Product p;
		//comboProducts.setValue(p);
		listAllProducts = ManagementProducts.listAllProducts();
		
		for (int i = 0; i < listAllProducts.size(); i++) {
			p = listAllProducts.get(i);
			// Adicionando na lista do comboBox
			comboNameList.add(p.getName());
			// Adicionando na hash pro futuro
			HashMapProducts.put(p.getName(), p.getId());
		}
		// Lendo os produtos no combo
		comboProducts.getItems().setAll(comboNameList);
		
	}
}
