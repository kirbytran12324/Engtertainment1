import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods to search for words in the dictionary based on different criteria.
 */
public class DictionarySearcher {
    /**
     * Searches for words in the dictionary that match the given search word exactly.
     *
     * @param dictionary The dictionary to search.
     * @param searchWord The search word.
     * @return A list of words matching the search word.
     */
    public static List<Word> searchByWord(Dictionary dictionary, String searchWord) {
        List<Word> results = new ArrayList<>();

        for (Word word : dictionary.getWords()) {
            if (word.getWord().equalsIgnoreCase(searchWord)) {
                results.add(word);
            }
        }

        return results;
    }

    /**
     * Searches for words in the dictionary that have synonyms containing the given search term.
     *
     * @param dictionary    The dictionary to search.
     * @param searchSynonyms The search term for synonyms.
     * @return A list of words with synonyms matching the search term.
     */
    public static List<Word> searchBySynonyms(Dictionary dictionary, String searchSynonyms) {
        List<Word> results = new ArrayList<>();

        for (Word word : dictionary.getWords()) {
            if (word.getSynonym() != null && word.getSynonym().toLowerCase().contains(searchSynonyms.toLowerCase())) {
                results.add(word);
            }
        }

        return results;
    }

    /**
     * Searches for words in the dictionary that have antonyms containing the given search term.
     *
     * @param dictionary    The dictionary to search.
     * @param searchAntonyms The search term for antonyms.
     * @return A list of words with antonyms matching the search term.
     */
    public static List<Word> searchByAntonyms(Dictionary dictionary, String searchAntonyms) {
        List<Word> results = new ArrayList<>();

        for (Word word : dictionary.getWords()) {
            if (word.getAntonym() != null && word.getAntonym().toLowerCase().contains(searchAntonyms.toLowerCase())) {
                results.add(word);
            }
        }

        return results;
    }

    /**
     * Searches for words in the dictionary that contain the given combination of characters.
     *
     * @param dictionary  The dictionary to search.
     * @param combination The character combination to search for.
     * @return A list of words containing the given combination.
     */
    public static List<Word> searchByWordCombination(Dictionary dictionary, String combination) {
        List<Word> results = new ArrayList<>();

        for (Word word : dictionary.getWords()) {
            if (word.getWord().toLowerCase().contains(combination.toLowerCase())) {
                results.add(word);
            }
        }

        return results;
    }
}
