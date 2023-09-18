import java.util.ArrayList;

// Lớp đại diện cho từ điển (Dictionary) lưu trữ một danh sách các từ vựng
public class Dictionary {
    private ArrayList<Word> words; // Danh sách từ vựng

    public Dictionary() {
        words = new ArrayList<>();
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public ArrayList<Word> getWords() {
        return words;
    }
}