package ui;

public class Item {
    String name;
    int price;

    public Item(String name, int price) {setName(name); setPrice(price); }

    public String getName() {return name;}
    public int getPrice() {return price;}
    public void setName (String name) {this.name = name; }
    public void setPrice (int price) {this.price = price; }

}
