import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides methods for managing the dictionary, including importing and exporting data, adding, editing, and deleting words.
 */
public class DictionaryManagement {
	private DictionaryUI ui;
	private Dictionary dictionary;
	
	public DictionaryManagement() {
		dictionary = new Dictionary();
	}
	public DictionaryManagement(DictionaryUI ui) {
		this.ui = ui;
	}
	
    /**
     * Imports word data from a CSV file and adds it to the dictionary.
     *
     * @param dictionary The dictionary to add the words to.
     * @param filename   The path to the CSV file.
     */
    public void insertFromCSV(Dictionary dictionary, String filename) {
        try (CSVParser csvParser = new CSVParser(new FileReader(filename), CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                String Engword = csvRecord.get("word").trim();
                String VieWord = csvRecord.get("translation").trim();
                String Definition = csvRecord.get("definition").trim();
                String Synonyms = csvRecord.get("synonyms").trim();
                String Antonyms = csvRecord.get("antonyms").trim();
                String Example = csvRecord.get("example").trim();
                Word word = new Word(Engword, VieWord, Definition, Synonyms, Antonyms, Example);
                dictionary.addWord(word);
            }
        } catch (IOException e) {
            ui.updateOutput("File not found: " + filename);
        }
    }

    /**
     * Exports word data from the dictionary to a CSV file.
     *
     * @param dictionary The dictionary to export the words from.
     * @param filename   The path to the CSV file.
     */
    public void exportToCSV(Dictionary dictionary, String filename) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filename), CSVFormat.DEFAULT
                .withHeader("Word", "Translation", "Definition", "Synonyms", "Antonyms", "Example"))) {
            for (Word word : dictionary.getWords()) {
                csvPrinter.printRecord(
                        word.getWord(),
                        word.getTranslation(),
                        word.getDefinition(),
                        word.getSynonym(),
                        word.getAntonym(),
                        word.getExample()
                );
            }
        } catch (IOException e) {
            ui.updateOutput("Error writing to file: " + filename);
        }
    }

    /**
     * Allows manual insertion of word data from user input.
     *
     * @param dictionary The dictionary to add the words to.
     */
    public void insertWord(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        ui.updateOutput("Enter the number of words: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character after reading the integer

        for (int i = 0; i < n; i++) {

            ui.updateOutput("Enter the English word: ");
            String Engword = scanner.nextLine();
            ui.updateOutput("Enter the Vietnamese explanation: ");
            String Vieword = scanner.nextLine();
            ui.updateOutput("Enter the definition: ");
            ui.updateOutput("Enter 'n' if there isn't any");
            String Definition = scanner.nextLine();
            if (Definition.equals("n"))
                Definition = "";
            ui.updateOutput("Enter the synonyms: ");
            ui.updateOutput("Enter 'n' if there isn't any");
            String Synonyms = scanner.nextLine();
            if (Synonyms.equals("n"))
                Synonyms = "";
            ui.updateOutput("Enter the antonyms: ");
            System.out.println("Enter 'n' if there isn't any");
            String Antonyms = scanner.nextLine();
            if (Antonyms.equals("n"))
                Antonyms = "";
            ui.updateOutput("Enter the example: ");
            ui.updateOutput("Enter 'n' if there isn't any");
            String Example = scanner.nextLine();
            if (Example.equals("n"))
                Example = "";
            Word word = new Word(Engword, Vieword, Definition, Synonyms, Antonyms, Example);
            dictionary.addWord(word);
        }
    }

    /**
     * Imports word data from a TXT file and adds it to the dictionary.
     *
     * @param dictionary The dictionary to add the words to.
     * @param filename   The path to the TXT file.
     */
    public void insertFromTxt(Dictionary dictionary, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String Engword = parts[0].trim();
                    String wordExplain = parts[1].trim();
                    Word word = new Word(Engword, wordExplain);
                    dictionary.addWord(word);
                }
            }
        } catch (IOException e) {
            ui.updateOutput("File not found: " + filename);
        }
    }

    /**
     * Exports word data from the dictionary to a TXT file.
     *
     * @param dictionary The dictionary to export the words from.
     * @param filename   The path to the TXT file.
     */
    public void exportToTxt(Dictionary dictionary, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Word word : dictionary.getWords()) {
                writer.write(word.getWord() + "\t" + word.getTranslation());
                writer.newLine();
            }
        } catch (IOException e) {
            ui.updateOutput("Error writing to file: " + filename);
        }
    }

    /**
     * Edits an existing word in the dictionary.
     *
     * @param dictionary The dictionary containing the word.
     */
    public void editWord(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        ui.updateOutput("Enter the English word to edit: ");
        String wordToEdit = scanner.nextLine().toLowerCase();

        for (Word word : dictionary.getWords()) {
            if (word.getWord().toLowerCase().equals(wordToEdit)) {
                ui.updateOutput("Enter the new English word: ");
                String newword = scanner.nextLine();
                ui.updateOutput("Enter the new Vietnamese explanation: ");
                String newVieword = scanner.nextLine();
                word.setWord(newword);
                word.setTranslation(newVieword);
                ui.updateOutput("Word edited successfully!");
                return;
            }
        }

        ui.updateOutput("Word not found!");
    }

    /**
     * Deletes an existing word from the dictionary.
     *
     * @param dictionary The dictionary containing the word.
     */
    public void deleteWord(Dictionary dictionary) {
        Scanner scanner = new Scanner(System.in);
        ui.updateOutput("Enter the English word to delete: ");
        String wordToDelete = scanner.nextLine().toLowerCase();

        ArrayList<Word> words = dictionary.getWords();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getWord().toLowerCase().equals(wordToDelete)) {
                words.remove(i);
                ui.updateOutput("Word deleted successfully!");
                return;
            }
        }

        ui.updateOutput("Word not found!");
    }
}