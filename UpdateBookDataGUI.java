import javax.swing.*;

public class UpdateBookDataGUI extends JFrame {
    
    public UpdateBookDataGUI(Book book) {
        setTitle("Edit Book Details - ID: " + book.getbookId());
        setSize(400, 500);
        setLayout(null);

        JLabel l1 = new JLabel("Title:"); l1.setBounds(30, 20, 100, 25); add(l1);
        JTextField titleField = new JTextField(book.gettitle());
        titleField.setBounds(140, 20, 200, 25); add(titleField);

        JLabel l2 = new JLabel("Publisher:"); l2.setBounds(30, 60, 100, 25); add(l2);
        JTextField pubField = new JTextField(book.getPublishingHouse());
        pubField.setBounds(140, 60, 200, 25); add(pubField);

        JLabel l3 = new JLabel("Author:"); l3.setBounds(30, 100, 100, 25); add(l3);
        JTextField authorField = new JTextField(book.getAuther());
        authorField.setBounds(140, 100, 200, 25); add(authorField);

        JLabel l4 = new JLabel("Date (YYYY):"); l4.setBounds(30, 140, 100, 25); add(l4);
        JTextField dateField = new JTextField(book.getDateOfPublication());
        dateField.setBounds(140, 140, 200, 25); add(dateField);

        JLabel l5 = new JLabel("Genre:"); l5.setBounds(30, 180, 100, 25); add(l5);
        JTextField genreField = new JTextField(book.getGenre());
        genreField.setBounds(140, 180, 200, 25); add(genreField);

        JLabel l6 = new JLabel("Description:"); l6.setBounds(30, 220, 100, 25); add(l6);
        JTextArea descArea = new JTextArea(book.getBookDescription());
        descArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(descArea);
        scroll.setBounds(140, 220, 200, 100); add(scroll);

        JButton saveBtn = new JButton("Save Changes");
        saveBtn.setBounds(100, 360, 180, 40);
        add(saveBtn);

        saveBtn.addActionListener(e -> {
            book.settitle(titleField.getText());
            book.setPublishingHouse(pubField.getText());
            book.setAuther(authorField.getText());
            book.setDateOfPublication(dateField.getText());
            book.setGenre(genreField.getText());
            book.setBookDescription(descArea.getText());

            Book.updateBookByID(book.getbookId(), book);
            
            JOptionPane.showMessageDialog(this, "Book updated successfully!");
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}