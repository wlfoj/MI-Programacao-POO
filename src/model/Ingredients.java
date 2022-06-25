package model;

/** Classe responsavel pelo objeto Ingredientes do prato, refere-se ao id do produto utilizado e a quantidade utilizada
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class Ingredients {
	private int id;// id do produto
	private int qtd;// quantidade
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
}