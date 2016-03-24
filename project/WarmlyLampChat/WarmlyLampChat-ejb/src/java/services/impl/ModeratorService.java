package services.impl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import models.ad.AdBlock;
import services.ModeratorServiceLocal;

@Stateless
public class ModeratorService implements ModeratorServiceLocal {

    @Override
    public void approveAd(AdBlock ad) {
        
    }

    @Override
    public void rejectAd(AdBlock ad) {
        
    }

    @Override
    public List<AdBlock> getAdBlocks() {
        List<AdBlock> adBlocks = new ArrayList<>();
        AdBlock ab = new AdBlock();
        ab.setId(1);
        ab.setTitle("Реклама");
        ab.setContent("Содержимое рекламы");
        adBlocks.add(ab);
        return adBlocks;
    }
}