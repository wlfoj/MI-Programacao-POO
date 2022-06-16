package model;

import java.util.ArrayList;

import exceptions.NullFieldException;
import exceptions.ObjectRegistred;


public class ManagementCostumer {
	private static ArrayList<Costumer> list = new ArrayList<Costumer>();//Lista de produtos
	private static int lastId;//Ultimo id disponivel. Nao representa o ultimo id da lista.
	//Acho que pode apagar vai usar o cpf mesmo
	

	/**Adiciona um novo cliente na lista de clientes, se o cpf já não estiver registrado.
	 * 
	 * @param obj	O usuario a ser adicionado na lista, pode ser Employee ou Management.
	 * @throws NullFieldException excecao para o campo nulo
	 */
	public static void addCostumer(Costumer obj) throws NullFieldException, ObjectRegistred {
		if (obj.getCpf() == "" || obj.getName() == "") {
			throw new NullFieldException();
		} else if (containsCpf(obj.getCpf())){
			throw new ObjectRegistred();
		}else {
			obj.setId(lastId++);
			list.add(obj);
		}
	}
	
	
	/** Retorna a lista de clientes
	 * 
	 * @return A lista completa de clientes
	 */
	public static ArrayList<Costumer> listAllCostumer(){
		return list;
	}
	
	
	/** Deleta um cliente da lista, a partir do id passado como parametro.
	 * Se o id passado nao estiver na lista, nada acontece.
	 * 
	 * @param id	identificador (id) do cliente a ser removido.
	 */
	public static void delete(int id) {
		for (int i = 0; i < list.size(); i++) {
			// Se achar o id na lista
			if(id == list.get(i).getId()) {
				list.remove(i);
			}
		}
	}
	
	
	/**Atualiza os dados do cliente, a partir do id passado como parametro.
	 * 
	 * @param id id do cliente a ser alterado.
	 * @param obj novos dados que irao substituir os do cliente.
	 * @throws NullFieldException excecao para campo vazio
	 */
		public static void update(int id, Costumer obj) throws NullFieldException{
			int idProvider;
			if (obj.getName() != "" && obj.getCpf() != "") {
				throw new NullFieldException ();
			}
			else {
				//Percorrendo alista
				for (int i = 0; i < list.size(); i++) {
					idProvider = list.get(i).getId();
					if (idProvider == id) {
						list.get(i).setName(obj.getName());
						list.get(i).setCpf(obj.getCpf());
						list.get(i).setEmail(obj.getEmail());
						list.get(i).setTelefone(obj.getTelefone());
					}
				}
			}
		}
		
		
	/** Verifica se o cpf ja esta registrado na lista de clientes.
	 * 
	 * @param cpf 	cpf do cliente
	 * @return 			<code>true</code> Se o cpf ja estiver registrado;
	 * 					<code>false</code> Se o cpf nao estiver registrado.
	 */
	private static boolean containsCpf(String cpf) {
		String cpfCostumer;
        for (int i = 0; i < list.size(); i++) {
        	cpfCostumer = list.get(i).getCpf();
        	if (cpf == cpfCostumer) {
        		return true;
        	}
        }
        return false;
	}
	
	
	/** Encontrando lista de clientes com o nome passado no parametro
	 * 
	 * @param name - Nome dos clientes a serem retornados na lista
	 * @return  Uma lista com os clientes que possuem o nome igual ao presente no parametro name
	 */
	public static ArrayList<Costumer> searchName(String name){
		ArrayList<Costumer> newlist = new ArrayList<Costumer>();
		Costumer costumer;

		for(int i=0; i<list.size(); i++) {
			costumer = list.get(i);
			// Se o pratos tiver o nome igual ao do parametro
			if (costumer.getName().equals(name)) {
				newlist.add(costumer);
			}
		}
		
		return newlist;
	}
	
	
	/** Pega um cliente da lista, com determinado id.
	 * 
	 * @param id	id do cliente a ser buscado.
	 * @return		Retorna o cliente, se o id estiver registrado;
	 * 				Retorna null, se o id nao estiver na lista.
	 */
	public static Costumer getOne(int id) {
		for (int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getId()) {
				return list.get(i);
			}
		}
		return null;
	}
}