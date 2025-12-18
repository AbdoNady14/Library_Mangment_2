import javax.swing.*;

public class SearchBookGUI extends JFrame {

    JTextField input;
    JComboBox<String> searchType;
    JLabel result;

    public SearchBookGUI() {

        setTitle("Search Book");
        setSize(350, 240);
        setLayout(null);

        JLabel s = new JLabel("Search By:");
        s.setBounds(20, 30, 100, 25);
        add(s);

        searchType = new JComboBox<>(new String[]{
                "ID",
                "Title",
                "Author",
                "Publisher",
                "Genre"
        });
        searchType.setBounds(140, 30, 170, 25);
        add(searchType);

        JLabel v = new JLabel("Value:");
        v.setBounds(20, 70, 100, 25);
        add(v);

        input = new JTextField();
        input.setBounds(140, 70, 170, 25);
        add(input);

        JButton search = new JButton("Search");
        search.setBounds(90, 120, 150, 30);
        add(search);

        result = new JLabel("", SwingConstants.CENTER);
        result.setBounds(20, 170, 300, 25);
        add(result);

        search.addActionListener(e -> searchBook());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void searchBook() {

        String value = input.getText().trim();
        if (value.isEmpty()) {
            result.setText("Enter search value");
            return;
        }

        try {
            String bookData = null;

            switch ((String) searchType.getSelectedItem()) {

                case "ID":
                    bookData = Book.searchBook(Integer.parseInt(value));
                    break;

                case "Title":
                    bookData = Book.searchBook(value);
                    break;

                case "Author":
                    bookData = Book.searchAuthor(value).isEmpty()
                            ? null
                            : Book.searchAuthor(value).get(0);
                    break;

                case "Publisher":
                    bookData = Book.searchPublishHouse(value).isEmpty()
                            ? null
                            : Book.searchPublishHouse(value).get(0);
                    break;

                case "Genre":
                    bookData = Book.searchGenre(
                            Genre.valueOf(value.toUpperCase())
                    ).isEmpty()
                            ? null
                            : Book.searchGenre(
                            Genre.valueOf(value.toUpperCase())
                    ).get(0);
                    break;
            }

            if (bookData != null) {
                new DisplayBookGUI(bookData);
                dispose();
            } else {
                result.setText("Book not found");
            }

        } catch (Exception e) {
            result.setText("Invalid input");
        }
    }
}
