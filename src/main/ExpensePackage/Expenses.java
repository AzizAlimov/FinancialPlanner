package main.ExpensePackage;

import main.ExpensePackage.CategoryPackage.Category;
import main.ExpensePackage.CategoryPackage.EssentialCategory;
import main.ExpensePackage.CategoryPackage.LuxuryCategory;
import main.ExpensePackage.Exceptions.InvalidDateException;
import main.ExpensePackage.Exceptions.OutOfMoneyException;
import main.ExpensePackage.ItemPackage.EssentialItem;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.ItemPackage.LuxuryItem;
import main.ui.Printer;
import main.ui.SavingClass;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Expenses {
    private List<MonthlyExpenses> TotalExpenses;
    private List<Item> lri;
    private String Person;
    private int totalbudgetsexpenses;
    //private int Surplus;

    public List<MonthlyExpenses> getTotalExpenses() { return TotalExpenses; }

    // MODIFIES: this
    // EFFECTS: Makes a new Expenses with the name of the person to whom it belongs
    public Expenses(String Person) {
        this.Person = Person;
        TotalExpenses = new LinkedList<>();
        lri = new LinkedList<>();
    }

    public void addExpense(int i) {
        this.totalbudgetsexpenses += i;
    }

    // MODIFIES: The list TotalExpenses
    // EFFECTS: Adds a monthly expense to an Expenses
    public void addmonth(MonthlyExpenses me) {
        if (!TotalExpenses.contains(me)) {
            TotalExpenses.add(0, me);
            me.setExpenses(this);
        }
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
        Printer.print("What would you like to do now?");
        Printer.print("A) Add an expense to this month.");
        Printer.print("B) See this month's expenses.");
        Printer.print("C) Plan for the next month.");
        Printer.print("D) Change an existing plan.");
        Printer.print("E) End Planner and save.");
        Printer.print("F) End Planner without saving");
        Printer.print("G) This month's rent");
    }

    // EFFECTS: Returns the monthly expense at a given index value
    public MonthlyExpenses getmonthlyexpense(int n) {
        return TotalExpenses.get(n);
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at a user inputted date
    public void setupdate(String name) throws InputMismatchException, InvalidDateException {
        Scanner s = new Scanner(System.in);
        Printer.print("Hi " + name + "! Let's set up your financial plan.");
        Printer.print("What date would you like to setup your financial plan for?");
        Printer.print("Year: ");
        int year = s.nextInt();
        s.nextLine();
        Printer.print("Month (Please give the number of the month): ");
        int month = s.nextInt();
        s.nextLine();
        Date firstdate = new Date(month, year);
        setup2(name, s, firstdate);
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at the month following the latest monthly expense
    public void setupnextmonth(String name, Scanner s, Date firstdate) throws InputMismatchException, InvalidDateException {
        setup2(name, s, firstdate.nextMonth());
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at the user inputted date
    private void setup2(String name, Scanner s, Date firstdate) throws InputMismatchException {

        Printer.print("What is your budget for " + firstdate.getMonth() +
                " " + firstdate.getYear() + "?");
        int budget = s.nextInt();
        s.nextLine();
        MonthlyExpenses me = new MonthlyExpenses(firstdate, budget);
        addmonth(me);
        Printer.print("What categories would you like to add to this month?");
        while (me.getbudget() > me.getTotalCategoryBudgets()) {
            try {
                Printer.print("Name: ");
                name = s.nextLine();
                Printer.print("Budget: ");
                budget = s.nextInt();
                s.nextLine();
                Printer.print("Is this category essential?");
                String ec = s.nextLine();
                if (ec.equals("yes")) {
                    Category c = new EssentialCategory(name, budget);
                    me.addCategory(c);
                } else {
                    Category c = new LuxuryCategory(name, budget);
                    me.addCategory(c);
                }
            } catch (OutOfMoneyException ne) {
                Printer.print("Exceeded monthly budget. Please try again while staying within the budget.");
            }
            Printer.print("Remaining total budget: " + (me.getbudget() - me.getTotalCategoryBudgets()) + "$");
        }
    }

    // MODIFIES: this
    // EFFECTS: Main user interaction method, asks the user what they would like to do
    public void mainloop(String name) throws InputMismatchException {
        Scanner s = new Scanner(System.in);
        while (true) {
            Options();
            String x = s.nextLine();
            if (x.equals("A") || x.equals("A)") || x.equals("A.") || x.equals("a")) {
                while (x.equals("A") || x.equals("A)") || x.equals("A.") || x.equals("y")
                        || x.equals("yes") || x.equals("Yes")) {
                    Printer.print("What category does your expense belong to?");
                    (getmonthlyexpense(0)).printcategories();
                    String z = s.nextLine();

                    Category c = getmonthlyexpense(0).getCategory(z);
                    try {
                    if (!c.isEssentialCategory()) {
                        Printer.print("Please input the name followed by the price of your expense.");
                        z = s.nextLine();
                        int y = s.nextInt();
                        s.nextLine();
                        Item m = new LuxuryItem(z, y);
                        c.addItem(m);
                    } else {
                            Printer.print("Is your item essential?.");
                            z = s.nextLine();
                            Printer.print("Please input the name followed by the price of your item.");
                            String o = s.nextLine();
                            int y = s.nextInt();
                            s.nextLine();
                            if (z.equals("yes")) {
                                Item m = new EssentialItem(o, y);
                                c.addItem(m);
                            } else {
                                Item m = new LuxuryItem(o, y);
                                c.addItem(m);
                            }
                        }
                        Printer.print("Added " + z + "! " + (c.getbudget() - c.getexpenses()) +
                                "$ is remaining in the " + c.getName() + " category.");
                    } catch (OutOfMoneyException m) {
                        Printer.print("There is not enough money in the category to add this item.");
                    } finally {
                        Printer.print("Add another expense?");
                        x = s.nextLine();
                    }
                }
            } else if (x.equals("B") || x.equals("b") || x.equals("B.")) {
                (getmonthlyexpense(0)).printExpenses();
            } else if (x.equals("C") || x.equals("c") || x.equals("C.")) {
                try {
                    setupnextmonth(name, s, getmonthlyexpense(0).getdate());
                } catch (InvalidDateException i) {
                    Printer.print("Something went wrong with setting up the next month." +
                            " There was an error with the date");
                }
            } else if (x.equals("D") || x.equals("d") || x.equals("D)") || x.equals("D.")) {

            } else if (x.equals("E") || x.equals("e")) {
                try {
                    SavingClass sc = new SavingClass(this);
                    sc.save();
                } catch (FileNotFoundException f) {
                    Printer.print("Something went wrong in saving. Your files were either deleted or renamed." +
                            " FinancialPlanner will now close.");
                } catch (UnsupportedEncodingException u) {
                    Printer.print("The specific encoding was not supported. FinancialPlanner will now close.");
                } finally {
                    System.exit(0);
                }
            } else if (x.equals("F") || x.equals("f")) {
                System.exit(0);
            } else if (x.equals("G") || x.equals("g")) {
                BufferedReader br = null;
                try {
                    String theURL = "https://finance.yahoo.com/quote/A?p=A"; //this can point to any URL
                    URL url = new URL(theURL);
                    br = new BufferedReader(new InputStreamReader(url.openStream()));

                    String line;

                    StringBuilder sb = new StringBuilder();

                    while ((line = br.readLine()) != null) {

                        sb.append(line);
                        sb.append(System.lineSeparator());
                    }

//                    Printer.print(sb);
                } catch (MalformedURLException m) {
                    Printer.print("Something went wrong");
                } catch (IOException i) {
                    Printer.print("Something went wrong");
            }finally {
                    try {
                        if (br != null) {
                            br.close();
                        }
                    } catch (IOException i) {
                    }
                }
            }
            Printer.print("Anything else?");
            x = s.nextLine();
            if (x.equals("N") || x.equals("n") || x.equals("No") || x.equals("no")) {
                try {
                    SavingClass sc = new SavingClass(this);
                    sc.save();
                } catch (IOException io) {

                } finally {
                    break;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expenses expenses = (Expenses) o;
        return Objects.equals(Person, expenses.Person);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Person);
    }
}
