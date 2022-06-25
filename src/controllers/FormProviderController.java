package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.NullFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementProducts;
import model.ManagementProvider;
import model.Product;
import model.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class FormProviderController implements Initializable {
	private Integer idSelected;
	// COMPONENTES DA JAVAFX
    @FXML
    private Button btnAddProduct,btnBack,btnDeleteProduct,btnSave;
    @FXML
    private ComboBox<String> comboProducts;
    @FXML
    private TextField inputAdress, inputName, inputCnpj;
    
    // ESTRUTURA DE DADOS PRO COMBOBOX
    private static ArrayList<String> comboNameList;
    private static ArrayList<Product> listAllProducts;
    private ArrayList<Product> tableViewList = new ArrayList<Product>();
    private static Map<String, Integer> HashMapProducts = new HashMap<String,Integer>();

    ObservableList<Product> observableListaProduct; 
    
    @FXML
    private TableColumn<Product, Integer> tableId;

    @FXML
    private TableColumn<Product, String> tableName;
    
    @FXML
    private TableView<Product> tableProducts;

    @FXML
    void actionAddProduct(ActionEvent event) {
    	Integer id;
    	Product p;
    	// Pega o nome da combobox, converte para id
    	id = HashMapProducts.get(comboProducts.getValue());
    	// Busca o produto com o id
    	p = ManagementProducts.getOne(id);
    	// Se existir produto, joga na lista
    	if (p != null) {
    		tableViewList.add(p);
    		refreshTableViewProducts();
    	}
    }
    
    @FXML
    void actionBack(ActionEvent event) throws IOException {
    	backToProvider();
    }

    private void backToProvider() throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
    @FXML
    void actionDeleteProduct(ActionEvent event) {
    	Product p;
    	// Procurando pelo produto selecionado
    	for (int i = 0; i < tableViewList.size(); i++) {
			p = tableViewList.get(i);
			if (p.getId() == idSelected) {
				tableViewList.remove(i);
			}
		}
    	refreshTableViewProducts();
    }

    @FXML
    void clickLine(MouseEvent event) {
    	btnDeleteProduct.setDisable(false);
    	Product p = tableProducts.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected =p.getId();
    	}
    }

    @FXML
    void tableLine(ActionEvent event) {
    	
    }
    
    @FXML
    void actionSave(ActionEvent event) throws IOException {
    	boolean aux = true;
    	// Verifica n�o existe um id selecionado
		if (Main.getIdSelected() == -1) {
	    		try {
					createProvider();
					aux = false;
				} catch (NullFieldException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Campos vazios");
					alert.setContentText("Preencha todos os campos");
					alert.show();
				}
	    // Caso tenha o id selecionado
	    } else {
	    	try {
				editProvider();
				aux = false;
			} catch (NullFieldException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Campos vazios");
				alert.setContentText("Preencha todos os campos");
				alert.show();
			}	
	    }
		// Se passar pelas etapas sem receber uma exce��o
		if (aux == false) {
			backToProvider();
		}
    }
    
    private void createProvider() throws NullFieldException {
    	ArrayList<Integer> idListProducts = new ArrayList<Integer>();
    	Provider p = new Provider();
		p.setName(inputName.getText());
		p.setCnpj(inputCnpj.getText());
		p.setAddress(inputAdress.getText());
		
		// Convertendo a lista de produtos em lista de ids
		for (int i = 0; i < tableViewList.size(); i++) {
			idListProducts.add(tableViewList.get(i).getId());
		}
		p.setProducts(idListProducts);
		
		ManagementProvider.addProvider(p);
    }
    
    private void editProvider() throws NullFieldException {
    	ArrayList<Integer> idListProducts = new ArrayList<Integer>();
    	Provider p = new Provider();
		p.setName(inputName.getText());
		p.setCnpj(inputCnpj.getText());
		p.setAddress(inputAdress.getText());
		// Convertendo a lista de produtos em lista de ids
		for (int i = 0; i < tableViewList.size(); i++) {
			idListProducts.add(tableViewList.get(i).getId());
		}
		p.setProducts(idListProducts);
		
		ManagementProvider.update(Main.getIdSelected(), p);
    }
    
    public void refreshTableViewProducts() {
		observableListaProduct = FXCollections.observableArrayList(tableViewList);
		tableProducts.setItems(observableListaProduct);
		
		tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnAddProduct.setCursor(Cursor.HAND);
		btnBack.setCursor(Cursor.HAND);
		btnDeleteProduct.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		
		if (Main.getIdSelected() != -1) {
			Provider p = ManagementProvider.getOne(Main.getIdSelected());
			inputName.setText(p.getName());
			inputCnpj.setText(p.getCnpj());
			inputAdress.setText(p.getAddress());
			tableViewList = ManagementProducts.convertInProductList(p.getProducts());
		}
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
		refreshTableViewProducts();
	}
}
