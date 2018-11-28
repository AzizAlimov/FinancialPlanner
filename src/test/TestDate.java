package test;

import main.ExpensePackage.Exceptions.InvalidDateException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import main.ExpensePackage.Date;


public class TestDate {
    Date date;

    @Test
    public void testnextMonth() {
        try {
            date = new Date(11, 2018);
            assertEquals(date.getMonth(), 11);
            assertEquals(date.getYear(), 2018);
            date = date.nextMonth();
            assertEquals(date.getMonth(), 12);
            assertEquals(date.getYear(), 2018);
            date = date.nextMonth();
            assertEquals(date.getMonth(), 0);
            assertEquals(date.getYear(), 2019);
        } catch (InvalidDateException i) {
            fail("Exception thrown");
        }
    }

    @Test
    public void testThrowInvalidDate() {
        try {
            date = new Date(-1, 2018);
            fail("Exception not thrown");
        } catch (InvalidDateException i) {
            System.out.println("Test passed");
        }
    }
}
