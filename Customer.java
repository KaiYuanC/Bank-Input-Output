import java.io.*;
import java.util.*;
/**
 * A customer object has a name, a birthday, a SIN number and
 * at least one account that he or she holds. 
 *
 * @author KaiYuan C
 * @version 2017 - 10 - 16
 */
public class Customer
{
    //instantce fields declaration
    
    private int birthDay;
    private int birthMonth;
    private int birthYear;
    private ArrayList<ChequingAccount> cheAccounts;
    private ArrayList<CreditCard> creditCards;
    private String firstName;
    private String lastName;
    private ArrayList<SavingAccount> savAccounts;
    private int sin;
    
    /**
     * Constructs a customer with first name, last name
     * SIN number, birth date and the account that he or she would
     * like to select.
     * 
     * @param last customer's last name
     * @param first customer's first name
     * @param sinnum customer's SIN number
     * @param year the year that the customer was born in
     * @param month the month that the customer was born in
     * @param day the day that the customer was born in
     * @param account an integer choice of the type of account that the customer is creating
     * @param initialBalance the balance for that account
     */
    public Customer(String last, String first, int sinnum, 
    int year, int month, int day, int account, double initialBalance)
    {
        lastName = last;
        firstName = first;
        sin = sinnum;
        birthDay = day;
        birthMonth = month;
        birthYear = year;
        birthDay = day;
        
        savAccounts = new ArrayList<>();
        cheAccounts = new ArrayList<>();
        creditCards = new ArrayList<>();
        this.addAccount(initialBalance, account);
    }
    
    /**
     * Constructs a customer with first name, last name
     * SIN number, birth date.
     * 
     * @param last customer's last name
     * @param first customer's first name
     * @param sinnum customer's SIN number
     * @param year the year that the customer was born in
     * @param month the month that the customer was born in
     * @param day the day that the customer was born in
     */
    public Customer(String last, String first, int sinnum, 
    int year, int month, int day)
    {
        lastName = last;
        firstName = first;
        sin = sinnum;
        birthDay = day;
        birthMonth = month;
        birthYear = year;
        birthDay = day;
        
        savAccounts = new ArrayList<>();
        cheAccounts = new ArrayList<>();
        creditCards = new ArrayList<>();
    }
    
    /*
     * Accessors
     */
    /**
     * Returns customer's day of birth.
     * 
     * @return customer's day of birth
     */
    public int getBirthDay()
    {
        return birthDay;
    }//end getBirthDay()
    
    /**
     * Returns customer's month of birth.
     * 
     * @return customer's month of birth
     */
    public int getBirthMonth()
    {
        return birthMonth;
    }// end getBirthMonth()
    
    /**
     * Returns customer's year of birth.
     * 
     * @return customer's year of birth
     */
    public int getBirthYear()
    {
        return birthYear;
    }// end getBirthYear()
    
    /**
     * Returns all the chequing accounts that a customer holds.
     * 
     * @return an array list of customer's chequing accounts
     */
    public String getCheAccounts()
    {
        String output = "\n-----Chequing Accounts-------\n";
        for(int i = 0; i < cheAccounts.size(); i++)
        {
            output = output + (i+1) + ": " + cheAccounts.get(i).getIden() + "\n";
        }
        return output;
    }//end getAccounts()
    
    /**
     * Returns the number of chequing account that a customer has.
     * 
     * @return the number of chequing account that a customer has.
     */
    public int cheAccountsSize()
    {
        return cheAccounts.size();
    }//end class cheAccountsSize()
    
    /**
     * Returns the number of credit cards that a customer has.
     * 
     * @return the number of credit cards that a customer has
     */
    public int creditCardsSize()
    {
        return creditCards.size();
    }//end creditCardsSize()
    
    /**
     * Returns the name of a chequing account within an 
     * array of Chequing accounts.
     * 
     * @param index an integer postion of the savings account
     * @return the name of a chequing account within an 
     * array of Chequing accounts
     */
    public ChequingAccount getCheAccount(int index)
    {
        //must check if index < savAccounts.size
        return cheAccounts.get(index);
    }//end getSavAccount(int index)
    
