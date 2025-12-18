import javax.swing.*;

public class RequestReserveGUI extends JFrame {

    JTextField bookId;
    JButton reserve;
    JLabel result;
    Patron patron;

    public RequestReserveGUI(Patron patron) {
        this.patron = patron;

        setTitle("Request Reservation");
        setSize(300, 200);
        setLayout(null);

        JLabel l = new JLabel("Book ID:");
        l.setBounds(20, 20, 80, 25);
        add(l);

        bookId = new JTextField();
        bookId.setBounds(100, 20, 150, 25);
        add(bookId);

        reserve = new JButton("Reserve");
        reserve.setBounds(90, 60, 100, 25);
        add(reserve);

        result = new JLabel("", SwingConstants.CENTER);
        result.setBounds(20, 100, 250, 25);
        add(result);

        reserve.addActionListener(e -> {

            result.setText("Reservation requested");
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
