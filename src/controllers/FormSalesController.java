package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import exceptions.InsufficientQuantityProducts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
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
import model.Item;
import model.ManagementItens;
import model.ManagementSales;
import model.Sale;
import utils.Alerts;

public class FormSalesController implements Initializable {

	private Integer idSelected;// id do prato vendido, selecionado da tabela
	
	@FXML
	private Button btnBack, btnSave, btnAdicionar, btnDelete;
	
    @FXML
    private TableColumn<Item, Integer> columnId;

    @FXML
    private TableColumn<Item, String> columnName;

    @FXML
    private TableColumn<Item, String> columnPrice;

    @FXML
    private TextField inputTotalValue, inputIdCostume;
    
    @FXML
    private ComboBox<String> comboBoxPaymentMethod;
    private String[] lista = {"Avista","Pix","Cartao de debito", "Cartao de credito"};
    
    
    /** Descritivo para estruturas abaixo
     * 	listAllItens -> Recebe todos os produtos existentes, vai gerar uma lista de nomes de pratos apatir dele
     *  comboNameList -> Lista de nomes de pratos gerados de listAllItens
     *  HashMapProducts -> Estrutura para converter o nome do prato em id do mesmo
     *  tableViewList -> Lista de pratos para compor a observable list
     * 
     */
    @FXML
    private ComboBox<String> comboBoxPratos;// O combo box que recebe a lista de nomes
    private static ArrayList<String> comboNameList;// Lista de nome de pratos que ser exibida no combo
    private static ArrayList<Item> listAllItens;// Lista de pratos utilizada no combobox
    private ArrayList<Item> tableViewList = new ArrayList<Item>();// Lista de pratos utilizados
    // Map que com a key sendo o nome do prato e o value o id do mesmo
    private static Map<String, Integer> HashMapProducts = new HashMap<String,Integer>();
    private ObservableList<Item> observableListItem; 
    
    @FXML
    private TableView<Item> tableView;

    
    /**Metodo para retornar a tela de vendas anterior
     * 
     * @param event - Evento disparado ao clicar no botao voltar
     * @throws IOException
     */
	@FXML
    void actionBack(ActionEvent event) throws IOException {
		actionBackToSale();
    }
	
