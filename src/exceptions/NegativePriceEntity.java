package exceptions;

/** Classe responsavel pela excecao de Preco informado negativo ou igual a 0
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 */

public class NegativePriceEntity extends Exception {

	private static final long serialVersionUID = 1L;

	public NegativePriceEntity() {
		super("Preco informado negativo, insira um valor maior que 0");
	}
}