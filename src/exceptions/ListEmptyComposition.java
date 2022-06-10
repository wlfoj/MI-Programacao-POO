package exceptions;

/** Classe responsavel pela excecao de Lista de composicao esta vazia ou nula
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 */

public class ListEmptyComposition extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ListEmptyComposition() {
		super("Lista de composicao esta vazia ou nula!");
	}
}