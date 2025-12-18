import java.io.*;
import java.util.*;

public abstract  class User{
    private String username;
    private String password;
    private int id;
    private static int numOfUsers = 0;
    private boolean loginStatus = false;
    private AccountType accountType;
    private static final File USERS_FILE = new File("Users.csv");
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public static int getNumOfUsers() {return numOfUsers;}
    public static void setNumOfUsers(int NumOfUsers) {numOfUsers= NumOfUsers;}

    public  AccountType getAccountType() {return this.accountType;}
    public void setAccountType(AccountType accountType) {this.accountType = accountType;}

    public boolean getLoginStatus() {return this.loginStatus;}
    public void setLoginStatus(boolean loginstatus) {this.loginStatus = loginstatus;}

    
    public void CreateAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.id= getInitialCounter();
        numOfUsers++;
        try {
            Scanner sc = new Scanner(USERS_FILE);
            while(sc.hasNextLine()){
              String[] fields = sc.nextLine().split(",");
              if(fields.length > 0 && fields[0].equals(this.getUsername())){
                  System.out.println("Username already exists");
                  sc.close();
                  return;
                }
            }
            sc.close();
            PrintWriter users = new PrintWriter(new FileWriter(USERS_FILE, true));
            users.println( username +"," + password+ "," + id + "," + accountType);
            users.close();
            System.out.println("Account created successfully.");
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    
    public void login() {
        if(this.loginStatus){
            System.out.println("You are already logged in");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username:");
        String uname = sc.nextLine();
        System.out.println("Enter password:");
        String pass = sc.nextLine();
        
        try {
            Scanner file = new Scanner(USERS_FILE);
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] F = line.split(",");
                
                if (F.length >= 3 && F[0].equals(uname) && F[1].equals(pass)) {
                    this.username = uname;
                    this.password = pass;
                    this.id = Integer.parseInt(F[2]);
                    this.accountType = AccountType.valueOf(F[3]);
                    this.loginStatus = true;
                    System.out.println("Login successful.");
                    file.close();
                    return;
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("Login failed.");
    }


    public boolean login(String uname, String pass) {

        if (this.loginStatus) {
            return true;
        }

        try {
            Scanner file = new Scanner(USERS_FILE);

            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] fields = line.split(",");

                if (fields.length >= 4 &&
                        fields[0].equals(uname) &&
                        fields[1].equals(pass)) {

                    this.username = fields[0];
                    this.password = fields[1];
                    this.id = Integer.parseInt(fields[2]);
                    this.accountType = AccountType.valueOf(fields[3]);
                    this.loginStatus = true;

                    file.close();
                    return true;
                }
            }
            file.close();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return false;
    }
    

    public void logout() {
        if(!this.loginStatus){
            System.out.println("You are not logged in");
            return;
        }
        this.loginStatus=false;
        System.out.println("logged out successfully");
    }


public boolean updateAccount(String newUsername, String newPassword) {

    try (Scanner sc = new Scanner(USERS_FILE)) {
        List<String> lines = new ArrayList<>();
        boolean usernameExists = false;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] F = line.split(",");
    
            if (F.length >= 1 && F[0].equals(newUsername) && !F[2].equals(String.valueOf(this.id))) {
                usernameExists = true;
            }

            if (F.length >= 3 && F[2].equals(String.valueOf(this.id))) {
                line = newUsername + "," + newPassword + "," + this.id + "," + this.accountType;
                this.username = newUsername;
                this.password = newPassword;
            }
            lines.add(line);
        }

        if (usernameExists) {
            System.out.println("Username already exists");
            return false;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (String l : lines) {
                pw.println(l);
            }
        }

        System.out.println("Account updated successfully.");
        return true;
    } catch (Exception e) {
        System.out.println("Error updating account: " + e.getMessage());
    }
    
    return false;
}


public void deleteAccount(int id) {
    try {
        Scanner sc = new Scanner(USERS_FILE);
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

        PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE));
        for (String line : lines) {
            pw.println(line); 
        }
        pw.close();

        this.username = null;
        this.password = null;
        this.id = -1;
        this.loginStatus = false;
        numOfUsers--;
        System.out.println("Account deleted successfully.");

    } catch (Exception e) {
        System.out.println("Error deleting account: " + e.getMessage());
    }
}

    private static int getInitialCounter() {
            int maxId = 0;
            try {
                if (!USERS_FILE.exists()) return 1;
                Scanner sc = new Scanner(USERS_FILE);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] fields = line.split(",");
                    if (fields.length >= 3) {
                        try {
                            int idVal = Integer.parseInt(fields[2].trim());
                            if (idVal > maxId) maxId = idVal;
                        } catch (NumberFormatException ignored) { }
                    }
                }
                sc.close();
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            return maxId + 1;
    }
}



