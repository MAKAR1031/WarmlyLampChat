package dao;

import java.util.List;
import javax.ejb.Local;
import models.ad.AdBlock;

@Local
public interface AdDAOLocal {
    List<AdBlock> getAdBlocksByAdvertiser(int idAdvertiser);
    AdBlock getAdBlockById(int id);
    public void createAdBlock(AdBlock ad);
    public void mergeAdBlock(AdBlock ad);
    public void removeAdBlock(AdBlock ad);
}
