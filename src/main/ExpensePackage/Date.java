package main.ExpensePackage;

import main.ExpensePackage.Exceptions.InvalidDateException;
import org.omg.CORBA.DynAnyPackage.Invalid;

public class Date {
    private int Month;
    private int Year;

    //  MODIFIES: this
    //  EFFECTS: Constructs a Date with a month and a year
    public Date (int Month, int Year) throws InvalidDateException {
        if (Month < 0 || Month > 12) {
            throw new InvalidDateException();
        }
        this.Month = Month;
        this.Year = Year;
    }

    public Date (String Month, String Year) {
        int i = Integer.parseInt(Month);
        int i2 = Integer.parseInt(Year);
        this.Month = i;
        this.Year = i2;
    }

    //  EFFECTS: Returns the year of a Date object
    public int getYear() { return Year;}

    //  EFFECTS: Returns the month of a Date object
    public int getMonth() {return Month;}

    // MODIFIES: this
    // EFFECTS: Constructs a Date in the next month as the given Date
    public Date nextMonth() throws InvalidDateException {
        if (Month == 12) {
            int temp = Year + 1;
            Date temp2 = new Date(0, temp);
            return temp2;
        } else {
            int temp = Month + 1;
            Date temp2 = new Date(temp, Year);
            return temp2;
        }
    }
}
