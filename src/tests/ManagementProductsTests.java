package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import model.ManagementProducts;
import model.Product;

class ManagementProductsTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//Produto id 0
		Product p1 = new Product();
		p1.setName("pao");
		p1.setQtd(1000);
		p1.setMedida("kg");
		p1.setPrice(2);
		p1.setValidity(LocalDate.now());
		// Produto id 1
		Product p2 = new Product();
		p2.setName("queijo");
		p2.setQtd(2340);
		p2.setMedida("L");
		p2.setPrice(4);
		p2.setValidity(LocalDate.now());
		// Produto id 2
		Product p3 = new Product();
		p3.setName("alface");
		p3.setQtd(2310);
		p3.setMedida("G");
		p3.setPrice(1);
		p3.setValidity(LocalDate.now());
		// Produto id 3
		Product p4 = new Product();
		p4.setName("tomate");
		p4.setQtd(3204);
		p4.setMedida("kg");
		p4.setPrice(2);
		p4.setValidity(LocalDate.now());
		// Produto id 4
		Product p5 = new Product();
		p5.setName("salsicha");
		p5.setQtd(2450);
		p5.setMedida("ml");
		p5.setPrice(5);
		p5.setValidity(LocalDate.now());
		// Produto id 5
		Product p6 = new Product();
		p6.setName("carne");
		p6.setQtd(5640);
		p6.setMedida("kg");
		p6.setPrice(10);
		p6.setValidity(LocalDate.now());
		//produto id 6
		Product p7 = new Product();
		p7.setName("frango");
		p7.setQtd(4052);
		p7.setMedida("kg");
		p7.setPrice(23);
		p7.setValidity(LocalDate.now());
		//produto id 7
		Product p8 = new Product();
		p8.setName("Farinha");
		p8.setQtd(4200);
		p8.setMedida("kg");
		p8.setPrice(13);
		p8.setValidity(LocalDate.now());
		//produto id 8
		Product p9 = new Product();
		p9.setName("acucar");
		p9.setQtd(4022);
		p9.setMedida("kg");
		p9.setPrice(25);
		p9.setValidity(LocalDate.now());
		//produto id 9
		Product p10 = new Product();
		p10.setName("arroz");
		p10.setQtd(4022);
		p10.setMedida("kg");
		p10.setPrice(52);
		p10.setValidity(LocalDate.now());
		//produto id 10
		Product p11 = new Product();
		p11.setName("feijao");
		p11.setQtd(3202);
		p11.setMedida("kg");
		p11.setPrice(75);
		p11.setValidity(LocalDate.now());
		//produto id 11
		Product p12 = new Product();
		p12.setName("bolo");
		p12.setQtd(3022);
		p12.setMedida("kg");
		p12.setPrice(23);
		p12.setValidity(LocalDate.now());

		ManagementProducts.addProduct(p1);
		ManagementProducts.addProduct(p2);
		ManagementProducts.addProduct(p3);
		ManagementProducts.addProduct(p4);
		ManagementProducts.addProduct(p5);
		ManagementProducts.addProduct(p6);
		ManagementProducts.addProduct(p7);
		ManagementProducts.addProduct(p8);
		ManagementProducts.addProduct(p9);
		ManagementProducts.addProduct(p10);
		ManagementProducts.addProduct(p11);
		ManagementProducts.addProduct(p12);
	}
	
	//1° Teste
	@Test
	void addAndRemoveProduct() throws NegativePriceEntity, InsufficientQuantityProducts, DateInvalid {
		Product pm = new Product();
		pm.setQtd(222);
		pm.setMedida("l");
		pm.setName("manteiga");
		pm.setPrice(2.3f);
		pm.setValidity(LocalDate.now());;
			ManagementProducts.addProduct(pm);
		// Verificando se o último produto adicionado é o mesmo instânciado
		assertEquals(pm, ManagementProducts.getOne(12));
		// Verificando a quantidade de produtos na lista, após adicionar
		assertEquals(13, ManagementProducts.listAllProducts().size());
		ManagementProducts.delete(6);
		// Verificando a quantidade de produtos na lista, após deletar
		assertEquals(12, ManagementProducts.listAllProducts().size());
	}
	
	//2° Teste
	@Test
	void deleteProductWithIdIn() {
		ManagementProducts.delete(1);
		// Verificando a quantidade de produtos na lista, após deletar elemento existente
		assertEquals(11, ManagementProducts.listAllProducts().size());
		
		ManagementProducts.delete(2);
		// Verificando a quantidade de produtos na lista, após deletar elemento existente
		assertEquals(10, ManagementProducts.listAllProducts().size());
	}
	
	//5° Teste
	@Test
	void deleteProductWithIdOut() {
		ManagementProducts.delete(44);
		//Verificando a quantidade de produtos na lista realizar o comando delete
		assertEquals(10, ManagementProducts.listAllProducts().size());
		
		ManagementProducts.delete(77);
		//Verificando a quantidade de produtos na lista realizar o comando delete
		assertEquals(10, ManagementProducts.listAllProducts().size());
	}
	
	//3° Teste
	@Test
	void updateProductInListAddNegativePrice(){
		Product novoP = new Product();
		novoP.setName("paçoca");
		novoP.setMedida("l");
		novoP.setQtd(232);
		novoP.setPrice(-18.0f);
		novoP.setValidity(LocalDate.now());
		//verificando se existe valor negativo tentando atualizar o mesmo
		assertThrows(NegativePriceEntity.class,() -> ManagementProducts.update(3, novoP));
	}
	
	//4° Teste
	@Test
	void updateProductOutListAddNegativePrice(){
		Product novoP = new Product();
		novoP.setName("milho");
		novoP.setMedida("Hl");
		novoP.setQtd(234);
		novoP.setPrice(-18);
		novoP.setValidity(LocalDate.now());
		// Verificando se existe valor negativo, tentando atualizar o mesmo
		assertThrows(NegativePriceEntity.class,() -> ManagementProducts.update(20, novoP));

	}
}