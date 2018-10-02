package main.ui;
import main.ExpensePackage.*;

import java.util.Scanner;

public class RunPlanner {
    public static void main(String[] args) {
        System.out.println("Welcome to FinancialPlanner! Are you a new user or an existing user?");
        System.out.println("What is your name?");
        Scanner s;
        s = new Scanner(System.in);
        String name = s.nextLine();
        Expenses e = new Expenses(name);

        e.setupdate(name, s);
        e.mainloop(s, name);

        if ((e.getmonthlyexpense(0)).getbudget() >= (e.getmonthlyexpense(0)).getTotalcategorybudgets()){
            System.out.println("Still on budget! " +
                    (e.getmonthlyexpense(0).getbudget() - e.getmonthlyexpense(0).getTotalcategorybudgets()) +
            " is remaining.");
        } else {
            System.out.println("Over budget by " + (e.getmonthlyexpense(0).getTotalcategorybudgets() -
            e.getmonthlyexpense(0).getbudget()));
        }
    }


}
