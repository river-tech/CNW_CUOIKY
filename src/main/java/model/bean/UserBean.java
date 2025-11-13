package model.bean;

public class UserBean {
    private int id;
    private String username;
    private String password;
    private String lastname;
    private String role;

    public UserBean() {
    }

    public UserBean(int id, String username, String password, String lastname, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
