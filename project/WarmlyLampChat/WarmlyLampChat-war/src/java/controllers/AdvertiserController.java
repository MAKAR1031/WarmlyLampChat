package controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.ad.AdBlock;
import models.chat.ChatUser;
import services.AdvertiserServiceLocal;

@Named
@SessionScoped
public class AdvertiserController implements Serializable{

    @EJB
    private AdvertiserServiceLocal advertiserService;
    private ChatUser currentUser;
    
    private List<AdBlock> adBlocks;
    private AdBlock adBlock;

    public AdvertiserController() {
        adBlock = new AdBlock();
    }
    
    @PostConstruct
    private void onCreate() {
        adBlocks = advertiserService.getAdBlocksByAdvertiser(currentUser);
    }

    public AdBlock getAdBlock() {
        return adBlock;
    }

    public void setAdBlock(AdBlock adBlock) {
        this.adBlock = adBlock;
    }

    public List<AdBlock> getAdBlocks() {
        return adBlocks;
    }

    public void setAdBlocks(List<AdBlock> adBlocks) {
        this.adBlocks = adBlocks;
    }
    
    public String createAdBlock() {
        this.adBlock = new AdBlock();
        return "create_ad";
    }
    
    public String createAdBlockConfirm() {
        return "index";
    }
    
    public String editAdBlock(int idAd) {
        return "edit_ad";
    }
    
    public String editAdBlockConfirm() {
        return "index";
    }
    
    public String payForAdBlock(int id) {
        return "pay_ad";
    }
    
    public String payForAdBlockConfirm() {
        return "index";
    }
}