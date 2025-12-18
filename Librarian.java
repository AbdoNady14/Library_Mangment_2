import java.io.*;
import java.util.*;

public class Librarian extends User{
    private static final  File file = new File("Library.csv");
    private static final  File historyFile = new File("CheckoutHistory.csv");
    private static final File reservationFile = new File("reservaionRequests.csv");
    private static final File notificationFile = new File("Notifications.csv");

    public Librarian() {
        super();
        this.setAccountType(AccountType.LIBRARIAN);
    }
    public Librarian(String username, String password) {
        super(username, password);
        this.setAccountType(AccountType.LIBRARIAN);
    }

    public BookStatus checkBook(Book book){
            return book.getStatus();
        }


    public BookStatus reserveBook(int patronId, int bookId){
        Book book = Book.searchBook(bookId); 
        if(book == null){
            System.out.println("Book not found.");
            return null;
        }
        BookStatus status = book.getStatus();
        if(status == null || status == BookStatus.AVAILABLE){
            book.setReservationId(patronId);
            book.setStatus(BookStatus.RESERVED);
            Book.updateBookByID(bookId, book);
            String returnDate = updateReservationStatus(patronId, bookId, "ACCEPTED");
            String message = "Your reservation for book ID " + bookId + " has been accepted.";
            if (returnDate != null && !returnDate.isEmpty()) {
                message += " Return date: " + returnDate;
            }
            addNotification(patronId, message);
            System.out.println("Book reserved successfully.");
            return BookStatus.RESERVED;
        }else{
            System.out.println("Book is not available for reservation.");
            return book.getStatus();
        }
    }

      public static Boolean checkBook(String title){
        try (Scanner scan = new Scanner(file)) {
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] fields = line.split(",");
                if(fields.length > 1 && fields[1].equalsIgnoreCase(title)){
                    if(fields.length > 7 && fields[7].equalsIgnoreCase("AVAILABLE")){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    } 
    // public Boolean checkBook(int id ){
    //     try {
    //         Scanner scan = new Scanner(file);
    //         while(scan.hasNextLine()){
    //             String line = scan.nextLine();
    //             String[] fields = line.split(",");
    //             if(fields.length > 0 && Integer.parseInt(fields[0]) == id){
    //                 scan.close();
    //                 if(fields.length > 7 && fields[7].equalsIgnoreCase("AVAILABLE")){
    //                     return true;
    //                 }else{
    //                     return false;
    //                 }
    //             }
    //         }
    //         scan.close();
    //     } catch (FileNotFoundException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }

    public static String checkBook(int id){
        return "Avalible";
    }

    public void rejectReservation(int patronId, int bookId) {
        updateReservationStatus(patronId, bookId, "REJECTED");
        addNotification(patronId, "Your reservation for book ID " + bookId + " has been rejected.");
        System.out.println("Reservation rejected.");
    }

      public void returnBook(Book book,int patronId){
        if(checkBook(book.getbookId()) == BookStatus.CHECKED_OUT.toString()){
            updateHistory(book.getbookId(), patronId, new Date().toString(), "AVAILABLE");
            book.setPatronId(-1);
            book.setStatus(BookStatus.AVAILABLE);
            Book.updateBookByID(book.getbookId(), book);
        }else if(checkBook(book.getbookId()) == BookStatus.RESERVED.toString()){
            updateHistory(book.getbookId(), patronId, new Date().toString(), "CHECKED_OUT");
            int newPatronId = book.getReservationId();
            book.setPatronId(newPatronId);
            book.setReservationId(-1);
            book.setStatus(BookStatus.CHECKED_OUT);
            Book.updateBookByID(book.getbookId(), book);
            try (PrintWriter pw = new PrintWriter(new FileWriter(historyFile, true))) {
                pw.println(newPatronId + "," + book.getbookId() + "," + new Date().toString() + ",," + "CHECKED_OUT");
            } catch (IOException e) {
                System.out.println("Error logging checkout: " + e.getMessage());
            }
        }else{
            System.out.println("Book is already available in library");
        }

    }


    private void updateHistory(int bookId, int patronId, String returnDate, String newStatus) {
        try {
            if (!historyFile.exists()) {
                return;
            }
            Scanner sc = new Scanner(historyFile);
            List<String> lines = new ArrayList<>();
            boolean updated = false;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] fields = line.split(",");
                boolean isHeader = fields.length >= 5 && "patronId".equalsIgnoreCase(fields[0]);
                if (!isHeader && fields.length >= 5) {
                    try {
                        int pId = Integer.parseInt(fields[0].trim());
                        int bId = Integer.parseInt(fields[1].trim());
                        String retDate = fields[3];
                        if (pId == patronId && bId == bookId && (retDate == null || retDate.isEmpty())) {
                            line = fields[0] + "," + fields[1] + "," + fields[2] + "," + returnDate + "," + newStatus;
                            updated = true;
                        }
                    } catch (NumberFormatException ignored) { }
                }
                lines.add(line);
            }
            sc.close();
            if (!updated) {
                return;
            }
            PrintWriter pw = new PrintWriter(new FileWriter(historyFile));
            for (String l : lines) pw.println(l);
            pw.close();
        } catch (Exception e) {
            System.out.println("Error updating history: " + e.getMessage());
        }
    }

    private String updateReservationStatus(int patronId, int bookId, String newStatus) {
        try {
            if (!reservationFile.exists()) {
                return "";
            }
            Scanner sc = new Scanner(reservationFile);
            List<String> lines = new ArrayList<>();
            String computedReturnDate = "";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] fields = line.split(",");
                if (fields.length >= 3) {
                    try {
                        int pId = Integer.parseInt(fields[0].trim());
                        int bId = Integer.parseInt(fields[1].trim());
                        String status = fields[fields.length - 1].trim();
                        if (pId == patronId && bId == bookId && status.equalsIgnoreCase("PENDING")) {
                            String requestDate = fields[2].trim();
                            String returnDate = "";
                            if (newStatus.equalsIgnoreCase("ACCEPTED")) {
                                Date now = new Date();
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(now);
                                cal.add(Calendar.DAY_OF_MONTH, 7);
                                Date due = cal.getTime();
                                returnDate = due.toString();
                                computedReturnDate = returnDate;
                            }
                            line = fields[0] + "," + fields[1] + "," + requestDate + "," + returnDate + "," + newStatus;
                        }
                    } catch (NumberFormatException ignored) { }
                }
                lines.add(line);
            }
            sc.close();
            PrintWriter pw = new PrintWriter(new FileWriter(reservationFile));
            for (String l : lines) {
                pw.println(l);
            }
            pw.close();
            return computedReturnDate;
        } catch (Exception e) {
            System.out.println("Error updating reservation file: " + e.getMessage());
            return "";
        }
    }

    private void addNotification(int patronId, String message) {
        try {
            if (!notificationFile.exists()) {
                PrintWriter pw = new PrintWriter(new FileWriter(notificationFile));
                pw.println("patronId,message,date,status");
                pw.close();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(notificationFile, true));
            pw.println(patronId + "," + message.replace(",", " ") + "," + new Date().toString() + ",UNREAD");
            pw.close();
        } catch (IOException e) {
            System.out.println("Error writing notification: " + e.getMessage());
        }
    }
}
     
