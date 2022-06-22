package model;

import java.util.ArrayList;

import exceptions.NullFieldException;

/** Classe responsavel pelo gerenciamento dos fornecedores.
* 
* @author Washington Luis Ferreira de Oliveira Junior
* @author Tassio Carvalho Rodrigues
*
*/
public abstract class ManagementProvider {
	private static ArrayList<Provider> list = new ArrayList<Provider>();//Lista de fornecedores
	private static int lastId;//Ultimo id disponivel. Nao representa o ultimo id da lista.

	/** Retorna a lista de fornecedores
	 * 
	 * @return A lista completa de fornecedores
	 */
	public static ArrayList<Provider> listAllProvider(){
		return list;
	}
		
	
	/** Retorna a lista de fornecedores que fornecem o produto com o determinado id
	 * 
	 * @param idProduct O id do produto que o fornecedor precisa ter para ser incluido na lista.
	 * @return A lista filtrada pelo id dos produtos
	 */
	public static ArrayList<Provider> listProviderPerProduct(int idProduct){
		ArrayList<Provider> resp = new ArrayList<Provider>();// Lista de resposta
		//Percorre os fornecedores
		for(int i=0; i<list.size(); i++) {
			ArrayList<Integer> prods = list.get(i).getProducts();
			//Percorre seus produtos
			for(int j=0; j<prods.size(); j++) {
				//Se tiver id igual, adiciona na lista de resposta
				if(prods.get(j)==idProduct) {
					resp.add(list.get(i));
				}
			}
		}
		return resp;
	}
	
	
	/**Adiciona um novo fornecedor na lista de fornecedores.
	 * 
	 * @param obj O fornercedor a ser adicionado na lista.
	 * @throws NullFieldException excecao para campo vazio
	 */
	public static void addProvider(Provider obj) throws NullFieldException {
		if (obj.getName() != "") {
		obj.setId(lastId++);
		list.add(obj);
		}else {
			throw new NullFieldException();
		}
	}
		
	
	/** Deleta um fornecedor da lista, a partir do id passado como parametro.
	 * Se o id passado nao estiver na lista, nada acontece.
	 * 
	 * @param id	identificador (id) do forncedor a ser removido.
	 */
	public static void delete(int id) {
		for (int i = 0; i < list.size(); i++) {
			// Se achar o id na lista
			if(id == list.get(i).getId()) {
				list.remove(i);
			}
		}
	}
		
	
/**Atualiza os dados do fornecedor, a partir do id passado como parametro.
 * 
 * @param id id do fornecedor a ser alterado.
 * @param obj novos dados que irao substituir os do fornecedor.
 * @throws NullFieldException excecao para campo vazio
 */
	public static void update(int id, Provider obj) throws NullFieldException{
		int idProvider;
		if (obj.getName() != "") {
		for (int i = 0; i < list.size(); i++) {
			idProvider = list.get(i).getId();
			if (idProvider == id) {
				list.get(i).setName(obj.getName());
				list.get(i).setAddress(obj.getAddress());
				list.get(i).setCnpj(obj.getCnpj());
				list.get(i).setProducts(obj.getProducts());
				list.get(i).setProducts(obj.getProducts());
				}
			} 
		}else {
			throw new NullFieldException ();
			}
		}
	
	
	/** Pega um fornecedor da lista, com determinado id.
	 * 
	 * @param id	id do fornecedor a ser buscado.
	 * @return		Retorna o fornecedor, se o id estiver registrado;
	 * 				Retorna null, se o id nao estiver na lista.
	 */
	public static Provider getOne(int id) {
		for (int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getId()) {
				return list.get(i);
			}
		}
		return null;
	}
	
	
	/** Encontrando lista de fornecedores com o nome passado no parametro
	 * 
	 * @param name - Nome dos fornecedores a serem retornados na lista
	 * @return  Uma lista com os fornecedores que possuem o nome igual ao presente no parametro name
	 */
	public static ArrayList<Provider> searchName(String name){
		ArrayList<Provider> newlist = new ArrayList<Provider>();
		Provider provider;

		for(int i=0; i<list.size(); i++) {
			provider = list.get(i);
			// Se o fornecedor tiver o nome igual ao do parametro
			if (provider.getName().equals(name)) {
				newlist.add(provider);
			}
		}
		
		return newlist;
	}
	
}