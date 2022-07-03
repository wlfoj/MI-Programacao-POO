package controllers;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class RelatorioDateController implements Initializable{

    @FXML
    private Button btnCancel, btnConfirma;

    @FXML
    private DatePicker datePickerFinish;

    @FXML
    private DatePicker datePickerInicial;
    
    private static boolean answer;

	private static LocalDate dateInicial;

	private static LocalDate dateFinish;

    /**Metodo atribuido no botao de voltar para retornar ao gerenciador de produtos
     * 
     * @param event Evento disparado ao clicar no botao voltar
     * @throws IOException
     */
    @FXML
    void actionBack(ActionEvent event) throws IOException {
    	setAnswer(false);
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }

    /**Metodo para inicializar o gerenciamento e  ativar a visualizacao dos botoes 
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnCancel.setCursor(Cursor.HAND);
		btnConfirma.setCursor(Cursor.HAND);
	}

	public static boolean getAnswer() {
		return answer;
	}

	public static void setAnswer(boolean answer) {
		RelatorioDateController.answer = answer;
	}

	public static LocalDate getDateInicial() {
		return dateInicial;
	}

	public static void setDateInicial(LocalDate dateInicial) {
		RelatorioDateController.dateInicial = dateInicial;
	}

	public static LocalDate getDateFinish() {
		return dateFinish;
	}

	public static void setDateFinish(LocalDate dateFinish) {
		RelatorioDateController.dateFinish = dateFinish;
	}
	
	/**Metodo para confirmar a escolha das datas e gerar relatorio
	 * 
	 * @param event  Evento disparado ao clicar no botao confirmar
	 * @throws IOException
	 */
    @FXML
    void actionConfirmation(ActionEvent event) throws IOException {
    	setAnswer(true);
    	setDateInicial(datePickerInicial.getValue());
    	setDateFinish(datePickerFinish.getValue());
    	confirmationBack();
    }
    
    /**Metodo que ao usuario confirmar grava e retorna gerenciamento de produtos
     * 
     * @throws IOException
     */
    public void confirmationBack() throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProducts.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
}
