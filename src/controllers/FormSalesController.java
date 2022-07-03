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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.Item;
import model.ManagementItens;
import model.ManagementProvider;

public class FormSalesController implements Initializable {

	ObservableList<Item> observableListItem; 
	
	@FXML
	private Button btnBack, btnSave, btnAdicionar, btnDelete;

    @FXML
    void actionBack(ActionEvent event) throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }


    @FXML
    private TableColumn<Item, Integer> columnId;

    @FXML
    private TableColumn<Item, String> columnName;

    @FXML
    private TableColumn<Item, String> columnPrice;
    
    @FXML
    private ComboBox<?> comboBoxPratos;
    

    @FXML
    private TextField inputTotalValue;
    
    @FXML
    private ComboBox<String> comboBoxPaymentMethod;
    private String[] lista = {"Avista","Pix","Cartão de débito", "Cartão de crédito"};
    
    @FXML
    private TableView<Item> tableView;

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
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		btnAdicionar.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		comboBoxPaymentMethod.getItems().setAll(lista);
		
		refreshTableView();
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Float
		inputTotalValue.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("\\d*(\\.\\d*)?")) 
		        return null;
		    else
		        return c;
		    }
		));
		
	}
	
	public void refreshTableView() {
		observableListItem = FXCollections.observableArrayList(ManagementItens.listAllItens());
		tableView.setItems(observableListItem);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
	}
	
}
