package services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import models.ad.AdBlock;
import models.ad.Status;
import services.AdvertiserServiceLocal;

@Stateless
public class AdvertiserService implements AdvertiserServiceLocal {

    @Override
    public void activateAd(AdBlock ad) {

    }

    @Override
    public void payForAd(AdBlock ad) {

    }

    @Override
    public List<AdBlock> getAdBlocks() {
        List<AdBlock> adBlocks = new ArrayList<>();
        Status status = new Status();
        status.setName("Создан");
        AdBlock ab = new AdBlock();
        ab.setId(1);
        ab.setTitle("Реклама");
        ab.setContent("Содержимое");
        ab.setActivationDate(new Date());
        ab.setDaysOfActive(0);
        ab.setStatus(status);
        adBlocks.add(ab);
        return adBlocks;
    }
}
