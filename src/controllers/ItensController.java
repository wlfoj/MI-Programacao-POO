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

/** Classe responsavel pelo Controller de Itens
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class ItensController implements Initializable {

	ObservableList<Item> observableListItem;
	
	@FXML
    private Button btnBack, btnEdit, btnCreate, btnDelete;
    
    @FXML
    private TableColumn<Item, String> columnCategory;

    @FXML
    private TableColumn<Item, String> columnDescription;

    @FXML
    private TableColumn<Item, Integer> columnId;

    @FXML
    private TableColumn<Item, String> columnName;

    @FXML
    private TableColumn<Item, Float> columnValue;
    
    @FXML
    private TableView<Item> tableView;
	
	private Integer idSelected;
	
	/**Metodo atribuido no botao de voltar para retornar ao menu
	 * 
	 * @param e - Evento disparado ao clicar no botao voltar
	 * @throws IOException
	 */
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	 /**Metodo de click para adicionar um novo Cardapio
     * 
     * @param event disparado ao clicar no botao criar
     * @throws IOException
     */
    @FXML
    void actionCreate(ActionEvent event) throws IOException {
    	Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
  
    
    /**Evento de click para editar o cardapio
	 * 
	 * @param event - Evento disparado ao clicar no botao editar
	 * @throws IOException
	 */
    @FXML
    void actionEdit(ActionEvent event) throws IOException {
    	Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
    /**Metodo para selecionar um prato da tabela
     * 
     * @param event - evento disparado ao clicar na tabela
     */
    @FXML
    void clickLine(MouseEvent event) {
    	Item p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected =p.getId();
    	}
    	btnEdit.setDisable(false);
    	btnDelete.setDisable(false);
    }
	
	 /**Metodo para inicializar o gerenciamento e  ativar a visualizacao dos botoes 
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		
		deleteItem();
		refreshTableView();
	}
	
	/**Metodo atribuindo um evento no botao de deletar para deletar o cardapio da lista
	 * 
	 */
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
	
	/**Metodo para carregar a listView da classe e formatar as celulas
	 * 
	 */
	public void refreshTableView() {
		observableListItem = FXCollections.observableArrayList(ManagementItens.listAllItens());
		tableView.setItems(observableListItem);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnCategory.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		columnValue.setCellValueFactory(new PropertyValueFactory<>("price"));
	}
	
}
