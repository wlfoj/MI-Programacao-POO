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
	private void eventoTwo(ActionEvent e) {
		System.out.println("Formulario Clientes");
		Main.scenes("formClientes");
		
		btt4.setDisable(true);
		btt5.setDisable(true);
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
    	btt4.setDisable(false);
    	btt5.setDisable(false);
    }
	
	@FXML
	private void eventoOne(ActionEvent e) {
		System.out.println("Voltar Cliente para Menu");
		Main.scenes("backclientes");
		
		btt4.setDisable(true);
		btt5.setDisable(true);
	}
	
	@FXML
    private Button btt1, btt2, btt3, btt4, btt5, btt6;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btt3.setCursor(Cursor.HAND);
		btt4.setCursor(Cursor.HAND);
		btt5.setCursor(Cursor.HAND);
		btt6.setCursor(Cursor.HAND);
		
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