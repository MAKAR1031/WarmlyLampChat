package dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.ad.AdBlock;

@Stateless
public class AdDAO implements AdDAOLocal {

    @PersistenceContext(unitName = "WarmlyLampChat-ejbPU-Ad")
    private EntityManager em;

    @Override
    public List<AdBlock> getAdBlocksByAdvertiser(int idAdvertiser) {
        return new ArrayList();
    }

    @Override
    public AdBlock getAdBlockById(int id) {
        return em.find(AdBlock.class, id);
    }

    @Override
    public void createAdBlock(AdBlock ad) {
        em.persist(ad);
    }

    @Override
    public void mergeAdBlock(AdBlock ad) {
        em.merge(ad);
    }

    @Override
    public void removeAdBlock(AdBlock ad) {
        em.remove(em.merge(ad));
    }
}
