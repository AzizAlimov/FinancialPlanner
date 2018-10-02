package main.ExpensePackage;

public class Item {
    private String name;
    private int price;

    // REQUIRES: price is a positive number
    // MODIFIES: this
    // EFFECTS: makes a new Item with a name and a price
    public Item(String name, int price) {setName(name); setPrice(price); }


    // EFFECTS: returns the name of an Item
    public String getName() {return name;}

    // EFFECTS: returns the price of an item
    public int getPrice() {return price;}

    // MODIFIES: this
    // EFFECTS: sets the name of an item
    public void setName (String name) {this.name = name; }

    // REQUIRES: price is a positive number
    // MODIFIES: this
    // EFFECTS: sets the price of an Item
    public void setPrice (int price) {this.price = price; }

}
