import java.io.*;
import java.util.*;

/**
 * Accepts customers. It can Add, delete a customer, sort 
 * customers by last name or first name, sort customers by
 * SIN, display customer summary(name, SIN), find profile by
 * last name or first name, and find profile by SIN. 
 *
 * @KaiYuan Chi
 * @2017-10-12
 */
public class Bank
{       
    // constants declarations
    private static final String CHEQUING_ACCOUNT_SELECTION = "1";
    private static final String CREDIT_CARD_SELECTION = "1";
    private static final int DIGITS_OF_SIN = 9;
    private static final int INITIAL_VALUE = 0;
    private static final int MAX_DAY_THIRTY = 30;
    private static final int MAX_DAY_THIRTY_ONE = 31;
    private static final int MAX_DAY_TWENTY_NINE = 29;
    private static final int MAX_MONTH = 12;
    private static final int MAX_YEAR = 2017;
    private static final int NUMBER_LINES_TAKEN_PER_CUSTOMER = 10; //Number of lines for every customer in the file.
    private static final int OVER_EIGHTEEN = 18;
    private static final int RETURN_TO_MAIN_MENU = 10;    
    private static final String SAVINGS_ACCOUNT_SELECTION = "1";
    
    //class field declarations
    private static Calendar cal = Calendar.getInstance();
    private static String fileName;
    
    // instance field declaration
    private ArrayList<Customer> customers;
    
    /**
     * Constructs a Bank object with an empty array of customers.
     */
    public Bank()
    {
        customers = new ArrayList<Customer>();
    }

