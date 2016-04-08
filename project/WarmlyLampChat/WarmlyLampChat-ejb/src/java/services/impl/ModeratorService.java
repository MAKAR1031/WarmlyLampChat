package services.impl;

import dao.AdDAOLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.ad.AdBlock;
import models.ad.Status;
import services.ModeratorServiceLocal;

@Stateless
public class ModeratorService implements ModeratorServiceLocal {
    
    @EJB
    private AdDAOLocal adDAO;

    @Override
    public void approveAd(int id) {
        Status status = adDAO.getStatusByName("Одобрен");
        AdBlock ad = adDAO.getAdBlockById(id);
        ad.setStatus(status);
    }

    @Override
    public void rejectAd(int id) {
        Status status = adDAO.getStatusByName("Отклонен");
        AdBlock ad = adDAO.getAdBlockById(id);
        ad.setStatus(status);
    }

    @Override
    public List<AdBlock> getConsiderationAdBlocks() {
        return adDAO.getAdBlocksByStatus("На рассмотрении");
    }
}