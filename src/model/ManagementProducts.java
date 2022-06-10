package model;

import java.util.ArrayList;
import java.util.Calendar;
import exceptions.InsufficientQuantityProducts;
import exceptions.NegativePriceEntity;
import exceptions.NullFieldException;

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
	public static ArrayList<Product> listProductsInExpiration(int qtdProducts, Calendar dateToExpi){
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
	 */
	public static void addProduct(Product obj) throws NegativePriceEntity, InsufficientQuantityProducts{
		if (obj.getQtd()<=0) {
			throw new InsufficientQuantityProducts();
		}
		if (obj.getPrice() >= 0) {
			obj.setId(lastId++);
			list.add(obj);
		}else {
			throw new NegativePriceEntity();
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
	 * @throws NullFieldException excecao para campo nulo
	 * @throws NegativePriceEntity excecao para valores negativos
	 */
	public static void update(int id, Product obj) throws NullFieldException,NegativePriceEntity{
		int idProduct;
		if (obj.getName() != "") {
		for (int i = 0; i < list.size(); i++) {
			idProduct = list.get(i).getId();
			if (obj.getPrice() >= 0) {
			if (idProduct == id) {
				list.get(i).setPrice(obj.getPrice());
				list.get(i).setName(obj.getName());
				list.get(i).setValidity(obj.getValidity());
				}
			} else {
				throw new NegativePriceEntity();
			}
				
			}
		}else {
			throw new NullFieldException();
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
}