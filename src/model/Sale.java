package model;
import java.util.ArrayList;
import java.util.Calendar;

/** Classe responsavel pelo objeto venda
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public class Sale {
	private int id;
	private float totalPrice;//Valor total da venda
	private String paymentMethod;//Metodo de pagamento
	private ArrayList<Integer> itens;//Lista com os id's dos pratos vendidos
	private Calendar date;//obj da data

	public int getId() {
		return this.id;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}

	
	
	public float getTotalPrice() {
		return this.totalPrice;
	}
	
	

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	
	
	/** MÃ©todo registra o metodo de pagamento
	 * 
	 * @param paymentMethod metodo de pagamento - DEBITO, CREDITO, AVISTA, PIX
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

	public ArrayList<Integer> getItens() {
		return this.itens;
	}
	
	

	/**Metodo para registrar a lista de id dos pratos na classe e calcular o valor total da venda
	 * 
	 * @param itens	Lista com os id's dos pratos vendidos na venda
	 */
	public void insertItens(ArrayList<Integer> itens) {
		this.itens = itens;
		// Se nÃ£o tiver nenhum prato vendido
		if (itens==null){
			this.totalPrice = 0;
		}
		// Se tiver prato vendido, calcula o preÃ§o total da venda e registra
		else{
			this.totalPrice = this.calcTotal();
		}
	}
	
	

	public Calendar getDate() {
		return this.date;
	}
	
	

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	
	
	/** Metodo para calcular o valor total da venda.
	 * Soma dos precos dos pratos.
	 * 
	 * @return retorna a soma do preço de todos os pratos da lista
	 */
	private float calcTotal() {
		float totalCost = 0;
		int id;
		ArrayList<Item> lisItens = ManagementItens.listAllItens();
		// Percorrendo a lista de id's
		for(int i=0; i<itens.size(); i++) {
			id = itens.get(i);
			// Percorrendo a lista de pratos
			for(int k=0; k< lisItens.size(); k++){
				// Comparando os id's
				if (lisItens.get(k).getId() == id){
					totalCost = totalCost + lisItens.get(k).getPrice();
					break;
				}
			}
		}
		return totalCost;
	}
	
}