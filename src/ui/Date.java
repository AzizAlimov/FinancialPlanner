package ui;

public class Date {
    private int Month;
    private int Year;

    public Date (int Month, int Year) {
        Month = this.Month;
        Year = this.Year;
    }

    public int getYear() { return Year;}
    public int getMonth() {return Month;}

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
