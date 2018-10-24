package main.ExpensePackage.ItemPackage;

import main.ExpensePackage.CategoryPackage.Category;

public class LuxuryItem extends AbstractItem implements Item {
    private String name;
    private int price;
    private Category category;

    public LuxuryItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void overbudgetmsg() {
        System.out.println("Are you sure you need that?");
    }
    public boolean isessential() { return false; }
}
