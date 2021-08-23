import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;


public class Bank {

    /*
       arraylist to store the no of User's object
       It also used to store user information into the file
     */
    private ArrayList<User> users;

    /////constructor of the class Bank
    public Bank()
    {
        users = new ArrayList<>();
    }

    /*
      This method is used to create the user ID for the new user
      It randomly create the user ID using random generator
      It make sure that every user has different ID
     */
    public String getNewUserID()
    {
        String userID;
        Random randomGenerator = new Random();

        int length = 6;
        boolean trackID;

        do {
            userID = "";
            for (int i = 0; i < length; i++)
            {
                userID += ((Integer) randomGenerator.nextInt(10)).toString();
            }

            trackID = false;
            for (User user : this.users)
            {
                if (userID.compareTo(user.getUserID()) == 0) {
                    trackID = true;
                    break;
                }
            }
        } while (trackID);
        return userID;
    }


    /*
     This method is used to create the user account number for the new user
     It randomly create the user account number using random generator
     It make sure that every user has different account number
    */
    public String getAccountNo()
    {
        String accountNo = "";
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
                accountNo += ((Integer) random.nextInt(10)).toString();
            }

        return accountNo;
    }


   /*
      this method adds the user to the arraylist
    */
    public void addUser(String firstName, String lastName, String pin)
    {
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);
    }


    /*
         this method to enter the newly created user's information
       */
    public void addNewUser()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name:");
        String firstName = sc.nextLine();
        System.out.println("Enter last name:");
        String lastName = sc.nextLine();
        System.out.println("Enter temporary pin code:");
        String pin = sc.nextLine();

        this.addUser(firstName, lastName, pin);
    }

    /*
    this method is used to delete the user if administrator want to
     */
    public void deleteUser()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name:");
        String firstName = sc.nextLine();
        System.out.println("Enter last name:");
        String lastName = sc.nextLine();

        Iterator<User> itr = users.iterator();
        while(itr.hasNext()) {
            User u = itr.next();
            if (firstName.compareTo(u.getFirstName()) == 0 && lastName.compareTo(u.getLastName()) == 0) {
                users.remove(u);
                System.out.print("User with name "+firstName+lastName+" has successfully deleted");
            } else {
                System.out.println("User not found, please try again'\n\n");
            }
        }
    }

   /*
     this method used to store arraylist of user object
     serialization is implemented here
    */
    public void writeToFile() throws IOException
    {
        FileOutputStream file = new FileOutputStream("ATMFile.txt");
        ObjectOutputStream oos = new ObjectOutputStream(file);

        for(User user : this.users)
        {
            oos.writeObject(user);
        }
        oos.close();
    }

    /*
      This method implement the task which administrator performs
      A menu is created here using switch statement
     */
    public void menu ()
    {
            System.out.println("\n1. Add new user");
            System.out.println("2. Delete an account");
            System.out.println("3. Exit \n");


            System.out.println("What would you like to do,Press a respective number");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();

            if(n<1||n>3)
                System.out.println("Incorrect choice, please enter correct choice\n");

            switch (n) {
                case 1:
                    addNewUser();
                         break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    System.exit(0);
            }
            menu();
    }


    /*
       this is the main method
       which create the class object if we need to call any function
     */
    public static void main (String[]args) throws IOException
    {

        Bank bank = new Bank();

          bank.addUser("Ashiq","Muhammad","2341");
          bank.writeToFile();

        LogInGUI logIn = new LogInGUI();
        logIn.startingPage();

        new ATM();


    }
}

