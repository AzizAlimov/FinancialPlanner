package main.ui;
import jdk.internal.util.xml.impl.Input;
import main.ExpensePackage.*;
import main.ExpensePackage.CategoryPackage.Category;
import main.ExpensePackage.CategoryPackage.EssentialCategory;
import main.ExpensePackage.CategoryPackage.LuxuryCategory;
import main.ExpensePackage.ItemPackage.EssentialItem;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.ItemPackage.LuxuryItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static main.ExpensePackage.Expenses.splitOnSpace;

public class RunPlanner {
    public static void main(String[] args) {
        System.out.println("Welcome to FinancialPlanner! Are you a new user or an existing user?");
        Scanner s = new Scanner(System.in);
        String a = s.nextLine();
        System.out.println("What is your name?");
        String name = s.nextLine();
        Expenses e = new Expenses(name);
        try {
            if (a.equals("new")) {
                e.setupdate(name);
                e.mainloop(name);
            } else {
                ArrayList<Expenses> ae = load();
                for (Expenses z: ae) {
                    if (name.equals(z)) {
                        z.mainloop(name);
                    }
                }
            }
            if ((e.getmonthlyexpense(0)).getbudget() >= (e.getmonthlyexpense(0)).getTotalcategorybudgets()) {
                System.out.println("Still on budget! " +
                        (e.getmonthlyexpense(0).getbudget() - e.getmonthlyexpense(0).getTotalcategorybudgets()) +
                        " is remaining.");
            } else {
                System.out.println("Over budget by " + (e.getmonthlyexpense(0).getTotalcategorybudgets() -
                        e.getmonthlyexpense(0).getbudget()));
            }
        } catch (InputMismatchException i) {
            System.out.println("You have given an invalid input. FinancialPlanner will now terminate.");
        } catch (IOException i) {

        }
    }

    public static ArrayList<Expenses> load() throws IOException {
        // loads expenses
        List<String> lines = Files.readAllLines(Paths.get("Expenses.txt"));
        ArrayList<Expenses> ae = new ArrayList<>();
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Expenses e = new Expenses(partsOfLine.get(0));
            ae.add(e);
        }
        // loads monthlyexpenses
        lines = Files.readAllLines(Paths.get("MonthlyExpenses.txt"));
        int x = 0;
        for (String line : lines) {
            if (line.equals("")) {
                x++;
            } else {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                Date d = new Date(partsOfLine.get(0), partsOfLine.get(1));
                MonthlyExpenses me = new MonthlyExpenses(d, partsOfLine.get(2));
                ae.get(x).addmonth(me);
            }
        }
        // loads categories
        lines = Files.readAllLines(Paths.get("Category.txt"));
        x = 0;
        Category c;
        for (String line : lines) {
            if (line.equals("")) {
                x++;
            } else {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                if (Boolean.parseBoolean(partsOfLine.get(2))) {
                    c = new EssentialCategory(partsOfLine.get(0), partsOfLine.get(1));
                    ae.get(0).getmonthlyexpense(x).addcategory(c);
                } else {
                    c = new LuxuryCategory(partsOfLine.get(0), partsOfLine.get(1));
                    ae.get(0).getmonthlyexpense(x).addcategory(c);
                }
            }
        }
        // loads Items
        lines = Files.readAllLines(Paths.get("Items.txt"));
        x = 0;
        int y = 0;
        Item i;
        for (String line : lines) {
            if (line.equals("")) {
                x++;
                y = 0;
            } else if (line.equals("/")) {
                y++;
            } else {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                if (Boolean.parseBoolean(partsOfLine.get(2))) {
                    i = new EssentialItem(partsOfLine.get(0), Integer.parseInt(partsOfLine.get(1)));
                    ae.get(0).getmonthlyexpense(x).getcategory(y).addItem(i);
                } else {
                    i = new LuxuryItem(partsOfLine.get(0), Integer.parseInt(partsOfLine.get(1)));
                    ae.get(0).getmonthlyexpense(x).getcategory(y).addItem(i);
                }
            }
        }
        return ae;
    }
}
