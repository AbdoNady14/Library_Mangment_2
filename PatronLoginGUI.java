import javax.swing.*;

public class PatronLoginGUI extends JFrame {

    JTextField username;
    JPasswordField password;
    JButton login;
    JLabel result;

    public PatronLoginGUI() {

        setTitle("Patron Login");
        setSize(320, 220);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel u = new JLabel("Username:");
        u.setBounds(20, 20, 100, 25);
        add(u);

        username = new JTextField();
        username.setBounds(130, 20, 150, 25);
        add(username);

        JLabel p = new JLabel("Password:");
        p.setBounds(20, 55, 100, 25);
        add(p);

        password = new JPasswordField();
        password.setBounds(130, 55, 150, 25);
        add(password);

        login = new JButton("Login");
        login.setBounds(105, 95, 100, 25);
        add(login);

        result = new JLabel("", SwingConstants.CENTER);
        result.setBounds(20, 135, 260, 25);
        add(result);

        login.addActionListener(e -> doPatronLogin());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void doPatronLogin() {

        User user = new Patron();

        boolean success = user.login(
                username.getText(),
                new String(password.getPassword())
        );

        if (success && user.getAccountType() == AccountType.PATRON) {
            dispose();
            new PatronDashboardGUI((Patron) user);
        } else {
            result.setText("Access denied: Patrons only");
        }
    }
}