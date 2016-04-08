package services;

import java.util.List;
import javax.ejb.Local;
import models.ad.AdBlock;
import models.chat.ChatUser;

@Local
public interface AdvertiserServiceLocal {
    AdBlock getAdBlockById(int id);
    void createAdBlock(AdBlock adBlock, int idAdvertiser);
    List<AdBlock> getAdBlocksByAdvertiser(ChatUser advertiser);
    void updateAd(AdBlock ad);
    void activateAd(AdBlock ad);
    void payForAd(AdBlock ad);
}
