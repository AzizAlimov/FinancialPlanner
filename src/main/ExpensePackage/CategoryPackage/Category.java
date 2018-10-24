package main.ExpensePackage.CategoryPackage;

import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.MonthlyExpenses;

import java.util.List;

public interface Category {

    void setMonthlyExpense(MonthlyExpenses me);

    int getbudget();

    String getName();

    List<Item> getItems();

    boolean isEssentialCategory();

    void overbudgetmsg();
    void addItem(Item i);
    int getexpenses();
    void removeItem(int item);
}
