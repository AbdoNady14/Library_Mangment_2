import javax.swing.*;
import java.awt.*;

public class PatronDashboard extends JFrame {

    private Patron patron;

    public PatronDashboard(Patron patron) {
        this.patron = patron;

        setTitle("Patron Dashboard");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + patron.getUsername() + " (ID: " + patron.getId() + ")",
                SwingConstants.CENTER
        );

        JButton logoutBtn = new JButton("Logout");

        logoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out");
            new LoginGUI().setVisible(true);
            dispose();
        });

        setLayout(new BorderLayout());
        add(welcomeLabel, BorderLayout.CENTER);
        add(logoutBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

}
