package controllers;

import java.io.IOException;
import java.net.URL;
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
import model.ManagementSales;
import model.Sale;
import utils.Relatorio;
	
/** Classe responsavel pelo Controller de Vendas
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class SalesController implements Initializable {
	
	private Optional<String> input;
	
	ObservableList<Sale> observableListSales;
	
	private Integer idSelected;
	
    @FXML
    private TableColumn<Sale, Calendar> columnDate;

    @FXML
    private TableColumn<Sale, Integer> columnId;

    @FXML
    private TableColumn<Sale, String> columnPaymentMethod;

    @FXML
    private TableColumn<Sale, Float> columnTotalValue;
	
    @FXML
    private TableView<Sale> tableView;
    
    @FXML
    private ComboBox<String> comboBoxPrint;// Combo para gerar o relatorio
    private String[] lista = {"Relatorio completo", "Venda por Item"};//Opcoes de relatorios
    
	@FXML
    private Button btnBack, btnCreate, btnEdit, btnDelete, btnPrint;
	
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
	
	 /**Evento de click para adicionar um novo produto
     * 
     * @param event Evento disparado ao clicar o botao criar
     * @throws IOException
     */
    @FXML
    void actionCreate(ActionEvent event) throws IOException {
    	Main.setIdSelected(-1);
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioSales.fxml"));
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
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioSales.fxml"));
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
		btnEdit.setCursor(Cursor.HAND);
		btnDelete.setCursor(Cursor.HAND);
		btnPrint.setCursor(Cursor.HAND);
		comboBoxPrint.getItems().setAll(lista);
		comboBoxPrint.setValue("Relatorio completo");
		
		deleteSales();
		refreshTableView();
	}
	
	/**Metodo atribuindo um evento no botao de deletar para deletar a venda da lista
	 * 
	 */
	public void deleteSales() {
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
					ManagementSales.delete(idSelected);
					refreshTableView();
				} 
			});
		});
	}
	
	/**Metodo para carregar a listView da classe e formatar as celulas
	 * 
	 */
	public void refreshTableView() {
		observableListSales = FXCollections.observableArrayList(ManagementSales.listAllSale());
		tableView.setItems(observableListSales);
		
		columnTotalValue.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		columnPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		
	}

	@FXML
    void actionPrint(ActionEvent event) throws Exception {
		if (comboBoxPrint.getValue() == "Relatorio completo") {
			SaleAll();
		} else if(comboBoxPrint.getValue() == "Venda por Item") {
			 SalePerItem();
		}else {
			
		}
    }
	
	public void SalePerItem() throws Exception {
		ArrayList<Sale> sales;
		int idPrato;
		int qtdSales;
		float totalPrice;
		
		
		
		
		TextInputDialog textInput = new TextInputDialog();
		textInput.setTitle("Informe o prato");
		textInput.getDialogPane().setHeaderText("Informe o id do prato");
		textInput.getDialogPane().setContentText("Informe o id do prato para gerar o relatorio:");
		Stage stage = (Stage) textInput.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("iconapp.png"));
		input = textInput.showAndWait();
		if(input.isPresent() == true) {
			try {
				idPrato = Integer.parseInt(input.get());
				sales = ManagementSales.listSaleWithItem(idPrato);
				qtdSales = ManagementSales.countItensInSale(sales);
				totalPrice = ManagementSales.sumTotalPrice(sales);
				Relatorio.relatorioVendas(sales, qtdSales, totalPrice);
			} catch (NumberFormatException e) {
				alertNumberFormat();
			}
		}
	}
	
	public void alertNumberFormat() {
		Alert alertExceptionNumber = new Alert(Alert.AlertType.CONFIRMATION);
		
		ButtonType btnOk = new ButtonType("Ok");
		Stage stage2 = (Stage) alertExceptionNumber.getDialogPane().getScene().getWindow();
		stage2.getIcons().add(new Image("iconapp.png"));
		alertExceptionNumber.setTitle("Informe corretamente");
		alertExceptionNumber.setHeaderText("Informe valor inteiro");
		alertExceptionNumber.setContentText("Informe uma valor inteiro!");
		alertExceptionNumber.getButtonTypes().setAll(btnOk);
		alertExceptionNumber.showAndWait().ifPresent(a -> {
			if (a == btnOk) {
				try {
					SalePerItem();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	/**Metodo para imprimir todas vendas
	 * 
	 * @throws Exception
	 */
	public void SaleAll() throws Exception {
		ArrayList<Sale> sales = ManagementSales.listAllSale();
		int qtdSales = ManagementSales.countItensInSale(sales);
		float totalPrice = ManagementSales.sumTotalPrice(sales);
		
		Relatorio.relatorioVendas(sales, qtdSales, totalPrice);
	}
	
	 /**Evento de click para selecionar determinada linha da tabela
     * 
     * @param event Evento disparado ao clicar na tabela
     */
    @FXML
    void clickLine(MouseEvent event) {
    	Sale p = tableView.getSelectionModel().getSelectedItem();
    	if(p != null) {
    		idSelected =p.getId();
    	}
    	btnDelete.setDisable(false);
    	btnEdit.setDisable(false);
    }
}