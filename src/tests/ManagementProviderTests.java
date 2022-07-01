package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.NullFieldException;
import model.ManagementProvider;
import model.Provider;

class ManagementProviderTests {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		Provider provider = new Provider();
		ArrayList<Integer> p1 = new ArrayList<Integer>();
		p1.add(0); //id que tem nos produtos verificar se o id foi deletado
		provider.setCnpj("77039172000101");
		provider.setName("Laticinios Bahia");
		provider.setAddress("Rua Salvador, Savador Bahia n 305");
		provider.setProducts(p1);
		//
		Provider provider2 = new Provider();
		ArrayList<Integer> p2 = new ArrayList<Integer>();
		p2.add(0);
		p2.add(2);
		provider2.setCnpj("3898356000102");
		provider2.setName("França Distribuidora");
		provider2.setAddress("Tv Getulio Vargas, Juazeiro Ba, n 10");
		provider2.setProducts(p2);
		//
		Provider provider3 = new Provider();
		ArrayList<Integer> p3 = new ArrayList<Integer>();
		p3.add(2);
		p3.add(1);
		provider3.setCnpj("18002345000123");
		provider3.setName("Hotaliças Premium");
		provider3.setAddress("Rua Aguas limpas, Recife Pe, n 902");
		provider3.setProducts(p3);
		//
		Provider provider4 = new Provider();
		ArrayList<Integer> p4 = new ArrayList<Integer>();
		p4.add(2);
		provider4.setCnpj("434576689000123");
		provider4.setName("Padaria Valenca");
		provider4.setAddress("Rua Marcos F. Mantos, Sr Bomfin Ba, n 2");
		provider4.setProducts(p4);
		ManagementProvider.addProvider(provider);
		ManagementProvider.addProvider(provider);
		ManagementProvider.addProvider(provider3);
		ManagementProvider.addProvider(provider4);	
	}
	
	//4º Teste
	@Test
	void deleteProvidesWithIdOut() {
		ManagementProvider.delete(59);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(2,ManagementProvider.listAllProvider().size());
		ManagementProvider.delete(23);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(2,ManagementProvider.listAllProvider().size());
	}
	
	//2º Teste
	@Test
	void addAndDeleteProviderList() throws NullFieldException {
		Provider provider5 = new Provider();
		ArrayList<Integer> p5 = new ArrayList<Integer>();
		p5.add(4);
		provider5.setCnpj("358976689000123");
		provider5.setName("Cervejaria Nordeste");			
		provider5.setAddress("Rua Sebastiao Nogueira, Vitoria Es, SN");
		provider5.setProducts(p5);
		ManagementProvider.addProvider(provider5);
		// Verificando o último fornecedor da lista
		assertEquals(provider5, ManagementProvider.getOne(4));
		//Verificando quantidade de itens na lista, antes de deletar
		assertEquals(5,ManagementProvider.listAllProvider().size());
		ManagementProvider.delete(4);
		//Verificando quantidade de itens na lista, após deletar
		assertEquals(4,ManagementProvider.listAllProvider().size());
	}
		
	//3º Teste
	@Test
	void deleteProviderWithIdIn() {
		ManagementProvider.delete(2);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(3,ManagementProvider.listAllProvider().size());
		ManagementProvider.delete(1);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(2,ManagementProvider.listAllProvider().size());
	}

	//1º Teste
	@Test
	void updateProviderInList() throws NullFieldException {
		Provider provider6 = new Provider();
		ArrayList<Integer> p6 = new ArrayList<Integer>();
		p6.add(1);
		provider6.setCnpj("786566689000123");
		provider6.setName("Atacadao");			
		provider6.setAddress("Av Leao Marinho, F de Santana Ba, SN");
		provider6.setProducts(p6);
		ManagementProvider.update(1, provider6);
		//Verificando se o cnpj do provider de id 1 é mesmo do objeto intânciado
		assertEquals(provider6.getCnpj(),ManagementProvider.getOne(1).getCnpj());
		//Verificando se o endereco do provider de id 1 é mesmo do objeto intânciado
		assertEquals(provider6.getAddress(),ManagementProvider.getOne(1).getAddress());
		//Verificando se o nome do provider de id 1 é mesmo do objeto intânciado
		assertEquals(provider6.getName(),ManagementProvider.getOne(1).getName());	
	}
	
	//5º Teste
	@Test
	void updateProviderNameNull() throws NullFieldException {
		Provider provider7 = new Provider();
		ArrayList<Integer> p7 = new ArrayList<Integer>();
		p7.add(6);
		provider7.setCnpj("187605489000193");
		provider7.setName("");			
		provider7.setAddress("Tv Rio de Janeiro, F de Satana Ba, n 5");
		provider7.setProducts(p7);
		//Fazendo update de fornecedor com nome vazio
		assertThrows(NullFieldException.class,() -> ManagementProvider.update(5, provider7));
		
	}
}