import javax.swing.*;

public class UpdateByAdminGUI extends JFrame {
    private Admin admin;
    private int id;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public UpdateByAdminGUI(int id, Admin admin) {
        this.id = id;
        this.admin = admin;
        setTitle("Update Account Information");
        setSize(300, 250);
        setLayout(null);

        JLabel userLabel = new JLabel("New Username:");
        userLabel.setBounds(20, 30, 100, 25);
        add(userLabel);

        usernameField = new JTextField(admin.getUsername());
        usernameField.setBounds(130, 30, 140, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("New Password:");
        passLabel.setBounds(20, 70, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField(admin.getPassword());
        passwordField.setBounds(130, 70, 140, 25);
        add(passwordField);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(90, 130, 100, 30);
        add(updateButton);

        updateButton.addActionListener(e -> {
            String newUsername = usernameField.getText();
            String newPassword = new String(passwordField.getPassword());

            if (newUsername.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            admin.updatebyID(id, newUsername, newPassword, true);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
