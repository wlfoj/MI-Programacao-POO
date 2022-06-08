package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
	
public class ProductsController implements Initializable {
	
	@FXML
	private void eventoOne(ActionEvent e) {
		System.out.println("Voltar Produtos para Menu");
		Main.scenes("backprodutos");
	}
	
	@FXML
    private Button btt1, btt2, btt3, btt4, btt5;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btt1.setCursor(Cursor.HAND);
		btt2.setCursor(Cursor.HAND);
		btt3.setCursor(Cursor.HAND);
		btt4.setCursor(Cursor.HAND);
		btt5.setCursor(Cursor.HAND);	
	}
	
}