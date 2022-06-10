package exceptions;

/** Classe responsavel pela excecao se o objeto já estiver sido registrado
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 */
public class ObjectRegistred extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjectRegistred() {
		super("Login já registrado");
	}
}