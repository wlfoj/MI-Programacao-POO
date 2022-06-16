package facede;

import java.time.LocalDate;

import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import model.Administrator;
import model.Costumer;
import model.Employee;
import model.ManagementCostumer;
import model.ManagementProducts;
import model.ManagementUsers;
import model.Product;
import model.User;

public abstract class FacedeManagement {
	public static void initData() throws NullFieldException, ObjectRegistred, NegativePriceEntity, InsufficientQuantityProducts, DateInvalid {
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
		
		Product p1 = new Product();
		p1.setMedida("L");
		p1.setName("Tomate");
		p1.setPrice(1.55f);
		p1.setQtd(50);
		p1.setValidity(LocalDate.now());
		ManagementProducts.addProduct(p1);
		
		Product p2 = new Product();
		p1.setMedida("L");
		p1.setName("Alface");
		p1.setPrice(2f);
		p1.setQtd(44);
		p1.setValidity(LocalDate.now());
		ManagementProducts.addProduct(p2);
	}
	//METODOS PARA CRIAR UM NOVO USUARIO
	public static void addUser(String name, String login, String pass, String type) throws NullFieldException, ObjectRegistred {
		if (type == "Administrador") {
    		Administrator u = new Administrator();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(pass);
			ManagementUsers.addUser(u);
    	} else {
    		Employee u = new Employee();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(pass);
    		ManagementUsers.addUser(u);
    	}
	}

}
