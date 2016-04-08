package services;

import java.util.List;
import javax.ejb.Local;
import models.ad.AdBlock;

@Local
public interface ModeratorServiceLocal {
    void approveAd(int id);
    void rejectAd(int id);
    List<AdBlock> getConsiderationAdBlocks();
}
