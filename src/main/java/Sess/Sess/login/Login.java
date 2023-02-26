package Sess.Sess.login;

import Sess.Sess.database.Database;
import Sess.Sess.users.User;

import java.util.ArrayList;

public class Login {
    static private Login instance = null;

    private Login() {}

    static public Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }

        return instance;
    }

    public int login(String userName) {
        Database db = Database.getInstance();

        ArrayList<User> users = db.getAllUsers();

        for (User user : users) {
            if (userName.toLowerCase().trim().equals(user.getFullName().toLowerCase().trim())) {
                return user.getId();
            }
        }

        return -1;
    }
}
