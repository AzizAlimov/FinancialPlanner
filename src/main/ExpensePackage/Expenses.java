package main.ExpensePackage;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Expenses {
    private List<MonthlyExpenses> TotalExpenses;
    private String Person;
    private int Surplus;

    public void addSurplus(int i) {

    }

    // MODIFIES: this
    // EFFECTS: Makes a new Expenses with the name of the person to whom it belongs
    public Expenses(String Person) {
        this.Person = Person;
        TotalExpenses = new LinkedList<>();
    }

    // MODIFIES: The list TotalExpenses
    // EFFECTS: Adds a monthly expense to an Expenses
    public void addmonth(MonthlyExpenses me) {
        TotalExpenses.add(0, me);
        me.setExpenses(this);
    }

    // EFFECTS: Returns the name of the person
    public String getPerson() {
        return Person;
    }

    // EFFECTS: Returns the list of  monthly expenses
    public List getmonthlyexpenses() {
        return TotalExpenses;
    }

    // REQUIRES: MonthlyExpenses are in chronological order
    // EFFECTS: Returns the monthly expense at the given date
    public MonthlyExpenses getmonthlyexpensewdate(Date date) {
        Date x = (TotalExpenses.get(0)).getdate();
        int y = this.monthsbefore(x, date);
        return TotalExpenses.get(y);
    }

    // EFFECTS: Returns the number of months the second date is before the first date
    private int monthsbefore(Date date1, Date date2) {
        int x = date1.getYear() - date2.getYear();
        x = x * 12 + (date1.getMonth() - date2.getMonth());
        return x;
    }

    // EFFECTS: Prints out the list of options that the user can do
    public void Options() {
        System.out.println("What would you like to do now?");
        System.out.println("A) Add an expense to this month.");
        System.out.println("B) See this month's expenses.");
        System.out.println("C) Plan for the next month.");
        System.out.println("D) Change an existing plan.");
        System.out.println("E) End Planner.");
    }

    // EFFECTS: Returns the monthly expense at a given index value
    public MonthlyExpenses getmonthlyexpense(int n) {
        return TotalExpenses.get(n);
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at a user inputted date
    public void setupdate(String name, Scanner s) {
        System.out.println("Hi " + name + "! Let's set up your financial plan.");
        System.out.println("What date would you like to setup your financial plan for?");
        System.out.println("Year: ");
        int year = s.nextInt();
        s.nextLine();
        System.out.println("Month (Please give the number of the month): ");
        int month = s.nextInt();
        s.nextLine();
        Date firstdate = new Date(month, year);
        setup2(name, s, firstdate);
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at the month following the latest monthly expense
    public void setupnextmonth(String name, Scanner s, Date firstdate) {
        setup2(name, s, firstdate.nextMonth());
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at the user inputted date
    private void setup2(String name, Scanner s, Date firstdate) {

        System.out.println("What is your budget for " + firstdate.getMonth() +
                " " + firstdate.getYear() + "?");
        int budget = s.nextInt();
        s.nextLine();
        MonthlyExpenses me = new MonthlyExpenses(firstdate, budget);
        addmonth(me);
        System.out.println("What categories would you like to add to your monthly budget?");
        while (me.getbudget() > me.getTotalcategorybudgets()) {
            try {
                System.out.println("Name: ");
                name = s.nextLine();
                System.out.println("Budget: ");
                budget = s.nextInt();
                s.nextLine();
                Category c = new Category(name, budget);
                me.addcategory(c);
                if (me.getTotalcategorybudgets() > me.getbudget()) {
                    throw new NullPointerException("Exceeded monthly budget");
                }
            } catch (NullPointerException ne) {
                me.removecategory(0);
                System.out.println("Exceeded monthly budget. Please try again while staying within the budget.");
            }
            System.out.println("Remaining budget: " + (me.getbudget() - me.getTotalcategorybudgets()));
        }
    }

    // MODIFIES: this
    // EFFECTS: Main user interaction method, asks the user what they would like to do
    public void mainloop(Scanner s, String name) {
        while (true) {
            Options();
            String x = s.nextLine();
            if (x.equals("A") || x.equals("A)") || x.equals("A.") || x.equals("a")) {
                while (x.equals("A") || x.equals("A)") || x.equals("A.") || x.equals("y")
                        || x.equals("yes") || x.equals("Yes")) {
                    System.out.println("What category does your expense belong to?");
                    (getmonthlyexpense(0)).printcategories();
                    String z = s.nextLine();
                    Category c = (getmonthlyexpense(0)).getcategorywname(z);
                    System.out.println("Please input the name followed by the price of your expense.");
                    z = s.nextLine();
                    int y = s.nextInt();
                    s.nextLine();
                    Item m = new Item(z, y);
                    c.addItem(m);
                    System.out.println("Added " + z + "! " + (c.getbudget() - c.getexpenses()) +
                            "$ is remaining in the " + c.getName() + " category.");
                    System.out.println("Add another expense?");
                    x = s.nextLine();
                }
            } else if (x.equals("B") || x.equals("b") || x.equals("B.")) {
                (getmonthlyexpense(0)).printexpenses();
            } else if (x.equals("C") || x.equals("c") || x.equals("C.")) {
                setupnextmonth(name, s, getmonthlyexpense(0).getdate());
            } else if (x.equals("D") || x.equals("d") || x.equals("D)") || x.equals("D.")) {

            }
            System.out.println("Anything else?");
            x = s.nextLine();
            if (x.equals("N") || x.equals("n") || x.equals("No") || x.equals("no")) {
                break;
            }
        }
    }
}
