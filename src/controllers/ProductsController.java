package controllers;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;
import model.ManagementProducts;
import model.Product;
	
public class ProductsController implements Initializable {

	ObservableList<Product> observableListaProduct; 
	
    @FXML
    private TableColumn<Product, Integer> tableId;

    @FXML
    private TableColumn<Product, String> tableMedida;

    @FXML
    private TableColumn<Product, String> tableName;

    @FXML
    private TableColumn<Product, Integer> tableQtd;

    @FXML
    private TableColumn<Product, Calendar> tableValidade;

    @FXML
    private TableColumn<Product, Float> tableValor;

    @FXML
    private TableView<Product> tableView;
	
	@FXML
	private void eventoOne(ActionEvent e) {
		System.out.println("Voltar Produtos para Menu");
		Main.scenes("backprodutos");
		btt4.setDisable(true);
    	btt3.setDisable(true);
	}
	
    @FXML
    void eventoTwo(ActionEvent event) {
    	System.out.println("Formulario Produtos");
    	Main.scenes("formProdutc");
    }
	
    @FXML
    void clickLine(MouseEvent event) {
    	Product p = tableView.getSelectionModel().getSelectedItem();
    	if(p == null) {
    		
    	} else {
    		String name=p.getName();
    		String medida=p.getMedida();
    		Calendar validade=p.getValidity();
    		Integer quantidade=p.getQtd();
    		Float valor=p.getPrice();
    		System.out.println("nome:  "+name+"|  Medida: "+medida+"|  validade:  "+validade+"|   quantidade: "+quantidade+"|   Valor: "+valor);
    	}
    	btt4.setDisable(false);
    	btt3.setDisable(false);
    }
	
	@FXML
    private Button btt1, btt2, btt3, btt4, btt5;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btt3.setCursor(Cursor.HAND);
		btt4.setCursor(Cursor.HAND);
		btt5.setCursor(Cursor.HAND);
		
		observableListaProduct = FXCollections.observableArrayList(ManagementProducts.listAllProducts());
		tableView.setItems(observableListaProduct);

		tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		tableMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
		tableValidade.setCellValueFactory(new PropertyValueFactory<>("validity"));
		tableValor.setCellValueFactory(new PropertyValueFactory<>("price"));
	}
	
}