    /**
     * Modifies bank accounts and customers by prompting user for instructions from console.
     *
     * @param  argument not used
     */
    public static void main(String argument[]) throws IOException
    {
        //Create a bank
        Bank bank = new Bank();
        //Connection to console
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        //Test whether file entered exist
        boolean fileNotFound = false;
        do
        {
            fileNotFound = false;
            try
            {
                System.out.println("Please enter a valid file name: (file name: data.txt)");
                //file and make sure that the file is right
                
                fileName =console.readLine();
                
                //Establish connections to the test files.
                BufferedReader inputFile = new BufferedReader(new FileReader(fileName));
                System.out.println("File uploaded to bank.");
                
                /*Read from the first file and echo to the second.*/
                //Declare and initialize temporary local variables
                String lineOfText = inputFile.readLine();
                int counter = 1;
                int customerIndex = 0;
                ArrayList<String> accounts = new ArrayList<String>();
                ArrayList<String> balances = new ArrayList<String>();
                String lastName = null;
                String firstName = null;
                int sin = 0;
                int year = 0;
                int month = 0;
                int day = 0;

                while(lineOfText != null)
                {
                    //Read lines and store them based on the type of information
                    //Assume all data from the file is correct, nothing is checked here
                    if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 1)
                    {
                        lastName = lineOfText;
                    }
                    else if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 2)
                    {
                        firstName = lineOfText;
                    }
                    else if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 3)
                    {
                        sin = Integer.parseInt(lineOfText);
                    }
                    else if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 4)
                    {
                        year = Integer.parseInt(lineOfText);
                    }
                    else if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 5)
                    {
                        month = Integer.parseInt(lineOfText);
                    }
                    else if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 6)
                    {
                        day = Integer.parseInt(lineOfText);
                    }
                    else if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 7)
                    {
                        if(!lineOfText.equals("none"))
                        {
                            balances.add(lineOfText);
                            accounts.add(SAVINGS_ACCOUNT_SELECTION);
                        }
                    }
                    else if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 8)
                    {
                        if(!lineOfText.equals("none"))
                        {
                            balances.add(lineOfText);
                            accounts.add(CHEQUING_ACCOUNT_SELECTION);
                        }
                    }
                    else if(counter%NUMBER_LINES_TAKEN_PER_CUSTOMER == 9)
                    {
                        if(!lineOfText.equals("none"))
                        {
                            balances.add(lineOfText);
                            accounts.add(CREDIT_CARD_SELECTION);
                        }
                        bank.addCustomerFile(lastName, firstName, sin, year, month, day);
                        for(int i = 0; i < accounts.size(); i++)
                        {
                            bank.getCustomerByIndex(customerIndex).addAccount(Double.parseDouble(balances.get(i)), Integer.parseInt(accounts.get(i)));
                            accounts.remove(i);
                            balances.remove(i);
                        }
                        customerIndex = customerIndex + 1;
                        
                    }
                    counter = counter +1;
                    lineOfText = inputFile.readLine();
                }//while (lineOfText != null);
                
                //Wrap up.
                inputFile.close();
            }
           catch(FileNotFoundException e)
           {
                System.out.println("File not Found.");
                fileNotFound = true;
           }
        }while(fileNotFound);//if file not found, it will keep finding the file. 
        
        String mainMenu = ("\nWelcome to the VP bank.\n" 
                            + "------------------------\n"
                            + "Please choose an action from the following:\n"
                            + "1: Add a customer\n"
                            + "2: Delete a customer\n"
                            + "3: Sort customers by last name, first name\n"
                            + "4: Sort customers by SIN\n"
                            + "5: Display customer summary (name, SIN)\n"
                            + "6: Find profile by last name, first name\n"
                            + "7: Find profile by SIN\n"
                            + "8: Quit\n");
        String customerProfileMenu = ("PROFILE MENU\n"
                            + "------------------------\n"
                            + "1: View account activity\n"
                            + "2: Deposit\n"
                            + "3: Withdraw\n"
                            + "4: Process cheque\n"
                            + "5: Process purchase\n"
                            + "6: Process payment\n"
                            + "7: Transfer funds\n"
                            + "8: Open account of issue card\n"
                            + "9: Cancel account or card\n"
                            + "10: Return to main menu\n");
        String accountSelectionMenu = ("ACCOUNT SELECTION\n"
                            + "------------------------\n"
                            + "1: Savings Account\n"
                            + "2: Chequing Account\n"
                            + "3: Credit Card\n");
        
        //Decaring local variables for the storing of information.
        //Selection and choice store customer's input as an integer. 
        int selection; 
        int choice;
        
        // Set choiceCust to the selection not entering the customer profile loop. Once customer selecte return to main menu, the loop terminates. 
        int choiceCust = RETURN_TO_MAIN_MENU;
        
        //Temporary storage location for input.
        String firstName, lastName;
        int sin, sinT, birthYear, birthMonth, birthDay;
        double balance;
        
        //Temporary storage of a customer selected by the user. 
        Customer selectedCust = null;
        
        do{                
            System.out.println(mainMenu);
            try
            {
                selection = Integer.parseInt(console.readLine());
                while(selection < 1 || selection > 8)
                {
                    System.out.println("Not a valid choice.");
                    System.out.println(mainMenu);
                    selection = Integer.parseInt(console.readLine());
                }
                    
                switch(selection)
                {
                    case 1: 
                        //Add a customer
                        try
                        {
                            System.out.println("-----Create New Customer----");
                            System.out.print("Enter last name: ");
                            lastName = console.readLine();
                            System.out.print("Enter first name: ");
                            firstName = console.readLine();
                            System.out.print("Enter SIN number: ");
                            sin = Integer.parseInt(console.readLine());
                            System.out.print("Enter birth year: ");
                            birthYear = Integer.parseInt(console.readLine());
                            System.out.print("Enter birth month: ");
                            birthMonth = Integer.parseInt(console.readLine());
                            System.out.print("Enter birth day: ");
                            birthDay = Integer.parseInt(console.readLine());
                            System.out.println("Enter integer corresponding to the first account you want to create: ");
                            System.out.println("If you would like to create more accounts, please go to customer profile after you have finished" +
                            " customer creation.");
                            System.out.println(accountSelectionMenu);
                            choice = Integer.parseInt(console.readLine());
                            System.out.print("Enter an initial balance for this account: ");
                            balance = Double.parseDouble(console.readLine());
                            if(bank.checkSin(sin) && bank.checkDate(birthYear, birthMonth, birthDay))
                            {
                                if(choice == 2 || choice == 3)
                                {
                                    if(bank.checkIsEighteen(birthYear, birthMonth, birthDay))
                                    {
                                        bank.addCustomer(lastName, firstName, sin, 
                                            birthYear, birthMonth, birthDay, choice, 
                                            balance);
                                        
                                        System.out.println("Your profile has added successfully into Bank!");
                                    }
                                    else
                                    {
                                        System.out.println("Your account is NOT created. \nYou cannot create a chequing account or a credit card if you are under 18.");
                                    }
                                }
                                else
                                {
                                    bank.addCustomer(lastName, firstName, sin, 
                                        birthYear, birthMonth, birthDay, choice, 
                                        balance);
                                    
                                    System.out.println("Your profile has added successfully into Bank!");
                                }
                            }
                            else
                            {
                                System.out.println("Your account is NOT created. \nYou have incorrect inputs, possible problems are: ");
                                System.out.println("A wrong SIN number");
                                System.out.println("You already have a profile");
                                System.out.println("A wrong date is entered");
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Your account is NOT created.\n There is an error in your input, please try again.");
                        }
                        break;
        
                    case 2: //delete a customer
                        System.out.println("Would you like to find yourself by name or by SIN number?");
                        System.out.println("1: By name");
                        System.out.println("2: By SIN number");
                        choice = Integer.parseInt(console.readLine());
                        while(choice < 1 || choice > 2)
                        {
                            System.out.println("Please enter 1 or 2.");
                            System.out.println("Would you like to find yourself by name or by SIN number?");
                            System.out.println("1: By name");
                            System.out.println("2: By SIN number");
                            choice = Integer.parseInt(console.readLine());
                        }
                        
                        if(choice == 1)
                        {
                            System.out.println("Enter last name: ");
                            String lastN = console.readLine();
                            System.out.println("Enter first name: ");
                            String firstN = console.readLine();
                            System.out.println("Please select your profile: ");
                            System.out.println(bank.getCustomerByNameString(lastN, firstN));
                            int numberRange = bank.checkSameName(lastN, firstN);
                            if(numberRange == 0)
                            {
                                System.out.println("There's no account called " + lastN + ", " + firstN + ".");
                            }
                            else
                            {
                                choice = Integer.parseInt(console.readLine());
                                if(choice > 0 && choice <= numberRange && numberRange != 0)
                                {
                                    selectedCust = bank.getCustomerByName(lastN, firstN, choice);
                                    bank.deleteCustomer(selectedCust);
                                    System.out.println(lastN + ", " + firstN + " has been deleted from the system.");
                                }
                                else
                                {
                                    System.out.println("You did not enter a valid choice, system will return back to main menu");
                                }
                            }
                        }
                        
                        else if(choice == 2)
                        {
                            System.out.println("Enter SIN number: ");
                            int sinN = Integer.parseInt(console.readLine());
                            int counter = 0;
                            for(int k = 0; k < bank.numberOfCustomers(); k++)
                            {
                                if(bank.getCustomerByIndex(k).getSin() == sinN)
                                {
                                    counter++;
                                }
                            }
                            if(counter == 0)
                            {
                                System.out.println("Customer with this SIN number does not exist.");
                            }
                            else
                            {
                                bank.deleteCustomerBySin(sinN);
                                System.out.println("Customer with SIN number: " + sinN + " has been deleted from the system.");
                            }
                        }
                        else
                        {
                            System.out.println("Error in your input.");
                        }
                       
                        break;
                    
                    case 3://sort by last name, first name
                        System.out.println(bank.sortCustomersByName());
                        break;
                    case 4://sort by SIN number
                        System.out.println(bank.sortCustomersBySin());
                        break;
                    case 5://Display Customer summary by name, SIN
                        System.out.println("----Display Customer Summary By Name, SIN----");
                        System.out.println(bank.displaySummary());
                        break;
                    case 6://find profile by last name first name
                        System.out.println("----Find Profile By Last Name First Name----");
                        System.out.println("Enter customer last name: ");
                        String lastN = console.readLine();
                        System.out.println("Enter customer first name: ");
                        String firstN = console.readLine();
                        System.out.println("Please select your profile: ");
                        System.out.println(bank.getCustomerByNameString(lastN, firstN));
                        int numberRange = bank.checkSameName(lastN, firstN);
                        if(numberRange == 0)
                        {
                            System.out.println("There's no profile under the name " + lastN + ", " + firstN + ".");
                        }
                        else
                        {
                            choice = Integer.parseInt(console.readLine());
                            if(choice > 0 && choice <= numberRange && numberRange != 0)
                            {
                                selectedCust = bank.getCustomerByName(lastN, firstN, choice);
                                System.out.println(customerProfileMenu);
                                choiceCust = Integer.parseInt(console.readLine());
                            }
                            else
                            {
                                System.out.println("You did not enter a valid choice, system will return back to main menu");
                            }
                        }
                        break;
                    case 7: //find profile by SIN
                        System.out.println("Enter SIN number: ");
                        sin = Integer.parseInt(console.readLine());
                        int sinLength = String.valueOf(sin).length();
                        if(sinLength != 9)
                        {
                            System.out.println("Your SIN number is not correct.");
                        }//end (sinLength != 9)
                        else
                        {
                            selectedCust = bank.getCustomerBySin(sin);
                            if(selectedCust != null)
                            {
                                System.out.println(customerProfileMenu);
                                choiceCust = Integer.parseInt(console.readLine());
                            }
                            else 
                            {
                                System.out.println("Your SIN number is not in the system.");
                            }
                        }
                        break;
                    case 8:
                        //exit program
                        //Save customers into file
                        PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));
                        for(int i = 0; i < bank.numberOfCustomers(); i++)
                        {
                            outputFile.println((bank.getCustomerByIndex(i)).getLastName());
                            outputFile.println((bank.getCustomerByIndex(i)).getFirstName());
                            outputFile.println((bank.getCustomerByIndex(i)).getSin());
                            outputFile.println((bank.getCustomerByIndex(i)).getBirthYear());
                            outputFile.println((bank.getCustomerByIndex(i)).getBirthMonth());
                            outputFile.println((bank.getCustomerByIndex(i)).getBirthDay());
                            if(bank.getCustomerByIndex(i).savAccountsSize() > 0)
                            {
                                outputFile.println((bank.getCustomerByIndex(i)).getSavAccount(0).getBalance());
                            }
                            else
                            {
                                outputFile.println("none");
                            }
                            if(bank.getCustomerByIndex(i).cheAccountsSize() > 0)
                            {
                                outputFile.println((bank.getCustomerByIndex(i)).getCheAccount(0).getBalance());
                            }
                            else
                            {
                                outputFile.println("none");
                            }
                            if(bank.getCustomerByIndex(i).creditCardsSize() > 0)
                            {
                                outputFile.println((bank.getCustomerByIndex(i)).getCreditCard(0).getBalance());
                            }
                            else
                            {
                                outputFile.println("none");
                            }
                            outputFile.println("");
                        }
                        outputFile.close();
                        System.out.println("Customers saved to file.");
                        System.exit(0);
                        break;
                    default: 
                        System.out.println("Please integer 1~8");
                        break;
                }
                while(choiceCust != RETURN_TO_MAIN_MENU)
                {
                    switch(choiceCust)
                    {
                        case 1://view account activity
                            System.out.println("\n----View Activities----\n");
                            System.out.println(selectedCust.toString());
                            System.out.print("\n------Accounts Information-----\n");
                            System.out.print("\nSavings Accounts--------\n");
                            System.out.println("-------------------------------");
                            for(int i = 0; i < selectedCust.savAccountsSize(); i++)
                            {
                                System.out.println(selectedCust.getSavAccount(i).getTransactions());
                            }
                            System.out.print("\nChequing Accounts-------\n");
                            System.out.println("-------------------------------");
                            for(int i = 0; i < selectedCust.cheAccountsSize(); i++)
                            {
                                System.out.println(selectedCust.getCheAccount(i).getTransactions());
                            }
                            System.out.print("\nCredit Cards---------\n");
                            System.out.println("-------------------------------");
                            for(int i = 0; i < selectedCust.creditCardsSize(); i++)
                            {
                                System.out.println(selectedCust.getCreditCard(i).getTransactions());
                            }
                            break;
                        case 2://Deposit
                            System.out.println("----Deposit----");
                            System.out.println("Please enter the amount that you would like to deposit: ");
                            double deposit = Double.parseDouble(console.readLine());
                            if(deposit < 0)
                            {
                                System.out.println("You cannot deposite a negative value.");
                            }
                            else
                            {
                                System.out.println("Please enter the account that you would like to deposite in: ");
                                System.out.println("1: Savings Accounts");
                                System.out.println("2: Chequing Accounts");
                                choice = Integer.parseInt(console.readLine());
                                
                                if(choice == 1)
                                {
                                    if(selectedCust.savAccountsSize() != 0)
                                    {
                                        System.out.println(selectedCust.getSavAccounts());
                                        System.out.print("Please selected a savings account for the deposit: ");
                                        choice = Integer.parseInt(console.readLine());
                                        if(choice > 0 && choice <= selectedCust.savAccountsSize())
                                        {
                                            selectedCust.getSavAccount(choice-1).deposit(deposit);
                                            System.out.println("You have succesffuly made your deposit!");
                                        }
                                        else
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("You do not have any Savings Accounts");
                                    }
                                }
                                else if(choice == 2)
                                {
                                    if(selectedCust.cheAccountsSize() != 0)
                                    {
                                        System.out.println(selectedCust.getCheAccounts());
                                        System.out.print("Please selected a chequing account for the deposit: ");
                                        choice = Integer.parseInt(console.readLine());
                                        if(choice > 0 && choice <= selectedCust.cheAccountsSize())
                                        {
                                            selectedCust.getCheAccount(choice-1).deposit(deposit);
                                            System.out.println("You have succesffuly made your deposit!");
                                        }
                                        else
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("You do not have any chequing accounts");
                                    }
                                }
                                else
                                {
                                    System.out.println("You did not enter a valid choice.");
                                }
                            }
                            break;
                        case 3://Withdraw
                            System.out.println("----Withdraw----");
                            System.out.println("Please enter the amount that you would like to withdraw: ");
                            double withdraw = Double.parseDouble(console.readLine());
                            if(withdraw < 0)
                            {
                                System.out.println("You cannot withdraw a negative value.");
                            }
                            else
                            {
                                System.out.print("Please enter the account that you would like to withdraw from: ");
                                System.out.println(accountSelectionMenu);
                                choice = Integer.parseInt(console.readLine());
                                
                                if(choice == 1)
                                {
                                    if(selectedCust.savAccountsSize() != 0)
                                    {
                                        System.out.println(selectedCust.getSavAccounts());
                                        System.out.print("Please selected a savings account for the withDraw: ");
                                        choice = Integer.parseInt(console.readLine());
                                        if(choice > 0 && choice <= selectedCust.savAccountsSize())
                                        {
                                            if(selectedCust.getSavAccount(choice-1).getBalance() > withdraw)
                                            {
                                                selectedCust.getSavAccount(choice-1).withdraw(withdraw);
                                                System.out.println("You have succesffuly made your withdraw!");
                                            }
                                            else
                                            {
                                                System.out.println("You do not have enough balance.");
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("You do not have any Savings Accounts");
                                    }
                                }
                                else if(choice == 2)
                                {
                                    if(selectedCust.cheAccountsSize() != 0)
                                    {
                                        System.out.println(selectedCust.getCheAccounts());
                                        System.out.print("Please selected a chequing account for the withdraw: ");
                                        choice = Integer.parseInt(console.readLine());
                                        if(choice > 0 && choice <= selectedCust.cheAccountsSize())
                                        {
                                            if(selectedCust.getCheAccount(choice-1).getBalance() > withdraw)
                                            {
                                                selectedCust.getCheAccount(choice-1).withdraw(withdraw);
                                                System.out.println("You have succesffuly made your withdraw!");
                                            }
                                            else
                                            {
                                                System.out.println("You do not have enough balance.");
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("You do not have any cheqing Accounts");
                                    }
                                }
                                else if(choice == 3)
                                {
                                    if(selectedCust.creditCardsSize() != 0)
                                    {
                                        System.out.println(selectedCust.getCreditCards());
                                        System.out.print("Please selected a credit Card for the withdraw: ");
                                        choice = Integer.parseInt(console.readLine());
                                        if(choice > 0 && choice <= selectedCust.creditCardsSize())
                                        {
                                            selectedCust.getCreditCard(choice-1).withdraw(withdraw);
                                            System.out.println("You have succesffuly made your withdraw!");
                                        }
                                        else
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                    }
                                    else 
                                    {
                                        System.out.println("You do not have any credit cards");
                                    }
                                }
                                else
                                {
                                    System.out.println("You did not enter a valid choice.");
                                }
                            }
                            break;
                        case 4://Process cheque
                            System.out.println("----Process A Cheque----");
                            System.out.println("Please eneter the amount for your cheque: ");
                            double cheque = Double.parseDouble(console.readLine());
                            if(cheque < 0)
                            {
                                System.out.println("You cannot have a negative value.");
                            }
                            else if(selectedCust.cheAccountsSize() == 0)
                            {
                                System.out.println("You do not have a chequing account.");
                            }
                            else
                            {
                                System.out.println(selectedCust.getCheAccounts());
                                System.out.print("Please selected a chequing account for the cheque: ");
                                choice = Integer.parseInt(console.readLine());
                                if(choice > 0 && choice <= selectedCust.cheAccountsSize())
                                {
                                    if(selectedCust.getCheAccount(choice-1).getBalance() < cheque)
                                    {
                                        System.out.println("Your account does not have a sufficient balance.");
                                    }
                                    else
                                    {
                                        selectedCust.getCheAccount(choice-1).drawCheque(cheque);
                                        System.out.println("You have succesffuly made your cheque!");
                                    }
                                }
                                else
                                {
                                    System.out.println("Not a valid choice.");
                                }
                            }
                            break;
                        case 5://Process purchase
                            System.out.println("----Process A Purchase----");
                            System.out.print("Please enter the amount of your purchase: ");
                            double purchase = Double.parseDouble(console.readLine());
                            if(purchase < 0)
                            {
                                System.out.println("You cannot have a negative purchase.");
                            }
                            else if(selectedCust.creditCardsSize() == 0)
                            {
                                System.out.println("You do not have a credit card to perform this purchase.");
                            }
                            else
                            {
                                System.out.println(selectedCust.getCreditCards());
                                System.out.print("Please selected a credit Card for the withdraw: ");
                                choice = Integer.parseInt(console.readLine());
                                if(choice > 0 && choice <= selectedCust.creditCardsSize())
                                {
                                    selectedCust.getCreditCard(choice-1).withdraw(purchase);
                                    System.out.println("You have succesffuly made your withdraw!");
                                }
                                else
                                {
                                    System.out.println("Not a valid choice.");
                                }
                            }
                            break;
                        case 6://Process payment
                            System.out.println("----Process A Payment----");
                            System.out.print("Please enter the amount you would like to pay: ");
                            double payment = Double.parseDouble(console.readLine());
                            if(payment < 0)
                            {
                                System.out.println("You cannot pay a negative value.");
                            }
                            else if(selectedCust.creditCardsSize() == 0)
                            {
                                System.out.println("You do not have any credit cards that you need to pay for.");
                            }
                            else if(selectedCust.savAccountsSize() == 0 && selectedCust.cheAccountsSize() == 0)
                            {
                                System.out.println("You do not have any account to perform this payment.");
                            }
                            else
                            {
                                System.out.print("Please enter the type of account that you would like to pay with: ");
                                System.out.println("1: Savings Accounts");
                                System.out.println("2: Chequing Accounts");
                                choice = Integer.parseInt(console.readLine());
                                
                                if(choice == 1)
                                {
                                    if(selectedCust.savAccountsSize() != 0)
                                    {
                                        System.out.println(selectedCust.getSavAccounts());
                                        System.out.print("Please selected a savings account to pay: ");
                                        int choiceF = Integer.parseInt(console.readLine());
                                        if(choiceF <= 0 && choiceF > selectedCust.savAccountsSize())
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                        else if(selectedCust.getSavAccount(choiceF-1).getBalance() < payment)
                                        {
                                            System.out.println("The balance in this account is not enought to pay for the "
                                            + "amount you entered");
                                        }
                                        else
                                        {
                                            System.out.println(selectedCust.getCreditCards());
                                            System.out.println("Please select the credit card that you are paying for: ");
                                            int choiceT = Integer.parseInt(console.readLine());
                                            if(choiceT > 0 && choiceT <= selectedCust.creditCardsSize())
                                            {
                                                selectedCust.paymentToCred(payment, choice, choiceF, choiceT);
                                                System.out.println("You have succesffuly paid!");
                                            }
                                            else
                                            {
                                                System.out.println("Not a valid choice.");
                                            }
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("You do not have any Savings Accounts");
                                    }
                                }
                                else if(choice == 2)
                                {
                                    if(selectedCust.cheAccountsSize() != 0)
                                    {
                                        System.out.println(selectedCust.getCheAccounts());
                                        System.out.print("Please selected a chequing account to pay with: ");
                                        int choiceF = Integer.parseInt(console.readLine());
                                        if(choiceF <= 0 && choiceF > selectedCust.cheAccountsSize())
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                        else if(selectedCust.getCheAccount(choiceF-1).getBalance() < payment)
                                        {
                                            System.out.println("The balance in this account is not enought to pay for the "
                                            + "amount you entered");
                                        }
                                        else
                                        {
                                            System.out.println(selectedCust.getCreditCards());
                                            System.out.println("Please select the credit card that you are paying for: ");
                                            int choiceT = Integer.parseInt(console.readLine());
                                            if(choiceT > 0 && choiceT <= selectedCust.creditCardsSize())
                                            {
                                                selectedCust.paymentToCred(payment, choice, choiceF, choiceT);
                                                System.out.println("You have succesffuly paid!");
                                            }
                                            else
                                            {
                                                System.out.println("Not a valid choice.");
                                            }
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("You do not have any chequing accounts");
                                    }
                                }
                                else
                                {
                                    System.out.println("You did not enter a valid choice.");
                                }
                            }
                            break;
                        case 7://Transfer funds
                            System.out.println("----Transfer Funds----");
                            System.out.print("Please enter the amount you want to transfer");
                            double transfer = Double.parseDouble(console.readLine());
                            if(transfer < 0)
                            {
                                System.out.println("Transfer cannot be a negative value.");
                            }
                            else
                            {
                                System.out.print("Please enter the type of account that you would like to transfer from: ");
                                System.out.println("1: Savings Accounts");
                                System.out.println("2: Chequing Accounts");
                                int choiceTypeF = Integer.parseInt(console.readLine());
                                if(choiceTypeF == 1)
                                {
                                    System.out.println("----Transfer From Savings----");
                                    System.out.println(selectedCust.getSavAccounts());
                                    System.out.print("Please selected a savings account to pay: ");
                                    int choiceF = Integer.parseInt(console.readLine());
                                    if(choiceF <= 0 && choiceF > selectedCust.savAccountsSize())
                                    {
                                        System.out.println("Not a valid choice.");
                                    }
                                    else if(selectedCust.getSavAccount(choiceF-1).getBalance() < transfer)
                                    {
                                        System.out.println("The balance in this account is not enought to transfer for the "
                                        + "amount you entered");
                                    }
                                    else
                                    {
                                        System.out.print("Please enter the type of account that you would like to transfer to: ");
                                        System.out.println("1: Savings Accounts");
                                        System.out.println("2: Chequing Accounts");
                                        int choiceTypeT = Integer.parseInt(console.readLine());
                                        if(choiceTypeT == 1)
                                        {
                                            System.out.println("----Transfer From Savings to Savings----");
                                            System.out.println(selectedCust.getSavAccounts());
                                            System.out.print("Please selected a savings account for transfer: ");
                                            int choiceT = Integer.parseInt(console.readLine());
                                            if(choiceT <= 0 && choiceT > selectedCust.savAccountsSize())
                                            {
                                                System.out.println("Not a valid choice.");
                                            }
                                            else
                                            {
                                                selectedCust.transferFunds(transfer, choiceTypeF, choiceTypeT, choiceF, choiceT);
                                                System.out.println("Transfer is successful.");
                                            }
                                        }
                                        else if(choiceTypeT == 2)
                                        {
                                            System.out.println("----Transfer From Savings to Chequing----");
                                            System.out.println(selectedCust.getCheAccounts());
                                            System.out.print("Please selected a chequing account for transfer: ");
                                            int choiceT = Integer.parseInt(console.readLine());
                                            if(choiceT <= 0 && choiceT > selectedCust.cheAccountsSize())
                                            {
                                                System.out.println("Not a valid choice.");
                                            }
                                            else
                                            {
                                                selectedCust.transferFunds(transfer, choiceTypeF, choiceTypeT, choiceF, choiceT);
                                                System.out.println("Transfer is successful.");
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                    }
                                }
                                else if(choiceTypeF == 2)
                                {
                                    System.out.println("----Transfer From Chequing---");
                                    System.out.println(selectedCust.getCheAccounts());
                                    System.out.print("Please selected a chequing account to pay: ");
                                    int choiceF = Integer.parseInt(console.readLine());
                                    if(choiceF <= 0 && choiceF > selectedCust.cheAccountsSize())
                                    {
                                        System.out.println("Not a valid choice.");
                                    }
                                    else if(selectedCust.getCheAccount(choiceF-1).getBalance() < transfer)
                                    {
                                        System.out.println("The balance in this account is not enought to transfer for the "
                                        + "amount you entered");
                                    }
                                    else
                                    {
                                        System.out.print("Please enter the type of account that you would like to transfer to: ");
                                        System.out.println("1: Savings Accounts");
                                        System.out.println("2: Chequing Accounts");
                                        int choiceTypeT = Integer.parseInt(console.readLine());
                                        if(choiceTypeT == 1)
                                        {
                                            System.out.println("----Transfer From Chequing to Savings----");
                                            System.out.println(selectedCust.getSavAccounts());
                                            System.out.print("Please selected a savings account for transfer: ");
                                            int choiceT = Integer.parseInt(console.readLine());
                                            if(choiceT <= 0 && choiceT > selectedCust.savAccountsSize())
                                            {
                                                System.out.println("Not a valid choice.");
                                            }
                                            else
                                            {
                                                selectedCust.transferFunds(transfer, choiceTypeF, choiceTypeT, choiceF, choiceT);
                                                System.out.println("Transfer is successful.");
                                            }
                                        }
                                        else if(choiceTypeT == 2)
                                        {
                                            System.out.println("----Transfer From Chequing to Chequing----");
                                            System.out.println(selectedCust.getCheAccounts());
                                            System.out.print("Please selected a chequing account for transfer: ");
                                            int choiceT = Integer.parseInt(console.readLine());
                                            if(choiceT <= 0 && choiceT > selectedCust.cheAccountsSize())
                                            {
                                                System.out.println("Not a valid choice.");
                                            }
                                            else
                                            {
                                                selectedCust.transferFunds(transfer, choiceTypeF, choiceTypeT, choiceF, choiceT);
                                                System.out.println("Transfer is successful.");
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("Not a valid choice.");
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("Not a valide choice.");
                                }
                            }
                            break;
                        case 8://Open account or issue card
                            System.out.println("----Create An Account----");
                            System.out.println(accountSelectionMenu);
                            System.out.print("Please select the type of account you would like to create: ");
                            choice = Integer.parseInt(console.readLine());
                            if(choice == 1)
                            {
                                System.out.println("----Create A Savings Account----");
                                System.out.print("Please enter the initial balance: ");
                                balance = Double.parseDouble(console.readLine());
                                if(balance < 0)
                                {
                                    System.out.println("You cannot have a negative balance for a chequing account.");
                                }
                                else
                                {
                                    selectedCust.addAccount(balance, choice);
                                    System.out.println("You account has ben succesffully created.");
                                }
                            }
                            else if(choice == 2)
                            {
                                System.out.println("----Create A Chequing Account----");
                                boolean isEighteen = bank.checkIsEighteen(selectedCust.getBirthYear(), selectedCust.getBirthMonth(), 
                                selectedCust.getBirthDay());
                                if(isEighteen == false)
                                {
                                    System.out.println("You cannot create a chequing account if you are under 18.");
                                }
                                else
                                {
                                    System.out.print("Please enter the initial balance: ");
                                    balance = Double.parseDouble(console.readLine());
                                    if(balance < 0)
                                    {
                                        System.out.println("You cannot have a negative balance for a chequing account.");
                                    }
                                    else
                                    {
                                        selectedCust.addAccount(balance, choice);
                                        System.out.println("You account has ben succesffully created.");
                                    }
                                }
                            }
                            else if (choice == 3)
                            {
                                System.out.println("----Create A Credit Card----");
                                boolean isEighteen = bank.checkIsEighteen(selectedCust.getBirthYear(), selectedCust.getBirthMonth(), 
                                selectedCust.getBirthDay());
                                if(!isEighteen)
                                {
                                    System.out.println("You cannot create a chequing account if you are under 18.");
                                }
                                else
                                {
                                    System.out.print("Please enter the initial balance: ");
                                    balance = Double.parseDouble(console.readLine());
                                    selectedCust.addAccount(balance, choice);
                                    System.out.println("You account has ben succesffully created.");
                                }
                            }
                            else
                            {
                                System.out.println("Not a valid choice.");
                            }
                            break;
                        case 9://cancel Account.
                            System.out.println("----Cancel An Account or A Credit Card----");
                            System.out.println(accountSelectionMenu);
                            System.out.print("Please select the type of account that you would like to cancel: ");
                            choice = Integer.parseInt(console.readLine());
                            if(choice == 1 && selectedCust.savAccountsSize() != 0)
                            {
                                System.out.println("-----Delete A Savings Account-----");
                                System.out.println(selectedCust.getSavAccounts());
                                System.out.print("Please selected a savings account you would like to delete: ");
                                int choiceT = Integer.parseInt(console.readLine());
                                if(choiceT > 0 && choiceT <= selectedCust.savAccountsSize())
                                {
                                    selectedCust.deleteAccount(choice, choiceT);
                                    System.out.println("You have succesffuly deleted this account.");
                                }
                                else
                                {
                                    System.out.println("Not a valid choice.");
                                }
                            }
                            else if(choice == 2 && selectedCust.cheAccountsSize() != 0)
                            {
                                System.out.println("-----Delete A Chequing Account-----");
                                System.out.println(selectedCust.getCheAccounts());
                                System.out.print("Please selected a chequing account you would like to delete: ");
                                int choiceT = Integer.parseInt(console.readLine());
                                if(choiceT > 0 && choiceT <= selectedCust.cheAccountsSize())
                                {
                                    selectedCust.deleteAccount(choice, choiceT);
                                    System.out.println("You have succesffuly deleted this account.");
                                }
                                else
                                {
                                    System.out.println("Not a valid choice.");
                                }
                            }
                            else if(choice == 3 && selectedCust.creditCardsSize() != 0)
                            {
                                System.out.println("-----Delete A Credit Card-----");
                                System.out.println(selectedCust.getCreditCards());
                                System.out.print("Please selected a credit card you would like to delete: ");
                                int choiceT = Integer.parseInt(console.readLine());
                                if(choiceT > 0 && choiceT <= selectedCust.creditCardsSize())
                                {
                                    selectedCust.deleteAccount(choice, choiceT);
                                    System.out.println("You have succesffuly deleted this account.");
                                }
                                else
                                {
                                    System.out.println("Not a valid choice.");
                                }
                            }
                            else
                            {
                            System.out.println("There is no accounts under the choice you entered.");
                            }
                            if(selectedCust.savAccountsSize() == 0 && selectedCust.cheAccountsSize() == 0 && selectedCust.creditCardsSize() == 0)
                            {
                                bank.deleteCustomerBySin(selectedCust.getSin());
                                System.out.println("Since you have no accounts left, you are deleted from this bank.");
                            }
                            break;
                    }
                    System.out.println(customerProfileMenu);
                    choiceCust = Integer.parseInt(console.readLine());
                }
            }
            catch(NumberFormatException e)
            {
                System.out.print("Please enter an integer. You are back at main menu.");
                selection = INITIAL_VALUE;
            }
        }while(selection != 8);
    }
    
    /*
     * Accessors
     */
    /**
     * Checks if any customer has the same name as the customer being searched and returns 
     * an integer value indicating the number of customer with the same name.
     * 
     * @param lastN customer's last name
     * @param firstN customer's first name
     */
    public int checkSameName(String lastN, String firstN)
    {
        int counter = 0;
        for(int i = 0; i < customers.size(); i++)
        {
            Customer cust = customers.get(i);
            if(cust.getLastName().equals(lastN) && cust.getFirstName().equals(firstN))
            {
                counter = counter + 1;
            }
        }
        return counter;
    }//end checkSameName(String lastN, String firstN)
    
    /**
     * Display a summary of all customers with name and SIN number.
     * 
     * @return a auumary of all customers with name and SIN number
     */
    public String displaySummary()
    {
        String output = "---------Summary--------\n";
        for(int i = 0; i < customers.size(); i++)
        {
            output = output + "Name: " + (customers.get(i)).getLastName() + ", " + (customers.get(i)).getFirstName() + "\n" +
            "SIN Number: " + (customers.get(i)).getSin() + "\n";
        }
        return output;
    }//end displaySummary()
    
    /**
     * Return a Customer with the input index from the customers array list
     * 
     * @return Customer with the input index from the customers array list
     */
    public Customer getCustomerByIndex(int index)
    {
        return customers.get(index);
    }//end getCustomerByIndex(int index)
    
    /**
     * Returns the a selected Customer from customers with the same name selected by an integer selection.
     * 
     * @param lastN Customer's last name
     * @param firstN Customer's first name
     * @param choice selection made by the user
     */
    public Customer getCustomerByName(String lastN, String firstN, int choice)
    {
        ArrayList<Customer> foundCusts = new ArrayList<Customer>();
        for(int i = 0; i < customers.size(); i++)
        {
            Customer cust = customers.get(i);
            if(cust.getLastName().equals(lastN))
            {
                if(cust.getFirstName().equals(firstN))
                {
                    foundCusts.add(customers.get(i));
                }
            }
        }
        return foundCusts.get(choice-1);
    }//end getCustomerByName(String lastN, String firstN, int choice)
    
    /**
     * Find a customer's profile by SIN number. 
     * 
     * @param sin customer's SIN number
     * @return a customer with the SIN number entered
     */
    public Customer getCustomerBySin(int sin)
    {
        Customer cust = null;
        for(int i = 0; i < customers.size(); i++)
        {
            if(customers.get(i).getSin() == sin)
            {
                cust = customers.get(i);
            }
        }
        return cust;
    }//end findCustomerBySin(int Sin)
    
    /**
     * Returns a customer's profile by last name, first name.
     * 
     * @param lastName customer's last name
     * @param firstName customer's first name
     */
    public String getCustomerByNameString(String lastName, String firstName)
    {
        ArrayList<Customer> sameNameCusts = new ArrayList<Customer>();
        for(int i = 0; i < customers.size(); i++)
        {
            Customer cust = customers.get(i);
            if(cust.getLastName().equals(lastName))
            {
                if(cust.getFirstName().equals(firstName))
                {
                    sameNameCusts.add(customers.get(i));
                }
            }
        }
        String output = "----Customers Under This Name-----\n";
        for(int j = 0; j < sameNameCusts.size(); j++)
        {
            output = output + (j+1) + ": " + sameNameCusts.get(j).getLastName() + ", " + sameNameCusts.get(j).getFirstName()+ "\n"
            + "SIN Number: " + sameNameCusts.get(j).getSin() + "\n";
        }
        return output;
    }// end findCustomerByName(String lastname, String firstName)
    
    /**
     * Returns the number of customers in a bank.
     * 
     * @return numer of customers in a bank
     */
    public int numberOfCustomers()
    {
        return customers.size();
    }
    
    /*
     * Mutators
     */
    /**
     * Adds a customer with an appropiate name, SIN number,
     * bithday, a initial account and its balance. 
     * 
     * @param lastName customer's last name
     * @param firstName customer's first name
     * @param sin customer's SIN number
     * @param year the year that the customer was born in
     * @param month the month that the customer was born in
     * @param day the day that the customer was born in
     * @param account an integer choice of the type of account that the customer is creating
     * @param balance the balance for that account
     */
    public void addCustomer(String lastName, String firstName, int sin,
    int year, int month, int day, int account, double balance)
    {
        customers.add(new Customer(lastName, firstName, sin, year, month, day, account, balance));
    }// end addCustomer(String name, int sin, int month, int day, int year, String account)
    
    /**
     * Adds a customer from file. The customers will have an appropiate name, SIN number,
     * bithday. 
     * 
     * @param lastName customer's last name
     * @param firstName customer's first name
     * @param sin customer's SIN number
     * @param year the year that the customer was born in
     * @param month the month that the customer was born in
     * @param day the day that the customer was born in
     */
    public void addCustomerFile(String lastName, String firstName, int sin,
    int year, int month, int day)
    {
        customers.add(new Customer(lastName, firstName, sin, year, month, day));
    }// end addCustomer(String name, int sin, int month, int day, int year, String account)
    
    /**
     * Checks if the date entered by the user is valid
     * 
     * @param year the year that the customer was born in
     * @param month the month that the customer was born in
     * @param day the day that the customer was born in
     * @return a boolean expression to indicate whether the date entered is valid)
     */
    public boolean checkDate(int year, int month, int day)
    {
        boolean isValid;
        int maxDayThatMonth;
        switch(month) 
            {
                case 1:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 2:
                    maxDayThatMonth = MAX_DAY_TWENTY_NINE;
                    break;
                case 3:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 4:
                    maxDayThatMonth = MAX_DAY_THIRTY;
                    break;
                case 5:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 6:
                    maxDayThatMonth = MAX_DAY_THIRTY;
                    break;
                case 7:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 8:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 9:
                    maxDayThatMonth = MAX_DAY_THIRTY;
                    break;
                case 10:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                case 11:
                    maxDayThatMonth = MAX_DAY_THIRTY;
                    break;
                case 12:
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
                    break;
                default: 
                    maxDayThatMonth = MAX_DAY_THIRTY_ONE;
            }
        if(year > 0 && year <= MAX_YEAR && month > 0 && month <= MAX_MONTH && day > 0 && day <= maxDayThatMonth)
        {
            isValid = true;
        }
        else
        {
            isValid = false;
        }
        return isValid;
    }//end checkDate(int year, int month, int day)
    
    /**
     * Returns a boolean expression to indicate whether a customer is over 18.
     * 
     * @param year the year that the customer was born in
     * @param month the month that the customer was born in
     * @param day the day that the customer was born in
     * @return boolean expression to indicate whether customer is over 18.
     */
    public boolean checkIsEighteen(int year, int month, int day)
    {
        boolean isEighteen;
        cal = Calendar.getInstance();
        int calYear = cal.get(Calendar.YEAR);
        int calMonth = cal.get(Calendar.MONTH) + 1;
        int calDay = cal.get(Calendar.DAY_OF_MONTH);
        if(year > 0 && (year + OVER_EIGHTEEN) < calYear)
        {
            isEighteen = true;
        }
        else if(year > 0 && (year + OVER_EIGHTEEN) == calYear && month > calMonth) 
        {
            isEighteen = true;
        }
        else if(year > 0 && (year + OVER_EIGHTEEN) == calYear && month == calMonth && day >= calDay)
        {
            isEighteen = true;
        }
        else
        {
            isEighteen = false;
        }
        return isEighteen;
    }
    
    /**
     * Checks if the SIN number entered is a valid SIN Number
     * 
     * @return a boolean expression stating whether the SIN number is valid. 
     */
    public boolean checkSin(int sinN)
    {
        boolean isValid;
        int length = String.valueOf(sinN).length();
        int counter = 0;
        if(length != DIGITS_OF_SIN)
        {
            isValid = false;
        }
        else
        {
            for(int i = 0; i < customers.size(); i++)
            {
                Customer cust = customers.get(i);
                if(cust.getSin() == sinN)
                {
                    counter = counter + 1;
                }
            }
            if(counter == 0)
            {
                isValid = true;
            }
            else
            {
                isValid = false;
            }
        }
        return isValid;
    }//end checkSin(int sinN)
    
    /**
     * Deletes a customer by last name, first name who has the same name as another customer.
     * 
     * @param cust the customer which need to be removed
     */
    public void deleteCustomer(Customer cust)
    {
        customers.remove(cust);
    }//end of deleteCustomerByName(String name)
    
    /**
     * Deletes a customer by last name, first name.
     * 
     * @param lastName customer's last name
     * @param firstName customer's first name
     */
    public void deleteCustomerByName(String lastName, String firstName)
    {
        for(int i = 0; i < customers.size(); i++)
        {
            Customer cust = customers.get(i);
            if((cust.getLastName()).equals(lastName))
            {   if((cust.getFirstName()).equals(firstName))
                {
                    customers.remove(i);
                }
            }
        }   
    }//end of deleteCustomerByName(String name)
    
    /**
     * deletes a customer by SIN number
     * 
     * @param sin customer's SIN number
     */
    public void deleteCustomerBySin(int sin)
    {
        for(int j = 0; j < customers.size(); j++)
        {
            if(((customers.get(j)).getSin()) == (sin))
            {
                customers.remove(j);
            }
        }
    }//end of deleteCustomerBySin(int sin)
    
    /**
     * Sort Customer alphabetically by last name, first name
     * 
     * @return a string list of a sorted list of customers.
     */
    public String sortCustomersByName()
    {
        ArrayList<Customer> sortedList = new ArrayList<Customer>();
        ArrayList<Customer> unsortedList = new ArrayList<>();
        for (int k = 0; k < customers.size(); k++)
        {
            unsortedList.add(customers.get(k));
        }
        
        for(int j = 0; j < customers.size(); j++)
        {
            Customer headByName = unsortedList.get(0);
            for(int i = 0; i < unsortedList.size(); i++)
            {
                Customer cust = unsortedList.get(i);
                if(cust.getLastName().compareToIgnoreCase(headByName.getLastName()) < 0)
                {
                    headByName = cust;
                }
                else if(cust.getLastName().compareToIgnoreCase(headByName.getLastName()) == 0)
                {
                    if(cust.getFirstName().compareToIgnoreCase(headByName.getFirstName()) < 0)
                    {
                        headByName = cust;
                    }
                    else if(cust.getFirstName().compareToIgnoreCase(headByName.getFirstName()) == 0)
                    {
                        if(cust.getSin() < headByName.getSin())
                        {
                            headByName = cust;
                        }
                    }
                }
            }
            sortedList.add(headByName);
            unsortedList.remove(headByName);
        }
        String output = "------Sorted List By Name-----\n";
        for(int k = 0; k < sortedList.size(); k++)
        {
            output = output + "Name: " + (sortedList.get(k)).getLastName() + ", " + (sortedList.get(k)).getFirstName() + "\n" +
            "SIN Number: " + (sortedList.get(k)).getSin() + "\n";
        }
        return output;
    }//end sortCustomersByName()

    /**
     * Sort customers by SIN number, from the smalles integer to the largest
     * 
     * @return a string list of a sorted list of customers.
     */
    public String sortCustomersBySin()
    {
        ArrayList<Customer> sortedList = new ArrayList<Customer>();
        ArrayList<Customer> unsortedList = new ArrayList<>();
        for (int k = 0; k < customers.size(); k++)
        {
            unsortedList.add(customers.get(k));
        }
        
        for(int j = 0; j < customers.size(); j++)
        {
            Customer headByName = unsortedList.get(0);
            for(int i = 0; i < unsortedList.size(); i++)
            {
                Customer cust = unsortedList.get(i);
                if(cust.getSin() < headByName.getSin())
                {
                    headByName = cust;
                }
            }
            sortedList.add(headByName);
            unsortedList.remove(headByName);
        }
        String output = "------Sorted List By SIN Number-----\n";
        for(int k = 0; k < sortedList.size(); k++)
        {
            output = output + "Name: " + (sortedList.get(k)).getLastName() + ", " + (sortedList.get(k)).getFirstName() + "\n" +
            "SIN Number: " + (sortedList.get(k)).getSin() + "\n";
        }
        return output;
    }//end sortCustomersBySIN()
}
