package main.ExpensePackage.ItemPackage;

import main.ExpensePackage.CategoryPackage.Category;
import main.ui.Printer;

public class LuxuryItem extends AbstractItem implements Item {

    public LuxuryItem(String name, int price) {
        super(name, price);
    }

    public void overBudgetMsg() {
        Printer.print("Are you sure you need that?");
    }
    public boolean isEssential() { return false; }

}
