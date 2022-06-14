package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private Button btnBack, btnCreate, btnEdit, btnRemove, btnPrint;
	
	private Integer idSelected;
	
	
	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	

    @FXML
    void eventEdit(ActionEvent event) throws IOException {
		Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
	
    @FXML
    void eventShowFormProducts(ActionEvent event) throws IOException {
		Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
    
    @FXML
    void eventDelete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	//VERIFICAR QUANDO PRESSIONAR OK!
    	alert.setTitle("Deletar");
    	alert.setHeaderText("Realmente deseja excluir?");
    	alert.setContentText("Ao apagar as informações não serão mais recuperadas");
    	alert.show();
    	ManagementProducts.delete(idSelected);
    	refreshTableView();
    }
    
    
    @FXML
    void clickLine(MouseEvent event) {
    	Product p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected=p.getId();
    	}
    	btnEdit.setDisable(false);
    	btnRemove.setDisable(false);
    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnRemove.setCursor(Cursor.HAND);
		btnPrint.setCursor(Cursor.HAND);
		refreshTableView();
	}
	
	
	public void refreshTableView() {
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