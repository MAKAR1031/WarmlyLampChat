package dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import models.ad.AdBlock;

@Stateless
@LocalBean
@Local(AdDAOReadAccessible.class)
public class AdDAO implements AdDAOReadAccessible {

    @Override
    public List<AdBlock> getAllAdBlocks() {
        return new ArrayList<>();
    }

    @Override
    public AdBlock getAdBlockById(Integer id) {
        return new AdBlock();
    }

    public void createAdBlock(AdBlock ad) {
        
    }
    
    public void updateAdBlock(AdBlock ad) {
        
    }
    
    public void removeAdBlock(AdBlock ad) {
        
    }
}