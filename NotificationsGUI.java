import java.awt.*;
import java.util.List;
import javax.swing.*;

public class NotificationsGUI extends JFrame {

    public NotificationsGUI(Patron patron) {

        setTitle("Notifications");
        setSize(500, 220);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        List<String> notifications = patron.getNotifications();

        if (notifications.isEmpty()) {
            textArea.setText("No notifications.");
        } else {
            for (String n : notifications) {
                textArea.append(n + "\n\n");
            }
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}

