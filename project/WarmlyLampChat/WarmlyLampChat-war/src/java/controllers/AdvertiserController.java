package controllers;

import dao.ChatDAOLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.ad.AdBlock;
import models.chat.ChatUser;
import services.AdvertiserServiceLocal;
import services.AuthServiceLocal;

@Named
@SessionScoped
public class AdvertiserController implements Serializable{

    @EJB
    private AuthServiceLocal authService;

    @EJB
    private ChatDAOLocal dao;
    
    @EJB
    private AdvertiserServiceLocal advertiserService;
    private ChatUser currentUser;
    private AdBlock adBlock;
    
    @PostConstruct
    private void onCreate() {
        currentUser = authService.getCurrentUser();
    }

    public AdBlock getAdBlock() {
        return adBlock;
    }

    public List<AdBlock> getAdBlocks() {
        return advertiserService.getAdBlocksByAdvertiser(currentUser);
    }

    public String createAdBlock() {
        this.adBlock = new AdBlock();
        return "create_ad";
    }
    
    public String createAdBlockConfirm() {
        advertiserService.createAdBlock(adBlock, currentUser.getId());
        return "index";
    }
    
    public String activateAdBlock(int idAd) {
        adBlock = advertiserService.getAdBlockById(idAd);
        return "activate_ad";
    }
    
    public String activateAdBlockConfirm() {
        advertiserService.activateAd(adBlock);
        return "index";
    }
    
    public String editAdBlock(int idAd) {
        adBlock = advertiserService.getAdBlockById(idAd);
        return "edit_ad";
    }
    
    public String editAdBlockConfirm() {
        advertiserService.updateAd(adBlock);
        return "index";
    }
    
    public String payForAdBlock(int id) {
        adBlock = advertiserService.getAdBlockById(id);
        return "pay_ad";
    }
    
    public String payForAdBlockConfirm() {
        advertiserService.payForAd(adBlock);
        return "index";
    }
}