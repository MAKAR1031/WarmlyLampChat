package services.impl;

import dao.AdDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.ad.AdBlock;
import models.ad.Status;
import models.chat.ChatUser;
import services.AdvertiserServiceLocal;

@Stateless
public class AdvertiserService implements AdvertiserServiceLocal {

    @EJB
    private AdDAO adDAO;

    @Override
    public AdBlock getAdBlockById(int id) {
        return adDAO.getAdBlockById(id);
    }

    @Override
    public void createAdBlock(AdBlock adBlock, int idAdvertiser) {
        adBlock.setAdvertiserId(idAdvertiser);
        Status status = adDAO.getStatusByName("Создан");
        adBlock.setStatus(status);
        adDAO.createAdBlock(adBlock);
    }

    @Override
    public List<AdBlock> getAdBlocksByAdvertiser(ChatUser advertiser) {
        return adDAO.getAdBlocksByAdvertiser(advertiser.getId());
    }

    @Override
    public void updateAd(AdBlock ad) {
        Status status = adDAO.getStatusByName("Создан");
        ad.setStatus(status);
        adDAO.mergeAdBlock(ad);
    }

    @Override
    public void removeAd(AdBlock ad) {
        if ("Создан".equals(ad.getStatus().getName())
                || "Отклонен".equals(ad.getStatus().getName())) {
            adDAO.removeAdBlock(ad);
        }
    }

    @Override
    public void activateAd(AdBlock ad) {
        Status status = adDAO.getStatusByName("На рассмотрении");
        ad.setStatus(status);
        adDAO.mergeAdBlock(ad);
    }

    @Override
    public void payForAd(AdBlock ad) {
        Status status = adDAO.getStatusByName("Активирован");
        ad.setActivationDate(new Date());
        ad.setStatus(status);
        adDAO.mergeAdBlock(ad);
    }
}
