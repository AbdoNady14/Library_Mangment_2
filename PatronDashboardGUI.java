import javax.swing.*;

public class PatronDashboardGUI extends JFrame {

    Patron patron;

    public PatronDashboardGUI(Patron patron) {

        this.patron = patron;

        setTitle("Patron Dashboard");
        setSize(300, 220);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcome = new JLabel("Welcome, " + patron.getUsername(), SwingConstants.CENTER);
        welcome.setBounds(30, 20, 220, 25);
        add(welcome);

        JButton search = new JButton("Search Book");
        search.setBounds(70, 60, 160, 30);
        add(search);


//diminsoins for buttons are same, need to change
        JButton reserveBook = new JButton("Reserve Book");
        search.setBounds(70, 60, 160, 30);
        add(search);

        JButton returnBook = new JButton("Return Book");
        search.setBounds(70, 60, 160, 30);
        add(search);

        JButton viewChecoutHistory = new JButton("View Checout History");
        search.setBounds(70, 60, 160, 30);
        add(search);
// ------------------------------------------------------------------------------


        JButton update = new JButton("Manage Account");
        update.setBounds(70, 100, 160, 30);
        add(update);

        JButton logout = new JButton("Logout");
        logout.setBounds(70, 140, 160, 30);
        add(logout);

        search.addActionListener(e -> new SearchBookGUI());

        update.addActionListener(e -> new ManageUserGUI(patron.getId(), patron));

        logout.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
