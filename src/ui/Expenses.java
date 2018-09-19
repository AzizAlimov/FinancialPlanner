package ui;

import java.util.List;
import java.util.LinkedList;

public class Expenses {
    private List<MonthlyExpenses> TotalExpenses;
    private String Person;

    public Expenses(String Person) {
        Person = this.Person;
        TotalExpenses = new LinkedList<>();
    }

    public void addmonth(MonthlyExpenses me) {
        TotalExpenses.add(me);
    }

    public String getPerson() {return Person;}
    public List getmonthlyexpenses() {return TotalExpenses;}
    public MonthlyExpenses getmonthlyexpense(Date d) {
        return TotalExpenses.get(0);
    }
}
