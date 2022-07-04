package controllers;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import main.Main;
import model.ManagementSales;
import model.Sale;
import utils.Relatorio;

/** Classe responsavel pelo Controller de Datas do relatorios de vendas
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */

public class RelatorioDateController implements Initializable{

    @FXML
    private Button btnCancel, btnConfirma;

    @FXML
    private DatePicker datePickerFinish;

    @FXML
    private DatePicker datePickerInicial;
    
	private static LocalDateTime dateInicial;

	private static LocalDateTime dateFinish;

    /**Metodo atribuido no botao de voltar para retornar ao gerenciador de produtos
     * 
     * @param event Evento disparado ao clicar no botao voltar
     * @throws IOException
     */
    @FXML
    void actionBack(ActionEvent event) throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
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

	public static LocalDateTime getDateInicial() {
		return dateInicial;
	}

	public static void setDateInicial(LocalDateTime dateInicial) {
		RelatorioDateController.dateInicial = dateInicial;
	}

	public static LocalDateTime getDateFinish() {
		return dateFinish;
	}

	public static void setDateFinish(LocalDateTime dateFinish) {
		RelatorioDateController.dateFinish = dateFinish;
	}
	
	/**Metodo para confirmar a escolha das datas e gerar relatorio
	 * 
	 * @param event  Evento disparado ao clicar no botao confirmar
	 * @throws Exception 
	 */
    @FXML
    void actionConfirmation(ActionEvent event) throws Exception {
    	ArrayList<Sale> sales;
		int qtdSales;
		float totalPrice;
		
    	LocalDate inicial = datePickerInicial.getValue();
    	LocalDate finish = datePickerFinish.getValue();
    	//Convertendo LocalDate em LocalDattime
        int ano = inicial.getYear();
        int mes =  inicial.getMonthValue();
        int dia = inicial.getDayOfMonth() -1;
        LocalDateTime dataInicial = LocalDateTime.of(ano, mes, dia, 00, 00, 0);
        
        ano = finish.getYear();
        mes =  finish.getMonthValue();
        dia = finish.getDayOfMonth() -1;
        LocalDateTime dataFinish = LocalDateTime.of(ano, mes, dia, 00, 00, 0);
    	
        sales = ManagementSales.listSalePerPeriod(dataInicial, dataFinish);
		qtdSales = ManagementSales.countItensInSale(sales);
		totalPrice = ManagementSales.sumTotalPrice(sales);
        
		Relatorio.relatorioVendas(sales, qtdSales, totalPrice );
        
    	confirmationBack();
    }
    
    /**Metodo que ao usuario confirmar grava e retorna gerenciamento de produtos
     * 
     * @throws IOException
     */
    public void confirmationBack() throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }
    
}
