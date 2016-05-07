package controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import models.UserLoginData;
import models.UserRegisterData;
import models.chat.ChatUser;
import models.chat.UserRole;
import services.AuthServiceLocal;

@Named
@RequestScoped
public class AuthController {

    @EJB
    private AuthServiceLocal authService;

    private UserLoginData loginData;
    private UserRegisterData registerData;

    public UserLoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(UserLoginData loginData) {
        this.loginData = loginData;
    }

    public UserRegisterData getRegisterData() {
        return registerData;
    }

    public void setRegisterData(UserRegisterData registerData) {
        this.registerData = registerData;
    }

    public AuthController() {
        loginData = new UserLoginData();
        registerData = new UserRegisterData();
    }

    public List<UserRole> getAllRoles() {
        return authService.getAllRoles();
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(loginData.getLogin(), loginData.getPassword());
            loginData = new UserLoginData();
            ChatUser user = authService.getCurrentUser();
            switch (user.getRole().getName()) {
                case "Модератор":
                    return "moderator_login";
                case "Рекламодатель":
                    return "advertiser_login";
                case "Пользователь":
                    return "user_login";
                case "Администратор":
                    return "admin_index";
                default:
                    return "login";
            }
        } catch (ServletException ex) {
            context.addMessage(null, new FacesMessage("Ошибка авторизации, вы ввели неверный логин или пароль!"));
            return "login";
        }
    }

    public String logout() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.logout();
            return "logout";
        } catch (ServletException ex) {
            return "index";
        }
    }

    public String register() {
        try {
            authService.registerUser(registerData.getFio(), 
                    registerData.getNickName(), 
                    registerData.getPassword(), 
                    registerData.getRoleName());
            return "login";
        } catch (Exception e) {
            return "register";
        }
    }
}