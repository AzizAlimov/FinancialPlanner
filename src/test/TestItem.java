package test;

import main.ExpensePackage.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    Item i;

    @Test
    public void onlyTest () {
        i = new Item("banana", 50);
        assertEquals(50, i.getPrice());
        assertEquals("banana", i.getName());
    }
}
