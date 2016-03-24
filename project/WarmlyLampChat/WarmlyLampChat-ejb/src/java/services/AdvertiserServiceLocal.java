package services;

import java.util.List;
import javax.ejb.Local;
import models.ad.AdBlock;

@Local
public interface AdvertiserServiceLocal {
    List<AdBlock> getAdBlocks();
    void activateAd(AdBlock ad);
    void payForAd(AdBlock ad);
}
