package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import exceptions.ListEmptyComposition;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main; 
import model.Ingredients;
import model.Item;
import model.ManagementItens;
import model.ManagementProducts;
import model.Product;
import utils.Alerts;
/** Classe responsavel pelo controle do formulario de pratos
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class FormItensController implements Initializable {
	private Integer idSelected;// id do ingrediente utilizado, selecionado da tabela
	
    @FXML
    private Button btnAdicionar, btnSave, btnBack, btnDelete;
    
    @FXML
    private ComboBox<String> comboBoxProdutos;// O combo box que recebe a lista de nomes
    // ESTRUTURA DE DADOS PRO COMBOBOX
    private static ArrayList<String> comboNameList;// Lista de nome de produtos que � exibida no combo
    private static ArrayList<Product> listAllProducts;// Lista de produtos utilizada no combobox
    
    private ArrayList<Ingredients> tableViewList = new ArrayList<Ingredients>();// Lista de ingredientes utilizados()
    // Map que com a key sendo o nome do produto e o value o id do mesmo
    private static Map<String, Integer> HashMapProducts = new HashMap<String,Integer>();
    
    private ObservableList<Ingredients> observableListaIngredients;

    @FXML
    private TextField inputCategory, inputName, inputValue, inputQuatity;

    @FXML
    private TextArea inputDescription;

    @FXML
    private TableView<Ingredients> tableProducts;
    
    @FXML
    private TableColumn<Product, String> columnMedida;
    
    @FXML
    private TableColumn<Product, String> columnProduct;

    @FXML
    private TableColumn<Product, Integer> columnQtd;
    
    
    /**Metodo para retornar a tela de prato anterior
     * 
     * @param event - Evento disparado ao clicar no botao voltar
     * @throws IOException
     */
	@FXML
    void actionBack(ActionEvent event) throws IOException {
		actionBackToItem();
    }

    
	/**Metodo para pegar o id do produto selecionado na tabela
	 * serve para remover da tabela posteriormente somente
	 * 
	 * @param event - Evento disparado ao clicar em uma linha da tabela
	 */
    @FXML
    void clickLine(MouseEvent event) {
    	btnDelete.setDisable(false);
    	Ingredients i = tableProducts.getSelectionModel().getSelectedItem();
    	if(i != null) {
    		idSelected = i.getId();
    	}
    }

    /**Metodo para adicionar um ingrediente na tabela de ingredientes do prato
     * 
     * @param event - Evento disparado ao clicar no botao de adicionar
     */
    @FXML
    void actionAdicionar(ActionEvent event) {
    	Integer id;
    	Product p;
    	// Pega o nome da combobox, converte para id
    	id = HashMapProducts.get(comboBoxProdutos.getValue());
    	// Busca o produto com o id
    	p = ManagementProducts.getOne(id);
    	// Se existir produto, joga na lista
    	if (p != null) {
    		Ingredients ing = new Ingredients();
    		ing.setName(p.getName());
    		ing.setMedida(p.getMedida());
    		ing.setId(p.getId());
    		ing.setQtd(Integer.parseInt(inputQuatity.getText()));
    		tableViewList.add(ing);
    		refreshTableViewProducts();
    	}
    }

    /**Metodo para deletar um produto da lista de produtos do ingrediente do prato
     * 
     * @param event - Evento disparado ao clicar no botao deletar
     */
    @FXML
    void actionDelete(ActionEvent event) {
    	Ingredients ing;
    	// Procurando pelo produto selecionado
    	for (int i = 0; i < tableViewList.size(); i++) {
			ing = tableViewList.get(i);
			if (ing.getId() == idSelected) {
				tableViewList.remove(i);
			}
		}
    	refreshTableViewProducts();
    }

    
    /**Metodo para salvar os dados do formulario
     * 
     * @param event - Evento disparado ao cliclar no botao salvar
     * @throws IOException 
     */
    @FXML
    void actionSave(ActionEvent event) throws IOException {
    	boolean aux = true;
    	// Verifica n�o existe um id selecionado
		if (Main.getIdSelected() == -1) {
			try {
				createItem();
				aux = false;
			} catch (ListEmptyComposition e) {
				String desc = "Lista de Ingredientes vazia";
				String detailDesc = "Adicione ingredientes ao seu prato";
				Alerts.alertError(e.getMessage(), desc, detailDesc);
			} catch (NegativePriceEntity e) {
				String desc = "Pre�o negativo";
				String detailDesc = "Insira um valor positivo para o pre�o";
				Alerts.alertError(e.getMessage(), desc, detailDesc);
			}
		}
		// Se for editar um prato
		else {
			try {
				updateItem();
				aux = false;
			} catch (NullFieldException e) {
				Alerts.alertError(e.getMessage(), "Campos vazios", "Preencha todos os campos");
			}
		}
		// Se passar pelas etapas sem receber uma exce��o
		if (aux == false) {
			actionBackToItem();
		}
    }
    
    /**Metodo para criar um novo item
     * 
     * @throws NegativePriceEntity - Preco menor que 0
     * @throws ListEmptyComposition - Lista de ingredientes vazia
     * 
     */
    public void createItem() throws ListEmptyComposition, NegativePriceEntity {
    	Item i = new Item();
		i.setName(inputName.getText());
		i.setCategory(inputCategory.getText());
    	i.setDescription(inputDescription.getText());
    	i.setComposition(tableViewList);
    	//i.setPrice(Float.parseFloat(inputValue.getText()));
    	if (! inputValue.getText().equals(".")) {
			i.setPrice(Float.parseFloat(inputValue.getText()));
		}else {
			i.setPrice(0);
		}
    	
		ManagementItens.addItem(i);
    }
    
    
    /**Metodo para editar um item
     * @throws NullFieldException - Campos vazios
     * 
     */
    public void updateItem() throws NullFieldException {
    	Item i = new Item();
		i.setName(inputName.getText());
		i.setCategory(inputCategory.getText());
    	i.setDescription(inputDescription.getText());
    	//i.setPrice(Float.parseFloat(inputValue.getText()));
    	i.setComposition(tableViewList);
    	if (! inputValue.getText().equals(".")) {
			i.setPrice(Float.parseFloat(inputValue.getText()));
		}else {
			i.setPrice(0);
		}
    	
		ManagementItens.update(Main.getIdSelected(), i);
    }
    
    /**Metodo para atualizar a tabela de produtos
     * 
     */
    public void refreshTableViewProducts() {
    	observableListaIngredients = FXCollections.observableArrayList(tableViewList);
		tableProducts.setItems(observableListaIngredients);
		
		columnMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
		columnProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
    }

    /**Metodo para carregar dados quando for editar e setar as listas no combbox ao inicializar o componente
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Se for editar um novo fornecedor, preenche os campos e tabela
		if (Main.getIdSelected() != -1) {
			Item i = ManagementItens.getOne(Main.getIdSelected());
			inputCategory.setText(i.getCategory());
			inputName.setText(i.getName());
			inputValue.setText(Float.toString(i.getPrice()));
			inputDescription.setText(i.getDescription());
			// Pegando a lista de composi��o e mudando para uma lista de Ingredientes e mudando para uma de produtos
			tableViewList = i.getComposition();
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
		comboBoxProdutos.getItems().setAll(comboNameList);
		refreshTableViewProducts();
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Inteiros
		inputQuatity.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("[0123456789]*")) 
		        return null;
		    else
		        return c;
		    }
		));
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Float
		inputValue.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("\\d*(\\.\\d*)?")) 
		        return null;
		    else
		        return c;
		    }
		));
	}
	
	
	/**Metodo para tornar o botao adicionar visivel
	 * 
	 * @param Evento disparado ao clicar na textField quantidade
	 */
    @FXML
    void clickedAddQtd(MouseEvent event) {
    	btnAdicionar.setDisable(false);
    }
    
	
	/**Metodo para retornar a tela de Item
	 * 
	 * @throws IOException
	 */
    private void actionBackToItem() throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
}

