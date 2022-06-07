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
	public static Scene formUser;
	
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
			formUser= new Scene(root3);
			
			
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
				stage.setScene(formUser);
				stage.centerOnScreen();
				break;
			case "backgUser":
				stage.setScene(guser);
				stage.centerOnScreen();
				break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
