package services.impl;

import dao.AdDAOLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.ad.AdBlock;
import models.chat.ChatUser;
import services.AdvertiserServiceLocal;

@Stateless
public class AdvertiserService implements AdvertiserServiceLocal {

//    @EJB
//    private ChatDAOReadAccessible chatDAO;
    @EJB
    private AdDAOLocal adDAO;

    @Override
    public void activateAd(AdBlock ad) {

    }

    @Override
    public void payForAd(AdBlock ad) {

    }

    @Override
    public List<AdBlock> getAdBlocksByAdvertiser(ChatUser advertiser) {
        return adDAO.getAdBlocksByAdvertiser(advertiser.getId());
    }
}
