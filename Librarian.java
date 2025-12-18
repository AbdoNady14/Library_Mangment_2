import java.io.*;
import java.util.*;

public class Librarian extends User{
    private static final  File file = new File("Library.csv");
    private static final  File historyFile = new File("CheckoutHistory.csv");

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


    public BookStatus reserveBook(Book book,int patronId){
        if(book.getStatus() == BookStatus.AVAILABLE){
            book.setStatus(BookStatus.CHECKED_OUT);
             book.setPatronId(patronId);
            Book.updateBookByID(book.getbookId(), book);
            try (PrintWriter pw = new PrintWriter(new FileWriter(historyFile, true))) {
                pw.println(patronId + "," + book.getbookId() + "," + new Date().toString() + ",," + "CHECKED_OUT");
            } catch (IOException e) {
                System.out.println("Error logging checkout: " + e.getMessage());
            }
            return BookStatus.CHECKED_OUT;
        }
        else if(book.getStatus() == BookStatus.CHECKED_OUT){
            book.setStatus(BookStatus.RESERVED);
            book.setReservationId(patronId);
            Book.updateBookByID(book.getbookId(), book);
            return BookStatus.RESERVED;
        }
            else{
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
}
