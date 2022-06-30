package exceptions;

/** Classe responsavel pela excecao deData invalida
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 */

public class DateInvalid extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DateInvalid() {
		super("Validade invalida");
	}

}

