package main;
	
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Costumer;
import model.ManagementCostumer;
import model.ManagementProducts;
import model.ManagementUsers;
import model.Product;
import model.User;
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
	public static Scene formclientes;
	
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
			
			AnchorPane root9 = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/FormularioCostume.fxml"));
			formclientes= new Scene(root9);
			
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
				stage.close();
				stage.setScene(guser);
				stage.centerOnScreen();
				stage.show();
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
				stage.close();
				stage.setScene(guser);
				stage.centerOnScreen();
				stage.show();
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
			case "backitens":
				stage.setScene(menu);
				stage.centerOnScreen();
				break;
			case "backsales":
				stage.setScene(menu);
				stage.centerOnScreen();
				break;
			case "formClientes":
				stage.setScene(formclientes);
				stage.centerOnScreen();
				break;
			case "backClientes":
				stage.setScene(gclientes);
				stage.centerOnScreen();
				break;
		}
	}
	
	public static void main(String[] args) throws NullFieldException, ObjectRegistred, NegativePriceEntity, InsufficientQuantityProducts {
		User u = new User();
		u.setLogin("riri");
		u.setPass("1234");
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
		
		Product produto0 = new Product();
		produto0.setMedida("kg");
		produto0.setPrice(20);
		produto0.setQtd(200);
		produto0.setValidity(Calendar.getInstance());
		produto0.setName("Farinha");
		ManagementProducts.addProduct(produto0);
		
		Product produto1 = new Product();
		produto1.setMedida("L");
		produto1.setPrice(10.8F);
		produto1.setQtd(250);
		produto1.setValidity(Calendar.getInstance());
		produto1.setName("Água");
		ManagementProducts.addProduct(produto1);
		
		Product produto2 = new Product();
		produto2.setMedida("g");
		produto2.setPrice(2.8F);
		produto2.setQtd(110);
		produto2.setValidity(Calendar.getInstance());
		produto2.setName("Açucar");
		ManagementProducts.addProduct(produto2);
		
		launch(args);
	}
}
