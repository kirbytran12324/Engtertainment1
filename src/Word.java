/**
 * Represents a vocabulary word in the dictionary.
 */
public class Word {
    private String Word; // The English word
    private String Translation; // The Vietnamese translation
    private String Definition; // The English definition
    private String Synonym; // Synonyms in English
    private String Antonym; // Antonyms in English
    private String Example; // Example sentence in English

    /**
     * Constructs a Word object with the given English word and Vietnamese translation.
     *
     * @param word        The English word.
     * @param translation The Vietnamese translation.
     */
    public Word(String word, String translation) {
        this.Word = word;
        this.Translation = translation;
        this.Definition = "";
        this.Synonym = "";
        this.Antonym = "";
        this.Example = "";
    }

    /**
     * Constructs a Word object with the given attributes.
     *
     * @param word       The English word.
     * @param translation The Vietnamese translation.
     * @param definition The English definition.
     * @param Synonym    Synonyms in English.
     * @param Antonym    Antonyms in English.
     * @param Example    Example sentence in English.
     */
    public Word(String word, String translation, String definition, String Synonym, String Antonym, String Example) {
        this.Word = word;
        this.Translation = translation;
        this.Definition = definition;
        this.Synonym = Synonym;
        this.Antonym = Antonym;
        this.Example = Example;
    }

    // Getter and setter methods for Definition, Synonym, Antonym, Example, Word, and Translation

    /**
     * Gets the English definition of the word.
     *
     * @return The English definition.
     */
    public String getDefinition() {
        return Definition;
    }

    /**
     * Sets the English definition of the word.
     *
     * @param definition The English definition.
     */
    public void setDefinition(String definition) {
        this.Definition = definition;
    }

    /**
     * Gets the synonyms of the word in English.
     *
     * @return Synonyms in English.
     */
    public String getSynonym() {
        return Synonym;
    }

    /**
     * Sets the synonyms of the word in English.
     *
     * @param synonym Synonyms in English.
     */
    public void setSynonym(String synonym) {
        Synonym = synonym;
    }

    /**
     * Gets the antonyms of the word in English.
     *
     * @return Antonyms in English.
     */
    public String getAntonym() {
        return Antonym;
    }

    /**
     * Sets the antonyms of the word in English.
     *
     * @param antonym Antonyms in English.
     */
    public void setAntonym(String antonym) {
        Antonym = antonym;
    }

    /**
     * Gets an example sentence for the word in English.
     *
     * @return Example sentence in English.
     */
    public String getExample() {
        return Example;
    }

    /**
     * Sets an example sentence for the word in English.
     *
     * @param example Example sentence in English.
     */
    public void setExample(String example) {
        Example = example;
    }

    /**
     * Gets the English word.
     *
     * @return The English word.
     */
    public String getWord() {
        return Word;
    }

    /**
     * Sets the English word.
     *
     * @param word The English word.
     */
    public void setWord(String word) {
        this.Word = word;
    }

    /**
     * Gets the Vietnamese translation.
     *
     * @return The Vietnamese translation.
     */
    public String getTranslation() {
        return Translation;
    }

    /**
     * Sets the Vietnamese translation.
     *
     * @param translation The Vietnamese translation.
     */
    public void setTranslation(String translation) {
        this.Translation = translation;
    }
}
