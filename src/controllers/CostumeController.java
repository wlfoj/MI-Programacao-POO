package controllers;

import java.net.URL;
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
import model.Costumer;
import model.ManagementCostumer;

public class CostumeController implements Initializable {

	ObservableList<Costumer> observableListaClientes;

    @FXML
    private TableColumn<Costumer, Integer> tableId;
	
    @FXML
    private TableColumn<Costumer, String> cpfTable;

    @FXML
    private TableColumn<Costumer, String> emailTable;

    @FXML
    private TableColumn<Costumer, String> nameTable;

    @FXML
    private TableView<Costumer> tableView;

    @FXML
    private TableColumn<Costumer, String> telTable;
	
	@FXML
	private void eventCreate(ActionEvent e) {
		System.out.println("Formulario Clientes");
		Main.scenes("formClientes");
		
		btnEdit.setDisable(true);
		btnRemove.setDisable(true);
	}
	

    @FXML
    void clickLine(MouseEvent event) {
    	Costumer p = tableView.getSelectionModel().getSelectedItem();
    	if(p == null) {
    	} else {
    		String name=p.getName();
    		String email=p.getEmail();
    		Integer id=p.getId();
    		String cpf=p.getCpf();
    		System.out.println("nome:  "+name+"|   cpf:"+cpf+"|  email: "+email+"|  id:  "+id);
    	}
    	btnEdit.setDisable(false);
    	btnRemove.setDisable(false);
    }
	
	@FXML
	private void eventBack(ActionEvent e) {
		System.out.println("Voltar Cliente para Menu");
		Main.scenes("backclientes");
		
		btnEdit.setDisable(true);
		btnRemove.setDisable(true);
	}
	
	@FXML
    private Button btnCreate, btnBack, btt2, btt3, btnEdit, btnRemove, btnPrint;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btt3.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnRemove.setCursor(Cursor.HAND);
		btnPrint.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		
		refreshTableView();
		
	}
	public void refreshTableView() {
		
		observableListaClientes = FXCollections.observableArrayList(ManagementCostumer.listAllCostumer());
		tableView.setItems(observableListaClientes);
		
		tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
		cpfTable.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		emailTable.setCellValueFactory(new PropertyValueFactory<>("email"));
		nameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
		telTable.setCellValueFactory(new PropertyValueFactory<>("telefone"));
	}
}