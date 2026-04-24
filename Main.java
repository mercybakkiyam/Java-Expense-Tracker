import java.util.ArrayList;
import java.util.Scanner;

// class for single expense
class Expense{
    static int idcounter = 1; /*this variable is shared by all the objects created from this class*/
    int id;
    private float amount;
    String category;
    String date;
    String description;
    Expense(float amount,String category,String date,String description){
        this.id = idcounter;/*this initializes the id to each object */
        idcounter +=1; /*and then increments to 1 to uniquely assign id for each object that is created further*/
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }
    float getAmount(){
        return amount;
    }
    void displayExpense(){
        System.out.print("\nid :"+ id + " amount: "+ amount+ " Category: "+ category+ " Date: "+ date+ " description: "+description);
    }
}

//class to manage multiple expenses
class ExpenseManager{
    private ArrayList<Expense> expenses;
    float limit;
    ExpenseManager(ArrayList<Expense> expenses, float limit){
        this.expenses = expenses;
        this.limit = limit;
    }
    //method to add a new expense
    void addExpense(Expense expense){
        expenses.add(expense);
        System.out.println("Expense is successfully added");
        checkLimit();/*after adding individual expense invoking this method to check whether it exceeds the limit*/
    }
    void checkLimit(){
        float total = 0;
        for(Expense exp: expenses){
            total += exp.getAmount(); /*adding all the expense amount in the total variable*/
        }
        //checks whether total exceeds the limit that is specified by the user
        if(total > limit){
            System.out.println("You have exceeded your limit");
            System.out.println("Your limit is "+limit+ " but your total amount in your expense list until now is: "+ total);
        }
    }
    void removeExpense(int id){
        for(int i = 0; i< expenses.size();i++){
            if(expenses.get(i).id == id){
                expenses.remove(i);
                System.out.println("The expense is successfully removd");
                return;
            }
        }
        System.out.println("Id not found");
    }
    void displayAllExpenses(){
        for(int i = 0;i<expenses.size();i++){
            expenses.get(i).displayExpense();
        }
    }
}
class Main{
    public static void main(String args[]){
        /*creating object initial_expense of type Expense to store the initial values*/
        ArrayList<Expense> initial_expenses = new ArrayList<>();
       
        /*creating 2 initial expenses*/
        Expense E1 = new Expense(500,"Food","02-02-2025","Lunch with Friends at Buhari hotel");
        Expense E2 = new Expense(1000,"dress","21-02-2025","Purchases a top at trends");
        initial_expenses.add(E1);
        initial_expenses.add(E2);
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the limit to keep an eye on your expenses(only digits): ");
        float limit = input.nextFloat();
        ExpenseManager expensemanager = new ExpenseManager(initial_expenses,limit);
        while (true){
        System.out.println("\nEXPENSE TRACKER MENU");
        System.out.println("1. to ADD an expense ");
        System.out.println("2. to REMOVE an expense ");
        System.out.println("3. to DISPLAY all the expemses");
        System.out.println("4 to EXIT the application");
        System.out.print("Please enter your choice: ");
        try{
        int choice = input.nextInt();
       
        switch(choice){
            case 1:
                System.out.println("Enter the amount of your expsnse(eg.200): ");
                float amount = input.nextFloat();
                System.out.println("Enter the category(eg.food): ");
                String category = input.next();
                System.out.println("ENter the date(dd-mm-yyyy): ");
                String date = input.next();
                System.out.println("Enter the description about your expense");
                input.nextLine();/*prevents from infinite loop*/
                String description = input.nextLine();
                Expense E = new Expense(amount,category,date,description);
                expensemanager.addExpense(E);
                break;
           
            case 2:
                System.out.println("you need to know the expense id to remove an expense");
                System.out.print("Do you Know? (1 for yes, 2 for no)");
                int response = input.nextInt();
                if(response == 1){
                    System.out.println("Great!! Please entter the id");
                    int id = input.nextInt();
                    expensemanager.removeExpense(id);
                }
                else{
                    /*if user doesn't know the id , displaying the expenses with id, so that they get to know the id*/
                    System.out.println("The expenses are displayed for you to know the id");
                    expensemanager.displayAllExpenses();
                    System.out.println("Hope you know the id now.");
                    System.out.println("Please enter the id");
                    int id = input.nextInt();
                    expensemanager.removeExpense(id);
                }
                break;
            case 3:
                expensemanager.displayAllExpenses();
 
              break;
            case 4:
                System.out.println("Thank you for using this Expense Tracker Application");
                return;
               
        }
        }
        catch(Exception e){
            System.out.println("Please Enter a valid input");
            input.nextLine(); /*clears the input prevents the infinite loop*/
           
        }        
       
        }  
    }
}
