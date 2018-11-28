package main.ExpensePackage.CategoryPackage;
import main.ExpensePackage.Exceptions.OutOfMoneyException;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.MonthlyExpenses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCategory implements Category{

    private String Name;
    private int Budget;
    private int Expenses;
    private List<Item> loi;
    protected MonthlyExpenses MonthlyExpenses;
    private List<Item> recurringItems;

    // EFFECTS: Constructs a Category with a name and a budget
    public AbstractCategory(String name, int budget) {
        this.Name = name;
        this.Budget = budget;
        this.loi = new ArrayList<>();
        recurringItems = new ArrayList<>();
    }

    public void setMonthlyExpense(MonthlyExpenses me) {
        this.MonthlyExpenses = me;
    }

    // EFFECTS: Returns the name of the category
    public String getName() {
        return Name;
    }

    // EFFECTS: Returns the budget of the category
    public int getbudget() {
        return Budget;
    }

    // EFFECTS: Returns the sum of the prices of all the items in the category
    public int getexpenses() {
        return Expenses;
    }

    // EFFECTS: Returns the list of items in a category
    public List<Item> getItems() {return loi;}

    // MODIFIES: this
    // EFFECTS:
    public void removeItem (int item) {
        Item i = loi.get(item);
        loi.remove(item);
        Expenses -= i.getPrice();
        MonthlyExpenses.removeItem(i);
    }

    public void removeItem(Item item) {
        loi.remove(item);
        Expenses -=item.getPrice();
        MonthlyExpenses.removeItem(item);
    }


    // EFFECTS: Constructs a Category with a name and a budget
    public AbstractCategory(String name, String budget) {
        this.Name = name;
        int i = Integer.parseInt(budget);
        this.Budget = i;
        loi = new ArrayList<>();
        this.recurringItems = new ArrayList<Item>();
    }

    public void printitems () {
        for (Item key: loi) {
            System.out.println(key.getName() + ": " + key.getPrice() + "$");
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds an item to a category
    public void addItem(Item item) throws OutOfMoneyException {
        loi.add(item);
        Expenses += item.getPrice();
        MonthlyExpenses.addtotalexpenses(item.getPrice());
        if (Expenses > Budget) {
            throw new OutOfMoneyException();
        }
    }

    abstract public void overbudgetmsg();

    abstract public boolean isEssentialCategory();

}
