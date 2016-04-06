package services;

import java.util.List;
import javax.ejb.Local;
import models.ad.AdBlock;
import models.chat.ChatUser;

@Local
public interface AdvertiserServiceLocal {
    List<AdBlock> getAdBlocksByAdvertiser(ChatUser advertiser);
    void activateAd(AdBlock ad);
    void payForAd(AdBlock ad);
}
