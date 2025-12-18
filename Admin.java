import java.io.*;
import java.util.*;

public class Admin extends User {
private static final File USER_FILE = new File("Users.csv");
private static final  File file = new File("Library.csv");

public Admin() { 
        super();
        this.setAccountType(AccountType.ADMIN);
}

public Admin(String username, String password) {
        super(username, password);
        this.setAccountType(AccountType.ADMIN);
}


public User searchById(int id) {
    if (!USER_FILE.exists()) {
        System.out.println("User file does not exist.");
        return null;
    }
    try (Scanner sc = new Scanner(USER_FILE)) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] fields = line.split(",");
            if (fields.length >= 3) {
                int userId = Integer.parseInt(fields[2]);
                if (userId == id) {
                    String accountType = fields.length >= 4 ? fields[3] : "PATRON";
                    System.out.println("User found: " + fields[0] + ", Password: " + fields[1] + " ID: " + fields[2] + ", AccountType: " + accountType);
                    User u;
                    if("ADMIN".equals(accountType)){
                        u = new Admin(fields[0], fields[1]);
                    } else if("LIBRARIAN".equals(accountType)){
                        u = new Librarian(fields[0], fields[1]);
                    } else {
                        u = new Patron(fields[0], fields[1]);
                    }
                    u.setId(userId);
                    return u;
                }
            }
        }
        System.out.println("User not found.");
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
    return null;
}


public void updatebyID(int id) {
        try {
            File file = USER_FILE;
            if (!file.exists()) {
                System.out.println("User file does not exist.");
                return;
            }

            Scanner sc = new Scanner(file);
            List<String> lines = new ArrayList<>();
            boolean found = false;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] fields = line.split(",");

                if (fields.length >= 3 && Integer.parseInt(fields[2]) == id) {
                    found = true;
                    Scanner input = new Scanner(System.in);
                    System.out.println("Enter new username:");
                    String newUname = input.nextLine();
                    System.out.println("Enter new password:");
                    String newPass = input.nextLine();
                    String type = fields.length >= 4 ? fields[3] : "PATRON";
                    line = newUname + "," + newPass + "," + fields[2] + "," + type; 
                    System.out.println("User updated successfully.");
                }

                lines.add(line);
            }
            sc.close();

            if (!found) {
                System.out.println("User not found.");
                return;
            }

    
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (String l : lines) pw.println(l);
            pw.close();

        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
}

    public void updatebyID(int id, String newUsername, String newPassword, boolean isAdmin) {
        try {
            File file = USER_FILE;
            if (!file.exists()) {
                System.out.println("User file does not exist.");
                return;
            }

            Scanner sc = new Scanner(file);
            List<String> lines = new ArrayList<>();
            boolean found = false;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] F = line.split(",");

                if (F.length >= 3 && Integer.parseInt(F[2]) == id) {
                    found = true;

                    line = newUsername + "," + newPassword + "," + F[2] + "," + F[3]; 
                    System.out.println("User updated successfully.");
                }

                lines.add(line);
            }
            sc.close();

            if (!found) {
                System.out.println("User not found.");
                return;
            }

            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (String l : lines) pw.println(l);
            pw.close();

        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }
public void deletebyID(int id) {
        try {
            File file = USER_FILE;
            Scanner sc = new Scanner(file);
            List<String> lines = new ArrayList<>();
            boolean found = false;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] F = line.split(",");

                if (F.length >= 3 && Integer.parseInt(F[2]) == id) {
                    found = true; 
                    continue;
                }
                lines.add(line);
            }
            sc.close();

            if (!found) {
                System.out.println("User not found.");
                return;
            }

            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (String l : lines) pw.println(l);
            pw.close();

            System.out.println("User deleted successfully.");

        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }  

// // private int getNextBookId() {
// //         int maxId = 0;
// //         try {
// //             if (!file.exists()) return 1;
// //             Scanner sc = new Scanner(file);
// //             while (sc.hasNextLine()) {
// //                 String line = sc.nextLine();
// //                 String[] fields = line.split(",");
// //                 if (fields.length >= 1) {
// //                     try {
// //                         int idVal = Integer.parseInt(fields[0].trim());
// //                         if (idVal > maxId) maxId = idVal;
// //                     } catch (NumberFormatException ignored) { }
// //                 }
// //             }
// //             sc.close();
// //         } catch (Exception e) {
// //             System.out.println("ERROR: " + e.getMessage());
// //         }
// //         return maxId + 1;
// // }

