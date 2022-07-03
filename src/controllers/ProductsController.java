package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Main;
import model.ManagementProducts;
import model.ManagementProvider;
import model.Product;
import model.Provider;
import utils.Relatorio;

/** Classe responsavel pelo Controller de Produtos
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class ProductsController implements Initializable {

	private Optional<String> input;
	
	ObservableList<Product> observableListaProduct; 
	
    @FXML
    private ComboBox<String> comboBoxPrint;// Combo para gerar o relatorio
    private String[] lista = {"Relatorio completo", "Produtos a vencer"};//Opcoes de relatorios
	
    @FXML
    private TableColumn<Product, Integer> columnId;

    @FXML
    private TableColumn<Product, String> columnMedida;

    @FXML
    private TableColumn<Product, String> columnName;

    @FXML
    private TableColumn<Product, Integer> columnQtd;

    @FXML
    private TableColumn<Product, Calendar> columnValidade;
    
    @FXML
    private TableColumn<Product, Float> columnValor;

    @FXML
    private TableView<Product> tableView;
    
	@FXML
    private Button btnBack, btnCreate, btnEdit, btnRemove, btnPrint;
	
	private Integer idSelected;
	
	/**Evento atribuido no botao de voltar para retornar ao menu
	 * 
	 * @param e Evento disparado ao clicar o botao voltar
	 * @throws IOException
	 */
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	/**Evento de click para editar o produto
	 * 
	 * @param event Evento disparado ao clicar o botao editar
	 * @throws IOException
	 */
    @FXML
    void actionEdit(ActionEvent event) throws IOException {
		Main.setIdSelected(idSelected);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
    /**Evento de click para adicionar um novo produto
     * 
     * @param event Evento disparado ao clicar o botao criar
     * @throws IOException
     */
    @FXML
    void actionCreate(ActionEvent event) throws IOException {
		Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
	
    /**Metodo para gerar o relatorio selecionado
     * 
     * @param event - Evento disparado ao clicar no botao de gerar relatorio
     * @throws Exception 
     */
    @FXML
    void actionPrint(ActionEvent event) throws Exception {
    	
    	if (comboBoxPrint.getValue() == "Relatorio completo") {
    		ProductAll();
    	} else {
    		 ProductExpiredDate ();
    	}
    }
    
    /**Evento de click para selecionar determinada linha da tabela
     * 
     * @param event Evento disparado ao clicar na tabela
     */
    @FXML
    void clickLine(MouseEvent event) {
    	Product p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected=p.getId();
    	}
    	btnEdit.setDisable(false);
    	btnRemove.setDisable(false);
    }
	
    /**Metodo para inicializar o gerenciamento e  ativar a visualizacao dos botoes 
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnCreate.setCursor(Cursor.HAND);
		btnEdit.setCursor(Cursor.HAND);
		btnRemove.setCursor(Cursor.HAND);
		btnPrint.setCursor(Cursor.HAND);
		comboBoxPrint.getItems().setAll(lista);
		comboBoxPrint.setValue("Relatorio completo");
		
		deleteProducts();
		refreshTableView();
	}
	
	/**Metodo atribuindo um evento no botao de deletar para deletar o produto da lista
	 * 
	 */
	public void deleteProducts() {
		//Adicionando o evento de deletar e configurando comportamento do alert
				btnRemove.setOnAction(e-> {
					Alert deleteExe = new Alert(Alert.AlertType.CONFIRMATION);
					
					ButtonType btnOk = new ButtonType("Deletar");
					ButtonType btnCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
					
					deleteExe.initOwner(btnRemove.getScene().getWindow());
					deleteExe.setTitle("Deletar");
					deleteExe.setHeaderText("Deseja realmente deletar?");
					deleteExe.setContentText("Ao apagar as informações não serão mais recuperadas");
					deleteExe.getButtonTypes().setAll(btnOk,btnCancel);
					deleteExe.showAndWait().ifPresent(a -> {
						if (a == btnOk) {
							ManagementProducts.delete(idSelected);
							refreshTableView();
						} 
					});
					
				});
	}
	
	/**Metodo para carregar a listView da classe e formatar as celulas
	 * 
	 */
	public void refreshTableView() {
		observableListaProduct = FXCollections.observableArrayList(ManagementProducts.listAllProducts());
		tableView.setItems(observableListaProduct);

		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		columnQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		columnMedida.setCellValueFactory(new PropertyValueFactory<>("medida"));
		columnValidade.setCellValueFactory(new PropertyValueFactory<>("validity"));
		columnValor.setCellValueFactory(new PropertyValueFactory<>("price"));
	}
	
	
	/**Metodo para auxiliar a gerar o relatorio de todos os produtos
	 * 
	 */
	public void ProductAll() {
		ArrayList<Product> list = ManagementProducts.listAllProducts();
		int qtdTotal = list.size();
		
		try {
			Relatorio.relatorioEstoque(list, qtdTotal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Metodo para gerar relatorios a vencer
	 * 
	 * @throws Exception
	 */
	public void ProductExpiredDate () throws Exception {
		ArrayList<Product> list;
		
		    TextInputDialog textInput = new TextInputDialog();
			textInput.setTitle("Quantidade de produtos");
			textInput.getDialogPane().setHeaderText("Informe a quantidade de produto");
			textInput.getDialogPane().setContentText("Informe a quatidade de produto para gerar o relatorio:");
			Stage stage = (Stage) textInput.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("iconapp.png"));
			input = textInput.showAndWait();
			if(input.isPresent() == true) {
				try {
					list = ManagementProducts.listProductsInExpiration(Integer.parseInt(input.get()));
					Relatorio.relatorioEstoque(list, Integer.parseInt(input.get()));;
				} catch (NumberFormatException ex) {
					alertNumberFormat();
				}
			}
		}
	

	/**Metodo que gera alerta para evitar entradas de outros valores a nao ser inteiros dentro do TextInputDialog
	 * 
	 */
	public void alertNumberFormat() {
		Alert logoutExe = new Alert(Alert.AlertType.CONFIRMATION);
		
		ButtonType btnOk = new ButtonType("Ok");
		Stage stage2 = (Stage) logoutExe.getDialogPane().getScene().getWindow();
		stage2.getIcons().add(new Image("iconapp.png"));
		logoutExe.setTitle("Informe corretamente");
		logoutExe.setHeaderText("Informe valor inteiro");
		logoutExe.setContentText("Informe uma valor inteiro!");
		logoutExe.getButtonTypes().setAll(btnOk);
		logoutExe.showAndWait().ifPresent(a -> {
			if (a == btnOk) {
				try {
					ProductExpiredDate ();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
			
	public void openRelatorioDate() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/RelatorioDataPicker.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	
}