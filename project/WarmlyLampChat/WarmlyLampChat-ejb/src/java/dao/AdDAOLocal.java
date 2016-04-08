package dao;

import java.util.List;
import javax.ejb.Local;
import models.ad.AdBlock;
import models.ad.Status;

@Local
public interface AdDAOLocal {
    List<AdBlock> getAdBlocksByAdvertiser(int idAdvertiser);
    List<AdBlock> getAdBlocksByStatus(String status);
    AdBlock getAdBlockById(int id);
    void createAdBlock(AdBlock ad);
    void mergeAdBlock(AdBlock ad);
    void removeAdBlock(AdBlock ad);
    Status getStatusByName(String name);
}
