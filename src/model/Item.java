package model;
import java.util.ArrayList;
/** Classe responsavel pelo objeto Itens do cardapio, os pratos
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class Item {
	private int id;
	private String name;
	private float price; // preço do prato
	private String description; // descriçao do prato
	private String category; // categoria do prato
	private ArrayList<Ingredients> composition; // lista dos ingredientes: produtos que compoem o prato, somente o id e a quantidade em kg utilizada.
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCatgory() {
		return category;
	}

	public void setCatgory(String category) {
		this.category = category;
	}

	/** Metodo para retornar a lista de produtos que compoem o prato
	 * 
	 * @return ArrayList<Integer> - lista de id's dos produtos utilizados no prato
	 */
	public ArrayList<Ingredients> getComposition() {
		return this.composition;
	}
	
	/** Metodo para inserir ou sobrescrever a lista de produtos do prato
	 * 
	 * @param composition - lista de id's de produtos
	 */
	public void setComposition(ArrayList<Ingredients> composition) {
		this.composition = composition;
	}
	
}