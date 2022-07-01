package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.InsufficientQuantityProducts;
import model.ManagementSales;
import model.Sale;

class ManagementSalesTests {

	@Test
	void addSalesWithItens() throws InsufficientQuantityProducts {
		//adicionando venda 1
		
		Sale venda = new Sale();
		venda.setPaymentMethod("Avista");
		
		ArrayList<Integer> aaa = new ArrayList<Integer>();
		aaa.add(0);
		aaa.add(2);
		
		venda.insertItens(aaa);
			ManagementSales.addSale(venda);
		//verificando a soma dos itens
		assertEquals(10,ManagementSales.getOne(0).getTotalPrice());
		//verificar quantidade de itens na lista, apos adicinar
		assertEquals(1,ManagementSales.listAllSale().size());
		
		//adicionando venda 2
		
		Sale venda2 = new Sale();
		venda2.setPaymentMethod("credito");
		
		ArrayList<Integer> aaa2 = new ArrayList<Integer>();
		aaa2.add(4);
		aaa2.add(5);
		
		venda2.insertItens(aaa2);

			ManagementSales.addSale(venda2);

			// TODO Auto-generated catch block

		//verificando a soma dos itens de id 4, 5 sendo assim 8 + 48 = 56
		assertEquals(56.0,ManagementSales.getOne(1).getTotalPrice());//valor esperado 56
		//verificar quantidade de itens na lista, apos adicinar
		assertEquals(2,ManagementSales.listAllSale().size());//size da lista
		
		// adicionando venda 3
		
		Sale venda3 = new Sale();
		venda3.setPaymentMethod("credito");
		
		ArrayList<Integer> aaa3 = new ArrayList<Integer>();
		aaa3.add(5);
		aaa3.add(9);
		
		venda3.insertItens(aaa3);
		try {
			ManagementSales.addSale(venda3);
		} catch (InsufficientQuantityProducts e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//verificando a soma dos itens de id 5 e 9 sendo assim 48 + 22 = 70
		assertEquals(70.0,ManagementSales.getOne(2).getTotalPrice());//valor esperado 51
		assertEquals(3,ManagementSales.listAllSale().size());//size da lista
	}
	
	//4º teste
	@Test
	void deleteSalestWithIdIn() {
		ManagementSales.delete(2); //deletando id 2
		assertEquals(2, ManagementSales.listAllSale().size()); //tamanho da lista esperado 1 
	}
	
	//5º Teste
	@Test
	void addSaleAndDeleteSales() throws InsufficientQuantityProducts {
		//adicionando venda 4
		Sale venda4 = new Sale();
		venda4.setPaymentMethod("pix");
		
		ArrayList<Integer> aaa4 = new ArrayList<Integer>();
		aaa4.add(9);
		aaa4.add(8);
		aaa4.add(7);
		
		venda4.insertItens(aaa4);
			ManagementSales.addSale(venda4);
		//verificando a soma dos itens de id 9, 8 e 7 sendo assim 22 + 5 + 29 = 56
		assertEquals(56.0,ManagementSales.getOne(3).getTotalPrice());
		// Verificando quantidade de itens na lista, apos adicionar
		assertEquals(3,ManagementSales.listAllSale().size());
		//deletando venda 4
		ManagementSales.delete(3);
		// Verificando quantidade de itens na lista, apos deletar
		assertEquals(2,ManagementSales.listAllSale().size());
	}
	
	//3º Teste
	@Test
	void deleteSalesWithIdOut() {
		ManagementSales.delete(44);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(3,ManagementSales.listAllSale().size());
		
		ManagementSales.delete(77);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(3, ManagementSales.listAllSale().size());
	}
	
	//2º Test
	@Test
	void updateSales() {
		Sale upsale = new Sale();
		//upsale.setDate(data);
		upsale.setPaymentMethod("pix");
		ManagementSales.update(1, upsale);
		//Verificando se o nome do item de id 1 é mesmo do objeto intanciado
		assertEquals("pix",ManagementSales.getOne(1).getPaymentMethod());
	}
}