import java.io.*;
import java.util.*;
/**
 * A CreditCard object has a balance and a name for identification.
 * It can be used for purchases by a customer. 
 *
 * @author KaiYuan C
 * @version 2017-10-17
 */
public class CreditCard
{
    //constants declaration
    
    private static final int INITIAL_INDEX = 0;
    private static final  int MAX_TRANSACTION_RECORDED = 5;
    
    //Class fields declaration
    
    private static int index = 0;
    
    //Instance fields declaration
    
    private String accIden;
    private double balance; 
    private ArrayList<String> transactions;
    
    /**
     * Constructs a Credit Card with initial balance and 
     * a name
     * 
     */
    public CreditCard()
    {
        accIden = "CreditCard" + index;
        balance = 0.00;
        index = index + 1;
        transactions = new ArrayList<String>();
        transactions.add("A credit card with name " + accIden + " is made." + "\n" 
        + "Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }
    
    /**
     * Constructs a Credit card with a balance set by the user
     * and a name.
     * 
     * @param input a real value, which can be positive or negative,
     * set by the user
     */
    public CreditCard(double input)
    {
        balance = input;
        accIden = "CreditCard" + index;
        index = index + 1;
        transactions = new ArrayList<String>();
        transactions.add("A credit card with name " + accIden + " is made." + "\n" 
        + "Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end CheckingAccount(double input)

    /*
     * Accessors
     */
    /**
     * Returns the balance of a CreditCard object
     * 
     * @return the balance of a CreditCard object
     */
    public double getBalance()
    {
        return balance;
    }//end getBalance()
    
    /**
     * Returns the identification name of a crdit card
     * 
     * @return string identification of a crdit card
     */
    public String getIden()
    {
        return accIden;
    }//end getIden()
    
    /**
     * Returns all the transactions in this account as a string
     * 
     * @return all the transactions in this account as a string
     */
    public String getTransactions()
    {
        String output = "Account Name: " + accIden + "\n" + "-----Transactions-----\n";
        for(int i = 0; i < transactions.size(); i++)
        {
            output = output + transactions.get(i) + "/n";
        }
        return output + "-----------------------------" + "\n";
    }
    
    /**
     * returns a string representation of a CrditCard object
     * 
     * @return a string representation of a CreditCard object
     */
    public String toString()
    {
        return
        "["+
        "Account/Card: " + accIden + 
        "Balance: " + balance +
        "]";
    }//end toString()
    
    /*
     * Mutators
     */
    
    /**
     * Receives a payment from a savings account or checking account
     * 
     * @param a non zero positive amount paid by another account and 
     * added to the balance of a credit card
     */
    public void payment(double amount)
    {
        //make sure that a payment is corresponding to a withdraw
        if(amount >= 0) 
        {
            balance = balance + amount;
        }//end if(amount >=0)
        
        transactions.add("A payment is made by " + accIden + "\n" 
        + "Amount of being paid: $" + amount
        + "\nBalance before: " + String.format(java.util.Locale.US,"%.2f", (balance - amount))
        + "\nResulted Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end payment(double amount)
    
        /**
     * Performs a purchase on a credit card
     * 
     * @param amount a non zero positive value, which will be taken
     * from a crdit card. 
     */
    public void withdraw(double amount)
    {
        if(amount >= 0)
        {
            balance = balance - amount;
        }//end if(amount >= 0)
        transactions.add("A withdraw is made by " + accIden + "\n" 
        + "Amount withdrawn: $" + amount
        + "\nBalance before: " + String.format(java.util.Locale.US,"%.2f", (balance + amount))
        + "\nResulted balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end withdraw(double amount)
    
}
