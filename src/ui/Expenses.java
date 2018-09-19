package ui;

import java.util.List;
import java.util.LinkedList;

public class Expenses {
    private List<MonthlyExpenses> TotalExpenses;
    private String Person;

    public Expenses(String Person) {
        this.Person = Person;
        TotalExpenses = new LinkedList<>();
    }

    public void addmonth(MonthlyExpenses me) {
        TotalExpenses.add(0, me);
    }

    public String getPerson() {return Person;}
    public List getmonthlyexpenses() {return TotalExpenses;}
    public MonthlyExpenses getmonthlyexpense(int n) {
        return TotalExpenses.get(n);
    }
}
