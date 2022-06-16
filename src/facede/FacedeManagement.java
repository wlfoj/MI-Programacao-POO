package facede;

import exceptions.NullFieldException;
import model.Administrator;
import model.Employee;
import model.ManagementUsers;
import model.User;

public abstract class FacedeManagement {
	//METODOS PARA CRIAR UM NOVO USUARIO
	public static void addUser(String name, String login, String pass, String type) throws NullFieldException {
		if (type == "Administrador") {
    		Administrator u = new Administrator();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(pass);
			ManagementUsers.addUser(u);
    	} else {
    		Employee u = new Employee();
    		u.setLogin(login);
    		u.setName(name);
    		u.setPass(pass);
    		ManagementUsers.addUser(u);
    	}
	}

}
