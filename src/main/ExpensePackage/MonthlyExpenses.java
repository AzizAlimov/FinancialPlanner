package main.ExpensePackage;

import java.util.LinkedList;
import java.util.List;

public class MonthlyExpenses {
    private Date date;
    private int totalcategorybudgets;
    private int budget;
    private int totalexpenses;
    private List<Category> ListOfCategories;
    private Expenses e;


    public void setExpenses(Expenses e) {
        this.e = e;
    }

    public int gettotalexpenses() {
        return totalexpenses;
    }

    // MODIFIES: this
    // EFFECTS: Creates a MonthlyExpenses with a date and a budget
    public MonthlyExpenses(Date date, int budget) {
        this.date = date;
        this.budget = budget;
        ListOfCategories = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a category to the list of categories
    public void addcategory(Category category) {
        ListOfCategories.add(0, category);
        totalcategorybudgets += category.getbudget();
        category.setMonthlyExpense(this);
    }

    // EFFECTS: returns the total expenses
    public int getTotalcategorybudgets() {
        return totalcategorybudgets;
    }

    // EFFECTS: prints the list of categories for a month
    public void printcategories() {
        for (Category t : ListOfCategories) {
            System.out.println(t.getName());
        }
    }

    // REQUIRES: budget to be positive
    // MODIFIES: this
    // EFFECTS: Sets the budget to a given integer
    public void setbudget(int budget) {
        this.budget = budget;
    }

    // MODIFIES: this
    // EFFECTS: Increases the total expenses by the given value
    public void addtotalexpenses(int e) {
        totalexpenses += e;
    }

    // MODIFIES: this
    // EFFECTS: sets the date to a given date
    public void setdate(Date date) {
        this.date = date;
    }

    // EFFECTS: returns the budget for the month
    public int getbudget() {
        return budget;
    }

    // EFFECTS: returns the date for the month
    public Date getdate() {
        return date;
    }

    // REQUIRES: the given integer to be an index of the list of categories
    // MODIFIES: this
    // EFFECTS: removes a category and updates the total expenses
    public void removecategory(int category) {
        totalcategorybudgets -= ListOfCategories.get(0).getbudget();
        ListOfCategories.remove(category);
    }


    // EFFECTS: returns a list of the categories of a month
    public List<Category> getListOfCategories() {
        return ListOfCategories;
    }

    // REQUIRES: The given category is a part of the list of categories in a monthly expense
    // EFFECTS: Returns the category with a given name
    public Category getcategorywname(String category) {
        for (Category c : ListOfCategories) {
            if (category.equals(c.getName())) {
                return c;
            }
        }
        System.out.println("The given category does not exist. The first category was chosen instead by default");
        return ListOfCategories.get(0);
    }

    // EFFECTS: Prints the categories of a monthly expense along with the expenses in each category
    public void printexpenses() {
        for (Category c: ListOfCategories) {
            System.out.println(c.getName() + ": ");
            List<Item> i = c.getItems();
            for (Item temp: i) {
                System.out.println(temp.getName() + " " + temp.getPrice());
            }
        }
    }

    public void updatecategory(int index) {

    }
}
