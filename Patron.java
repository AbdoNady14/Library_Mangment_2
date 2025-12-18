import java.io.*;
import java.util.*;


public class Patron extends User {
        private static final  File file = new File("Library.csv");
        private static final  File historyFile = new File("CheckoutHistory.csv");
        private static final File reservationFile = new File("reservaionRequests.csv");
    
    public Patron() {
        super();
        this.setAccountType(AccountType.PATRON);
    }
    public Patron(String username, String password) {
        super(username, password);
        this.setAccountType(AccountType.PATRON);
    }
    
    public void requestReservation(int id,String title) {
        Book book = new Book();
        book = this.searchBook(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        try {
            if (!reservationFile.exists()) {
                PrintWriter pw = new PrintWriter(new FileWriter(reservationFile));
                pw.println("patronId,bookId,requestDate,status");
                pw.close();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(reservationFile, true));
            pw.println(id + "," + book.getbookId() + "," + new Date().toString() + "," + "PENDING");
            pw.close();
            System.out.println("Reservation request submitted.");
        } catch (IOException e) {
            System.out.println("Error writing reservation request: " + e.getMessage());
        }
    }
    
   public void returnBook(Book book) {
       Librarian librarian = new Librarian();
       librarian.returnBook(book,this.getId());
    }

     public Book searchBook(String title) {
        
        try(Scanner scan = new Scanner(file)){
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] fields = line.split(",");
            if(fields.length > 1 && fields[1].equalsIgnoreCase(title)){
                Book book = new Book();
                book.setBookId(Integer.parseInt(fields[0]));
                book.settitle(fields[1]);
                book.setPublishingHouse(fields[2]);
                book.setAuther(fields[3]);
                book.setDateOfPublication(fields[4]);
                book.setGenre(fields[5]);
                book.setBookDescription(fields[6]);
                scan.close();
                return book;
            }
        }
        return null;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


       public static List<String> searchGenre(Genre genre){
        List<String> foundBooks = new ArrayList<>();
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] fields = line.split(",");
                if(fields.length > 5 && fields[5].equalsIgnoreCase(genre.toString())){
                    foundBooks.add(line);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return foundBooks;
    }
    public static List<String> searchAuthor(String author){
        List<String> foundBooks = new ArrayList<>();
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] fields = line.split(",");
                if(fields.length > 3 && fields[3].equalsIgnoreCase(author)){
                    foundBooks.add(line);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return foundBooks;
    }
    public static List<String> searchPublishHouse(String publishHouse){
        List<String> foundBooks = new ArrayList<>();
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] fields = line.split(",");
                if(fields.length > 2 && fields[2].equalsIgnoreCase(publishHouse)){
                    foundBooks.add(line);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return foundBooks;
    }


    public static String viewBook(Book book ){
    return book.toString();
    }


    public void addBookToCheckoutHistory(Book book) {
        try {
            if (!historyFile.exists()) {
                PrintWriter pw = new PrintWriter(new FileWriter(historyFile));
                pw.println("patronId,bookId,checkoutDate,returnDate,status");
                pw.close();
            }
            try (Scanner sc = new Scanner(historyFile)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] fields = line.split(",");
                    if (fields.length >= 5) {
                        try {
                            int pId = Integer.parseInt(fields[0].trim());
                            int bId = Integer.parseInt(fields[1].trim());
                            String retDate = fields[3];
                            if (pId == this.getId() && bId == book.getbookId() && (retDate == null || retDate.isEmpty())) {
                                return;
                            }
                        } catch (NumberFormatException ignored) { }
                    }
                }
            }
            PrintWriter pw = new PrintWriter(new FileWriter(historyFile, true));
            pw.println(this.getId() + "," + book.getbookId() + "," + new Date().toString() + "," + "CHECKED_OUT");
            pw.close();
        } catch (IOException e) {
            System.out.println("Error writing history: " + e.getMessage());
        }
    }


    public List<String> getCheckoutHistory() {
        List<String> history = new ArrayList<>();
        try {
            if (!historyFile.exists()) {
                PrintWriter pw = new PrintWriter(new FileWriter(historyFile));
                pw.println("patronId,bookId,checkoutDate,returnDate,status");
                pw.close();
                return history;
            }
            Scanner sc = new Scanner(historyFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] fields = line.split(",");
                boolean isHeader = fields.length >= 5 && "patronId".equalsIgnoreCase(fields[0]);
                if (isHeader) {
                    continue;
                }
                if (fields.length >= 5) {
                    try {
                        int patronId = Integer.parseInt(fields[0].trim());
                        if (patronId == this.getId()) {
                            history.add("Book ID: " + fields[1] + ", Checkout: " + fields[2] + ", Return: " + fields[3] + ", Status: " + fields[4]);
                        }
                    } catch (NumberFormatException ignored) { }
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading history: " + e.getMessage());
        }
        return history;
    }
}
