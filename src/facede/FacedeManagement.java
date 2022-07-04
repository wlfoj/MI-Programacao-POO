package facede;

import java.time.LocalDate;

import java.util.ArrayList;

import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.ListEmptyComposition;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import model.Administrator;
import model.Costumer;
import model.Employee;
import model.Ingredients;
import model.Item;
import model.ManagementCostumer;
import model.ManagementItens;
import model.ManagementProducts;
import model.ManagementProvider;
import model.ManagementSales;
import model.ManagementUsers;
import model.Product;
import model.Provider;
import model.Sale;
import model.User;

/** Classe responsavel pela fachada para inicialização
* 
* @author Washington Luis Ferreira de Oliveira Junior
* @author Tassio Carvalho Rodrigues
*
*/
public abstract class FacedeManagement {
	
	/**Metodo para inicializar valores no sistema
	 * 
	 * @throws NullFieldException
	 * @throws ObjectRegistred
	 * @throws NegativePriceEntity
	 * @throws InsufficientQuantityProducts
	 * @throws DateInvalid
	 * @throws ListEmptyComposition
	 */
	public static void initData() throws NullFieldException, ObjectRegistred, NegativePriceEntity, InsufficientQuantityProducts, DateInvalid, ListEmptyComposition {
		// BLOCO DE USUARIO
		User u = new User();
		u.setLogin("0");
		u.setPass("0");
		u.setName("Ricardo Milos");
		ManagementUsers.addUser(u);
		
		User p = new User();
		p.setLogin("pao");
		p.setPass("paozin");
		p.setName("Marcos Pedro Cruz Henrique");
		ManagementUsers.addUser(p);
		
		User a = new User();
		a.setLogin("pedrin");
		a.setPass("1234");
		a.setName("Pedro Henrique Cruz");
		ManagementUsers.addUser(a);
		
		// BLOCO DE CLIENTES
		Costumer cliente0 = new Costumer();
		cliente0.setCpf("000000000");
		cliente0.setEmail("vazio@exemplo.com");
		cliente0.setName("nao especificado");
		cliente0.setTelefone("00000000000");
		ManagementCostumer.addCostumer(cliente0);
		
		Costumer cliente1 = new Costumer();
		cliente1.setCpf("72745028345");
		cliente1.setEmail("geradordecpf@exemplo.com");
		cliente1.setName("cpfgerado santos");
		cliente1.setTelefone("75984349255");
		ManagementCostumer.addCostumer(cliente1);
		
		// BLOCO DE PRODUTOS
		Product p1 = new Product();
		p1.setMedida("Kg");
		p1.setName("Tomate");
		p1.setPrice(1.55f);
		p1.setQtd(50);
		p1.setValidity(LocalDate.of(2023, 01, 12));;
		ManagementProducts.addProduct(p1);
		
		Product p2 = new Product();
		p2.setMedida("Kg");
		p2.setName("Alface");
		p2.setPrice(2f);
		p2.setQtd(44);
		p2.setValidity(LocalDate.of(2022, 12, 11));
		ManagementProducts.addProduct(p2);
		
		Product p3 = new Product();
		p3.setMedida("Kg");
		p3.setName("Pao");
		p3.setPrice(2.3f);
		p3.setQtd(78);
		p3.setValidity(LocalDate.of(2022, 11, 23));
		ManagementProducts.addProduct(p3);
		
		Product p4 = new Product();
		p4.setMedida("L");
		p4.setName("Leite");
		p4.setPrice(3.84f);
		p4.setQtd(55);
		p4.setValidity(LocalDate.now());
		ManagementProducts.addProduct(p4);
		
		// BLOCO DE FORNECEDOR
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
		
		// BLOCO DE INGREDIENTES E PRATOS
		Ingredients ing = new Ingredients();
		ing.setMedida("Kg");
		ing.setName("Tomate");
		ing.setId(0);
		ing.setQtd(0.5f);
		ArrayList<Ingredients> c1 = new ArrayList<Ingredients>();
		c1.add(ing);
		Item item = new Item();
		item.setCategory("lanche");
		item.setPrice(4);
		item.setDescription("Simplasso");
		item.setName("Hamburguer");
		item.setComposition(c1);
		ManagementItens.addItem(item);
		
		Item item2 = new Item();
		Ingredients ing2 = new Ingredients();
		ing2.setId(2); //id que exista no produtos
		ing.setMedida("L");
		ing.setName("Leite");
		ing2.setQtd(2);
		ArrayList<Ingredients> c2 = new ArrayList<Ingredients>();
		c2.add(ing2);
		item2.setCategory("salgados");
		item2.setPrice(2);
		item2.setDescription("coxinha de frango");
		item2.setName("coxinha");
		item2.setComposition(c2);
		ManagementItens.addItem(item2);
		
		// BLOCO DE VENDAS
		Sale venda = new Sale();
		venda.setPaymentMethod("Avista");
		ArrayList<Integer> aaa = new ArrayList<Integer>();
		aaa.add(0);
		aaa.add(1);
		venda.insertItens(aaa);
		ManagementSales.addSale(venda);
		
	}
	
	/**Metodo para fachada para adicionar usuarios
	 * 
	 * @param name
	 * @param login
	 * @param pass
	 * @param type
	 * @throws NullFieldException
	 * @throws ObjectRegistred
	 */
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
