package model;

import java.util.ArrayList;

import exceptions.NullFieldException;

/** Classe responsavel pelo gerenciamento dos usuarios.
 * 
 * @author Washington Luis Ferreira de Oliveira Junior
 * @author Tassio Carvalho Rodrigues
 *
 */
public abstract class ManagementUsers {
	private static ArrayList<User> list = new ArrayList<User>();//Lista de usuarios
	private static int lastId;// Ultimo id disponivel. Não representa o ultimo id da lista.
	
	/** Retorna a lista de usuarios
	 * 
	 * @return A lista completa de usuarios
	 */
	public static ArrayList<User> listAllUsers(){
		return list;
	}
	
	

	/**Adiciona um novo usuario na lista de usuarios, se o login ja nao estiver registrado.
	 * 
	 * @param obj	O usuario a ser adicionado na lista, pode ser Employee ou Management.
	 * @throws NullFieldException excecao para o campo nulo
	 */
	public static void addUser(User obj) throws NullFieldException {
		if (obj.getLogin() != "" || obj.getPass() != "") {
		// Se o login não tiver na lista
		if(!containsLogin(obj.getLogin())) {
			obj.setId(lastId++);
			list.add(obj);
		}
		} else {
	  		   throw new NullFieldException();
				}
	}
	
	
	/** Deleta um usuario da lista, apartir do id passado como parametro.
	 * Se o id passado nao estiver na lista, nada acontece.
	 * 
	 * @param id	identificador (id) do usuario a ser removido.
	 */
	public static void delete(int id) {
		for (int i = 0; i < list.size(); i++) {
			// Se achar o id na lista
			if(id == list.get(i).getId()) {
				list.remove(i);
			}
		}
	}
	
	
	/**Atualiza os dados do usuario, apartir do id passado como parametro.
	 * 
	 * @param id  id do usuario a ser alterado.
	 * @param obj  novos dados que irao substituir os do usuario.
	 * @throws NullFieldException excecao para o campo nulo
	 */
	public static void update(int id, User obj) throws NullFieldException {
		int idUser;
		if (obj.getLogin() != "" || obj.getPass() != "") {
		for (int i = 0; i < list.size(); i++) {
			idUser = list.get(i).getId();
			if (idUser == id) {
				list.get(i).setLogin(obj.getLogin());
				list.get(i).setPass(obj.getPass());
				list.get(i).setName(obj.getName());
				}
			} 
		}else {
					throw new NullFieldException();
				}
			}
		
	
	
	
	/** Verifica se o login ja esta registrado na lista de usuarios.
	 * 
	 * @param login 	login do usuario
	 * @return 			<code>true</code> Se o login ja estiver registrado;
	 * 					<code>false</code> Se o login nao estiver registrado.
	 */
	private static boolean containsLogin(String login) {
		String loginUser;
        for (int i = 0; i < list.size(); i++) {
        	loginUser = list.get(i).getLogin();
        	if (login == loginUser) {
        		return true;
        	}
        }
        return false;
	}
	
	
	/** Verifica se existe um usuario com o login e a senha.
	 * É um metodo para autenticacao do usuario.
	 * 
	 * @param login		login do usuario.
	 * @param pass		senha do usuario.
	 * @return			<code>true</code> Se houver um usuario com o login e senha;
	 * 					<code>false</code> Se nao houver um usuario com os dados.
	 */
	public static User auth(String login, String pass) {
		String loginUser;
		String passUser;
		for (int i = 0; i < list.size(); i++) {
			loginUser = list.get(i).getLogin();
			passUser = list.get(i).getPass();
			// Se os dados forem iguais
			 if (loginUser.equals(login) && passUser.equals(pass)) {
				 return list.get(i);
			 }
		}
		return null;
	}
	
	
	/** Identifica se o usuario ser um Management (Gerente).
	 *  Metodo a ser desenvolvido e integrado em futuros passo do projeto. Talvez fique no Controller
	 * @param id	id do usuario.
	 * @return		<code>true</code> Se for um Management;
	 * 				<code>false</code> Se nao for Management.
	 */
	private static boolean isAdministrator(int id) {
		User obj = getOne(id);
		if(obj instanceof Administrator) {
			return true;
		}
		return false;
	}
	
	
	/** Pega um usuario da lista, com determinado id.
	 * 
	 * @param id	id do usuario a ser buscado.
	 * @return		Retorna o usuario, se o id estiver registrado;
	 * 				Retorna null, se o id nao estiver na lista.
	 */
	public static User getOne(int id) {
		for (int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getId()) {
				return list.get(i);
			}
		}
		return null;
	}
	
}