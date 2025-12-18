import javax.swing.*;

public class PatronDashboardGUI extends JFrame {

    Patron patron;

    public PatronDashboardGUI(Patron patron) {

        this.patron = patron;

        setTitle("Patron Dashboard");
        setSize(300, 320);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcome = new JLabel("Welcome, " + patron.getUsername(), SwingConstants.CENTER);
        welcome.setBounds(30, 20, 220, 25);
        add(welcome);

        // Buttons (same width, different Y positions)
        JButton search = new JButton("Search Book");
        search.setBounds(70, 60, 160, 30);
        add(search);

        JButton reserveBook = new JButton("Reserve Book");
        reserveBook.setBounds(70, 100, 160, 30);
        add(reserveBook);

        JButton returnBook = new JButton("Return Book");
        returnBook.setBounds(70, 140, 160, 30);
        add(returnBook);

        JButton viewCheckoutHistory = new JButton("View Checkout History");
        viewCheckoutHistory.setBounds(50, 180, 200, 30);
        add(viewCheckoutHistory);

        JButton update = new JButton("Manage Account");
        update.setBounds(70, 220, 160, 30);
        add(update);

        JButton logout = new JButton("Logout");
        logout.setBounds(70, 260, 160, 30);
        add(logout);

        // Actions
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
