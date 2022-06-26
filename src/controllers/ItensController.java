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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import main.Main;
import model.Item;
import model.ManagementItens;

public class ItensController implements Initializable {

	ObservableList<Item> observableListItem;
	
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
    @FXML
    void actionCreate(ActionEvent event) throws IOException {
    	Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
    @FXML
    private TableColumn<Item, String> tableCategory;

    @FXML
    private TableColumn<Item, String> tableDescription;

    @FXML
    private TableColumn<Item, Integer> tableId;

    @FXML
    private TableColumn<Item, String> tableName;

    @FXML
    private TableColumn<Item, Float> tableValue;
    
    @FXML
    private TableView<Item> tableView;
    
    @FXML
    void actionEdit(ActionEvent event) {

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
    private Button btnBack, btnEdit, btnCreate, btnDelete;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		
		observableListItem = FXCollections.observableArrayList(ManagementItens.listAllItens());
		tableView.setItems(observableListItem);
		
		tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableCategory.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		tableDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		tableValue.setCellValueFactory(new PropertyValueFactory<>("price"));
		
	}

}
