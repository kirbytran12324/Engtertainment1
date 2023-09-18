import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandLine {
    private Dictionary dictionary;
    private DictionaryManagement dictionaryManagement;
    private DictionarySearcher dictionarySearcher;
    private DictionaryUI ui;

    public DictionaryCommandLine() {
        dictionary = new Dictionary();
        dictionaryManagement = new DictionaryManagement();
    }
    
    public DictionaryCommandLine(DictionaryUI ui, DictionaryManagement dictionaryManagement) {
        this.ui = ui;
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords(Dictionary dictionary) {
        ArrayList<Word> words = dictionary.getWords();

        ui.clearOutput();
        ui.updateOutput("No. | English | Vietnamese | Definition | Synonyms | Antonyms | Example");
        ui.updateOutput("------------------------------------------------------------------------");
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            ui.updateOutput(String.format("%-3d| %-8s| %-10s| %-10s| %-8s| %-8s| %s",
                    (i + 1),
                    word.getWord(),
                    word.getTranslation(),
                    word.getDefinition(),
                    word.getSynonym(),
                    word.getAntonym(),
                    word.getExample()));
        }
    }

    public void dictionarySearcher(Dictionary dictionary) {
        List<Word> searchResults = Collections.emptyList();
        Scanner scanner = new Scanner(System.in);
        ui.clearOutput();
        ui.updateOutput("Enter the search term:");
        String searchTerm = scanner.nextLine();
        ui.updateOutput("Enter the number for the search type:");
        ui.updateOutput("1. Search by word");
        ui.updateOutput("2. Search by Synonyms");
        ui.updateOutput("3. Search by Antonyms");
        ui.updateOutput("4. Search by Word Combination");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> searchResults = DictionarySearcher.searchByWord(dictionary, searchTerm);
            case 2 -> searchResults = DictionarySearcher.searchBySynonyms(dictionary, searchTerm);
            case 3 -> searchResults = DictionarySearcher.searchByAntonyms(dictionary, searchTerm);
            case 4 -> searchResults = DictionarySearcher.searchByWordCombination(dictionary, searchTerm);
            default -> ui.updateOutput("Invalid choice. Please try again.");
        }

        if (!searchResults.isEmpty()) {
            ui.updateOutput("Search results:");
            for (int i = 0; i < searchResults.size(); i++) {
                Word word = searchResults.get(i);
                ui.updateOutput(String.format("%-3d| %-8s| %s",
                        (i + 1),
                        word.getWord(),
                        word.getTranslation()));
            }

            ui.updateOutput("Enter the number of the word to see more details (0 to cancel): ");
            choice = scanner.nextInt();

            if (choice >= 1 && choice <= searchResults.size()) {
                Word selectedWord = searchResults.get(choice - 1);
                displayWordDetails(selectedWord);
            } else if (choice != 0) {
                ui.updateOutput("Invalid choice.");
            }
        } else {
            ui.updateOutput("No matching words found!");
        }
    }

    public void displayWordDetails(Word word) {
        ui.updateOutput("Word: " + word.getWord());
        ui.updateOutput("Translation: " + word.getTranslation());
        ui.updateOutput("Definition: " + word.getDefinition());
        ui.updateOutput("Synonyms: " + word.getSynonym());
        ui.updateOutput("Antonyms: " + word.getAntonym());
        ui.updateOutput("Example: " + word.getExample());
    }
}




