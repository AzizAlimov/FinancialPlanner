package main.ui;

import main.ExpensePackage.CategoryPackage.Category;
import main.ExpensePackage.CategoryPackage.EssentialCategory;
import main.ExpensePackage.CategoryPackage.LuxuryCategory;
import main.ExpensePackage.Date;
import main.ExpensePackage.Exceptions.OutOfMoneyException;
import main.ExpensePackage.Expenses;
import main.ExpensePackage.ItemPackage.EssentialItem;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.ItemPackage.LuxuryItem;
import main.ExpensePackage.MonthlyExpenses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoadingClass {

    Expenses parent;

    public LoadingClass(Expenses parent) {
        this.parent = parent;
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
        lines = Files.readAllLines(Paths.get("Categories.txt"));
        x = 0;
        Category c;
        for (String line : lines) {
            try {
                if (line.equals("")) {
                    x++;
                } else {
                    ArrayList<String> partsOfLine = splitOnSpace(line);
                    if (Boolean.parseBoolean(partsOfLine.get(2))) {
                        c = new EssentialCategory(partsOfLine.get(0), partsOfLine.get(1));
                        ae.get(0).getmonthlyexpense(x).addCategory(c);
                    } else {
                        c = new LuxuryCategory(partsOfLine.get(0), partsOfLine.get(1));
                        ae.get(0).getmonthlyexpense(x).addCategory(c);
                    }
                }
            } catch (OutOfMoneyException o) {
                Printer.print("There was an error in loading your financial plan. The budget of category " +
                        x + " was exceeded. Please remove an item to make space");
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
                try {
                    ArrayList<String> partsOfLine = splitOnSpace(line);
                    if (Boolean.parseBoolean(partsOfLine.get(2))) {
                        i = new EssentialItem(partsOfLine.get(0), Integer.parseInt(partsOfLine.get(1)));
                        ae.get(0).getmonthlyexpense(x).getCategory(partsOfLine.get(3)).addItem(i);
                    } else {
                        i = new LuxuryItem(partsOfLine.get(0), Integer.parseInt(partsOfLine.get(1)));
                        ae.get(0).getmonthlyexpense(x).getCategory(partsOfLine.get(3)).addItem(i);
                    }
                } catch (OutOfMoneyException o) {
                    Printer.print("Something went wrong in loading your items. The categories they belonged to " +
                            "had their budgets exceeded. Which item would you like to remove?");
                    ae.get(0).getTotalExpenses().get(0).printcategories();
                    Scanner s = new Scanner(System.in);
                    Printer.print("Category: ");
                    String z = s.nextLine();
                    Category zc = ae.get(0).getmonthlyexpense(x).getCategory(z);
                    Printer.print("Item:");
                    String q = s.nextLine();
                    Item yq = ae.get(0).getTotalExpenses().get(0).getItem(q);
                    ae.get(0).getTotalExpenses().get(0).removeItem(zc, yq);
                }
            }
        }
        return ae;
    }

    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}