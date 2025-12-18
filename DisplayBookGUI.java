import javax.swing.*;

public class DisplayBookGUI extends JFrame {

    public DisplayBookGUI(String bookData) {

        setTitle("Book Details");
        setSize(420, 300);

        JTextArea area = new JTextArea(bookData);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        add(new JScrollPane(area));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
