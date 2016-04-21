package dao;

import java.time.Duration;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.ad.AdBlock;
import models.ad.Status;

@Stateless
public class AdDAO implements AdDAOLocal {

    @PersistenceContext(unitName = "WarmlyLampChat-ejbPUAd")
    private EntityManager em;

    @Override
    public List<AdBlock> getAdBlocksByAdvertiser(int idAdvertiser) {
        Query query = em.createQuery("SELECT ad FROM AdBlock ad WHERE ad.advertiserId=?1", AdBlock.class);
        query.setParameter(1, idAdvertiser);
        List<AdBlock> adBlocks = query.getResultList();
        Status status = getStatusByName("Создан");
        adBlocks.stream().forEach(ad -> {
            if (ad.getActivationDate() != null && Duration.ofMillis(System.currentTimeMillis() - ad.getActivationDate().getTime()).toDays() > ad.getDaysOfActive()) {
                ad.setStatus(status);
            }
         });    
        return adBlocks;
    }

    @Override
    public List<AdBlock> getAdBlocksByStatus(String status) {
        Query query = em.createQuery("SELECT ad FROM AdBlock ad WHERE ad.status.name=?1", AdBlock.class);
        query.setParameter(1, status);
        return query.getResultList();
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

    @Override
    public Status getStatusByName(String name) {
        Query query = em.createQuery("SELECT s FROM Status s WHERE s.name=?1", Status.class);
        query.setParameter(1, name);
        return (Status) query.getSingleResult();
    }
}
