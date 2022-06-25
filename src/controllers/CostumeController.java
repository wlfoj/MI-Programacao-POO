
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private Button btnCreate, btnBack, btnEdit, btnRemove, btnPrint;
	
	private Integer idSelected;
	
	@FXML
	private void eventCreate(ActionEvent e) throws IOException {
		Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioCostume.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
		
		//	btnEdit.setDisable(true);
		//	btnRemove.setDisable(true);
	}
	
    //EVENTO DE EDITAR AQUI AO CLICAR ABRIR FORMULÃ�RIO!
    @FXML
    void eventEdit(ActionEvent event) throws IOException {
		Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioCostume.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);

    }

    
    @FXML
    void clickLine(MouseEvent event) {
    	Costumer p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected=p.getId();
    	}
    	btnEdit.setDisable(false);
    	btnRemove.setDisable(false);
    }
	
    
   //EVENTO DE DELETAR AQUI AO CLICAR DELETAR ITEM DA TABLE VIEW
    @FXML
    void eventDelete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	//VERIFICAR QUANDO PRESSIONAR OK!
    	alert.setTitle("Deletar");
    	alert.setHeaderText("Realmente deseja excluir?");
    	alert.setContentText("Ao apagar as informaÃ§Ãµes nÃ£o serÃ£o mais recuperadas");
    	alert.show();
    	ManagementCostumer.delete(idSelected);
    	refreshTableView();
    }
    
	@FXML
	private void eventBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnRemove.setCursor(Cursor.HAND);
		btnPrint.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		
		//Adicionando o evento de deletar e configurando comportamento do alert
		btnRemove.setOnAction(e-> {
			Alert deleteExe = new Alert(Alert.AlertType.CONFIRMATION);
			
			ButtonType btnOk = new ButtonType("Deletar");
			ButtonType btnCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
			
			deleteExe.initOwner(btnRemove.getScene().getWindow());
			deleteExe.setTitle("Deletar");
			deleteExe.setHeaderText("Deseja realmente deletar?");
			deleteExe.setContentText("Ao apagar as informaÃ§Ãµes nÃ£o serÃ£o mais recuperadas");
			deleteExe.getButtonTypes().setAll(btnOk,btnCancel);
			deleteExe.showAndWait().ifPresent(a -> {
				if (a == btnOk) {
					ManagementCostumer.delete(idSelected);
					refreshTableView();
				} 
			});
			
		});
		
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