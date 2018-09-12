package ui;
import java.util.Scanner;
import java.util.List;

public class RunPlanner {
    public static void main(String[] args) {
        MonthlyExpenses s;
        s = new MonthlyExpenses("0918", 1000);
        Groceries(s);
    }

    public static MonthlyExpenses Groceries(MonthlyExpenses me){
        Scanner s;
        s = new Scanner(System.in);

        System.out.println("Welcome to FinancialPlanner! Would you like to add any expenses?");
        String x = s.nextLine();
        List<Item> loe = me.getListOfExpenses();
        int Index = loe.size();

        while (x.equals("Y") || x.equals("y") || x.equals("yes") || x.equals("Yes")) {
            System.out.println("Please input the name then the price of this month's expenses.");
            String z = s.nextLine();
            int y = s.nextInt();
            s.nextLine();
            Item m = new Item(z, y);
            me.additem(m);
            System.out.println("Added " + z + "!");
            System.out.println("Anything else?");
            x = s.nextLine();
        }

        if (me.getbudget() >= me.gettotalexpenses()){
            System.out.println("Still on budget!");
        } else {
            System.out.println("Over budget.");
        }
        return me;
    }
}
