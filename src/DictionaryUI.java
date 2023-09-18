import javax.swing.*;
import java.awt.*;

public class DictionaryUI {
    private Dictionary dictionary;
    private DictionaryManagement dictionaryManagement;
    private DictionaryCommandLine dictionaryCommandLine;

    public DictionaryUI() {
        initializeUI();
        dictionary = new Dictionary();
        dictionaryManagement = new DictionaryManagement(this);
        dictionaryCommandLine = new DictionaryCommandLine(this, dictionaryManagement);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void initializeUI() {
        frame = new JFrame("Dictionary Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        outputArea = new JTextArea(20, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void clearOutput() {
        outputArea.setText(""); // Xóa nội dung hiện tại trong JTextArea
    }

    public void updateOutput(String text) {
        outputArea.append(text + "\n");
    }

    public void run() {
        clearOutput();
        updateOutput("Dictionary Application");

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = getUserInput();

            clearOutput(); // Xóa nội dung hiển thị trước đó

            switch (choice) {
                case 1 -> InsertMenu();
                case 2 -> dictionaryCommandLine.dictionarySearcher(dictionary);
                case 3 -> dictionaryManagement.editWord(dictionary);
                case 4 -> dictionaryManagement.deleteWord(dictionary);
                case 5 -> ExportMenu();
                case 6 -> dictionaryCommandLine.showAllWords(dictionary);
                case 0 -> running = false;
                default -> updateOutput("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        updateOutput("1. Insert Word");
        updateOutput("2. Search Word");
        updateOutput("3. Edit Word");
        updateOutput("4. Delete Word");
        updateOutput("5. Export Dictionary to File");
        updateOutput("6. Show All Words");
        updateOutput("0. Exit");
        updateOutput("Enter your choice: ");
    }

    private void InsertMenu() {
        boolean insert = true;

        while (insert) {
            displayInsertMenu();

            int choiceInsert = getUserInput();

            clearOutput(); // Xóa nội dung hiển thị trước đó

            switch (choiceInsert) {
                case 1 -> dictionaryManagement.insertWord(dictionary);
                case 2 -> {
                    String pathCsv = JOptionPane.showInputDialog(frame, "Enter the path to the CSV file:");
                    dictionaryManagement.insertFromCSV(dictionary, pathCsv);
                }
                case 3 -> {
                    String pathTxt = JOptionPane.showInputDialog(frame, "Enter the path to the TXT file:");
                    dictionaryManagement.insertFromTxt(dictionary, pathTxt);
                }
                case 0 -> insert = false;
                default -> updateOutput("Invalid choice. Please try again.");
            }
        }
    }

    private void displayInsertMenu() {
        updateOutput("1. Insert Word Manually");
        updateOutput("2. Insert Word from CSV file");
        updateOutput("3. Insert Word from TXT file");
        updateOutput("0. Return to Main Menu");
        updateOutput("Enter your choice: ");
    }

    private void ExportMenu() {
        boolean export = true;

        while (export) {
            displayExportMenu();

            int choiceExport = getUserInput();

            clearOutput(); // Xóa nội dung hiển thị trước đó

            switch (choiceExport) {
                case 1 -> {
                    String pathCsv = JOptionPane.showInputDialog(frame, "Enter the path to the CSV file:");
                    dictionaryManagement.exportToCSV(dictionary, pathCsv);
                }
                case 2 -> {
                    String pathTxt = JOptionPane.showInputDialog(frame, "Enter the path to the TXT file:");
                    dictionaryManagement.exportToTxt(dictionary, pathTxt);
                }
                case 0 -> export = false;
                default -> updateOutput("Invalid choice. Please try again.");
            }
        }
    }

    private void displayExportMenu() {
        updateOutput("1. Export to CSV file");
        updateOutput("2. Export to TXT file");
        updateOutput("0. Return to Main Menu");
        updateOutput("Enter your choice: ");
    }

    private int getUserInput() {
        String input = JOptionPane.showInputDialog(frame, "Enter your choice:");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DictionaryUI dictionaryUI = new DictionaryUI();
            dictionaryUI.run();
        });
    }
}
