package dao;

import java.util.List;
import java.util.Random;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.ad.AdBlock;
import models.ad.KeyWord;
import models.ad.Status;

@LocalBean
@Stateless
public class AdDAO {

    @PersistenceContext(unitName = "WarmlyLampChat-ejbPUAd")
    private EntityManager em;

    private final Random rand;

    public AdDAO() {
        rand = new Random();
    }

    public List<AdBlock> getAdBlocksByAdvertiser(int id) {
        Query query = em.createQuery("SELECT ad FROM AdBlock ad "
                + "WHERE ad.advertiserId=?1", AdBlock.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    public List<AdBlock> getAdBlocksByStatus(String status) {
        Query query = em.createQuery("SELECT ad FROM AdBlock ad "
                + "WHERE ad.status.name=?1", AdBlock.class);
        query.setParameter(1, status);
        return query.getResultList();
    }

    public AdBlock getAdBlockByEmotionFactor(double factor) {
        Query query = em.createQuery("SELECT ad FROM AdBlock ad "
                + "WHERE ?1 BETWEEN ad.minEmotionalFactor "
                + "AND ad.maxEmotionalFactor AND ad.status.name='Активирован'",
                AdBlock.class);
        query.setParameter(1, factor);
        List<AdBlock> adBlocks = query.getResultList();
        if (adBlocks.size() > 1) {
            return adBlocks.get(rand.nextInt(adBlocks.size()));
        } else if (adBlocks.size() == 1) {
            return adBlocks.get(0);
        } else {
            return null;
        }
    }

    public AdBlock getAdBlockById(int id) {
        return em.find(AdBlock.class, id);
    }

    public void createAdBlock(AdBlock ad) {
        em.persist(ad);
    }

    public void mergeAdBlock(AdBlock ad) {
        em.merge(ad);
    }

    public void removeAdBlock(AdBlock ad) {
        em.remove(em.merge(ad));
    }

    public Status getStatusByName(String name) {
        Query query = em.createQuery("SELECT s FROM Status s WHERE s.name=?1",
                Status.class);
        query.setParameter(1, name);
        return (Status) query.getSingleResult();
    }

    public KeyWord getKeyWordById(int id) {
        return em.find(KeyWord.class, id);
    }

    public List<KeyWord> getAllKeyWords() {
        Query query = em.createQuery("SELECT k FROM KeyWord k", KeyWord.class);
        return query.getResultList();
    }

    public void addKeyWord(KeyWord keyWord) {
        em.persist(keyWord);
    }

    public void mergeKeyWord(KeyWord keyWord) {
        em.merge(keyWord);
    }

    public void removeKeyWord(KeyWord keyWord) {
        em.remove(em.merge(keyWord));
    }
}
