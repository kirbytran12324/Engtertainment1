import javax.swing.*;

public class DictionaryApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dictionary");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DictionaryUI dictionaryUI = new DictionaryUI();
            frame.add(dictionaryUI.getMainPanel());

            frame.pack();
            frame.setVisible(true);
        });
    }
}

