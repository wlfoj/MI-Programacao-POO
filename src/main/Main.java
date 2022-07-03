package main;
	
import java.io.IOException;
import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.ListEmptyComposition;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import facede.FacedeManagement;
import javafx.application.Application;
import javafx.stage.Stage;
import model.User;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene scene;
	private static Integer idSelected = -1;// id do objeto selecionado nos controllers principais
	private static User session;
	
	/** Setando uma nova scene no stage
	 * 
	 * @param newScene - A nova scene que vai ser exibida
	 */
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

	public static User getSession() {
		return session;
	}

	public static void setSession(User session) {
		Main.session = session;
	}
}
