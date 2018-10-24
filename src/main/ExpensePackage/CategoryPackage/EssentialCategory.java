package main.ExpensePackage.CategoryPackage;

import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.MonthlyExpenses;

import java.util.ArrayList;
import java.util.List;
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
        adjustitems();
    }

    private void adjustitems() {
        Scanner s = new Scanner(System.in);
        System.out.print(" Which luxury items would you like to remove?");
        me.printcategories();
        System.out.println("Category #: ");
        int a = s.nextInt();
        System.out.println("Item #: ");
        int b = s.nextInt();
        s.nextLine();
        me.removeitem(a, b);
    }

}
