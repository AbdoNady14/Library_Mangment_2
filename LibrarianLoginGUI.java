import javax.swing.*;

public class LibrarianLoginGUI extends JFrame {

    JTextField username;
    JPasswordField password;

    public LibrarianLoginGUI() {

        setTitle("Librarian Login");
        setSize(280, 180);
        setLayout(null);

        JLabel u = new JLabel("Username:");
        u.setBounds(20, 20, 80, 25);
        add(u);

        username = new JTextField();
        username.setBounds(100, 20, 150, 25);
        add(username);

        JLabel p = new JLabel("Password:");
        p.setBounds(20, 55, 80, 25);
        add(p);

        password = new JPasswordField();
        password.setBounds(100, 55, 150, 25);
        add(password);

        JButton login = new JButton("Login");
        login.setBounds(90, 95, 100, 25);
        add(login);

        login.addActionListener(e -> login());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void login() {

        Librarian librarian = new Librarian();

        if (librarian.login(
                username.getText(),
                new String(password.getPassword()))
            && librarian.getAccountType() == AccountType.LIBRARIAN) {

            dispose();
            new LibrarianDashboardGUI(librarian);

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Wrong username or password",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
