package model.bo;

import model.bean.UserBean;
import model.dao.UserDAO;

import java.util.List;

public class UserBO {
    private final UserDAO userDAO = new UserDAO();

    public UserBean login(String username, String password) {
        return userDAO.checkLogin(username, password);
    }

    public boolean register(UserBean user) {
        return userDAO.createUser(user);
    }

    public List<UserBean> search(String keyword) {
        return userDAO.searchUsers(keyword == null ? "" : keyword.trim());
    }

    public boolean update(int id, String lastname, String role) {
        return userDAO.updateUser(id, lastname, role);
    }

    public boolean delete(int id) {
        return userDAO.deleteUser(id);
    }
}
