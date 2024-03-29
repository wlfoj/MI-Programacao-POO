package model;

import java.time.LocalDate;

/** Classe responsavel pelo objeto produto primario. EX: tomate, arroz...
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class Product {
	private int id;
	private String name;// nome do produto
	private float price;// preço do produto
	private LocalDate validity;// validade do produto.
	private float qtd;// quantidade
	private String medida;// unidade de medida para a quantidade. Ex. 15Kg, 2L
	
	
	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}
	
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
	
	public LocalDate getValidity() {
		return validity;
	}
	
	public void setValidity(LocalDate validity) {
		this.validity = validity;
	}

	public float getQtd() {
		return qtd;
	}

	public void setQtd(float f) {
		this.qtd = f;
	}
}