	/**Metodo para retornar a tela de Item
	 * 
	 * @throws IOException
	 */
    private void actionBackToSale() throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }


    /**Metodo para adicionar um prato na tabela de pratos da venda
     * 
     * @param event - Evento disparado ao clicar no botao de adicionar
     */
    @FXML
    void actionAdicionar(ActionEvent event) {
    	float totalPrice = 0;
    	Integer id;
    	Item item;
    	// Pega o nome da combobox, converte para id
    	id = HashMapProducts.get(comboBoxPratos.getValue());
    	// Busca o prato com o id
    	item = ManagementItens.getOne(id);
    	// Se existir o prato, joga na lista
    	if (item != null) {
    		tableViewList.add(item);
    		refreshTableView();
    	}
    	
    	//Atualiza o input preço total
    	for (int i = 0; i < tableViewList.size(); i++) {
    		totalPrice = totalPrice + tableViewList.get(i).getPrice();
		}
    	inputTotalValue.setText(Float.toString(totalPrice));
    }


	/**Metodo para pegar o id do prato selecionado na tabela
	 * serve para remover da tabela posteriormente somente
	 * 
	 * @param event - Evento disparado ao clicar em uma linha da tabela
	 */
    @FXML
    void clickLine(MouseEvent event) {
    	btnDelete.setDisable(false);
    	Item i = tableView.getSelectionModel().getSelectedItem();
    	if(i != null) {
    		idSelected = i.getId();
    	}
    }
    
    
    /**Metodo para deletar um prato da lista de pratos vendidos
     * 
     * @param event - Evento disparado ao clicar no botao deletar
     */
    @FXML
    void actionDelete(ActionEvent event) {
    	float totalPrice=0;
    	Item item;
    	// Procurando pelo prato selecionado
    	for (int i = 0; i < tableViewList.size(); i++) {
			item = tableViewList.get(i);
			if (item.getId() == idSelected) {
				tableViewList.remove(i);
				break;
			}
		}
    	
    	//Atualiza o input preço total
    	if (tableViewList.size()>0) {
        	for (int i = 0; i < tableViewList.size(); i++) {
        		totalPrice = totalPrice + tableViewList.get(i).getPrice();
    		}
        	inputTotalValue.setText(Float.toString(totalPrice));
    	} 
    	else {
    		inputTotalValue.setText("0");
    	}
    	refreshTableView();
    }

    /**Metodo para salvar as informacoes inseridas no formulario
     * 
     * @param event - Evento disparado ao clicar no botao registrar
     * @throws IOException
     */
    @FXML
    void actionSave(ActionEvent event) throws IOException {
    	boolean aux = true;
    	// Verifica nï¿½o existe um id selecionado
		if (Main.getIdSelected() == -1) {
			try {
				createSale();
				aux = false;
			} catch (InsufficientQuantityProducts e) {
				String desc = "Quantidade insuficiente";
				String detailDesc = "Nao tem produto suficiente para fazer esse prato";
				Alerts.alertError(e.getMessage(), desc, detailDesc);
			}
		}
		// Se for editar uma venda
		else {
			editSale();
			aux = false;
		}
		// Se passar pelas etapas sem receber uma exceï¿½ï¿½o
		if (aux == false) {
			actionBackToSale();
		}
    }
    
    
    /**Metodo para utilizar as informacoes do formulario para registrar nova venda
     * @throws InsufficientQuantityProducts 
     * 
     */
    public void createSale() throws InsufficientQuantityProducts {
    	Sale s = new Sale();
    	s.setIdCostumer(Integer.parseInt(inputIdCostume.getText()));
    	s.setPaymentMethod(comboBoxPaymentMethod.getValue());
    	
    	// Pegando somente o id dos pratos da listview
    	ArrayList<Integer> listId = new ArrayList<Integer>();
    	for (int i = 0; i < tableViewList.size(); i++) {
    		listId.add(tableViewList.get(i).getId());
		}
    	s.insertItens(listId);
    	
    	ManagementSales.addSale(s);
    	
    }
    
    /**Metodo para utilizar as informacoes do formulario para editar uma venda
     * 
     */
    public void editSale() {
    	Sale s = new Sale();
    	s.setIdCostumer(Integer.parseInt(inputIdCostume.getText()));
    	s.setPaymentMethod(comboBoxPaymentMethod.getValue());
    	
    	// Pegando somente o id dos pratos da listview
    	ArrayList<Integer> listId = new ArrayList<Integer>();
    	for (int i = 0; i < tableViewList.size(); i++) {
    		listId.add(tableViewList.get(i).getId());
		}
    	s.insertItens(listId);
    	
    	ManagementSales.update(Main.getIdSelected(), s);
    }
    
    
    /**Metodo de inicializacao do componente, controi mascaras para inputs e preenche algumas estruturas
     * quando se escolhe editar uma venda
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		btnAdicionar.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		comboBoxPaymentMethod.getItems().setAll(lista);	
		comboBoxPaymentMethod.setValue("Avista");
		
		/**Inicio do bloco preenchendo o combo box com a lista de pratos */
		comboNameList = new ArrayList<String>();
		listAllItens = ManagementItens.listAllItens();
		Item item;
		// Percorrendo todos os produtos e adicionando sï¿½ o nome na lista
		for (int i = 0; i < listAllItens.size(); i++) {
			item = listAllItens.get(i);
			// Adicionando na lista do comboBox
			comboNameList.add(item.getName());
			// Adicionando na hash pro futuro
			HashMapProducts.put(item.getName(), item.getId());
		}
		// Setando os produtos no combo
		comboBoxPratos.getItems().setAll(comboNameList);
		/**Fim do bloco*/
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Inteiros
		inputIdCostume.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("\\d*")) 
		        return null;
		    else
		        return c;
		    }
		));
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Float
		inputTotalValue.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("\\d*(\\.\\d*)?")) 
		        return null;
		    else
		        return c;
		    }
		));
		
		/**Bloco para preencher os inputs em caso de edicao de venda */
		if (Main.getIdSelected() != -1) {
			Sale sale = ManagementSales.getOne(Main.getIdSelected());
			inputIdCostume.setText(Integer.toString(sale.getIdCostumer()));
			inputTotalValue.setText(Float.toString(sale.getTotalPrice()));
			// Pegando a lista de composiï¿½ï¿½o e mudando para uma lista de Ingredientes e mudando para uma de produtos
			tableViewList = ManagementItens.convertIdItemList(sale.getItens());
		}
		
		refreshTableView();
		
	}
	
	/**Metodo para atualizar os dados da tabela de pratos vendidos
	 * 
	 */
	public void refreshTableView() {
		observableListItem = FXCollections.observableArrayList(tableViewList);
		tableView.setItems(observableListItem);
		
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
	}
	
}
