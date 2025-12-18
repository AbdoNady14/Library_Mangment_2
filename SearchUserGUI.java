import javax.swing.*;

public class SearchUserGUI extends JFrame {

    Admin admin;
    JTextField idField;

    public SearchUserGUI(Admin admin) {

        this.admin = admin;

        setTitle("Search User");
        setSize(320, 220);
        setLayout(null);

        JLabel title = new JLabel("Search User By ID");
        title.setBounds(90, 20, 200, 25);
        add(title);

        JLabel idLabel = new JLabel("User ID:");
        idLabel.setBounds(30, 70, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 70, 160, 25);
        add(idField);

        JButton search = new JButton("Search");
        search.setBounds(60, 120, 200, 30);
        add(search);

        search.addActionListener(e -> searchUser());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void searchUser() {
        try {
            int id = Integer.parseInt(idField.getText());
            User user = admin.searchById(id);

            if (user != null) {
                dispose();
                new ManageUserGUI(id, user);
            } else {
                JOptionPane.showMessageDialog(this, "User not found");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid ID");
        }
    }
}
