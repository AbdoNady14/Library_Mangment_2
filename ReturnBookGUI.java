import javax.swing.*;

public class ReturnBookGUI extends JFrame {

    public ReturnBookGUI(Patron patron) {

        setTitle("Return Book");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Enter Book Title:");
        label.setBounds(30, 30, 200, 25);
        add(label);

        JTextField bookIdField = new JTextField();
        bookIdField.setBounds(30, 60, 200, 25);
        add(bookIdField);

        JButton returnBtn = new JButton("Return Book");
        returnBtn.setBounds(30, 100, 200, 30);
        add(returnBtn);

        returnBtn.addActionListener(e -> {
            try {
                String title = bookIdField.getText();
                Book book = patron.searchBook(title);

                if (book == null) {
                    JOptionPane.showMessageDialog(this, "Book not found");
                    return;
                }

                patron.returnBook(book);

                JOptionPane.showMessageDialog(this, "Book returned successfully");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
