package main.ExpensePackage.CategoryPackage;

import main.ExpensePackage.Exceptions.OutOfMoneyException;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.MonthlyExpenses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Category {

    void setMonthlyExpense(MonthlyExpenses me);

    int getbudget();

    String getName();

    List<Item> getItems();

    boolean isEssentialCategory();

    void overbudgetmsg();
    void addItem(Item i) throws OutOfMoneyException;
    int getexpenses();
    void removeItem(int item);
    void removeItem(Item item);
}
