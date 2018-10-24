package main.ExpensePackage.CategoryPackage;
import main.ExpensePackage.ItemPackage.AbstractItem;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.MonthlyExpenses;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCategory implements Category{

    private String Name;
    private int budget;
    private int expenses;
    private List<Item> loi;
    protected MonthlyExpenses me;
    private List<Item> recurringItems;

    public void setMonthlyExpense(MonthlyExpenses me) {
        this.me = me;
    }

    // EFFECTS: Returns the name of the category
    public String getName() {
        return Name;
    }

    // EFFECTS: Returns the budget of the category
    public int getbudget() {
        return budget;
    }

    // EFFECTS: Returns the sum of the prices of all the items in the category
    public int getexpenses() {
        return expenses;
    }

    // EFFECTS: Returns the list of items in a category
    public List<Item> getItems() {return loi;}

    // MODIFIES: this
    // EFFECTS:
    public void removeItem (int item) {
        loi.remove(item);
    }

    // EFFECTS: Constructs a Category with a name and a budget
    public AbstractCategory(String name, int budget) {
        this.Name = name;
        this.budget = budget;
        this.loi = new ArrayList<>();
        recurringItems = new ArrayList<>();
    }

    // EFFECTS: Constructs a Category with a name and a budget
    public AbstractCategory(String name, String budget) {
        this.Name = name;
        int i = Integer.parseInt(budget);
        this.budget = i;
        loi = new ArrayList<Item>();
        this.recurringItems = new ArrayList<Item>();
    }

    public void printitems () {
        for (Item temp: loi) {
            System.out.println(temp.getName() + ": " + temp.getPrice() + "$");
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds an item to a category
    public void addItem(Item item) {
        loi.add(item);
        expenses += item.getPrice();
        me.addtotalexpenses(item.getPrice());
        if (expenses > budget) {
            item.overbudgetmsg();
        }
        loi.remove(0);
        throw new NullPointerException("Exceeded monthly budget");
    }

    public void addrecurringitem(Item i) {
        recurringItems.add(i);
    }

    abstract public void overbudgetmsg();

    abstract public boolean isEssentialCategory();

}
