package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import model.Costumer;
import model.ManagementCostumer;
import model.ManagementItens;

class ManagementCostumeTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
		
		Costumer cliente2 = new Costumer();
		cliente2.setCpf("67805647645");
		cliente2.setEmail("testf@exemplo.com");
		cliente2.setName("Swat Americana");
		cliente2.setTelefone("75-887349212");
		ManagementCostumer.addCostumer(cliente2);
		
		Costumer cliente3 = new Costumer();
		cliente3.setCpf("57894532368");
		cliente3.setEmail("batman@exemplo.com");
		cliente3.setName("Batman E superman");
		cliente3.setTelefone("71-347349221");
		ManagementCostumer.addCostumer(cliente3);
	}
	@Test
	void addCostumeInList() throws NullFieldException, ObjectRegistred {
		Costumer cliente4 = new Costumer();
		cliente4.setCpf("57894332368");
		cliente4.setEmail("man@exemplo.com");
		cliente4.setName("llolo");
		cliente4.setTelefone("21-347349221");
		ManagementCostumer.addCostumer(cliente4);
		//verificando tamanho da listas
		assertEquals(5, ManagementCostumer.listAllCostumer().size());
		
		Costumer cliente5 = new Costumer();
		cliente5.setCpf("57894132568");
		cliente5.setEmail("seemail@exemplo.com");
		cliente5.setName("paulo frança");
		cliente5.setTelefone("22-347349221");
		ManagementCostumer.addCostumer(cliente5);
		//verificando tamanho da lista
		assertEquals(6, ManagementCostumer.listAllCostumer().size());
	}
	
	@Test
	void addAndDeleteCostumeInList() throws NullFieldException, ObjectRegistred {
		Costumer cliente6 = new Costumer();
		cliente6.setCpf("57894332362");
		cliente6.setEmail("mana@exemploo.com");
		cliente6.setName("Miranha alucinado");
		cliente6.setTelefone("71-337349222");
		ManagementCostumer.addCostumer(cliente6);
		ManagementCostumer.delete(6);
		//verificando tamanho da lista apos criar e apagar o mesmo cliente
		assertEquals(6, ManagementCostumer.listAllCostumer().size());
		
		Costumer cliente7 = new Costumer();
		cliente7.setCpf("856632568673");
		cliente7.setEmail("sevencostume@exemploo.com");
		cliente7.setName("Batman louco");
		cliente7.setTelefone("71-34569822");
		ManagementCostumer.addCostumer(cliente7);
		ManagementCostumer.delete(7);
		//verificando tamanho da lista apos criar e apagar o mesmo cliente
		assertEquals(6, ManagementCostumer.listAllCostumer().size());
	}
	
	@Test
	void deleteCostumeWithIdOutList() {
		ManagementCostumer.delete(57);
		//verificando tamanho da lista apos tentar apagar um cliente inexistente
		assertEquals(6, ManagementCostumer.listAllCostumer().size());
		
		ManagementCostumer.delete(29);
		//verificando tamanho da lista apos tentar apagar um cliente inexistente
		assertEquals(6, ManagementCostumer.listAllCostumer().size());
	}
	
	@Test
	void deleteCostumeWithIdInList() {
		ManagementCostumer.delete(2);
		//verificando tamanho da lista após apagar um cliente
		assertEquals(5, ManagementCostumer.listAllCostumer().size());
	}
	
	@Test
	void addAndUpdateCostumeWithIdInList() throws NullFieldException, ObjectRegistred {
		Costumer cliente8 = new Costumer();
		cliente8.setCpf("856222568673");
		cliente8.setEmail("testando@exemploo.com");
		cliente8.setName("Flash lento");
		cliente8.setTelefone("71-234669822");
		ManagementCostumer.addCostumer(cliente8);
		ManagementCostumer.getOne(6);
		
		//Verificando o cliente editado tem realmente o mesmo nome
		assertEquals(cliente8.getName(), ManagementCostumer.getOne(6).getName());
	}
}
