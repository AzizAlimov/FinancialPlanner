package main.ExpensePackage;

import main.ExpensePackage.CategoryPackage.Category;
import main.ExpensePackage.ItemPackage.Item;

import java.util.LinkedList;
import java.util.List;

public class MonthlyExpenses {
    private Date date;
    private int totalcategorybudgets;
    private int budget;
    private int totalexpenses;
    private List<Category> category;
    private Expenses e;

    // EFFECTS: Returns the date of a monthly expense
    public Date getDate() {
        return date;
    }

    // MODIFIES: this
    // EFFECTS: Sets the expenses of a monthly expense
    public void setExpenses(Expenses e) {
        this.e = e;
    }

    // EFFECTS: Returns the sum of the prices of all the items in a monthly expense
    public int gettotalexpenses() {
        return totalexpenses;
    }

    // MODIFIES: this
    // EFFECTS: Creates a MonthlyExpenses with a date and a budget
    public MonthlyExpenses(Date date, int budget) {
        this.date = date;
        this.budget = budget;
        category = new LinkedList<>();
    }

    //
    public MonthlyExpenses(Date date, String budget) {
        Integer i = Integer.parseInt(budget);
        this.date = date;
        this.budget = i;
        category = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a category to the list of categories
    public void addcategory(Category category) {
        this.category.add(0, category);
        totalcategorybudgets += category.getbudget();
        category.setMonthlyExpense(this);
    }

    public void addcategory(List<Category> category) {
        for (Category c: category) {
            this.category.add(c);
            totalcategorybudgets += c.getbudget();
        }
    }


    // EFFECTS: returns the total expenses
    public int getTotalcategorybudgets() {
        return totalcategorybudgets;
    }

    // EFFECTS: prints the list of categories for a month
    public void printcategories() {
        for (Category t : category) {
            System.out.println(t.getName() + ": " + t.getbudget() + "$");
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
        totalcategorybudgets -= this.category.get(0).getbudget();
        this.category.remove(category);
    }

    //
    public void removecategory(String category) {
        for (Category c: this.category) {
            if (c.getName().equals(category)) {
                this.category.remove(c);
                break;
            }
        }
    }


    // EFFECTS: returns a list of the categories of a month
    public List<Category> getcategory() {
        return category;
    }

    // REQUIRES: The given category is a part of the list of categories in a monthly expense
    // EFFECTS: Returns the category with a given name
    public Category getcategory(String category) {
        for (Category c : this.category) {
            if (category.equals(c.getName())) {
                return c;
            }
        }
        throw new NoSuchFieldError("The category does not exist");
    }

    //
    public Category getcategory(int i) {
        return category.get(i);
    }

    // EFFECTS: Prints the categories of a monthly expense along with the expenses in each category
    public void printexpenses() {
        int x = 0;
        for (Category c: category) {
            int y = 0;
            System.out.println(x + ": " + c.getName() + ", " + c.getbudget() + "$");
            List<Item> i = c.getItems();
            for (Item temp: i) {
                System.out.println(y + ". " + temp.getName() + " : " + temp.getPrice() + "$");
                y++;
            }
            System.out.println("Remaining: " + (c.getbudget() - c.getexpenses()));
            x++;
        }
    }

    public void removeitem(int ctg, int item) {
        (category.get(ctg)).removeItem(item);
    }
}
