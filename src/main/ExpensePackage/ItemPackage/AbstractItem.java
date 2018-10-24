package main.ExpensePackage.ItemPackage;

public abstract class AbstractItem {

    private String name;
    private int price;
    private boolean recurring;

    // EFFECTS: returns the name of an AbstractItem
    public String getName() {return name;}

    // EFFECTS: returns the price of an item
    public int getPrice() {return price;}

    // MODIFIES: this
    // EFFECTS: sets the name of an item
    public void setName (String name) {this.name = name; }

    // REQUIRES: price is a positive number
    // MODIFIES: this
    // EFFECTS: sets the price of an AbstractItem
    public void setPrice (int price) {this.price = price; }

    abstract void overbudgetmsg();
    abstract boolean isessential();
}
