package facede;

import java.time.LocalDate;
import java.util.ArrayList;

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
import model.ManagementProvider;
import model.ManagementUsers;
import model.Product;
import model.Provider;
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
		p2.setMedida("L");
		p2.setName("Alface");
		p2.setPrice(2f);
		p2.setQtd(44);
		p2.setValidity(LocalDate.now());
		ManagementProducts.addProduct(p2);
		
		Product p3 = new Product();
		p3.setMedida("Kg");
		p3.setName("Pão");
		p3.setPrice(2.3f);
		p3.setQtd(78);
		p3.setValidity(LocalDate.now());
		ManagementProducts.addProduct(p3);
		
		Product p4 = new Product();
		p4.setMedida("Kg");
		p4.setName("Bolo de Chocolate");
		p4.setPrice(23f);
		p4.setQtd(55);
		p4.setValidity(LocalDate.now());
		ManagementProducts.addProduct(p4);
		
		Provider provider = new Provider();
		ArrayList<Integer> p0 = new ArrayList<Integer>();
		p0.add(0); //id que tem nos produtos verificar se o id foi deletado
		provider.setCnpj("77039172000101");
		provider.setName("Laticinios Bahia");
		provider.setAddress("Rua Salvador, Savador Bahia n 305");
		provider.setProducts(p0);
		ManagementProvider.addProvider(provider);
		
		Provider provider2 = new Provider();
		ArrayList<Integer> pv = new ArrayList<Integer>();
		pv.add(2); //id que tem nos produtos verificar se o id foi deletado
		pv.add(3); //id que tem nos produtos verificar se o id foi deletado
		provider2.setCnpj("56934054500001");
		provider2.setName("Padaria La massa");
		provider2.setAddress("Rua Brasil, Feira de Santana Centro, n 4");
		provider2.setProducts(pv);
		ManagementProvider.addProvider(provider2);
		
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
