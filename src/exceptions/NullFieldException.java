package exceptions;

/** Classe responsavel pela excecao se o Campo informado e nulo
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 */
public class NullFieldException extends Exception {

	private static final long serialVersionUID = 1L;

	public NullFieldException() {
		super("Campo informado é nulo");
	}
}