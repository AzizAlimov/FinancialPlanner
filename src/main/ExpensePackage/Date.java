package main.ExpensePackage;

public class Date {
    private int Month;
    private int Year;

    //  REQUIRES: Month is an integer between 0 and 12 and Year is a positive integer
    //  MODIFIES: this
    //  EFFECTS: Constructs a Date with a month and a year
    public Date (int Month, int Year) {
        this.Month = Month;
        this.Year = Year;
    }

    //  EFFECTS: Returns the year of a Date object
    public int getYear() { return Year;}

    //  EFFECTS: Returns the month of a Date object
    public int getMonth() {return Month;}

    // MODIFIES: this
    // EFFECTS: Constructs a Date in the next month as the given Date
    public Date nextMonth() {
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
