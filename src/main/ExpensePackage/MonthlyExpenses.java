package main.ExpensePackage;

import main.ExpensePackage.CategoryPackage.Category;
import main.ExpensePackage.Exceptions.OutOfMoneyException;
import main.ExpensePackage.ItemPackage.Item;

import java.util.*;

public class MonthlyExpenses {
    private Date date;
    private int totalcategorybudgets;
    private int budget;
    private int totalexpenses;
    private Map<Category, ArrayList<Item>> category;
    private Expenses e;

    // EFFECTS: Returns the date of a monthly expense
    public Date getDate() {
        return date;
    }

    // MODIFIES: this
    // EFFECTS: Sets the expenses of a monthly expense
    public void setExpenses(Expenses e) {
        if(!(this.e).equals(e)) {
            this.e = e;
            e.addmonth(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a MonthlyExpenses with a date and a budget
    public MonthlyExpenses(Date date, int budget) {
        this.date = date;
        this.budget = budget;
        category = new HashMap<Category, ArrayList<Item>>();
    }

    //
    public MonthlyExpenses(Date date, String budget) {
        Integer i = Integer.parseInt(budget);
        this.date = date;
        this.budget = i;
        category = new HashMap<Category, ArrayList<Item>>();
    }

    // MODIFIES: this
    // EFFECTS: adds a category to the list of categories
    public void addCategory(Category category) throws OutOfMoneyException {
        this.category.put(category, null);
        totalcategorybudgets += category.getbudget();
        category.setMonthlyExpense(this);
        if (budget<totalcategorybudgets) {
            throw new OutOfMoneyException();
        }
    }

    public void addCategory(List<Category> category) {
        for (Category c: category) {
            this.category.put(c, null);
            totalcategorybudgets += c.getbudget();
            e.addExpense(c.getbudget());
        }
    }


    // EFFECTS: returns the total expenses
    public int getTotalCategoryBudgets() {
        return totalcategorybudgets;
    }

    // EFFECTS: prints the list of categories for a month
    public void printcategories() {
        Set<Category> loi = category.keySet();
        for (Category t : loi) {
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

    // EFFECTS: returns a list of the categories of a month
    public Set<Category> getCategory() {
        return category.keySet();
    }

    // REQUIRES: The given category is a part of the list of categories in a monthly expense
    // EFFECTS: Returns the category with a given name
    public Category getCategory(String category) {
        for (Category c : this.category.keySet()) {
            if (category.equals(c.getName())) {
                return c;
            }
        }
        throw new NoSuchFieldError("The category does not exist");
    }


    // EFFECTS: Prints the categories of a monthly expense along with the expenses in each category
    public void printExpenses() {
        int x = 0;
        for (Category c: category.keySet()) {
            int y = 0;
            System.out.println(x + ": " + c.getName() + ", " + c.getbudget() + "$");
            for (Item i: c.getItems()) {
                System.out.println(y + ". " + i.getName() + " : " + i.getPrice() + "$");
                y++;
            }
            System.out.println("Remaining: " + (c.getbudget() - c.getexpenses()));
            x++;
        }
    }

    public void removeItem(Category ctg, Item i) {
        ctg.removeItem(i);
        category.remove(ctg, i);
    }

    public void removeItem(Item item) {
        Set<Category> keys = category.keySet();
        List<Category> loc = null;
        for (Category k: keys) {
            if (category.get(k).equals(item)) {
                category.remove(k, item);
            }
        }
    }

    public Item getItem(Category ctg, int i) {
        return category.get(ctg).get(i);
    }

    public Item getItem(String s) {
        Set<Category> ks = category.keySet();
        for (Category k: ks) {
            ArrayList<Item> ll = category.get(k);
            for (int n = 0; n<ll.size(); n++)
            if (category.get(k).get(n).getName().equals(s)) {
                return category.get(k).get(n);
            }
        }
        return null;
    }

    public void adjustitems() {
        Scanner s = new Scanner(System.in);
        System.out.print("Which luxury items would you like to remove?");
        printcategories();
        System.out.println("Category: ");
        String a = s.nextLine();
        Category c = getCategory(a);
        System.out.println("Item: ");
        String b = s.nextLine();
        Item d = getItem(b);
        removeItem(c, d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyExpenses that = (MonthlyExpenses) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(e, that.e);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, e);
    }
}
