package main.ExpensePackage.CategoryPackage;

import main.ui.Printer;

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
        Printer.print("Overbudget! Better make room.");
        MonthlyExpenses.adjustitems();
    }

}
