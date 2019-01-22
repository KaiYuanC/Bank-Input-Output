import java.io.*;
import java.util.*;
/**
 * A chequing account has a balance and an identification. Each 
 * chequing account is able to deposite, withdraw, and make cheques.
 *
 * @author KaiYuan C
 * @version 2017-10-17
 */
public class ChequingAccount
{
    //constants declaration
    
    private static final int INITIAL_INDEX = 0;
    private static final  int MAX_TRANSACTION_RECORDED = 5;
    private static final double PROCESS_CHEQUE_FEE = 0.15;
    
    //class fields declareation
    
    private static int index = 0;
    
    
    //instance field declaration
    
    private String accIden; 
    private double balance;
    private ArrayList<String> transactions;
    
    /**
     * Constructs a Chequing Account with initial balance and an
     * identification. 
     * 
     */
    public ChequingAccount()
    {
        //checked if customer is over 18
        balance = 0.00;
        //formulate balance to be two decimal places
        accIden = "CheAcc" + index;
        index = index + 1;
        transactions = new ArrayList<String>();
        transactions.add("A chequing account with name " + accIden + " is made." + "\n" 
        + "Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end ChequingAccount()
    
    /**
     * Constructs a Chequing Account with a set balance and an
     * identification
     * 
     * @param input a non zero positive balance that the 
     * customer input 
     */
    public ChequingAccount(double input)
    {
        if(input > 0.0)
        {
            balance = input;
        }//end (input > 0.0)
        accIden = "CheAcc" + index;
        index = index + 1;
        transactions = new ArrayList<String>();
        transactions.add("A chequing account with name " + accIden + " is made." + "\n" 
        + "Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end ChequingAccount(double input)
    
    /*
     * Accessors
     */
    /**
     * Returns the balance of a chequing account
     * 
     * @return balance of a chequing account
     */
    public double getBalance()
    {
        return balance;
    }//end getBalance()
    
    /**
     * Returns the identification name of a chequing account
     * 
     * @return string identification of a chequing account
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
     * Returns a String representation of a chequing account
     * 
     * @returns a string represetation of a chequing account
     */
    public String toString()
    {
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
     * @param amount a non zero positive double value for the 
     * amount added by user.
     */
    public void deposit(double amount)
    {
        if(amount >= 0)
        {
            balance = balance + amount;
        }//end if (amount >=0)
        
        transactions.add("A deposit is made by " + accIden + "\n" 
        + "Amount deposited: $" + amount
        + "\nBalance before: $" + String.format(java.util.Locale.US,"%.2f", (balance - amount))
        + "\nResulted Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end deposit(double amount)
    
    /**
     * Process a cheque drawn from a chequing account. If the 
     * balance is less thant $1000 before a cheque is processed,
     * $0.15 is charged for the cheque
     * 
     * @param amount a none zero value that is less than the 
     * balance in the chequing account. 
     */
    public void drawCheque (double amount)
    {
        if(amount >= 0 && (balance - amount) >= 0)
        {
            if(amount < 1000)
            {
                balance = balance - (amount + PROCESS_CHEQUE_FEE);
            }//end if (amount <1000)
            else
            {
                balance = balance - amount;
            }
        }//end if(amount >= 0 && (balance - amount) >= 0)
        transactions.add("A cheque is drawn from " + accIden + "\n" 
        + "Amount drawn for the Cheque: $" + amount
        + "\nBalance before: $" + String.format(java.util.Locale.US,"%.2f", (balance + amount))
        + "\nResulted Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }// end drawCheque(double amount)
    
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
        transactions.add("A withdraw is made by " + accIden + "\n" 
        + "Amount Withdrawn: $" + amount
        + "\nBalance before: $" + String.format(java.util.Locale.US,"%.2f", (balance+ amount))
        + "\nResulted Balance: $" + String.format(java.util.Locale.US,"%.2f", balance));
        if(transactions.size() > MAX_TRANSACTION_RECORDED)
        {
            transactions.remove(INITIAL_INDEX);
        }
    }//end withdraw(double amount)
}
