package main.ui;

import main.ExpensePackage.CategoryPackage.Category;
import main.ExpensePackage.Date;
import main.ExpensePackage.Expenses;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.MonthlyExpenses;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SavingClass {

    Expenses parent;

    public SavingClass(Expenses e) {
        this.parent = e;
    }

    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("Expenses.txt", "UTF-8");
        writer.println(parent.getPerson());
        saveMonthlyExpenses();
        saveCategories();
        saveItems();
        writer.close();
        // writer.println("") is the key word
    }

    public void saveCategories() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("Categories.txt", "UTF-8");
        for (MonthlyExpenses temp : parent.getTotalExpenses()) {
            for (Category temp2: temp.getCategory()) {
                String x = temp2.getName() + " " + temp2.getbudget() + " " + temp2.isEssentialCategory();
                writer.println(x);
            }
            writer.println("");
        }
        writer.close();
    }

    public void saveItems() throws FileNotFoundException, UnsupportedEncodingException {
        List<Item> i;
        PrintWriter writer = new PrintWriter("Items.txt", "UTF-8");
        for (MonthlyExpenses temp: parent.getTotalExpenses()) {
            for (Category temp2: temp.getCategory()) {
                i = temp2.getItems();
                for (Item item: i) {
                    writer.println(item.getName() + " " + (item.getPrice() + " " + item.isEssential())
                    + " " + temp2);
                }
                writer.println("/");
            }
            writer.println("");
        }
        writer.close();
    }

    public void saveMonthlyExpenses() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("MonthlyExpenses.txt", "UTF-8");
        Date d;
        for (MonthlyExpenses temp : parent.getTotalExpenses()) {
            d = temp.getDate();
            writer.println(Integer.toString(d.getYear()) + " " + Integer.toString(d.getMonth())
                    + " " + temp.getbudget());
        }
        writer.println("");
        writer.close();
    }


    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
