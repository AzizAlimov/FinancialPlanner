package main.ui;
import main.ExpensePackage.*;
import main.ExpensePackage.CategoryPackage.Category;
import main.ExpensePackage.CategoryPackage.EssentialCategory;
import main.ExpensePackage.CategoryPackage.LuxuryCategory;
import main.ExpensePackage.Exceptions.InvalidDateException;
import main.ExpensePackage.Exceptions.OutOfMoneyException;
import main.ExpensePackage.ItemPackage.EssentialItem;
import main.ExpensePackage.ItemPackage.Item;
import main.ExpensePackage.ItemPackage.LuxuryItem;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RunPlanner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                                       public void run() {
                                           //Turn off metal's use of bold fonts
                                           UIManager.put("swing.boldMetal", Boolean.FALSE);
                                           new TextEntryBox().setVisible(true);
                                       }
                                   } );
        System.out.println("Welcome to FinancialPlanner! Are you a new user or an existing user?");
        Scanner s = new Scanner(System.in);
        String a = s.nextLine();
        System.out.println("What is your name?");
        String name = s.nextLine();
        Expenses e = new Expenses(name);
        try {
            if (a.equals("new")) {
                e.setupdate(name);
                e.mainloop(name);
            } else {
                LoadingClass lc = new LoadingClass(e);
                ArrayList<Expenses> ae = lc.load();
                for (Expenses z: ae) {
                    if (name.equals(z.getPerson())) {
                        e = z;
                        z.mainloop(name);
                    }
                }
            }
            if ((e.getmonthlyexpense(0)).getbudget() >= (e.getmonthlyexpense(0)).getTotalCategoryBudgets()) {
                System.out.println("Still on budget! " +
                        (BudgetExpenseDifference(e)) +
                        " is remaining.");
            } else {
                System.out.println("Over budget by " + (-BudgetExpenseDifference(e)));
            }
        } catch (InputMismatchException i) {
            System.out.println("You have given an invalid input. FinancialPlanner will now terminate.");
        } catch (InvalidDateException i) {
            System.out.println("You have given an invalid date.");
        } catch (IOException n) {

        }
    }

    private static int BudgetExpenseDifference(Expenses e) {
        return e.getmonthlyexpense(0).getbudget() - e.getmonthlyexpense(0).getTotalCategoryBudgets();
    }
}
