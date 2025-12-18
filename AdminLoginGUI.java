import javax.swing.*;

public class AdminLoginGUI extends JFrame {

    JTextField username;
    JPasswordField password;
    JButton login;
    JLabel result;

    public AdminLoginGUI() {

        setTitle("Admin Login");
        setSize(320, 220);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel u = new JLabel("Admin Username:");
        u.setBounds(20, 20, 120, 25);
        add(u);

        username = new JTextField();
        username.setBounds(140, 20, 150, 25);
        add(username);

        JLabel p = new JLabel("Password:");
        p.setBounds(20, 55, 120, 25);
        add(p);

        password = new JPasswordField();
        password.setBounds(140, 55, 150, 25);
        add(password);

        login = new JButton("Login");
        login.setBounds(110, 95, 100, 25);
        add(login);

        result = new JLabel();
        result.setBounds(20, 135, 260, 25);
        add(result);

        login.addActionListener(e -> doAdminLogin());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void doAdminLogin() {

        User user = new Admin();

        boolean success = user.login(
                username.getText(),
                new String(password.getPassword())
        );

        if (success && user.getAccountType() == AccountType.ADMIN) {
            dispose();
            new AdminDashboardGUI((Admin) user);
        } else {
            result.setText("Access denied: Admins only");
        }
    }
}
