package model;

/** Classe responsavel pelo objeto Ingredientes do prato, refere-se ao id do produto utilizado e a quantidade utilizada
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class Ingredients {
	private int id;// id do produto
	private float qtd;// quantidade
	private String medida;// unidade de medida para a quantidade. Ex. 15Kg, 2L
	private String name;// nome do produto
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getQtd() {
		return qtd;
	}

	public void setQtd(float qtd) {
		this.qtd = qtd;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}