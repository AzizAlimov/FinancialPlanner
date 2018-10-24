package main.ExpensePackage;

import main.ExpensePackage.CategoryPackage.AbstractCategory;
import main.ExpensePackage.CategoryPackage.Category;
import main.ExpensePackage.CategoryPackage.EssentialCategory;
import main.ExpensePackage.CategoryPackage.LuxuryCategory;
import main.ExpensePackage.ItemPackage.AbstractItem;
import main.ExpensePackage.ItemPackage.EssentialItem;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.ItemPackage.LuxuryItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Expenses {
    private List<MonthlyExpenses> TotalExpenses;
    private List<Item> lri;
    private String Person;
    //private int Surplus;

    public List<MonthlyExpenses> getTotalExpenses() { return TotalExpenses; }

    public void addri (Item ri) {
        lri.add(ri);
    }

    public void addSurplus(int i) {

    }

    // MODIFIES: this
    // EFFECTS: Makes a new Expenses with the name of the person to whom it belongs
    public Expenses(String Person) {
        this.Person = Person;
        TotalExpenses = new LinkedList<>();
        lri = new LinkedList<>();
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
        System.out.println("E) End Planner and save.");
        System.out.println("F) End Planner without saving");
    }

    // EFFECTS: Returns the monthly expense at a given index value
    public MonthlyExpenses getmonthlyexpense(int n) {
        return TotalExpenses.get(n);
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at a user inputted date
    public void setupdate(String name) throws InputMismatchException {
        Scanner s = new Scanner(System.in);
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


    public void recurringcategories(Scanner s) {
        System.out.println("Would you like to remove any recurring categories?");
        String y = s.nextLine();
        while (y.equals("Yes")) {
//            System.out.println("Which category would you like to remove?");
            int i = s.nextInt() - 1;
            s.nextLine();
  //          lrc.remove(i);
            System.out.println("Any other categories you would like to remove?");
            y = s.nextLine();
        }
        System.out.println("Would you like to add more recurring categories?");
        y = s.nextLine();
        while (y.equals("yes")) {
            System.out.println("Is the recurring category essential?");
            String f = s.nextLine();
            System.out.println("What is the name and the budget of your recurring category?");
            System.out.println("name: ");
            y = s.nextLine();
            System.out.println("budget: ");
            int q = s.nextInt();
            s.nextLine();
            if (f.equals("yes") || f.equals("Yes")) {
                Category c = new EssentialCategory(y, q);
     //           lrc.add(c);
            } else {
                Category c = new LuxuryCategory(y, q);
       //         lrc.add(c);
            }
            System.out.println("Any other recurring categories you would like to add?");
            y = s.nextLine();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at the month following the latest monthly expense
    public void setupnextmonth(String name, Scanner s, Date firstdate) throws InputMismatchException {
        setup2(name, s, firstdate.nextMonth());
    }

    // MODIFIES: this
    // EFFECTS: sets up a monthly expense at the user inputted date
    private void setup2(String name, Scanner s, Date firstdate) throws InputMismatchException {

        System.out.println("What is your budget for " + firstdate.getMonth() +
                " " + firstdate.getYear() + "?");
        int budget = s.nextInt();
        s.nextLine();
        String y = s.nextLine();
        MonthlyExpenses me = new MonthlyExpenses(firstdate, budget);
        addmonth(me);
        System.out.println("What categories would you like to add to this month?");
        while (me.getbudget() > me.getTotalcategorybudgets()) {
            try {
                System.out.println("Name: ");
                name = s.nextLine();
                System.out.println("Budget: ");
                budget = s.nextInt();
                s.nextLine();
                System.out.println("Is this category essential?");
                String ec = s.nextLine();
                if (ec.equals("yes")) {
                    Category c = new EssentialCategory(name, budget);
                    me.addcategory(c);
                } else {
                    Category c = new LuxuryCategory(name, budget);
                    me.addcategory(c);
                }
            } catch (NullPointerException ne) {
                System.out.println("Exceeded monthly budget. Please try again while staying within the budget.");
            }
            System.out.println("Remaining total budget: " + (me.getbudget() - me.getTotalcategorybudgets()) + "$");
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
                    System.out.println("What category does your expense belong to?");
                    (getmonthlyexpense(0)).printcategories();
                    String z = s.nextLine();
                    Category c = new EssentialCategory("", 0);
                    c = (getmonthlyexpense(0)).getcategory(z);
                    if (!c.isEssentialCategory()) {
                        System.out.println("Please input the name followed by the price of your expense.");
                        z = s.nextLine();
                        int y = s.nextInt();
                        s.nextLine();
                        Item m = new LuxuryItem(z, y);
                        c.addItem(m);
                    } else {
                        System.out.println("Is your item essential?.");
                        z = s.nextLine();
                        System.out.println("Please input the name followed by the price of your item.");
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

            } else if (x.equals("E") || x.equals("e")) {
                try {
                    save();
                    System.exit(0);
                } catch (IOException i) {
                    System.exit(0);
                }
            } else if (x.equals("F") || x.equals("f")) {
                System.exit(0);
            }
            System.out.println("Anything else?");
            x = s.nextLine();
            if (x.equals("N") || x.equals("n") || x.equals("No") || x.equals("no")) {
                try {
                    save();
                } catch (IOException io) {

                } finally {
                    break;
                }
            }
        }
    }
    public void save() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("Expenses.txt"));
        PrintWriter writer = new PrintWriter("Expenses.txt", "UTF-8");
        writer.println(this.getPerson());
        saveMonthlyExpenses(lines);
        saveCategories(lines);
        saveItems(lines);
        writer.close();
        // writer.println("") is the key word
    }

    public void saveCategories(List<String> lines) throws IOException {
        PrintWriter writer = new PrintWriter("Categories.txt", "UTF-8");
        for (MonthlyExpenses temp : this.getTotalExpenses()) {
            for (Category temp2: temp.getcategory()) {
                String x = temp2.getName() + " " + temp2.getbudget() + " " + temp2.isEssentialCategory();
                writer.println(x);
            }
            writer.println("");
        }
        writer.close();
    }

    public void saveItems(List<String> lines) throws IOException {
        PrintWriter writer = new PrintWriter("Items.txt", "UTF-8");
        for (MonthlyExpenses temp: this.getTotalExpenses()) {
            for (Category temp2: temp.getcategory()) {
                for (Item temp3: temp2.getItems()) {
                    writer.println(temp3.getName() + " " + temp3.getPrice() + " " + temp3.isessential());
                }
                writer.println("/");
            }
            writer.println("");
        }
    }

    public void saveMonthlyExpenses(List<String> lines) throws IOException {
        PrintWriter writer = new PrintWriter("MonthlyExpenses.txt", "UTF-8");
        Date d;
        for (MonthlyExpenses temp : this.getTotalExpenses()) {
            d = temp.getDate();
            writer.println(Integer.toString(d.getYear()) + " " + Integer.toString(d.getMonth())
                    + " " + " " + temp.getbudget());
        }
        writer.println("");
    }


    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
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
