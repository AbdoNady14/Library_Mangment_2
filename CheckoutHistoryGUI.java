import java.awt.*;
import java.util.List;
import javax.swing.*;

public class CheckoutHistoryGUI extends JFrame {

    public CheckoutHistoryGUI(Patron patron) {

        setTitle("Checkout History");
        setSize(280, 180);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        List<String> history = patron.getCheckoutHistory();

        if (history.isEmpty()) {
            textArea.setText("No checkout history found.");
        } else {
            for (String record : history) {
                textArea.append(record + "\n\n");
            }
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}