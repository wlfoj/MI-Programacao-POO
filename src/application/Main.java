package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static Stage stage;
	public static Scene login;
	public static Scene menu;
	public static Scene guser;
	public static Scene formuser;
	public static Scene gproduct;
	public static Scene gclientes;
	public static Scene gfornecedores;
	public static Scene gcardapio;
	public static Scene gvendas;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			stage = primaryStage;
			primaryStage.setTitle("Comercial Store");
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			login = new Scene(root);
			
			AnchorPane root1 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
			menu = new Scene(root1);
			
			AnchorPane root2 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorUser.fxml"));
			guser= new Scene(root2);
			
			AnchorPane root3 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioUser.fxml"));
			formuser= new Scene(root3);
			
			AnchorPane root4 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProducts.fxml"));
			gproduct= new Scene(root4);
			
			AnchorPane root5 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorCostume.fxml"));
			gclientes= new Scene(root5);
			
			AnchorPane root6 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorProvider.fxml"));
			gfornecedores= new Scene(root6);
			
			AnchorPane root7 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorItens.fxml"));
			gcardapio= new Scene(root7);
			
			AnchorPane root8 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/GerenciadorSales.fxml"));
			gvendas= new Scene(root8);
			
			Image anotherIcon = new Image("iconapp.png");
			primaryStage.getIcons().add(anotherIcon);
			
			primaryStage.setScene(login);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void scenes(String url) {
		switch(url) {
			case "loginAcess":
				stage.setScene(login);
				break;
			case "Acesso Liberado!":
				stage.setScene(menu);
				stage.centerOnScreen();
				break;
			case "guser":
				stage.setScene(guser);
				stage.centerOnScreen();
				break;
			case "backUser":
				stage.setScene(menu);
				stage.centerOnScreen();
				break;
			case "formUser":
				stage.setScene(formuser);
				stage.centerOnScreen();
				break;
			case "backgUser":
				stage.setScene(guser);
				stage.centerOnScreen();
				break;
			case "gproduct":
				stage.setScene(gproduct);
				stage.centerOnScreen();
				break;
			case "gclientes":
				stage.setScene(gclientes);
				stage.centerOnScreen();
				break;
			case "backclientes":
				stage.setScene(menu);
				stage.centerOnScreen();
				break;
			case "gfornecedores":
				stage.setScene(gfornecedores);
				stage.centerOnScreen();
				break;
			case "gcardapio":
				stage.setScene(gcardapio);
				stage.centerOnScreen();
				break;
			case "gvendas":
				stage.setScene(gvendas);
				stage.centerOnScreen();
				break;
			case "backfornecedor":
				stage.setScene(menu);
				stage.centerOnScreen();
				break;
			case "backprodutos":
				stage.setScene(menu);
				stage.centerOnScreen();
				break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
