package lolmatchticketing.model;

public class Admin extends User {

    private String id = "서영";
    private String password = "seoyoung";

    public boolean login(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }
}
