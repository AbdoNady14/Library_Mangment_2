import java.io.IOException;
import javax.swing.*;

public class AddBookGUI extends JFrame {

    JTextField name, house, author, date, genre, desc;
    JButton add;
    JLabel result;

    public AddBookGUI( ) {

        setTitle("Add Book");
        setSize(320, 330);
        setLayout(null);

        JLabel l1 = new JLabel("Name");
        l1.setBounds(20, 20, 80, 25);
        add(l1);

        name = new JTextField();
        name.setBounds(120, 20, 160, 25);
        add(name);

        JLabel l2 = new JLabel("House");
        l2.setBounds(20, 55, 80, 25);
        add(l2);

        house = new JTextField();
        house.setBounds(120, 55, 160, 25);
        add(house);

        JLabel l3 = new JLabel("Author");
        l3.setBounds(20, 90, 80, 25);
        add(l3);

        author = new JTextField();
        author.setBounds(120, 90, 160, 25);
        add(author);

        JLabel l4 = new JLabel("Date");
        l4.setBounds(20, 125, 80, 25);
        add(l4);

        date = new JTextField();
        date.setBounds(120, 125, 160, 25);
        add(date);

        JLabel l5 = new JLabel("Genre");
        l5.setBounds(20, 160, 80, 25);
        add(l5);

        genre = new JTextField();
        genre.setBounds(120, 160, 160, 25);
        add(genre);

        JLabel l6 = new JLabel("Desc");
        l6.setBounds(20, 195, 80, 25);
        add(l6);

        desc = new JTextField();
        desc.setBounds(120, 195, 160, 25);
        add(desc);

        add= new JButton("Add");
        add.setBounds(100, 230, 100, 25);
        add(add);

        result = new JLabel();
        result.setBounds(20, 265, 260, 25);
        add(result);

        add.addActionListener(e -> {
            String bookName = name.getText();
            String publishingHouse = house.getText().trim();
            String authorName = author.getText().trim();
            String dateOfPublication = date.getText().trim();
            String genreText = genre.getText().trim();
            String description = desc.getText().trim();

            Book book = new Book(bookName, publishingHouse, authorName, dateOfPublication, genreText, description);

            try {
                Book.addBook(book);
                result.setText("Book added successfully.");
            } catch (IOException ex) {
                result.setText("Error adding book: " + ex.getMessage());
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
