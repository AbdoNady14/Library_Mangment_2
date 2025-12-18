import javax.swing.*;

public class LibrarianDashboardGUI extends JFrame {

    Librarian librarian;

    public LibrarianDashboardGUI(Librarian librarian) {
        this.librarian = librarian;

        setTitle("Librarian Dashboard");
        setSize(300, 280);
        setLayout(null);

        JButton manageUser = new JButton("Manage User");
        manageUser.setBounds(75, 80, 150, 25);
        add(manageUser);
        
// dimension change made here       
        JButton manageReservation = new JButton("Manage Reservation");
        manageUser.setBounds(75, 80, 150, 25);
        add(manageUser);

        JButton search = new JButton("Search Book");
        search.setBounds(75, 120, 150, 25);
        add(search);

        JButton logout = new JButton("Logout");
        logout.setBounds(75, 170, 150, 25);
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