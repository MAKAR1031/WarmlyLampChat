package services;

import java.util.List;
import javax.ejb.Local;
import models.ad.AdBlock;
import models.ad.KeyWord;

@Local
public interface ModeratorServiceLocal {
    void approveAd(int id);
    void rejectAd(int id);
    List<AdBlock> getConsiderationAdBlocks();
    KeyWord getKeyWordById(int id);
    List<KeyWord> getAllKeyWords();
    void addKeyWord(KeyWord keyWord);
    void updateKeyWord(KeyWord keyWord);
    void removeKeyWord(KeyWord keyWord);
}
