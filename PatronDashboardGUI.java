import javax.swing.*;

public class PatronDashboardGUI extends JFrame {

    Patron patron;

    public PatronDashboardGUI(Patron patron) {

        this.patron = patron;

        setTitle("Patron Dashboard");
        setSize(300, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcome = new JLabel("Welcome, " + patron.getUsername(), SwingConstants.CENTER);
        welcome.setBounds(30, 20, 220, 25);
        add(welcome);

        JButton search = new JButton("Search Book");
        search.setBounds(50, 60, 200, 30);
        add(search);

        JButton reserveBook = new JButton("Reserve Book");
        reserveBook.setBounds(50, 100, 200, 30);
        add(reserveBook);

        JButton returnBook = new JButton("Return Book");
        returnBook.setBounds(50, 140, 200, 30);
        add(returnBook);

        JButton viewCheckoutHistory = new JButton("View Checkout History");
        viewCheckoutHistory.setBounds(50, 180, 200, 30);
        add(viewCheckoutHistory);

        JButton Manage = new JButton("Manage Account");
        Manage.setBounds(50, 220, 200, 30);
        add(Manage);

        JButton logout = new JButton("Logout");
        logout.setBounds(50, 260, 200, 30);
        add(logout);

        // Actions
        search.addActionListener(e -> new SearchBookGUI());
        Manage.addActionListener(e -> new ManageUserGUI(patron.getId(), patron));
        reserveBook.addActionListener(e -> new ReservationRequestGUI(patron.getId()));
        returnBook.addActionListener(e -> new ReturnBookGUI(patron));
        viewCheckoutHistory.addActionListener(e -> new CheckoutHistoryGUI(patron));
        logout.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
