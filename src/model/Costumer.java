package model;

import java.util.ArrayList;

public class Costumer {
	private int id;
	private String name;
	private String cpf;
	private String email;
	private String telefone;
	private ArrayList<Integer> listSales;// Lista com o id das vendas
	
	
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void addSaleId(Integer id) {
		listSales.add(id);
	}
}