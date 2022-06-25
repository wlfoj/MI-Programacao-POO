package main;
	
import java.io.IOException;
import java.time.LocalDate;

import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.ListEmptyComposition;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import facede.FacedeManagement;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Costumer;
import model.ManagementCostumer;
import model.ManagementUsers;
import model.Product;
import model.Provider;
import model.User;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene scene;
	// PODE FICAR MELHOR EM UMA CLASSE AUXILIAR
	private static Integer idSelected = -1;
	
	//Tornar melhor o código
	public static void setScene(Scene newScene) {
		scene = newScene;
		stage.setScene(scene);
		stage.centerOnScreen();
	}
	
	public static Integer getIdSelected() {
		return idSelected;
	}
	
	public static void setIdSelected(Integer newId) {
		idSelected = newId;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		try {
			stage = primaryStage;
			primaryStage.setTitle("Comercial Store");
			primaryStage.setResizable(false);
			// TALVEZ FAÇA MAIS SENTIDO EM VOID MAIN
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene login = new Scene(root);

			Image anotherIcon = new Image("iconapp.png");
			primaryStage.getIcons().add(anotherIcon);
			
			primaryStage.setScene(login);
			primaryStage.centerOnScreen();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Não pode retornar exceções
	public static void main(String[] args) throws NullFieldException, ObjectRegistred, NegativePriceEntity, InsufficientQuantityProducts, DateInvalid, ListEmptyComposition {
		FacedeManagement.initData();
		
		launch(args);
	}
}
