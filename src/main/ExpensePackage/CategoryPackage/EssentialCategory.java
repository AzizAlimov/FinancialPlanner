package main.ExpensePackage.CategoryPackage;

import java.util.Scanner;

public class EssentialCategory extends AbstractCategory{

    public EssentialCategory(String name, int budget) {
        super(name, budget);
    }

    public EssentialCategory(String name, String budget) {
        super(name, budget);
    }

    @Override
    public boolean isEssentialCategory() {
        return true;
    }

    @Override
    public void overbudgetmsg() {
        System.out.println("Overbudget! Better make room.");
        MonthlyExpenses.adjustitems();
    }

}
