package main.ExpensePackage.CategoryPackage;

import main.ExpensePackage.ItemPackage.*;
import main.ExpensePackage.MonthlyExpenses;

import java.util.ArrayList;
import java.util.List;

public class LuxuryCategory extends AbstractCategory{

    // REQUIRES: budget to be positive
    // MODIFIES: this
    // EFFECTS: makes a new category with a name and a budget
    public LuxuryCategory(String name, int budget) {
        super(name, budget);
    }

    public LuxuryCategory(String name, String budget) {
        super(name, budget);
    }

    @Override
    public void overbudgetmsg() {
        System.out.println("Overbudget! Are you sure you needed this?");
    }

    @Override
    public boolean isEssentialCategory() {
        return false;
    }
}
