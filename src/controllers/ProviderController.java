package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementProvider;
import model.Provider;

public class ProviderController implements Initializable{

	ObservableList<Provider> observableListaProvider; 
	

    @FXML
    private TableColumn<Provider, String> tableAdress;

    @FXML
    private TableColumn<Provider, String> tableCnpj;

    @FXML
    private TableColumn<Provider, Integer> tableId;

    @FXML
    private TableColumn<Provider, String> tableName;

    @FXML
    private TableColumn<Provider, String> tableProducts;

    @FXML
    private TableView<Provider> tableView;
	
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
    @FXML
    void actionDelete(ActionEvent event) {
    	
    }
    
    @FXML
    void clickLine(MouseEvent event) {
    	btnEdit.setDisable(false);
    	btnDelete.setDisable(false);
    }
    
    @FXML
    void actionEdit(ActionEvent event) throws IOException {
    	Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
    @FXML
    void actionCreate(ActionEvent event) throws IOException {
    	Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
	@FXML
    private Button btnBack, btnCreate, btnEdit, btnDelete; //btt3, btt4, //btt5;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		refreshTableView();
	}
	
	public void refreshTableView() {
		observableListaProvider = FXCollections.observableArrayList(ManagementProvider.listAllProvider());
		tableView.setItems(observableListaProvider);
		
		tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
		tableCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
		tableProducts.setCellValueFactory(new PropertyValueFactory<>("products"));
	}
}
