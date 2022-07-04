package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.ListEmptyComposition;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;
import model.Ingredients;
import model.Item;
import model.ManagementItens;

class ManagementItensTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		Ingredients ing = new Ingredients();
		ing.setId(0);
		ing.setQtd(2);
		Item item = new Item();
		ArrayList<Ingredients> c1 = new ArrayList<Ingredients>();
		c1.add(ing);
		item.setCategory("laticinio");
		item.setPrice(4);
		item.setDescription("queijo mussarela");
		item.setName("queijo");
		item.setComposition(c1);
		//
		Item item2 = new Item();
		Ingredients ing2 = new Ingredients();
		ing2.setId(3); //id que exista no produtos
		ing2.setQtd(2);
		ArrayList<Ingredients> c2 = new ArrayList<Ingredients>();
		c2.add(ing2);
		item2.setCategory("salgados");
		item2.setPrice(2);
		item2.setDescription("coxinha de frango");
		item2.setName("coxinha");
		item2.setComposition(c2);
		//
		Item item3 = new Item();
		Ingredients ing3 = new Ingredients();
		ArrayList<Ingredients> c3 = new ArrayList<Ingredients>();
		c3.add(ing3);
		ing3.setId(4); //id que exista no produtos
		ing3.setQtd(1);
		item3.setCategory("doce");
		item3.setPrice(5);
		item3.setDescription("fatia de bolo de chocolate");
		item3.setName("bolo de chocolate");
		item3.setComposition(c3);
		//
		Item item4 = new Item();
		Ingredients ing4 = new Ingredients();
		ArrayList<Ingredients> c4 = new ArrayList<Ingredients>();
		c4.add(ing4);
		ing4.setId(5); 
		ing4.setQtd(1);
		item4.setCategory("bebida");
		item4.setPrice(2);
		item4.setDescription("agua sem gas");
		item4.setName("agua");
		item4.setComposition(c4);
		//
		ManagementItens.addItem(item);
		ManagementItens.addItem(item2);
		ManagementItens.addItem(item3);
		ManagementItens.addItem(item4);
		
		Item obj = new Item();
		Ingredients ing5 = new Ingredients();
		ing5.setId(6);
		ing5.setQtd(2);
		ArrayList<Ingredients> p1 = new ArrayList<Ingredients>();
		p1.add(ing5);
		obj.setCategory("lanche");
		obj.setPrice(8);
		obj.setName("hot dog");
		obj.setComposition(p1);
		//
		Item obj2 = new Item();
		Ingredients ing6 = new Ingredients();
		ing6.setId(7);
		ing6.setQtd(1);
		ArrayList<Ingredients> p2 = new ArrayList<Ingredients>();
		p2.add(ing6);
		obj2.setCategory("massa");
		obj2.setPrice(48);
		obj2.setName("pizza");
		obj2.setComposition(p2);
		//
		Item obj3 = new Item();
		Ingredients ing7 = new Ingredients();
		ing7.setId(8);
		ing7.setQtd(5);
		ArrayList<Ingredients> p3 = new ArrayList<Ingredients>();
		p3.add(ing7);
		obj3.setCategory("lanche");
		obj3.setPrice(3);
		obj3.setName("misto");
		obj3.setComposition(p3);
		//
		Item obj4 = new Item();
		Ingredients ing8 = new Ingredients();
		ing8.setId(9);
		ing8.setQtd(2);
		ArrayList<Ingredients> p4 = new ArrayList<Ingredients>();
		p4.add(ing8);
		obj4.setCategory("lanche");
		obj4.setPrice(29);
		obj4.setName("hamburguer");
		obj4.setComposition(p4);
		//
		Item obj5 = new Item();
		Ingredients ing9 = new Ingredients();
		ing9.setId(10);
		ing9.setQtd(2);
		ArrayList<Ingredients> p5 = new ArrayList<Ingredients>();
		p5.add(ing9);
		obj5.setCategory("sobremesa");
		obj5.setPrice(5);
		obj5.setName("cupcake");
		obj5.setComposition(p5);
		//
		Item obj6 = new Item();
		Ingredients ing10 = new Ingredients();
		ing10.setId(11);
		ing10.setQtd(1);
		ArrayList<Ingredients> p6 = new ArrayList<Ingredients>();
		p6.add(ing10);;
		obj6.setCategory("almoco");
		obj6.setPrice(22);
		obj6.setName("prato feito");
		obj6.setComposition(p6);
		//
		ManagementItens.addItem(obj);
		ManagementItens.addItem(obj2);
		ManagementItens.addItem(obj3);
		ManagementItens.addItem(obj4);	
		ManagementItens.addItem(obj5);
		ManagementItens.addItem(obj6);
		
	}

	//2º Teste
	@Test
	void addAndDeleteItemList() throws ListEmptyComposition, NegativePriceEntity {
		Item item5 = new Item();
		Ingredients ing11 = new Ingredients();
		ArrayList<Ingredients> c5 = new ArrayList<Ingredients>();
		c5.add(ing11);
		c5.add(ing11);
		item5.setCategory("suco");
		item5.setPrice(3);
		item5.setDescription("suco de laranja");
		item5.setName("laranjada");
		item5.setComposition(c5);
			ManagementItens.addItem(item5);
		// Verificando o último prato da lista
		assertEquals(item5,ManagementItens.getOne(10));
		// Verificando quantidade de itens na lista, após adicionar
		assertEquals(11,ManagementItens.listAllItens().size());
		ManagementItens.delete(10);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(10, ManagementItens.listAllItens().size());
	}

	//5º Teste
	@Test
	void deleteItensWithIdIn() {
		ManagementItens.delete(1);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(9, ManagementItens.listAllItens().size());
		ManagementItens.delete(3);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(8, ManagementItens.listAllItens().size());
		
	}

	//1º Teste
	@Test
	void deleteItensWithIdOut() {
		ManagementItens.delete(24);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(10, ManagementItens.listAllItens().size());
		
		ManagementItens.delete(43);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(10, ManagementItens.listAllItens().size());
	}

	//3º Teste
	@Test
	void updateItemInList() throws NullFieldException {
		Item upitem2 = new Item();
		Ingredients ing12 = new Ingredients();
		ArrayList<Ingredients> up = new ArrayList<Ingredients>();
		up.add(ing12);
		upitem2.setCategory("doce amargo");
		upitem2.setPrice(6);
		upitem2.setDescription("bolo de chocolate amargo");
		upitem2.setName("bolo amargo");
		upitem2.setComposition(up);
		ManagementItens.update(2, upitem2);
		//Verificando se o nome do item de id 2 é mesmo do objeto intânciado
		assertEquals(upitem2.getName(), ManagementItens.getOne(2).getName());
		//Verificando se a categoria do item de id 2 é mesmo do objeto intânciado
		assertEquals(upitem2.getCategory(), ManagementItens.getOne(2).getCategory());
		//Verificando se o valor do item de id 2 é mesmo do objeto intânciado
		assertEquals(upitem2.getPrice(), ManagementItens.getOne(2).getPrice());

	}
	
	//4º Teste
	@Test
	void updateItemOutListNull() {
		ArrayList<Ingredients> up3 = new ArrayList<Ingredients>();
		Ingredients ing14 = new Ingredients();
		up3.add(ing14);
		Item upitem4 = new Item();
		upitem4.setCategory("");
		upitem4.setPrice(6);
		upitem4.setDescription("torta de frango e requeijão");
		upitem4.setName("");
		upitem4.setComposition(up3);
		//Verificando excecao para upadete de Nome e categorias vazias 
		assertThrows(NullFieldException.class,() -> ManagementItens.update(6, upitem4) );
	}
}