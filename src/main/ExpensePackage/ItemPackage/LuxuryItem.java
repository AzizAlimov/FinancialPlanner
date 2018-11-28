package main.ExpensePackage.ItemPackage;

import main.ExpensePackage.CategoryPackage.Category;

public class LuxuryItem extends AbstractItem implements Item {

    public LuxuryItem(String name, int price) {
        super(name, price);
    }

    public void overBudgetMsg() {
        System.out.println("Are you sure you need that?");
    }
    public boolean isEssential() { return false; }

}
