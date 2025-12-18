import javax.swing.*;

public class AdminDashboardGUI extends JFrame {
    Admin admin;

    public AdminDashboardGUI(Admin admin) {
        this.admin = admin;

        setTitle("Admin Dashboard");
        setSize(350, 520); // Increased height for the new button
        setLayout(null);

        // --- Existing Buttons ---
        JButton addUser = new JButton("Add User");
        addUser.setBounds(75, 30, 200, 30);
        add(addUser);

        JButton searchUser = new JButton("Search User");
        searchUser.setBounds(75, 70, 200, 30);
        add(searchUser);

        JButton addBook = new JButton("Add Book");
        addBook.setBounds(75, 120, 200, 30);
        add(addBook);

        JButton searchBook = new JButton("Search Book");
        searchBook.setBounds(75, 160, 200, 30);
        add(searchBook);

        // --- THE UPDATE BOOK BUTTON ---
        JButton updateBook = new JButton("Update Book");
        updateBook.setBounds(75, 200, 200, 30);
        add(updateBook);

        JButton removeBook = new JButton("Remove Book");
        removeBook.setBounds(75, 240, 200, 30);
        add(removeBook);

        JButton logout = new JButton("Logout");
        logout.setBounds(75, 350, 200, 30);
        add(logout);

        addUser.addActionListener(e -> new AddUserGUI());
        searchUser.addActionListener(e -> new SearchUserGUI(admin));
        addBook.addActionListener(e -> new AddBookGUI());
        searchBook.addActionListener(e -> new SearchBookGUI());

        updateBook.addActionListener(e -> {
            String inputId = JOptionPane.showInputDialog(this, "Enter Book ID to Update:");
            if (inputId != null && !inputId.isEmpty()) {
                try {
                    int id = Integer.parseInt(inputId);
                    Book bookToUpdate = Book.searchBook(id); 
                    
                    if (bookToUpdate != null) {
                        new UpdateBookDataGUI(bookToUpdate); 
                    } else {
                        JOptionPane.showMessageDialog(this, "Book ID not found!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
                }
            }
        });

        removeBook.addActionListener(e -> {
            String inputId = JOptionPane.showInputDialog(this, "Enter Book ID to Remove:");
            if (inputId != null) {
                Book.removeBookByID(Integer.parseInt(inputId));
            }
        });

        logout.addActionListener(e -> {
            dispose();
            new AdminLoginGUI();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}