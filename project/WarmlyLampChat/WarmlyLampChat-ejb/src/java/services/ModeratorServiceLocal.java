package services;

import java.util.List;
import javax.ejb.Local;
import models.ad.AdBlock;

@Local
public interface ModeratorServiceLocal {
    void approveAd(AdBlock ad);
    void rejectAd(AdBlock ad);
    List<AdBlock> getAdBlocks();
}
