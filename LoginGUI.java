import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    JButton patronLogin, signup, adminLogin, librarianLogin;

    public LoginGUI() {

        setTitle("WELCOME TO OUR LIBRARY");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Welcome to the Library System", SwingConstants.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 18));
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(welcome, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        patronLogin = new JButton("Patron Login");
        signup = new JButton("Sign Up");
        adminLogin = new JButton("Admin Login");
        librarianLogin = new JButton("Librarian Login");

        buttonPanel.add(patronLogin);
        buttonPanel.add(signup);
        buttonPanel.add(adminLogin);
        buttonPanel.add(librarianLogin);

        add(buttonPanel, BorderLayout.CENTER);

        patronLogin.addActionListener(e -> new PatronLoginGUI());
        signup.addActionListener(e -> new SignupGUI());
        adminLogin.addActionListener(e -> new AdminLoginGUI());
        librarianLogin.addActionListener(e -> new LibrarianLoginGUI());

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
