import java.io.*;
import java.util.*;
/**
 * an accoount that contains a balance and a neme for identification
 * The valance can be changed by having a deposite or a payment. 
 *
 * @author KaiYuan C    
 * @version 2017-10-16
 */
public class SavingAccount
{
    //Constants declaration
    
    private static final int INITIAL_INDEX = 0;
    private static final  int MAX_TRANSACTION_RECORDED = 5;
    
    
    //Class fields declaration
    
    private static int index = 0;
    
    //instance fields declaration
    
    private String accIden;
    private double balance;
    private ArrayList<String> transactions;
    /**
     * Constructs a Saving Account with initial balance and an
     * identifiation
     * 
     */
    public SavingAccount()
    {
        //checked if customer is over 18
        balance = 0.00;
        accIden = "SavAcc" + index;
        index = index + 1;
        transactions = new ArrayList<String>();
        transactions.add("A savings account with name " + accIden + " is made." + "\n" 
        + "Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end SavingAccount()
    
    
    /**
     * Constructs a Saving Account with a set balance and an
     * identification
     * 
     * @param input a set balance that the customer input
     */
    public SavingAccount(double input)
    {
        if(input > 0.0)
        {
            balance = input;
        }//end (input > 0.0)
        else
        {
            //throw new BelowZeroException();
        }//end else
        accIden = "SavAcc" + index;
        index = index + 1;
        transactions = new ArrayList<String>();
        transactions.add("A savings account with name " + accIden + " is made." + "\n"
        + "Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end SavingAccount(double input)
    
    /*
     * Accessors
     */
    /**
     * Returns balance of a saving account
     * 
     * @return balance of this saving account
     */
    public double getBalance()
    {
        return balance;
    }//end getBalance()
    
    /**
     * Returns the identification name of a saving account
     * 
     * @return string identification of a saving account
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
            output = output + transactions.get(i) + "\n" ;
        }
        return output + "-----------------------------" + "\n";
    }
    
    /**
     * Returns a String representation of a saving account
     * 
     * @returns a string represetation of a saving account
     */
    public String toString()
    {
        // check how it looks
        return
        "[" +
        "Account/Card: " + accIden + 
        "Balance: " + balance +
        "]";
    }//end toString()
    
    /*
     * Mutators
     */
    /**
     * deposits the balance by the ammount entered by the user
     * 
     * @param amount a non zero double value for the amount added 
     * by user
     */
    public void deposit(double amount)
    {
        if(amount >= 0)
        {
            balance = balance + amount;
        }//end if (amount >=0)
        
        transactions.add("A deposit is made by " + accIden + "!\n" 
        + "Amount Deposited: $" + amount
        + "\nBalance before: " + String.format(java.util.Locale.US,"%.2f", (balance-amount))
        + "\nResulted Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end deposit(double amount)
    
    /**
     * Withdraw funds from this account
     * 
     * @param amount a non zero double value for the puchase made by 
     * user from this account
     */
    public void withdraw(double amount)
    {
        if(amount >= 0 && (balance - amount) >=0)
        {
            balance = balance - amount;
        }
        transactions.add("A withdraw is made by " + accIden + "!\n" 
        + "Amount Withdrawn: $" + amount
        + "\nBalance before: " + String.format(java.util.Locale.US,"%.2f", (balance + amount))
        + "\nResulted Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end withdraw(double amount)
}
