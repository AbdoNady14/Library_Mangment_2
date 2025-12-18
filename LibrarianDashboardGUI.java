import javax.swing.*;

public class LibrarianDashboardGUI extends JFrame {

    Librarian librarian;

    public LibrarianDashboardGUI(Librarian librarian) {
        this.librarian = librarian;

        setTitle("Librarian Dashboard");
        setSize(300, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcome = new JLabel("Welcome, " + librarian.getUsername(), SwingConstants.CENTER);
        welcome.setBounds(30, 20, 220, 25);
        add(welcome);

        JButton manageUser = new JButton("Manage User");
        manageUser.setBounds(50, 60, 200, 30);
        add(manageUser);

        JButton manageReservation = new JButton("Manage Reservation");
        manageReservation.setBounds(50, 95, 200, 30);
        add(manageReservation);

        JButton ReturnBOOK = new JButton("Return Book");
        ReturnBOOK.setBounds(50, 95, 200, 30);
        add(ReturnBOOK);

        JButton search = new JButton("Search Book");
        search.setBounds(50, 130, 200, 30);
        add(search);

        JButton logout = new JButton("Logout");
        logout.setBounds(50, 165, 200, 30);
        add(logout);

        manageUser.addActionListener(e ->
                new ManageUserGUI(librarian.getId(), librarian)
        );

        search.addActionListener(e ->
                new SearchBookGUI()
        );

        logout.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}