
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import model.Costumer;
import model.ManagementCostumer;
import model.ManagementProvider;
import model.Provider;
import utils.Relatorio;

/** Classe responsavel pelo Controller de Clientes
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class CostumeController implements Initializable {

	ObservableList<Costumer> observableListaClientes;

    @FXML
    private TableColumn<Costumer, Integer> columnId;
	
    @FXML
    private TableColumn<Costumer, String> columnCpf;

    @FXML
    private TableColumn<Costumer, String> columnEmail;

    @FXML
    private TableColumn<Costumer, String> columnName;

    @FXML
    private TableView<Costumer> tableView;

    @FXML
    private TableColumn<Costumer, String> columnTel;
	
	@FXML
    private Button btnCreate, btnBack, btnEdit, btnRemove;
	
	private Integer idSelected;// Id do cliente selecionado na tabela
	
	/**Metodo para criar um novo cliente
	 * 
	 * @param e - Evento disparado ao clicar no botao de criar novo
	 * @throws IOException 
	 */
	@FXML
	private void actionCreate(ActionEvent e) throws IOException {
		Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioCostume.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}

	/** Metodo para editar o cliente
	 * 
	 * @param event - Evento disparado ao clicar no botao de editar
	 * @throws IOException excecoes da classe costumer
	 */
    @FXML
    void actionEdit(ActionEvent event) throws IOException {
		Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioCostume.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);

    }

    /**Metodo para selecionar determinada linha da tabela
     * 
     * @param event - Evento disparado ao clicar em uma linha da tabela
     */
    @FXML
    void clickLine(MouseEvent event) {
    	Costumer p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected=p.getId();
    	}
    	btnEdit.setDisable(false);
    	btnRemove.setDisable(false);
    }
    
    /**Metodo relacionado ao botao de voltar para retornar ao menu
     * 
     * @param e - Evento disparado ao clicar no botao de voltaar
     * @throws IOException
     */
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	/** Metodo para inicializar o gerenciamento e  ativar a visualizacao dos botoes 
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnRemove.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		
		deleteCostume();
		refreshTableView();
		
	}
	
	/** Metodo para atribuir um evento no botao de deletar para deletar o cliente da lista
	 * 
	 */
	public void deleteCostume() {
		//Adicionando o evento de deletar e configurando comportamento do alert
		btnRemove.setOnAction(e-> {
			Alert deleteExe = new Alert(Alert.AlertType.CONFIRMATION);
			
			ButtonType btnOk = new ButtonType("Deletar");
			ButtonType btnCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
			
			deleteExe.initOwner(btnRemove.getScene().getWindow());
			deleteExe.setTitle("Deletar");
			deleteExe.setHeaderText("Deseja realmente deletar?");
			deleteExe.setContentText("Ao apagar as informações nao serão mais recuperadas");
			deleteExe.getButtonTypes().setAll(btnOk,btnCancel);
			deleteExe.showAndWait().ifPresent(a -> {
				if (a == btnOk) {
					ManagementCostumer.delete(idSelected);
					refreshTableView();
				} 
			});
			
		});
	}
	
	
	/**Metodo para atualizar a listView da classe e formatar as celulas
	 * 
	 */
	public void refreshTableView() {
		
		observableListaClientes = FXCollections.observableArrayList(ManagementCostumer.listAllCostumer());
		tableView.setItems(observableListaClientes);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnTel.setCellValueFactory(new PropertyValueFactory<>("telefone"));
	}
}