package testapp.redoge.cyp.pojo;

public class ChangeActiveStatusRequest {
    private boolean active;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;

    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