    /**
     * Returns all the credit cards that a customer holds.
     * 
     * @return an array list of customer's credit cards
     */
    public String getCreditCards()
    {
        String output = "\n--------Credit Cards-------\n";
        for(int i = 0; i < creditCards.size(); i++)
        {
            output = output + (i+1) + ": " + creditCards.get(i).getIden() + "\n";
        }
        return output;
    }//end getAccounts()
    
    /**
     * Returns the name of a Credit Card within an 
     * array of credit cards.
     * 
     * @param index an integer postion of the savings account
     * @return the name of a Credit Card within an 
     * array of credit cards.
     */
    public CreditCard getCreditCard(int index)
    {
        //must check if index < savAccounts.size
        return creditCards.get(index);
    }//end getSavAccount(int index)
    
    /**
     * Returns customer's first name.
     * 
     * @return first name of the custmoer
     */
    public String getFirstName()
    {
        return firstName;
    }// end getFirstName()
    
    /**
     * Returns customer's last name.
     * 
     * @return last name of customer
     */
    public String getLastName()
    {
        return lastName;
    }//end getLastname()
    
    /**
     * Returns all the saving accounts that a customer holds.
     * 
     * @return an array list of all the saving account that 
     * a customer holds
     */
    public String getSavAccounts()
    {
        String output = "\n-----Savings Accounts-------\n";
        for(int i = 0; i < savAccounts.size(); i++)
        {
            output = output + (i+1) + ": " + savAccounts.get(i).getIden() + "\n";
        }
        return output;
    }
    
    /**
     * Returns the name of a savings account within an 
     * array of savings accounts.
     * 
     * @param index an integer postion of the savings account
     * @return the name of a savings account within an 
     * array of savings accounts.
     */
    public SavingAccount getSavAccount(int index)
    {
        //must check if index < savAccounts.size
        return savAccounts.get(index);
    }//end getSavAccount(int index)
    
    /**
     * Returns customer's SIN number.
     * 
     * @return customer's SIN number
     */
    public int getSin()
    {
        return sin;
    }//end getSin()
    
    /**
     * Returns the number of Savings account that a customer has.
     * 
     * @return the number of Savings account that a customer has
     */
    public int savAccountsSize()
    {
        return savAccounts.size();
    }
    
    /**
     * Returns a string representation of the fields of a customer.
     * 
     * @return a string representation of the fields of a customer
     */
    public String toString()
    {
        return 
        "\nName: " + lastName + ", " + firstName +
        "\nSIN Number: " + sin + 
        "\nBirthday: " + birthYear + " " + birthMonth + " " + birthDay;
    }// end toString()
    
    /*
     * Mutators
     */
    
    /**
     * Adds an account to a customer, ask user to input the type 
     * of account that the would like to add.
     * 
     * @param accountType a integer representation of the account 
     * choice that customer selected in the main method selection.
     */
    public void addAccount(double accountBalance, int accountChoice )
    {
        if(accountChoice == 1)
        {
            savAccounts.add(new SavingAccount(accountBalance));
        }
        else if (accountChoice == 2)
        {
            cheAccounts.add(new ChequingAccount(accountBalance));
        }
        else if(accountChoice == 3)
        {
            creditCards.add(new CreditCard(accountBalance));
        }
    }//end of addAccount()
    
    /**
     * Creates a savings account with balance set by the user.
     * 
     * @param amount a double value for the balance of this savings account
     */
    public void addSavAccount(double amount)
    {
        savAccounts.add(new SavingAccount(amount));
    }
    
