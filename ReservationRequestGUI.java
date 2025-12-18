import javax.swing.*;

public class ReservationRequestGUI extends JFrame {
     JTextField title;
     Patron patron=new Patron();
     public ReservationRequestGUI(int id) {
         setTitle("Reservation Request");
        setSize(300, 200);
        setLayout(null);

        JLabel t = new JLabel("Book Title:");
        t.setBounds(20, 20, 120, 25);
        add(t);

        title = new JTextField();
        title.setBounds(140, 20, 150, 25);
        add(title);

        JButton okButton = new JButton("OK");
        okButton.setBounds(100, 100, 100, 30);
        add(okButton);
        

        okButton.addActionListener(e -> {
            String bookTitle = title.getText();
            patron.requestReservation(id,bookTitle);
            dispose();
        });
        

        setLocationRelativeTo(null);
        setVisible(true);
    }
}