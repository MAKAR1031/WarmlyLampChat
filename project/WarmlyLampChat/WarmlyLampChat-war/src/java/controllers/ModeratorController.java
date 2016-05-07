package controllers;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import models.ad.AdBlock;
import models.ad.KeyWord;
import services.ModeratorServiceLocal;

@Named
@SessionScoped
public class ModeratorController implements Serializable {

    @EJB
    private ModeratorServiceLocal moderatorService;
    private KeyWord keyWord;
    private int updatebleKeyWordId;

    public ModeratorController() {
        updatebleKeyWordId = 0;
        keyWord = new KeyWord();
    }

    public KeyWord getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(KeyWord keyWord) {
        this.keyWord = keyWord;
    }

    public int getUpdatebleKeyWordId() {
        return updatebleKeyWordId;
    }

    public boolean isUpdateMode() {
        return updatebleKeyWordId != 0;
    }
    
    public void approveAd(int idAd) {
        moderatorService.approveAd(idAd);
    }

    public void rejectAd(int idAd) {
        moderatorService.rejectAd(idAd);
    }

    public List<AdBlock> getAdBlocks() {
        return moderatorService.getConsiderationAdBlocks();
    }

    public List<KeyWord> getAllKeyWords() {
        return moderatorService.getAllKeyWords();
    }
    
    public void addKeyWordWithoutConfirm() {
        moderatorService.addKeyWord(keyWord);
        keyWord = new KeyWord();
    }
    
    public void updateKeyWord(int id) {
        keyWord = moderatorService.getKeyWordById(id);
        updatebleKeyWordId = id;
    }
    
    public void updateKeyWordConfirm() {
        moderatorService.updateKeyWord(keyWord);
        keyWord = new KeyWord();
        updatebleKeyWordId = 0;
    }
    
    public void updateKeyWordAbort() {
        keyWord = new KeyWord();
        updatebleKeyWordId = 0;
    }
    
    public void removeKeyWordWithoutConfirm(int id) {
        moderatorService.removeKeyWord(moderatorService.getKeyWordById(id));
    }
}
