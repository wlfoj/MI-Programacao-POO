package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import exceptions.InsufficientQuantityProducts;

/** Classe responsavel pelo gerenciamento dos Vendas.
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 * 
 */
public abstract class ManagementSales {
	private static ArrayList<Sale> list = new ArrayList<Sale>();//Lista de vendas, historico de vendas
	private static int lastId;// Ultimo id disponivel. No caso se a lista for preenchida, 
	//o ultimo elemento tera id lastId-1
	
	/** Retorna a lista de vendas
	 * 
	 * @return A lista completa de vendas
	 */
	public static ArrayList<Sale> listAllSale(){
		return list;
	}

	
	/**Metodo para pegar a venda que aparece o id de um prato desejado.
	 * Obs. O metodo retorna todos os pratos presentes na venda, bastando que um dos pratos
	 * seja o desejado
	 * 
	 * @param idPrato	Id do prato que se busca
	 * @return	A lista filtrada, vendas que possuem o prato solicitado
	 */
	public static ArrayList<Sale> listSaleWithItem(int idPrato){
		Sale obj;
		ArrayList<Integer> pratos;
		ArrayList<Sale> listWithItem = new ArrayList<Sale>();
		//Percorrendo a lista de vendas
		for(int i=0; i<list.size(); i++) {
			obj = list.get(i);
			pratos = obj.getItens();
			//Percorrendo a lista de pratos
			for(int j=0; j<pratos.size(); j++) {
				//Verificando se algum dos ids ï¿½ igual ao solicidato
				if (pratos.get(j) == idPrato) {
					listWithItem.add(obj);
					break;
				}
			}	
		}
		return listWithItem;
	}
	
	
	
	public static ArrayList<Sale> listSalePerPeriod(LocalDateTime dataInicial, LocalDateTime dataFinal){
		ArrayList<Sale> SalePerPeriod = new ArrayList<Sale>();
		Sale s;
		//data1 é depois de data2?
		
		for(int i=0; i<list.size(); i++) {
			s = list.get(i);
			if (s.getDate().isAfter(dataInicial)==true  && s.getDate().isBefore(dataFinal)==true) {
				SalePerPeriod.add(s);
			}
		}
		return SalePerPeriod;
	}
	
	
	
	/** Registra uma nova venda no sistema.
	 * 
	 * @param obj a venda a ser adicionada na lista.
	 */
	public static void addSale(Sale obj) throws InsufficientQuantityProducts {
		LocalDateTime now = LocalDateTime.now();
		obj.setDate(now);
		ArrayList<Integer> pratosVenda = obj.getItens();// Lista com o id dos pratos vendidos
		ArrayList<Item> todosPratos = ManagementItens.listAllItens();// Lista de todos os pratos
		ArrayList<Product> todosProdutos = ManagementProducts.listAllProducts();// Lista de todos os produtos
		// Buscando o objeto Prato com o id recebido do objeto venda
		for (int i = 0; i < pratosVenda.size(); i++) {// Percorrendo a lista de id's dos pratos vendidos
			for (int j = 0; j < todosPratos.size(); j++){// Percorrendo a lista de pratos no cardÃ¡pio
				if(pratosVenda.get(i) == todosPratos.get(j).getId()){ // Verificando se o prato Ã© o mesmo
					ArrayList<Ingredients> ingredientes = todosPratos.get(j).getComposition();
					// Buscando a relação entre pratos e ingredientes
					for (int k = 0; k < ingredientes.size(); k++){// Percorre a lista de ingredientes
						for (int c = 0; c < todosProdutos.size(); c++){// Percorre a lista de produtos
							//caso não tenha o produto utilizado como ingrediente
							if(ManagementProducts.getOne(ingredientes.get(k).getId())==null) {
								throw new InsufficientQuantityProducts();
							}
							
							else if(ingredientes.get(k).getId()==todosProdutos.get(c).getId()){
								if (ingredientes.get(k).getQtd() > todosProdutos.get(c).getQtd() ) {
									throw new InsufficientQuantityProducts();
								}
								todosProdutos.get(c).setQtd( todosProdutos.get(c).getQtd() - ingredientes.get(k).getQtd());
								
								break;
							}
						}
					}
					break;
				}
			}
		}
		obj.setId(lastId++);
		list.add(obj);
	}

	
	
	/** Deleta uma venda da lista, a partir do id passado como parametro.
	 * Se o id passado nao estiver na lista, nada acontece.
	 * 
	 * @param id	identificador (id) da venda a ser removido.
	 */
	public static void delete(int id) {
		for (int i = 0; i < list.size(); i++) {
			// Se achar o id na lista
			if(id == list.get(i).getId()) {
				list.remove(i);
			}
		}
	}
	
	

	/** Atualiza os dados da venda, apartir do id passado como parametro.
	 * 
	 * @param id	id da venda a ser alterado.
	 * @param obj	novos dados que irao substituir as da venda
	 * @return		<code>true</code> Se existir uma venda com o id passado como parametro;
	 * 				<code>false</code> Se nao houver venda com o id.
	 */
	public static boolean update(int id, Sale obj) {
		int idSale;
		for (int i = 0; i < list.size(); i++) {
			idSale = list.get(i).getId();
			if (idSale == id) {
				list.get(i).setDate(obj.getDate());
				list.get(i).insertItens(obj.getItens());
				list.get(i).setPaymentMethod(obj.getPaymentMethod());
				return true;
			}
		}
		return false;
	}
	
	
	/** Pega uma venda da lista, com determinado id.
	 * 
	 * @param id	id da venda a ser buscada.
	 * @return		Retorna a venda, se o id estiver registrado;
	 * 				Retorna null, se o id nao estiver na lista.
	 */
	public static Sale getOne(int id) {
		for (int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getId()) {
				return list.get(i);
			}
		}
		return null;
	}
	
	
	
	/**Metodo para contar a quantidade de pratos vendidos em uma lista de vendas
	 * 
	 * @param	Sales A lista de vendas a ser contada
	 * @return	A quantidade de pratos vendidos
	 */
	public static int countItensInSale(ArrayList<Sale> sales) {
		int count =0;
		for(int i=0; i<sales.size(); i++) {
			count = count + sales.get(i).getItens().size();
		}
		return count;
	}
	
	
	
	/** Metodo que faz a soma de todos os valores de todas as vendas analisadas
	 * 
	 * @param sales	Lista de vendas a ser analisada e somados os valores
	 * @return	Soma de todas as vendas da lista
	 */
	public static float sumTotalPrice(ArrayList<Sale> sales) {
		float total= 0;
		
		for(int i=0; i<sales.size(); i++) {
			total = total + sales.get(i).getTotalPrice();
		}
		
		return total;
	}
}