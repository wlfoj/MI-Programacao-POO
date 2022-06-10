package exceptions;

/** Classe responsavel pela excecao de Quantidade de produtos insuficientes
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 */

public class InsufficientQuantityProducts extends Exception {

	private static final long serialVersionUID = 1L;

	public InsufficientQuantityProducts() {
		super("Quantidade de produtos insuficientes");
	}
}