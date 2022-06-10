package model;

import java.util.ArrayList;
/** Classe responsavel pelo objeto Fornecedor.
 * Que fornece os produtos primarios.
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class Provider {
	private int id;	// id do fornecedor
	private String name;
	private String cnpj;
	private String address; // endereço do fornecedor
	private ArrayList<Integer> products; // lista de produtos fornecidos, somente o id deles.
	
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/** Metodo para buscar os id's dos produtos fornecidos
	 * 
	 * @return ArrayList<Integer> - lista com os id's dos produtos fornecidos
	 */
	public ArrayList<Integer> getProducts() {
		return products;
	}
	
	/**Metodo para alterar ou inserir os id's dos produtos fornecidos
	 * 
	 * @param products - lista de id's dos produtos fornecidos
	 */
	public void setProducts(ArrayList<Integer> products) {
		this.products = products;
	}
}