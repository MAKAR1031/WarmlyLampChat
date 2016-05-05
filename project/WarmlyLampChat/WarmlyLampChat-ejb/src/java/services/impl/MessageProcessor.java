package services.impl;

import dao.AdDAOLocal;
import dao.ChatDAOLocal;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import models.ad.KeyWord;
import models.chat.Room;

@Stateless
@LocalBean
public class MessageProcessor {

    @EJB
    private AdDAOLocal adDAO;
    @EJB
    private ChatDAOLocal chatDAO;

    private List<KeyWord> keyWords;

    @PostConstruct
    private void onCreate() {
        keyWords = adDAO.getAllKeyWords();
    }

    @Asynchronous
    public void processMessage(int roomId, String text) {
        text = text.replaceAll("(\\.|,|!|\\?)", "");
        StringTokenizer tokenizer = new StringTokenizer(text);
        Room room = chatDAO.getRoomById(roomId);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            Optional<KeyWord> keyWord = checkWord(token);
            if (!keyWord.isPresent()) {
                continue;
            }
            room.getCurrentMood()
                    .addEmotionalFactor(keyWord.get()
                            .getEmotionFactorIncrease());
        }
    }

    public Optional<KeyWord> checkWord(String word) {
        return keyWords.stream()
                .filter(key -> key.getValue().equalsIgnoreCase(word))
                .findFirst();
    }
}
