package main.ExpensePackage.ItemPackage;

import main.ExpensePackage.CategoryPackage.Category;

public class EssentialItem extends AbstractItem implements Item {
    private String name;
    private int price;
    // Category the item belongs to
    private Category category;

    // REQUIRES: price is a positive number
    // MODIFIES: this
    // EFFECTS: makes a new AbstractItem with a name and a price
    public EssentialItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void overbudgetmsg () {
        System.out.println("Better make room! What luxury item would you like to remove?");
    }

    @Override
    public boolean isessential() { return true;}

}
