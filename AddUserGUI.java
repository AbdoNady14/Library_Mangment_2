import javax.swing.*;

public class AddUserGUI extends JFrame {

    JTextField username;
    JPasswordField password;
    JLabel result;
    JButton addBtn;
    JComboBox<AccountType> roleBox;

    public AddUserGUI() {

        setTitle("Add User");
        setSize(340, 280);
        setLayout(null);

        JLabel u = new JLabel("Username:");
        u.setBounds(20, 20, 100, 25);
        add(u);

        username = new JTextField();
        username.setBounds(130, 20, 170, 25);
        add(username);

        JLabel p = new JLabel("Password:");
        p.setBounds(20, 60, 100, 25);
        add(p);

        password = new JPasswordField();
        password.setBounds(130, 60, 170, 25);
        add(password);

        JLabel r = new JLabel("Account Type:");
        r.setBounds(20, 100, 100, 25);
        add(r);

        roleBox = new JComboBox<>(AccountType.values());
        roleBox.setBounds(130, 100, 170, 25);
        add(roleBox);

        addBtn = new JButton("Add User");
        addBtn.setBounds(90, 150, 150, 30);
        add(addBtn);

        result = new JLabel("", SwingConstants.CENTER);
        result.setBounds(20, 195, 300, 25);
        add(result);

        setLocationRelativeTo(null);
        setVisible(true);

        addBtn.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword()).trim();
            AccountType role = (AccountType) roleBox.getSelectedItem();

            if (user.isEmpty() || pass.isEmpty()) {
                result.setText("Please fill in all fields.");
                return;
            }

            if (role == null) {
                result.setText("Please select an account type.");
                return;
            }

            try {
                User account;

                switch (role) {
                    case ADMIN:
                        account = new Admin();
                        break;
                    case LIBRARIAN:
                        account = new Librarian();
                        break;
                    case PATRON:
                        account = new Patron();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + role);
                }

                account.setAccountType(role);
                account.CreateAccount(user, pass);
                result.setText("Account created successfully!");

            } catch (Exception ex) {
                result.setText("Error: " + ex.getMessage());
            }
        });
    }
}
