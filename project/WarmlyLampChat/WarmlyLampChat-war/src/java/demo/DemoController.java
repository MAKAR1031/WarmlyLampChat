package demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class DemoController implements Serializable {

    private UserData userData;
    private List<String> roles;

    public DemoController() {
        userData = new UserData();
        roles = new ArrayList<>();
        roles.add("user");
        roles.add("moderator");
        roles.add("advertiser");
    }
    
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String login() {
        switch (userData.getRole()) {
            case "user":
                return "user_login";
            case "moderator":
                return "moderator_login";
            case "advertiser":
                return "advertiser_login";
            default:
                throw new AssertionError();
        }
    }
}
