package dao;

import java.util.List;
import models.ad.AdBlock;

public interface AdDAOReadAccessible {
    List<AdBlock> getAllAdBlocks();
    AdBlock getAdBlockById(Integer id);
}
