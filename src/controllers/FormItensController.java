package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class FormItensController {

    @FXML
    void eventBack(ActionEvent event) throws IOException {
    	AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorItens.fxml"));
		Scene cena = new Scene(anchor);
		Main.setScene(cena);
    }


    @FXML
    private Button btnAdicionar, btnSave, btnBack, btnDelete;

    @FXML
    private ComboBox<?> comboBoxProdutos;

    @FXML
    private TextField inputCategory, inputName, inputQuality, inputValue, inputQuatity;

    @FXML
    private TextArea inputDescription;

    @FXML
    private Label labelUnd;

    @FXML
    void clickLine(MouseEvent event) {
    	btnDelete.setDisable(false);
    }

    @FXML
    void eventAdicionar(ActionEvent event) {

    }

    @FXML
    void eventDelete(ActionEvent event) {

    }

    @FXML
    void eventSave(ActionEvent event) {

    }
    
    @FXML
    void clickComboProduct(MouseEvent event) {
    	inputQuatity.setDisable(false);
    }

}

