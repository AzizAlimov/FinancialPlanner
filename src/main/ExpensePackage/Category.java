package main.ExpensePackage;
import java.util.List;
import java.util.ArrayList;

public class Category {
    private String Name;
    private int budget;
    private int expenses;
    private List<Item> loi;
    private MonthlyExpenses me;

    public void setMonthlyExpense(MonthlyExpenses me) {
        this.me = me;
    }

    // REQUIRES: budget to be positive
    // MODIFIES: this
    // EFFECTS: makes a new category with a name and a budget
    public Category(String name, int budget) {
        this.Name = name;
        this.budget = budget;
        loi = new ArrayList<Item>();

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
    // EFFECTS: Adds an item to a category
    public void addItem(Item item) {
        loi.add(item);
        expenses += item.getPrice();
        me.addtotalexpenses(item.getPrice());
    }
}
