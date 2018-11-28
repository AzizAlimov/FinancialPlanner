package main.ExpensePackage.ItemPackage;

import java.util.Objects;

public abstract class AbstractItem {

    private String Name;
    private int price;

    public AbstractItem(String name, int price) {
        this.Name = name;
        this.price = price;
    }
    // EFFECTS: returns the name of an AbstractItem
    public String getName() {return this.Name;}

    // EFFECTS: returns the price of an item
    public int getPrice() {return price;}

    // MODIFIES: this
    // EFFECTS: sets the name of an item
    public void setName (String name) {this.Name = name; }

    // REQUIRES: price is a positive number
    // MODIFIES: this
    // EFFECTS: sets the price of an AbstractItem
    public void setPrice (int price) {this.price = price; }

    abstract void overBudgetMsg();
    abstract boolean isEssential();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractItem)) return false;
        AbstractItem that = (AbstractItem) o;
        return Objects.equals(Name, that.Name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Name);
    }
}