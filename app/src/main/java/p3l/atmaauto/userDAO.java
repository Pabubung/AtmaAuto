package p3l.atmaauto;

public class userDAO {
    String username;
    String name;
    String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public userDAO(String username,String name,String role){
        this.username=username;
        this.name=name;
        this.role=role;
    }
}
