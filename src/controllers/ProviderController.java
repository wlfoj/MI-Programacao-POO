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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementProvider;
import model.Provider;
import utils.Relatorio;

/** Classe responsavel pelo Controller de Fornecedores
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class ProviderController implements Initializable{

	ObservableList<Provider> observableListaProvider; 
	
	private Integer idSelected;

	@FXML
    private Button btnBack, btnCreate, btnEdit, btnDelete, btnPrint;
	
	@FXML
    private ComboBox<String> comboBoxPrint;
	private String[] lista = {"Relatorio completo", "Fornecedor por Produto"};
	
    @FXML
    private TableColumn<Provider, String> columnAdress;

    @FXML
    private TableColumn<Provider, String> columnCnpj;

    @FXML
    private TableColumn<Provider, Integer> columnId;

    @FXML
    private TableColumn<Provider, String> columnName;


    @FXML
    private TableView<Provider> tableView;
	
    /**Evento atribuido no botao de voltar para retornar ao menu
     * 
     * @param e
     * @throws IOException
     */
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	/**Evento de click para selecionar determinada linha da tabela
	 * 
	 * @param event
	 */
    @FXML
    void clickLine(MouseEvent event) {
    	Provider p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected =p.getId();
    	}
    	btnEdit.setDisable(false);
    	btnDelete.setDisable(false);
    }
    
    /**Evento de click para editar o Fornecedor
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void actionEdit(ActionEvent event) throws IOException {
    	Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
    /**Evento para criar um novo Fornecedor
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    void actionCreate(ActionEvent event) throws IOException {
    	Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }

    /**Metodo para inicializar o gerenciamento e  ativar a visualizacao dos botoes 
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnPrint.setCursor(Cursor.HAND);
		comboBoxPrint.getItems().setAll(lista);
		comboBoxPrint.setValue("Relatorio completo");
		
		//Adicionando o evento de deletar e configurando comportamento do alert
	
		deleteProvider();
		refreshTableView();
	}
	
	/**Metodo atribuindo um evento no botao de deletar para deletar o fornecedor da lista
	 * 
	 */
	public void deleteProvider() {
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
					ManagementProvider.delete(idSelected);
					refreshTableView();
				} 
			});
		});
		
	}
	
	/**Evento para imprimir relatorios de fornecedores
	 * 
	 * @param event
	 */
	@FXML
    void eventPrint(ActionEvent event) {
		if (comboBoxPrint.getValue() == "Relatorio completo") {
			providerAll();
		}else{
			providerPerProduct();
		}
    }

	/**Metodo para carregar a listView da classe e formatar as celulas
	 * 
	 */
	public void refreshTableView() {
		observableListaProvider = FXCollections.observableArrayList(ManagementProvider.listAllProvider());
		tableView.setItems(observableListaProvider);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
		columnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
	}
	
	public void providerAll() {
		ArrayList<Provider> list = ManagementProvider.listAllProvider();
		int qtdTotal = list.size();
		
		try {
			Relatorio.relatorioFornecedor(list, qtdTotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void providerPerProduct() {
		int idProduct = 0;
		ArrayList<Provider> list;
		int qtdTotal;
		
		list = ManagementProvider.listProviderPerProduct(idProduct);
		qtdTotal = list.size();
		
		try {
			Relatorio.relatorioFornecedor(list, qtdTotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
