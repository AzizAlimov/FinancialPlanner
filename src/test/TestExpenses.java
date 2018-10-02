package test;
import main.ExpensePackage.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExpenses {
    Expenses e;
    MonthlyExpenses m;
    Date d;

    @Test
    public void testonemonth() {
        e = new Expenses("Aziz");
        d = new Date(11, 2018);
        m = new MonthlyExpenses(d, 2000);
        e.addmonth(m);
        assertEquals(e.getmonthlyexpensewdate(d).getTotalcategorybudgets(), 0);
        Category c = new Category ("food", 2000);
        (e.getmonthlyexpensewdate(d)).addcategory(c);
        Item i = new Item("fish", 500);
        c.addItem(i);
        int x = e.getmonthlyexpense(0).gettotalexpenses();
        assertEquals(e.getmonthlyexpensewdate(d).gettotalexpenses(), 500);
    }

    @Test
    public void testtwomonths() {
        e = new Expenses("Aziz");
        d = new Date(11, 2018);
        m = new MonthlyExpenses(d, 2000);
        e.addmonth(m);
        assertEquals(e.getmonthlyexpensewdate(d).getTotalcategorybudgets(), 0);
        Category c = new Category ("food", 2000);
        (e.getmonthlyexpensewdate(d)).addcategory(c);
        Item i = new Item("fish", 500);
        c.addItem(i);
        int x = e.getmonthlyexpense(0).gettotalexpenses();
        assertEquals(e.getmonthlyexpensewdate(d).gettotalexpenses(), 500);
        Item z = new Item("tofu", 300);
        c.addItem(z);
        assertEquals(e.getmonthlyexpensewdate(d).gettotalexpenses(), 800);
    }
}
