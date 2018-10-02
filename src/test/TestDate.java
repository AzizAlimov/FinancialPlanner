package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import main.ExpensePackage.Date;


public class TestDate {
    Date date;

    @Test
    public void testnextMonth() {
        date = new Date(11, 2018);
        assertEquals(date.getMonth(), 11);
        assertEquals(date.getYear(), 2018);
        date = date.nextMonth();
        assertEquals(date.getMonth(), 12);
        assertEquals(date.getYear(), 2018);
        date = date.nextMonth();
        assertEquals(date.getMonth(), 0);
        assertEquals(date.getYear(), 2019);
    }
}
