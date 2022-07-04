package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementProducts;
import model.Product;
import utils.Alerts;

/** Classe responsavel pelo controle do formulario de produtos
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class FormProductsController implements Initializable {

	@FXML
    private ComboBox<String> inputMedida;// Combox para selecionar a unidade da medida
	
	private String[] lista = {"L","Kg" , "g", "mL"};// Medidas disponiveis para selecionar no combobox

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputQtd;

    @FXML
    private DatePicker inputValidity;

    @FXML
    private TextField inputValue;

    @FXML
    private Button btnBack, btnSave;
	
    /**Metodo para salvar as informa��es digitadas do produto, seja para criar ou para editar
     * 
     * @param event - Evento disparado ao clicar no bot�o salvar
     * @throws IOException
     */
	@FXML
	private void eventSave(ActionEvent event) throws IOException{
		boolean aux = true;
		if (Main.getIdSelected() == -1) {
			try {
				createProduct();
				aux = false;
			} catch (NegativePriceEntity e) {
				Alerts.alertError(e.getMessage(), "Valor do produto negativo","Usuário por favor preencher com valores acima de 0" );
			} catch (InsufficientQuantityProducts e) {
				Alerts.alertError(e.getMessage(), "Quantidade de produtos insuficiente","Usuário por favor preencher com quantidade de produtos suficiente" );
			} catch (DateInvalid e) {
				Alerts.alertError(e.getMessage(),"Data de validade anterior a atual", "Usuário por favor preencher com data posterior a atual" );
			}
		// Caso tenha o id selecionado
		} else {
			try {
				editProduct();
				aux = false;
			} catch (DateInvalid e) {
				Alerts.alertError(e.getMessage(),"Data de validade anterior a atual", "Usuário por favor preencher com data posterior a atual" );
			} catch (InsufficientQuantityProducts e) {
				Alerts.alertError(e.getMessage(), "Quantidade de produtos insuficiente","Usuário por favor preencher com quantidade de produtos suficiente" );
			} catch (NegativePriceEntity e) {
				Alerts.alertError(e.getMessage(), "Valor do produto negativo","Usuário por favor preencher com valores acima de 0" );
			}
		}
    	// Se passar pelas etapas sem receber uma exce��o, volta para tela anterior
    	if(aux==false) {
    		actionBackToProduct();
    	}
	}
	
	/**Metodo para criar o produto
	 * 
	 * @throws NegativePriceEntity - Excecao para valores de pre�o negativos
	 * @throws InsufficientQuantityProducts - Excecao para quantidade de produto insuficiente ou negativa
	 * @throws DateInvalid - Data de validade inv�lida ou anterior a data atual
	 */
	private void createProduct() throws NegativePriceEntity, InsufficientQuantityProducts, DateInvalid{
		Product p = new Product();
		p.setName(inputName.getText());
		p.setMedida(inputMedida.getValue());
		p.setQtd(Integer.parseInt(inputQtd.getText()));
		p.setValidity(inputValidity.getValue());
		
		if (! inputValue.getText().equals(".")) {
			p.setPrice(Float.parseFloat(inputValue.getText()));
		}else {
			p.setPrice(0);
		}
		ManagementProducts.addProduct(p);
	}
	
	
	/**Metodo para editar o produto
	 * 
	 * @throws NegativePriceEntity - Excecao para valores de pre�o negativos
	 * @throws InsufficientQuantityProducts - Excecao para quantidade de produto insuficiente ou negativa
	 * @throws DateInvalid - Data de validade inv�lida ou anterior a data atual
	 */
	private void editProduct() throws DateInvalid, InsufficientQuantityProducts, NegativePriceEntity {
		Product p = new Product();
		p.setName(inputName.getText());
		p.setQtd(Integer.parseInt(inputQtd.getText()));
		p.setValidity(inputValidity.getValue());
		p.setMedida(inputMedida.getValue());
		
		if (! inputValue.getText().equals(".")) {
			p.setPrice(Float.parseFloat(inputValue.getText()));
		}else {
			p.setPrice(0);
		}
		ManagementProducts.update(Main.getIdSelected(), p);	
	}
	
	
	/**Metodo executa ao inicializar o componente
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnBack.setCursor(Cursor.HAND);
		btnSave.setCursor(Cursor.HAND);
		inputMedida.getItems().setAll(lista);
		//INICIALIZACOES PARA CRIAR UM NOVO USUARIO
		if (Main.getIdSelected() == -1) {
			inputMedida.setValue("Kg");
		}
		// Se tiver id selecionado, j� preenche os campos
		else{
			Product p = ManagementProducts.getOne(Main.getIdSelected());
			inputName.setText(p.getName());
			inputMedida.setValue(p.getMedida());
			inputQtd.setText(Float.toString(p.getQtd()));
			inputValue.setText(Float.toString(p.getPrice()));
			inputValidity.setValue(p.getValidity());
		}
		
		//Mascara para impedir que o usuario ponha dados de String na textField de Inteiros
		inputQtd.setTextFormatter(new TextFormatter<>(c -> {
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
    
	/**Metodo para retornar a pagina anterior
	 * 
	 * @param e - Evento disparado ao clicar no botao voltar
	 * @throws IOException
	 */
	@FXML
	private void actionBack(ActionEvent e) throws IOException {
		actionBackToProduct();
	}
	
	
	/**Metodo para retorna a pagina de produtos
	 * 
	 * @throws IOException
	 */
	private void actionBackToProduct() throws IOException {
		AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
	}
	
	
}