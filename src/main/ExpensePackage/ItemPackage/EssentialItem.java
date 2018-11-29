package main.ExpensePackage.ItemPackage;

import main.ExpensePackage.CategoryPackage.Category;
import main.ui.Printer;

public class EssentialItem extends AbstractItem implements Item {

    // REQUIRES: price is a positive number
    // MODIFIES: this
    // EFFECTS: makes a new AbstractItem with a name and a price
    public EssentialItem(String name, int price) {
        super(name,price);
    }

    @Override
    public void overBudgetMsg() {
        Printer.print("Better make room! What luxury item would you like to remove?");
    }

    @Override
    public boolean isEssential() { return true;}


}
