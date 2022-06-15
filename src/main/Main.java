package main;
	
import java.io.IOException;
import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Costumer;
import model.ManagementCostumer;
import model.ManagementUsers;
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
	public static void main(String[] args) throws NullFieldException, ObjectRegistred, NegativePriceEntity, InsufficientQuantityProducts {
		User u = new User();
		u.setLogin("0");
		u.setPass("0");
		u.setName("Ricardo Milos");
		ManagementUsers.addUser(u);
		
		User p = new User();
		p.setLogin("pao");
		p.setPass("paozin");
		p.setName("Ricardo Melos");
		ManagementUsers.addUser(p);;
		
		User a = new User();
		a.setLogin("pedrin");
		a.setPass("1234");
		a.setName("Pedro Henrique Cruz");
		ManagementUsers.addUser(a);
		
		Costumer cliente0 = new Costumer();
		cliente0.setCpf("44022245200");
		cliente0.setEmail("testador@exemplo.com");
		cliente0.setName("testador colossal");
		cliente0.setTelefone("75-981020666");
		ManagementCostumer.addCostumer(cliente0);
		
		Costumer cliente1 = new Costumer();
		cliente1.setCpf("72745028345");
		cliente1.setEmail("geradordecpf@exemplo.com");
		cliente1.setName("cpfgerado santos");
		cliente1.setTelefone("75-984349255");
		ManagementCostumer.addCostumer(cliente1);
		
		launch(args);
	}
}
