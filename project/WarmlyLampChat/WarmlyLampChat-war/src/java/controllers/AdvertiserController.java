package controllers;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.ad.AdBlock;
import services.AdvertiserServiceLocal;
import services.AuthServiceLocal;

@Named
@SessionScoped
public class AdvertiserController implements Serializable{

    @EJB
    private AuthServiceLocal authService;
    
    @EJB
    private AdvertiserServiceLocal advertiserService;
    private AdBlock adBlock;

    public AdBlock getAdBlock() {
        return adBlock;
    }

    public List<AdBlock> getAdBlocks() {
        return advertiserService.getAdBlocksByAdvertiser(authService.getCurrentUser());
    }
    
    public String createAdBlock() {
        this.adBlock = new AdBlock();
        return "create_ad";
    }
    
    public String createAdBlockConfirm() {
        advertiserService.createAdBlock(adBlock, authService.getCurrentUser().getId());
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