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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementSales;
import model.Sale;
	
public class SalesController implements Initializable {
	
	ObservableList<Sale> observableListSales;
	
	private Integer idSelected;
	
    @FXML
    private TableColumn<Sale, Calendar> tableDate;

    @FXML
    private TableColumn<Sale, Integer> tableId;

    @FXML
    private TableColumn<Sale, String> tablePaymentMethod;

    @FXML
    private TableColumn<Sale, Float> tableTotalValue;
	
    @FXML
    private TableView<Sale> tableView;
    
	@FXML
    private Button btnBack, btnCreate, btnEdit, btnDelete;
	
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
    @FXML
    void actionCreate(ActionEvent event) throws IOException {
    	Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
    @FXML
    void actionEdit(ActionEvent event) throws IOException {
    	Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
	
		deleteSales();
		refreshTableView();
	}
	
	public void deleteSales() {
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
					ManagementSales.delete(idSelected);
					refreshTableView();
				} 
			});
		});
	}
	
	public void refreshTableView() {
		observableListSales = FXCollections.observableArrayList(ManagementSales.listAllSale());
		tableView.setItems(observableListSales);
		
		tableTotalValue.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		tablePaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
		tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		
	}

    @FXML
    void clickLine(MouseEvent event) {
    	Sale p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected =p.getId();
    	}
    	btnDelete.setDisable(false);
    	btnEdit.setDisable(false);
    }
}