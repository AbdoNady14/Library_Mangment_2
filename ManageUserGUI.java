import javax.swing.*;

public class ManageUserGUI extends JFrame {

    public ManageUserGUI(int id, User user) {

        setTitle("Manage User");
        setSize(320, 230);
        setLayout(null);

        JLabel nameLabel = new JLabel("User: " + user.getUsername() + " (ID: " + id + ")");
        nameLabel.setBounds(60, 55, 200, 25);
        add(nameLabel);

        JButton update = new JButton("Update User");
        update.setBounds(60, 90, 200, 30);
        add(update);

        JButton delete = new JButton("Delete User");
        delete.setBounds(60, 130, 200, 30);
        add(delete);

        update.addActionListener(e -> {
            new UpdateInfoGUI(id, user);
            dispose();
        });

        delete.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete this user?", 
                "Confirm Deletion", 
                JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                 user.deleteAccount(id); {
                    JOptionPane.showMessageDialog(null, "User deleted successfully.");
                    dispose();
                } 
            }
            else {
                    JOptionPane.showMessageDialog(null, "Error: Could not delete user. Check console.");
                }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}