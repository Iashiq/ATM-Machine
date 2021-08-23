import java.io.Serializable;

/*
  user's information
 */
public class User implements Serializable {

   private String firstName;

   private String lastName;

   private String pin;

   private String userID;

   private String accountNo;

   private double balance;

   public User(){}


   /*
     -Constructor of the class user
     -It initialize the class attributes
    */
   public User(String firstName, String lastName, String pin, Bank theBank)
   {
      this.firstName = firstName;
      this.lastName = lastName;


         this.pin = pin;


      this.userID = theBank.getNewUserID();
      this.accountNo = theBank.getAccountNo();
      this.balance = 0;


      System.out.printf("New user %s %s with pin %s has created. \n", firstName, lastName,pin);
   }

   /*
      Following is the details of the getter and setter methods
      to access and update the user attributes
    */
   public String getUserID()
   {
      return userID;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public String getAccountNo()
   {
      return accountNo;
   }

   public String getPin()
   {
       return pin;
   }

   public double getBalance()
   {
      return balance;
   }

   public void setPin(String pin)
   {
      this.pin = pin;
   }

   public void setBalance(double balance)
   {
      this.balance = balance;
   }


}
