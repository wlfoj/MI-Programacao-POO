package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exceptions.NullFieldException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementProducts;
import model.ManagementProvider;
import model.Product;
import model.Provider;
import utils.Alerts;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class FormProviderController implements Initializable {
	private Integer idSelected;// id do produto fornecido, selecionado da tabela
	// COMPONENTES DA JAVAFX
    @FXML
    private Button btnAddProduct,btnBack,btnDeleteProduct,btnSave;
    @FXML
    private ComboBox<String> comboProducts;
    @FXML
    private TextField inputAdress, inputName, inputCnpj;
    
    // ESTRUTURA DE DADOS PRO COMBOBOX
    private static ArrayList<String> comboNameList;
    private static ArrayList<Product> listAllProducts;// Lista de produtos utilizada no combobox
    private ArrayList<Product> tableViewList = new ArrayList<Product>();
    // Map que com a key sendo o nome do produto e o value o id do mesmo
    private static Map<String, Integer> HashMapProducts = new HashMap<String,Integer>();

    ObservableList<Product> observableListaProduct; 
    
    @FXML
    private TableColumn<Product, Integer> columnId;

    @FXML
    private TableColumn<Product, String> columnName;
    
    @FXML
    private TableView<Product> tableProducts;

    /**Metodo para adicionar um produto na lista de produtos fornecidos pelo provider
     * 
     * @param event - Evento disparado ao clicar no botao de adicionar
     */
    @FXML
    void actionAddProduct(ActionEvent event) {
    	Integer id;
    	Product p;
    	// Pega o nome da combobox, converte para id
    	id = HashMapProducts.get(comboProducts.getValue());
    	// Busca o produto com o id
    	p = ManagementProducts.getOne(id);
    	// Se existir produto, joga na lista
    	if (p != null) {
    		tableViewList.add(p);
    		refreshTableViewProducts();
    	}
    }
    
    /**Metodo para retornar a tela anterior
     * 
     * @param e - Evento disparado ao clicar no bot�o de voltar
     * @throws IOException
     */
    @FXML
    void actionBack(ActionEvent event) throws IOException {
    	actionBackToProvider();
    }

	/**Metodo para retornar a tela de fornecedor
	 * 
	 * @throws IOException
	 */
    private void actionBackToProvider() throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProvider.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
    
    /**Metodo para deletar um produto da lista de produtos fornecidos
     * 
     * @param event - Evento disparado ao clicar no botao deletar
     */
    @FXML
    void actionDeleteProduct(ActionEvent event) {
    	Product p;
    	// Procurando pelo produto selecionado
    	for (int i = 0; i < tableViewList.size(); i++) {
			p = tableViewList.get(i);
			if (p.getId() == idSelected) {
				tableViewList.remove(i);
			}
		}
    	refreshTableViewProducts();
    }

    
    /**Metodo para selecionar um produto na tabela de produtos fornecidos
     * 
     * @param event - Evento disparado ao clicar em uma linha da tabela
     */
    @FXML
    void clickLine(MouseEvent event) {
    	btnDeleteProduct.setDisable(false);
    	Product p = tableProducts.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected =p.getId();
    	}
    }
    
    
    /** Metodo para salvar as informa��es inseridas no formulario
     * 
     * @param event - Evento disparado ao clicar no botao salvar
     * @throws IOException
     */
    @FXML
    void actionSave(ActionEvent event) throws IOException {
    	boolean aux = true;
    	// Verifica n�o existe um id selecionado
		if (Main.getIdSelected() == -1) {
	    		try {
					createProvider();
					aux = false;
				} catch (NullFieldException e) {
					Alerts.alertError(e.getMessage(), "Campos vazios","Preencha todos os campos" );
				}
	    // Caso tenha o id selecionado
	    } else {
	    	try {
				editProvider();
				aux = false;
			} catch (NullFieldException e) {
				Alerts.alertError(e.getMessage(), "Campos vazios","Preencha todos os campos" );
			}	
	    }
		// Se passar pelas etapas sem receber uma exce��o
		if (aux == false) {
			actionBackToProvider();
		}
    }
    
    /**Metodo para criar um novo fornecedor
     * 
     * @throws NullFieldException - Excecao para campos vazios
     */
    private void createProvider() throws NullFieldException {
    	ArrayList<Integer> idListProducts = new ArrayList<Integer>();
    	Provider p = new Provider();
		p.setName(inputName.getText());
		p.setCnpj(inputCnpj.getText());
		p.setAddress(inputAdress.getText());
		
		// Convertendo a lista de produtos em lista de ids
		for (int i = 0; i < tableViewList.size(); i++) {
			idListProducts.add(tableViewList.get(i).getId());
		}
		p.setProducts(idListProducts);
		
		ManagementProvider.addProvider(p);
    }
    
    
    /**Metodo para editar um fornecedor
     * 
     * @throws NullFieldException - Excecao para campos vazios
     */
    private void editProvider() throws NullFieldException {
    	ArrayList<Integer> idListProducts = new ArrayList<Integer>();
    	Provider p = new Provider();
		p.setName(inputName.getText());
		p.setCnpj(inputCnpj.getText());
		p.setAddress(inputAdress.getText());
		// Convertendo a lista de produtos em lista de ids
		for (int i = 0; i < tableViewList.size(); i++) {
			idListProducts.add(tableViewList.get(i).getId());
		}
		p.setProducts(idListProducts);
		
		ManagementProvider.update(Main.getIdSelected(), p);
    }
    
    /**Metodo para atualizar a tabela de produtos
     * 
     */
    public void refreshTableViewProducts() {
		observableListaProduct = FXCollections.observableArrayList(tableViewList);
		tableProducts.setItems(observableListaProduct);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    
    
    /**Metodo executado ao inicializar a aplicacao.
     * Insere mascaras nos inputs e preenchi campos, caso tenha selecionado editar na tela anterior
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnAddProduct.setCursor(Cursor.HAND);
		btnBack.setCursor(Cursor.HAND);
		btnDeleteProduct.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Inteiros
		inputCnpj.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("[0123456789]*")) 
		        return null;
		    else
		        return c;
		    }
		));
		
		// Se for editar um novo fornecedor, preenche os campos e tabela
		if (Main.getIdSelected() != -1) {
			Provider p = ManagementProvider.getOne(Main.getIdSelected());
			inputName.setText(p.getName());
			inputCnpj.setText(p.getCnpj());
			inputAdress.setText(p.getAddress());
			tableViewList = ManagementProducts.convertIdProductList(p.getProducts());
		}
		// Preenchendo o combo box com a lista de produtos
		comboNameList = new ArrayList<String>();
		listAllProducts = ManagementProducts.listAllProducts();
		Product p;
		// Percorrendo todos os produtos e adicionando s� o nome na lista
		for (int i = 0; i < listAllProducts.size(); i++) {
			p = listAllProducts.get(i);
			// Adicionando na lista do comboBox
			comboNameList.add(p.getName());
			// Adicionando na hash pro futuro
			HashMapProducts.put(p.getName(), p.getId());
		}
		// Setando os produtos no combo
		comboProducts.getItems().setAll(comboNameList);
		refreshTableViewProducts();
	}
}
