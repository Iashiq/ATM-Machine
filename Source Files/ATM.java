import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ATM
{
   /*
     * arraylist to store the objects of user when program reads from the file
     * password to store the pin of the user which want to performs different tasks
    */
    private ArrayList<User> user;
    private int password;

    public ATM()
    {
      user = new ArrayList<>();
      password = 0;
    }

  /*
    It is used to read the user object and store in an arraylist
    Deserialization is implemented here
    It returns the arraylist of the user
   */
   public  ArrayList<User> readFromFile()
   {
        try
        {
            InputStream fis = new FileInputStream("ATMFile.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while(fis.available()>0)
            {
                User u = (User) ois.readObject();
                user.add(u);
            }
            ois.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
          e.printStackTrace();
        }

        return user;

    }


    /*
       It has called in the enterPin method of the LogInGUI class
       It compares the pin which user enter with actual pin of the user stored in database of the user
       If entered pin is correct it returns the true value otherwise false
     */
    public boolean validatePin(String pin) {
        ArrayList<User> usr = readFromFile();
        Iterator<User> itr = usr.iterator();
       while (itr.hasNext())
      {

          if(pin.equals(itr.next().getPin()))
          {
              password = Integer.parseInt(pin);
              return true;
          }

      }
        return false;
    }

    /*
      It is called in the checkBalance method of the AtmOperations
      It returns the current balance of the user
     */
    public double getBalance()
    {
        ArrayList<User> u = this.readFromFile();
        Iterator<User> itr = u.iterator();
        String pass = String.valueOf(password);
       while (itr.hasNext())
       {

           if(pass.equals(itr.next().getPin()))
           {
                return itr.next().getBalance();
           }
       }
        return 0;
    }


    /*
      It is called in the depositMoney method of the AtmOperations
      It returns the true value of the user enters the correct information of
      the other user whom he want to send the money
      otherwise it returns the false
     */
    public boolean checkUserInfo(String firstName, String lastName, String accountNo)
    {
      ArrayList<User> u = this.readFromFile();
      Iterator<User> itr = u.iterator();
      while (itr.hasNext())
      {
          if(firstName.equals(itr.next().getFirstName())&&(lastName.equals(itr.next().getLastName()))&&(accountNo.equals(itr.next().getAccountNo())))
          {
              return true;
          }

      }

      return false;
    }

    /*
     It is called in the cashWithdraw and depositMoney method of the AtmOperations
     It returns the true value if amount enter by the user is more than his current balance
     otherwise it returns the false value
    */
    public  boolean checkBalance(double balance)
    {
        ArrayList<User> user = this.readFromFile();
        Iterator<User> itr = user.iterator();
        String pass = String.valueOf(password);
        while (itr.hasNext())
        {
            if(pass.equals(itr.next().getPin()))
            {
                if(balance<=itr.next().getBalance())
                    return true;
            }
        }

        return false;
    }


    /*
     It is called in the depositMoney method of the AtmOperations
     It is used to update the user's balance after user deposit the money
    */
    public void depositMoney(double money)
    {

        ArrayList<User> user = this.readFromFile();
        Iterator<User> itr = user.iterator();
        String pass = String.valueOf(password);
        while (itr.hasNext())
        {

            if(pass.equals(itr.next().getPin()))
            {
                itr.next().setBalance(money+itr.next().getBalance());
                break;
            }
        }

    }

    /*
      This method is used to change the pin of the user
      It is called in the changePin method of LogInGUI class
      It returns the true value if user enter the correct previous pin
      Otherwise it returns the false value
     */
    public boolean changePin(String prePin, String newPin)
    {

        ArrayList<User> user = this.readFromFile();
        Iterator<User> itr = user.iterator();

        while (itr.hasNext())
        {
            if(prePin.equals(itr.next().getPin()))
            {
                itr.next().setPin(newPin);
                return true;
            }
        }

        return false;
    }



}
