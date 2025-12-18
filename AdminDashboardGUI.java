import javax.swing.*;

public class AdminDashboardGUI extends JFrame {

    Admin admin;

    public AdminDashboardGUI(Admin admin) {

        this.admin = admin;

        setTitle("Admin Dashboard");
        setSize(350, 360);
        setLayout(null);

        JButton addUser = new JButton("Add User");
        addUser.setBounds(75, 30, 200, 30);
        add(addUser);

        JButton searchUser = new JButton("Search User");
        searchUser.setBounds(75, 70, 200, 30);
        add(searchUser);

        JButton addBook = new JButton("Add Book");
        addBook.setBounds(75, 110, 200, 30);
        add(addBook);

        JButton searchBook = new JButton("Search Book");
        searchBook.setBounds(75, 150, 200, 30);
        add(searchBook);

        JButton logout = new JButton("Logout");
        logout.setBounds(75, 230, 200, 30);
        add(logout);

        addUser.addActionListener(e -> new AddUserGUI());
        searchUser.addActionListener(e -> new SearchUserGUI(admin));
        addBook.addActionListener(e -> new AddBookGUI());
        searchBook.addActionListener(e -> new SearchBookGUI());

        logout.addActionListener(e -> {
            dispose();
            new AdminLoginGUI();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
