package test;

import main.ExpensePackage.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import main.ExpensePackage.Date;

public class TestCategory {
    Category c = new Category("grocieres", 1000);;
    Item i = new Item("banana", 500);

    @Test
    public void testaddoneItem() {
        c.addItem(i);
        assertEquals(1, c.getItems().size());
        assertEquals(500, c.getexpenses());
    }

    @Test
    public void testaddtwoItems() {
        Item i2 = new Item("oranges", 400);
        c.addItem(i);
        c.addItem(i2);
        assertEquals(2, c.getItems().size());
        assertEquals(900, c.getexpenses());
    }

}
