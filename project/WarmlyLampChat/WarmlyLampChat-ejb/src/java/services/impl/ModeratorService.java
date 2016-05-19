package services.impl;

import dao.AdDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.ad.AdBlock;
import models.ad.KeyWord;
import models.ad.Status;
import services.ModeratorServiceLocal;

@Stateless
public class ModeratorService implements ModeratorServiceLocal {
    
    @EJB
    private AdDAO adDAO;

    @Override
    public void approveAd(int id) {
        Status status = adDAO.getStatusByName("Одобрен");
        AdBlock ad = adDAO.getAdBlockById(id);
        ad.setStatus(status);
    }

    @Override
    public void rejectAd(int id) {
        Status status = adDAO.getStatusByName("Отклонен");
        AdBlock ad = adDAO.getAdBlockById(id);
        ad.setStatus(status);
    }

    @Override
    public List<AdBlock> getConsiderationAdBlocks() {
        return adDAO.getAdBlocksByStatus("На рассмотрении");
    }

    @Override
    public KeyWord getKeyWordById(int id) {
        return adDAO.getKeyWordById(id);
    }

    @Override
    public List<KeyWord> getAllKeyWords() {
        return adDAO.getAllKeyWords();
    }

    @Override
    public void addKeyWord(KeyWord keyWord) {
        adDAO.addKeyWord(keyWord);
    }

    @Override
    public void updateKeyWord(KeyWord keyWord) {
        adDAO.mergeKeyWord(keyWord);
    }

    @Override
    public void removeKeyWord(KeyWord keyWord) {
        adDAO.removeKeyWord(keyWord);
    }
}