package main.ExpensePackage.ItemPackage;

import java.util.List;

public interface Item {

    int getPrice();
    String getName();
    void overbudgetmsg();
    boolean isessential();

}
