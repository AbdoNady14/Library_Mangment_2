import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                Admin admin1 = new Admin();
                admin1.CreateAccount("first", "adminPass");
                admin1.CreateAccount("second", "fady");

                Patron patron1 = new Patron();
                patron1.CreateAccount("third", "patronPass2");
                patron1.CreateAccount("fifth", "patronPass2");
                patron1.CreateAccount("seventh", "patronPass2");

                Librarian librarian1 = new Librarian();
                librarian1.CreateAccount("fourth", "librarianPass");
                librarian1.CreateAccount("sixth", "librarianPass");

            } catch (Exception e) {
                e.printStackTrace();
            }
//lol
            new LoginGUI();
        });
    }
}
