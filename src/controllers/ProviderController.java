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
	
	private Integer idSelected;// Id do fornecedor selecionado na tabela

	@FXML
    private Button btnBack, btnCreate, btnEdit, btnDelete, btnPrint;
	
	@FXML
    private ComboBox<String> comboBoxPrint;// ComboBox para selecionar o tipo de relatório
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
	
    /**Metodo atribuido no botao de voltar para retornar ao menu
     * 
     * @param e - Evento disparado ao clicar no botao
     * @throws IOException
     */
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	
	/**Metodo para selecionar um fornecedor da tabela
	 * 
	 * @param event - Evento disparado ao clicar em uma linha na tabela
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
    
    /**Metodo para editar o Fornecedor
     * 
     * @param event - Evento disparado ao clicar no botao editar
     * @throws IOException
     */
    @FXML
    void actionEdit(ActionEvent event) throws IOException {
    	Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
    /**Metodo para criar um novo Fornecedor
     * 
     * @param event - Evento disparado ao clicar no botao criar
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
			deleteExe.setContentText("Ao apagar as informaÃ§Ãµes nÃ£o serÃ£o mais recuperadas");
			deleteExe.getButtonTypes().setAll(btnOk,btnCancel);
			deleteExe.showAndWait().ifPresent(a -> {
				if (a == btnOk) {
					ManagementProvider.delete(idSelected);
					refreshTableView();
				} 
			});
		});
		
	}
	
	/**Metodo para imprimir o relatorio selecionado de fornecedores
	 * 
	 * @param event - Evento disparado ao clicar no botao de imprimir
	 */
	@FXML
    void eventPrint(ActionEvent event) {
		if (comboBoxPrint.getValue() == "Relatorio completo") {
			providerAll();
		}else{
			providerPerProduct();
		}
    }
	

	/**Metodo para atualizar a listView da classe e formatar as celulas
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
	
	/**Metodo para gerar o relatorio de todos os fornecedores
	 * 
	 */
	public void providerAll() {
		//VAI FICAR MELHOR SE BOTAR EM UM FACE, PQ ISSO NÃO É TAREFA DO CONTROLLER
		ArrayList<Provider> list = ManagementProvider.listAllProvider();
		int qtdTotal = list.size();
		
		try {
			Relatorio.relatorioFornecedor(list, qtdTotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Metodo para gerar o relatorio de fornecedores por produto
	 * 
	 */
	public void providerPerProduct() {
		//VAI FICAR MELHOR SE BOTAR EM UM FACE, PQ ISSO NÃO É TAREFA DO CONTROLLER
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