public BookStatus trackBook(Book book) {
        return book.getStatus();
}
//  public void addBook(Book book)  {
//     if (!this.getLoginStatus()) {
//         System.out.println("You must log in first");
//         return;
//     }
//     try{
//             Scanner scan = new Scanner(file);       
//             while(scan.hasNextLine()){
//                 String line = scan.nextLine();
//                 String[] fields = line.split(",");
//                 if(fields.length > 1 && fields[1].equalsIgnoreCase( book.gettitle())){
//                     System.out.println("Book already exists in the library.");
//                     scan.close();
//                     return;
//                 }
//             }
//             PrintWriter writer = new PrintWriter(new FileWriter(file, true));
//             writer.println( book.getbookId()+","+book.gettitle() + "," + book.getPublishingHouse() + "," + book.getAuther() + "," + book.getDateOfPublication() + "," + book.getGenre() + "," + book.getBookDescription()+","+book.getStatus() );
//             writer.close();
//             System.out.println("Book added to the library successfully.");
//         } catch (IOException e) {
//             System.out.println("Error adding book: " + e.getMessage());
//         }
        
//     }
//     public String searchBook(String title) throws FileNotFoundException {
//         Scanner scan = new Scanner(file);
//         while(scan.hasNextLine()){
//             String line = scan.nextLine();
//             String[] fields = line.split(",");
//             if(fields.length > 1 && fields[1].equalsIgnoreCase(title)){
//                 Book book = new Book();
//                 book.setBookId(Integer.parseInt(fields[0]));
//                 book.settitle(fields[1]);
//                 book.setPublishingHouse(fields[2]);
//                 book.setAuther(fields[3]);
//                 book.setDateOfPublication(fields[4]);
//                 book.setGenre(fields[5]);
//                 book.setBookDescription(fields[6]);
//                 scan.close();
//                 return book.toString();
//             }
//         }
//         scan.close();
//         return null;
//     }
//     public String searchBook(int id) throws FileNotFoundException {
//         Scanner scan = new Scanner(file);
//         while(scan.hasNextLine()){
//             String line = scan.nextLine();
//             String[] fields = line.split(",");
//             if(fields.length > 0 && Integer.parseInt(fields[0]) == id) {
//                 Book book = new Book();
//                 book.setBookId(Integer.parseInt(fields[0]));
//                 book.settitle(fields[1]);
//                 book.setPublishingHouse(fields[2]);
//                 book.setAuther(fields[3]);
//                 book.setDateOfPublication(fields[4]);
//                 book.setGenre(fields[5]);
//                 book.setBookDescription(fields[6]);
//                 scan.close();
//                 return book.toString();
//             }
//         }
//         scan.close();
//         return null;
//     }
//     public static List<String> catigoriesByGenre(Genre genre){
//         List<String> foundBooks = new ArrayList<>();
//         try {
//             Scanner scan = new Scanner(file);
//             while(scan.hasNextLine()){
//                 String line = scan.nextLine();
//                 String[] fields = line.split(",");
//                 if(fields.length > 5 && fields[5].equalsIgnoreCase(genre.toString())){
//                     foundBooks.add(line);
//                 }
//             }
//             scan.close();
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         }
//         return foundBooks;
//     }
//     public static List<String> catigoriesByAuthor(String author){
//         List<String> foundBooks = new ArrayList<>();
//         try {
//             Scanner scan = new Scanner(file);
//             while(scan.hasNextLine()){
//                 String line = scan.nextLine();
//                 String[] fields = line.split(",");
//                 if(fields.length > 3 && fields[3].equalsIgnoreCase(author)){
//                     foundBooks.add(line);
//                 }
//             }
//             scan.close();
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         }
//         return foundBooks;
//     }
//     public List<String> catigoriesByPublishHouse(String publishHouse){
//         List<String> foundBooks = new ArrayList<>();
//         try {
//             Scanner scan = new Scanner(file);
//             while(scan.hasNextLine()){
//                 String line = scan.nextLine();
//                 String[] fields = line.split(",");
//                 if(fields.length > 2 && fields[2].equalsIgnoreCase(publishHouse)){
//                     foundBooks.add(line);
//                 }
//             }
//             scan.close();
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         }
//         return foundBooks;
//     }
//     public String viewBook(Book book ){
//     return book.toString();
//     }
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
    public Boolean checkBook(int id ){
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] fields = line.split(",");
                if(fields.length > 0 && Integer.parseInt(fields[0]) == id){
                    scan.close();
                    if(fields.length > 7 && fields[7].equalsIgnoreCase("AVAILABLE")){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

     public void removeBookByID(int bookId) {
        if(!this.getLoginStatus()){
            System.out.println("You must log in first");
            return;
        }
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

    public void updateBookByID(int bookId, Book updatedBook) {
            if(this.getLoginStatus()){
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
        }else{
            System.out.println("You must log in first");
        }
    }
}

