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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
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
	
	private Integer idSelected;
	
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
    void actionEdit(ActionEvent event) throws IOException {
    	Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
    @FXML
    void actionDelete(ActionEvent event) {

    }
   
    @FXML
    void clickLine(MouseEvent event) {
    	Item p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected =p.getId();
    	}
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
		
		deleteItem();
		refreshTableView();
	}
	
	public void deleteItem() {
		//Adicionando o evento de deletar e configurando comportamento do alert
				btnDelete.setOnAction(e-> {
					Alert deleteExe = new Alert(Alert.AlertType.CONFIRMATION);
					
					ButtonType btnOk = new ButtonType("Deletar");
					ButtonType btnCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
					
					deleteExe.initOwner(btnDelete.getScene().getWindow());
					deleteExe.setTitle("Deletar");
					deleteExe.setHeaderText("Deseja realmente deletar?");
					deleteExe.setContentText("Ao apagar as informações não serão mais recuperadas");
					deleteExe.getButtonTypes().setAll(btnOk,btnCancel);
					deleteExe.showAndWait().ifPresent(a -> {
						if (a == btnOk) {
							ManagementItens.delete(idSelected);
							refreshTableView();
						} 
					});
				});
	}
	
	public void refreshTableView() {
		observableListItem = FXCollections.observableArrayList(ManagementItens.listAllItens());
		tableView.setItems(observableListItem);
		
		tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tableCategory.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		tableDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		tableValue.setCellValueFactory(new PropertyValueFactory<>("price"));
	}
	
}
