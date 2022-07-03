package model;
import java.util.ArrayList;

import exceptions.ListEmptyComposition;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;

/** Classe responsavel pelo gerenciamento dos itens (pratos do estabelecimento).
* 
* @author Washington Luis Ferreira de Oliveira Junior
* @author Tassio Carvalho Rodrigues
*
*/
public abstract class ManagementItens {
	private static ArrayList<Item> list = new ArrayList<Item>();//Lista de pratos
	private static int lastId; //Ultimo id disponivel. Nao representa o ultimo id da lista.
	
	/** Retorna a lista de itens
	 * 
	 * @return A lista completa de itens
	 */
	public static ArrayList<Item> listAllItens(){
		return list;
	}
	

	 /** Adiciona um novo item na lista de pratos
	  * 
	  * @param obj O item a ser adicionado na lista.
	  * @throws ListEmptyComposition excecao para Lista de composicao esta vazia ou nula
	  * @throws NegativePriceEntity excecao para Preco informado negativo
	  */
	public static void addItem(Item obj) throws ListEmptyComposition, NegativePriceEntity {
		// Se não tiver uma lista de composição, não adiciona
		if (obj.getComposition()==null || obj.getComposition().size()==0){
			throw new ListEmptyComposition();
		}
		if (obj.getPrice() >= 0) { 
		obj.setId(lastId++);
		list.add(obj);
		}else {
			throw new NegativePriceEntity();
		}
	}
	
	
	/** Deleta um item da lista, a partir do id passado como parametro.
	 * Se o id passado nao estiver na lista, nada acontece.
	 * 
	 * @param id	identificador (id) do item a ser removido.
	 */
	public static void delete(int id) {
		for (int i = 0; i < list.size(); i++) {
			// Se achar o id na lista
			if(id == list.get(i).getId()) {
				list.remove(i);
			}
		}
	}
	
	
	/** Atualiza os dados do item, apartir do id passado como parametro.
	 * 
	 * @param id id do item a ser alterado.
	 * @param obj novos dados que irao substituir os do item.
	 * @throws NullFieldException excecao para campo vazio
	 */
	public static void update(int id, Item obj) throws NullFieldException {
		int idItem;
		if (obj.getName() != "" || obj.getCategory() != "") {
		for (int i = 0; i < list.size(); i++) {
			idItem = list.get(i).getId();
			if (idItem == id) {
				list.get(i).setName(obj.getName());
				list.get(i).setPrice(obj.getPrice());
				list.get(i).setDescription(obj.getDescription());
				list.get(i).setCategory(obj.getCategory());
				list.get(i).setComposition(obj.getComposition());
			}
		}
		}else {
			throw new NullFieldException();
		}
	}
	
	
	
	/** Pega um prato da lista, com determinado id.
	 * 
	 * @param id	id do prato a ser buscado.
	 * @return		Retorna o prato, se o id estiver registrado;
	 * 				Retorna null, se o id nao estiver na lista.
	 */
	public static Item getOne(int id) {
		for (int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getId()) {
				return list.get(i);
			}
		}
		return null;
	}
	
	
	/** Encontrando lista de pratos com o nome passado no parametro
	 * 
	 * @param name - Nome dos pratos a serem retornados na lista
	 * @return  Uma lista com os pratos que possuem o nome igual ao presente no parametro name
	 */
	public static ArrayList<Item> searchName(String name){
		ArrayList<Item> newlist = new ArrayList<Item>();
		Item item;

		for(int i=0; i<list.size(); i++) {
			item = list.get(i);
			// Se o pratos tiver o nome igual ao do parametro
			if (item.getName().equals(name)) {
				newlist.add(item);
			}
		}
		
		return newlist;
	}
	
	
	/** Metodo para converter uma lista de id' de pratos em uma lista de pratos
	 * 
	 * @param listId - Lista de ids
	 * @return A lista de pratos
	 */
	public static ArrayList<Item> convertIdItemList(ArrayList<Integer> listId){
		ArrayList<Item> newList = new ArrayList<Item>();
		Item item;
		for(int i=0; i<listId.size(); i++) {
			item = getOne(listId.get(i));
			if (item != null) {
				newList.add(item);
			}
		}
		return newList;
	}
}