    /**
     * Deletes an account under the customer, the accounts are first
     * displaced, then user is given a integer choice of
     * the account.
     * 
     * @param accountChoice an integer value which indicate the account selected
     * @param indexChoice an integer value which indicate the account selected
     */
    public void deleteAccount(int accountChoice, int indexChoice)
    {
        if(accountChoice == 1)
        {
            if(indexChoice >= 0 && indexChoice <= savAccounts.size())
            {
                savAccounts.remove(indexChoice-1);
            }
        }//end if(accountChoice == 1)
        else if(accountChoice == 2)
        {
            if(indexChoice >= 0 && indexChoice <= cheAccounts.size())
            {
                cheAccounts.remove(indexChoice-1);
            }
        }//end else if(accountChoice == 2)
        else if(accountChoice == 3)
        {
            if(indexChoice >= 0 && indexChoice <= creditCards.size())
            {
                creditCards.remove(indexChoice-1);
            }
        }
    }//end of deleteAccount()
    
    /**
     * Processes a payment from chequing or saving account to
     * credit card. 
     * 
     * @param amount the amount being paid
     * @param accountType the type of account that is paying
     * @param accountChoice the accont which is used to make this payment
     * @param cardChoice the card that is accepting the payment
     */
    public void paymentToCred(double amount, int accountType, int accountChoice, int cardChoice)
    {
        //choice 1 is paying with savings account
        if(accountType == 1)
        {
            if(accountChoice > 0 && accountChoice <= savAccounts.size() 
            && cardChoice > 0 && cardChoice <= creditCards.size() && amount >= 0 )
            {
                savAccounts.get(accountChoice - 1).withdraw(amount);
                creditCards.get(accountChoice - 1).payment(amount);
            }
        }
        //choice 2 is paying with chequing account
        else if (accountType ==2)
        {
            if(accountChoice > 0 && accountChoice <= cheAccounts.size() 
            && cardChoice > 0 && cardChoice <= creditCards.size() && amount >= 0 )
            {
                cheAccounts.get(accountChoice - 1).withdraw(amount);
                creditCards.get(accountChoice - 1).payment(amount);
            }
        }
    }//end paymentToCred(double amount, int choice)
    
    /**
     * Transfers funds between chequing and savings account.
     * 
     * @param amount the amount that is transfered
     * @param accTypeFrom the type of account that is transfering from
     * @param accTypeTo the type of account that is transfering to
     * @param accFrom the account that is transfering from
     * @param accTo the account that is transfering to. 
     */
    public void transferFunds(double amount, int accTypeFrom, int accTypeTo, int accFrom, int accTo)
    {
        //choice 1 is from savings account to savings account
        if(accTypeFrom == 1 && accTypeTo == 1)
        {
            if(accFrom >= 0 && accFrom <= savAccounts.size() && accTo >= 0
            && accTo <= savAccounts.size() && amount >= 0)
            {
                savAccounts.get(accFrom-1).withdraw(amount);
                savAccounts.get(accTo-1).deposit(amount);
            }
        }
        //choice 2 is from savings account to chequing account
        else if(accTypeFrom == 1 && accTypeTo == 2)
        {
            if(accFrom >= 0 && accFrom <= savAccounts.size() && accTo >= 0
            && accTo <= cheAccounts.size() && amount >= 0)
            {
                savAccounts.get(accFrom-1).withdraw(amount);
                cheAccounts.get(accTo-1).deposit(amount);
            }
        }
        //choice 3 is from chequing account to savings account
        else if(accTypeFrom == 2 && accTypeTo == 1)
        {
            if(accFrom >= 0 && accFrom <= cheAccounts.size() && accTo >= 0
            && accTo <= savAccounts.size() && amount >= 0)
            {
                cheAccounts.get(accFrom-1).withdraw(amount);
                savAccounts.get(accTo-1).deposit(amount);
            }
        }
        //choice 4 is from chequing account to chequing account
        if(accTypeFrom == 1 && accTypeTo == 1)
        {
            if(accFrom >= 0 && accFrom <= cheAccounts.size() && accTo >= 0
            && accTo <= cheAccounts.size() && amount >= 0)
            {
                cheAccounts.get(accFrom-1).withdraw(amount);
                cheAccounts.get(accTo-1).deposit(amount);
            }
        }
    }//end of transferFunds(int accFrom, int accTo)
}