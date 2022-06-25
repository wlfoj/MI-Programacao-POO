package model;

import java.time.LocalDate;
import java.util.ArrayList;
import exceptions.DateInvalid;
import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;

/** Classe responsavel pelo gerenciamento dos produtos.
* 
* @author Washington Luis Ferreira de Oliveira Junior
* @author Tassio Carvalho Rodrigues
*
*/
public abstract class ManagementProducts {
	private static ArrayList<Product> list = new ArrayList<Product>();//Lista de produtos
	private static int lastId;//Ultimo id disponivel. Nao representa o ultimo id da lista.
	
	
	/** Retorna a lista de produtos.
	 * 
	 * @return A lista completa de produtos
	 */
	public static ArrayList<Product> listAllProducts(){
		return list;
	}
	
	
	/** Metodo para buscar os produtos que estar�o expirados at� a data recebida
	 * 
	 * @param qtdProducts	Quantidade maxima de itens que o usuario quer ver no relatorio
	 * @param dateToExpi	Data limite em que os produtos v�o expirar
	 * @return	A lista filtrada, contendo at� <code>qtdProducts</code> elementos
	 */
	public static ArrayList<Product> listProductsInExpiration(int qtdProducts, LocalDate dateToExpi){
		int count = 0;
		Product p;
		ArrayList<Product> productExpiration = new ArrayList<Product>();
		
		for(int i=0; i<list.size(); i++) {
			p = list.get(i);
			if (count<qtdProducts && p.getValidity().compareTo(dateToExpi)<=0) {
				productExpiration.add(p);
				count = count + 1;
			}
		}
		return productExpiration;
	}
	
	
	/** Adiciona um novo produto na lista de produto.
	 * 
	 * @param obj adicionando produto
	 * @throws NegativePriceEntity Excecao para precos negativos ou nulo
	 * @throws InsufficientQuantityProducts Excecao para quantidade invalida
	 * @throws DateInvalid Data Excecao para data de validade menor que a data atual
	 */
	public static void addProduct(Product obj) throws NegativePriceEntity, InsufficientQuantityProducts, DateInvalid{
		if (obj.getValidity().compareTo(LocalDate.now()) < 0) {
			throw new DateInvalid();
		}
		else if (obj.getQtd()<=0) {
			throw new InsufficientQuantityProducts();
		}
		else if (obj.getPrice() < 0){
			throw new NegativePriceEntity();
		}
		else {
			obj.setId(lastId++);
			list.add(obj);
		}
	}
	
	
	/** Deleta um produto da lista, a partir do id passado como parametro.
	 * Se o id passado nao estiver na lista, nada acontece.
	 * 
	 * @param id	identificador (id) do produto a ser removido.
	 */
	public static void delete(int id) {
		for (int i = 0; i < list.size(); i++) {
			// Se achar o id na lista
			if(id == list.get(i).getId()) {
				list.remove(i);
			}
		}
	}
	
	
	/**Atualiza os dados do produto, apartir do id passado como parametro.
	 * 
	 * @param id id do produto a ser alterado.
	 * @param obj novos dados que irao substituir os do produto.
	 * @throws NegativePriceEntity excecao para valores negativos de preco
	 * @throws DateInvalid excecao para data de validade inferior a atual
	 * @throws InsufficientQuantityProducts excecao para uma quantidade menor ou igual a 0 do produto
	 */
	public static void update(int id, Product obj) throws NegativePriceEntity, DateInvalid, InsufficientQuantityProducts{
		int idProduct;
		if (obj.getValidity().compareTo(LocalDate.now()) < 0) {
			throw new DateInvalid();
		}
		else if (obj.getQtd()<=0) {
			throw new InsufficientQuantityProducts();
		}
		else if (obj.getPrice() < 0){
			throw new NegativePriceEntity();
		}
		else {
			for (int i = 0; i < list.size(); i++) {
				idProduct = list.get(i).getId();
				if (idProduct == id) {
					list.get(i).setPrice(obj.getPrice());
					list.get(i).setName(obj.getName());
					list.get(i).setValidity(obj.getValidity());
				}
			}
		}
	}
	
	
	/** Pega um produto da lista, com determinado id.
	 * 
	 * @param id	id do produto a ser buscado.
	 * @return		Retorna o produto, se o id estiver registrado;
	 * 				Retorna null, se o id nao estiver na lista.
	 */
	public static Product getOne(int id) {
		for (int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getId()) {
				return list.get(i);
			}
		}
		return null;
	}
	
	
	/** Encontrando lista de produtos com o nome passado no parametro
	 * 
	 * @param name - Nome dos produtos a serem retornados na lista
	 * @return  Uma lista com os produtos que possuem o nome igual ao presente no parametro name
	 */
	public static ArrayList<Product> searchName(String name){
		ArrayList<Product> newlist = new ArrayList<Product>();
		Product product;

		for(int i=0; i<list.size(); i++) {
			product = list.get(i);
			// Se o produtos tiver o nome igual ao do parametro
			if (product.getName().equals(name)) {
				newlist.add(product);
			}
		}
		
		return newlist;
	}
	
	
	public static ArrayList<Product> convertInProductList(ArrayList<Integer> listId){
		ArrayList<Product> newList = new ArrayList<Product>();
		Product p;
		for(int i=0; i<listId.size(); i++) {
			p = getOne(listId.get(i));
			if (p != null) {
				newList.add(p);
			}
		}
		return newList;
	}
	
}