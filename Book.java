import java.io.*;
import java.util.*;

public class Book {
    private static final File file = new File("library.csv");
    private String title;
    private String publishingHouse;
    private String autherName;
    private String dateOfPublication ;
    private String genre;
    private String bookDescription;
    private int bookId;
    private int PatronId;
    private int reservationId;
    private BookStatus status;

    public Book() {
    }

    

    public Book(String title, String publishingHouse, String autherName, String dateOfPublication, String genre, String bookDiscription) {
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.autherName = autherName;
        this.dateOfPublication = dateOfPublication;
        this.genre = genre;
        this.bookDescription = bookDiscription;
        this.bookId = getInitialCounter();
        this.status = BookStatus.AVAILABLE;
    }

    public void settitle(String title){
        this.title=title;

    }
    public String gettitle(){
        return title;
    }
    public void setPublishingHouse(String publishingHouse){
        this.publishingHouse=publishingHouse;
    }

    public String getPublishingHouse(){
        return publishingHouse;
    }
    public void setAuther(String autherName){
        this.autherName=autherName;
    }
    public String getAuther(){
       return autherName; 
    }
    public void setDateOfPublication(String dateOfPublication){
        this.dateOfPublication=dateOfPublication;
    }
    public String getDateOfPublication(){
        return dateOfPublication;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public String getGenre(){
        return genre;
    }
    public void setBookDescription(String bookDiscription){
        this.bookDescription=bookDiscription;
    }
    public String getBookDescription(){
        return bookDescription;
    }
    public void setBookId(int bookId){
        this.bookId=bookId;
    }
    public int getbookId(){
        return bookId;
    }
    public BookStatus getStatus() {
        return status;
    }
    public void setStatus(BookStatus status) {
        this.status = status;
    }
    public void setPatronId(int patronId) {
        PatronId = patronId;
    }
    public int getPatronId() {
        return PatronId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    public int getReservationId() {
        return reservationId;
    }



    
    private static int getInitialCounter() {
        int maxId = 0;
        try {
            if (!file.exists()) return 0;
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] fields = line.split(",");
                if (fields.length >= 1) {
                    try {
                        int idVal = Integer.parseInt(fields[0].trim());
                        if (idVal > maxId) maxId = idVal;
                    } catch (NumberFormatException ignored) { }
                }
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return maxId ;
    }


    public static  void addBook(Book book) throws IOException {
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] fields = line.split(",");
            if(fields.length > 1 && fields[1].equalsIgnoreCase( book.gettitle())){
                System.out.println("Book already exists in the library.");
                scan.close();
                return;
            }
        }
        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        writer.println( book.getbookId()+","+book.gettitle() + "," + book.getPublishingHouse() + "," + book.getAuther() + "," + book.getDateOfPublication() + "," + book.getGenre() + "," + book.getBookDescription() +"," + book.getStatus().toString() );
        writer.close();
        System.out.println("Book added to the library successfully.");
    }

    public static String searchBook(String title) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
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
                return book.toString();
            }
        }
        scan.close();
        return null;
    }

    public static String searchBook(int id) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] fields = line.split(",");
            if(fields.length > 0 && (Integer.parseInt( fields[0]))== id){
                Book book = new Book();
                book.setBookId(Integer.parseInt(fields[0]));
                book.settitle(fields[1]);
                book.setPublishingHouse(fields[2]);
                book.setAuther(fields[3]);
                book.setDateOfPublication(fields[4]);
                book.setGenre(fields[5]);
                book.setBookDescription(fields[6]);
                scan.close();
                return book.toString();
            }
        }
        scan.close();
        return null;
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
    public static void removeBookByID(int bookId) {
            try {
                if (!file.exists()) {
                    System.out.println("Book file does not exist.");
                    return;
                }

                Scanner sc = new Scanner(file);
                List<String> lines = new ArrayList<>();
                boolean found = false;

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] F = line.split(",");

                    if (F.length >= 7 && Integer.parseInt(F[0]) == bookId) {
                        found = true; 
                        continue;
                    }
                    lines.add(line);
                }
                sc.close();

                if (!found) {
                    System.out.println("Book not found.");
                    return;
                }

                PrintWriter pw = new PrintWriter(new FileWriter(file));
                for (String l : lines) pw.println(l);
                pw.close();

                System.out.println("Book removed successfully.");

            } catch (Exception e) {
                System.out.println("Error removing book: " + e.getMessage());
            }
        
    }   

    public static void updateBookByID(int bookId, Book updatedBook) {
            try {
                Scanner sc = new Scanner(file);
                List<String> lines = new ArrayList<>();
                boolean found = false;

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] F = line.split(",");

                    if (F.length >= 7 && Integer.parseInt(F[0]) == bookId) {
                        found = true;
                        line = bookId + "," + updatedBook.gettitle() + "," +
                            updatedBook.getPublishingHouse() + "," + updatedBook.getAuther() + "," +
                            updatedBook.getDateOfPublication() + "," + updatedBook.getGenre() + "," +
                            updatedBook.getBookDescription();
                    }
                    lines.add(line);
                }
                sc.close();

                if (!found) {
                    System.out.println("Book not found.");
                    return;
                }

                PrintWriter pw = new PrintWriter(new FileWriter(file));
                for (String l : lines) pw.println(l);
                pw.close();

                System.out.println("Book updated successfully.");

            } catch (Exception e) {
                System.out.println("Error updating book: " + e.getMessage());
            }
            return;
        }


    @Override
public String toString() {
    return
        "ID: " + bookId + "\n" +
        "Title: " + title + "\n" +
        "Publisher: " + publishingHouse + "\n" +
        "Author: " + autherName + "\n" +
        "Date: " + dateOfPublication + "\n" +
        "Genre: " + genre + "\n" +
        "Description: " + bookDescription;
}

    // public static Boolean checkBook(String title){} 
    // public static Boolean checkBook(int id ){}

    

}
