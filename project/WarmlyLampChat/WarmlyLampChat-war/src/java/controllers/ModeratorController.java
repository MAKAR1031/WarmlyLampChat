package controllers;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.ad.AdBlock;
import services.ModeratorServiceLocal;

@Named
@SessionScoped
public class ModeratorController implements Serializable{

    @EJB
    private ModeratorServiceLocal moderatorService;
    
    public void approveAd(int idAd) {
        
    }
    
    public void rejectAd(int idAd) {
        
    }

    public List<AdBlock> getAdBlocks() {
        return moderatorService.getAdBlocks();
    }
    
    
}