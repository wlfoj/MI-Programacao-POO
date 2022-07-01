package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Administrator;
import model.Employee;
import model.User;
import exceptions.NullFieldException;
import exceptions.ObjectRegistred;
import model.ManagementUsers;

class ManagementUsersTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		User obj1 = new Employee();
		obj1.setLogin("riri");
		obj1.setName("Rick");
		obj1.setPass("1234");
		//
		User obj2 = new Administrator();
		obj2.setLogin("wayne");
		obj2.setName("Bruce");
		obj2.setPass("1234");
		//
		Administrator obj3 = new Administrator();
		obj3.setLogin("carlos");
		obj3.setName("Carlos");
		obj3.setPass("12345");
		//
		Employee obj4 = new Employee();
		obj4.setLogin("jao");
		obj4.setName("Jonas");
		obj4.setPass("1111");
		//
		User obj5 = new Employee();
		obj5.setLogin("rafa");
		obj5.setName("Rafael");
		obj5.setPass("88");
		//
		Administrator obj6 = new Administrator();
		obj6.setLogin("manu");
		obj6.setName("Manuel");
		obj6.setPass("12345");
		// Inserindo no gerenciamento
		ManagementUsers.addUser(obj1);
		ManagementUsers.addUser(obj2);
		ManagementUsers.addUser(obj3);
		ManagementUsers.addUser(obj4);
		ManagementUsers.addUser(obj5);
		ManagementUsers.addUser(obj6);
	}
	
	//1° Teste
	@Test
	void addAndRemoveUser() throws NullFieldException, ObjectRegistred{
		Administrator admin = new Administrator();
		admin.setLogin("admin");
		admin.setName("Teste");
		admin.setPass("admin");
			ManagementUsers.addUser(admin);
		// Verificando o último user da lista
		assertEquals(admin, ManagementUsers.getOne(6));
		// Verificando quantidade de itens na lista, após adicionar
		assertEquals(7, ManagementUsers.listAllUsers().size());
		ManagementUsers.delete(6);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(6, ManagementUsers.listAllUsers().size());
	}
	
	//4° Teste
	@Test
	void addUsersWithSameLogin() throws NullFieldException, ObjectRegistred {
		// Verificando a quantidade inicial de usuários
		assertEquals(6, ManagementUsers.listAllUsers().size());
		Employee funcionario1 = new Employee();
		funcionario1.setLogin("nate");
		funcionario1.setName("Nathan");
		funcionario1.setPass("1111");
		//
		Administrator adm = new Administrator();
		adm.setLogin("natee");
		adm.setName("Natanael");
		adm.setPass("aaab");
		//
		Employee funcionario2 = new Employee();
		funcionario2.setLogin("nateee");
		funcionario2.setName("Natalia");
		funcionario2.setPass("317");
		
		
			ManagementUsers.addUser(funcionario1);

		// Verificando quantidade de itens na lista, após adicionar
		assertEquals(7, ManagementUsers.listAllUsers().size());
		
			ManagementUsers.addUser(funcionario2);


		// Verificando quantidade de itens na lista, após adicionar
		assertEquals(8, ManagementUsers.listAllUsers().size());
		

			ManagementUsers.addUser(adm);


		// Verificando quantidade de itens na lista, após adicionar
		assertEquals(9, ManagementUsers.listAllUsers().size());
	}
	
	//3° Teste
	@Test
	void deleteUsersWithIdOut() {
		ManagementUsers.delete(44);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(6, ManagementUsers.listAllUsers().size());
		
		ManagementUsers.delete(77);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(6, ManagementUsers.listAllUsers().size());
	}
	
	//5° Teste
	@Test
	void deleteUsersWithIdIn() {
		ManagementUsers.delete(0);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(8, ManagementUsers.listAllUsers().size());
		
		ManagementUsers.delete(3);
		// Verificando quantidade de itens na lista, após deletar
		assertEquals(7, ManagementUsers.listAllUsers().size());
	}
	
	//2° Teste
	@Test
	void updateUsersNull(){
		Employee aa1 = new Employee();
		aa1.setName("maluco");
		aa1.setLogin("");
		aa1.setPass("");
		// Fazendo update com a excecao do campo vazio para os login e senha
		assertThrows(NullFieldException.class,() -> ManagementUsers.update(5, aa1));

		
	}
	
	//7° Teste
	@Test
	void addUserLoginNull(){
		Employee aa = new Employee();
		aa.setName("crono");
		aa.setLogin("");
		aa.setPass("");
		// Adicionando usuario com a excecao do campo vazio para os login e senha
		assertThrows(NullFieldException.class,() -> ManagementUsers.addUser(aa) );
	}
}