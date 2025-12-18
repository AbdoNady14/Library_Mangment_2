import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AcceptReservationGUI extends JFrame {

    JTextField patronID;
    JTextField bookID;
    JTextArea requestsArea;
    Librarian librarian;

    private static final File reservationFile = new File("c:/Users/hussain1/OneDrive/Desktop/PROGRAMING/Library_Mangment_2/reservaionRequests.csv");

    public AcceptReservationGUI(Librarian librarian) {
        this.librarian = librarian;
        setTitle("Accept Reservation Requests");
        setSize(500, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Reservation Requests:");
        label.setBounds(20, 20, 200, 25);
        add(label);

        requestsArea = new JTextArea();
        requestsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(requestsArea);
        scrollPane.setBounds(20, 50, 440, 150);
        add(scrollPane);

        JLabel u = new JLabel("Patron ID:");
        u.setBounds(20, 210, 80, 25);
        add(u);

        patronID = new JTextField();
        patronID.setBounds(100, 210, 100, 25);
        add(patronID);

        JLabel t = new JLabel("Book ID:");
        t.setBounds(220, 210, 80, 25);
        add(t);

        bookID = new JTextField();
        bookID.setBounds(290, 210, 100, 25);
        add(bookID);

        JButton acceptButton = new JButton("Accept");
        acceptButton.setBounds(120, 250, 100, 30);
        add(acceptButton);

        JButton rejectButton = new JButton("Reject");
        rejectButton.setBounds(260, 250, 100, 30);
        add(rejectButton);

        loadReservationRequests();

        acceptButton.addActionListener(e -> {
            String patronText = patronID.getText().trim();
            String bookText = bookID.getText().trim();
            if (patronText.isEmpty() || bookText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter patron and book IDs");
                return;
            }
            try {
                int pId = Integer.parseInt(patronText);
                int bId = Integer.parseInt(bookText);
                librarian.reserveBook(pId, bId);
                loadReservationRequests();
                JOptionPane.showMessageDialog(this, "Reservation Accepted");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IDs must be numbers");
            }
        });

        rejectButton.addActionListener(e -> {
            String patronText = patronID.getText().trim();
            String bookText = bookID.getText().trim();
            if (patronText.isEmpty() || bookText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter patron and book IDs");
                return;
            }
            try {
                int pId = Integer.parseInt(patronText);
                int bId = Integer.parseInt(bookText);
                librarian.rejectReservation(pId, bId);
                loadReservationRequests();
                JOptionPane.showMessageDialog(this, "Reservation Rejected");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IDs must be numbers");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadReservationRequests() {
        StringBuilder sb = new StringBuilder();
        try (Scanner scan = new Scanner(reservationFile)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] fields = line.split(",");
                if (fields.length < 3) {
                    continue;
                }
                try {
                    int pId = Integer.parseInt(fields[0].trim());
                    int bId = Integer.parseInt(fields[1].trim());
                    String requestDate = fields[2].trim();
                    String status = fields[fields.length - 1].trim();
                    String returnDate = "";
                    if (fields.length >= 5) {
                        returnDate = fields[3].trim();
                    }
                    if (status.equalsIgnoreCase("PENDING")) {
                        sb.append("Patron ").append(pId)
                                .append(" - Book ").append(bId)
                                .append(" - Request: ").append(requestDate);
                        if (!returnDate.isEmpty()) {
                            sb.append(" - Return: ").append(returnDate);
                        }
                        sb.append(" - ").append(status).append("\n");
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        } catch (FileNotFoundException e) {
            sb.append("No reservation requests found.");
        }
        requestsArea.setText(sb.toString());
    }
}
