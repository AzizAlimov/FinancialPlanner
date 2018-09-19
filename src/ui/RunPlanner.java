package ui;
import java.util.Scanner;
import java.util.List;

public class RunPlanner {
    public static void main(String[] args) {
        Groceries();
    }

    public static MonthlyExpenses Groceries(){
        System.out.println("Welcome to FinancialPlanner! Who is the user today?");
        Scanner s;
        s = new Scanner(System.in);
        String name = s.nextLine();

        Date n = new Date(9, 2018);
        MonthlyExpenses me = new MonthlyExpenses(n, 1200);
        while (true) {
            Introduction(name);
            Expenses e = new Expenses(name);
            String x = s.nextLine();
            if (x.equals("A") || x.equals("A)") || x.equals("A.")) {
                while (x.equals("A")||x.equals("A)")||x.equals("A.")||x.equals("y") || x.equals("yes") || x.equals("Yes")) {
                    System.out.println("Please input the name then the price of this month's expenses.");
                    String z = s.nextLine();
                    int y = s.nextInt();
                    s.nextLine();
                    Item m = new Item(z, y);
                    me.additem(m);
                    System.out.println("Added " + z + "!");
                    System.out.println("Add another expense?");
                    x = s.nextLine();
                }
            } else if (x.equals("B") || x.equals("b") || x.equals("B.")) {
                for (Item temp: me.getListOfExpenses()) {
                    System.out.println(temp.getName() + ": " + temp.getPrice() + "$");
                }
                System.out.println("Total: " + me.gettotalexpenses() + "$");
            } else if (x.equals("C") || x.equals("c") || x.equals("C.")) {
                System.out.println("What is your budget?");
                int budget = s.nextInt();
                s.nextLine();
                Date n2 = n.nextMonth();
                me = new MonthlyExpenses(n2, budget);
            }
            System.out.println("Anything else?");
            x = s.nextLine();
            if (x.equals("N") || x.equals("n") || x.equals("No") || x.equals("no")) {
                break;
            }
        }


        if (me.getbudget() >= me.gettotalexpenses()){
            System.out.println("Still on budget!");
        } else {
            System.out.println("Over budget.");
        }
        return me;
    }

    private static void Introduction(String n) {
        System.out.println("Hello " + n + "! Welcome to FinancialPlanner! What would you like to do?");
        System.out.println("A) Add an expense.");
        System.out.println("B) See this month's expenses.");
        System.out.println("C) Plan for the next month.");
    }
}
