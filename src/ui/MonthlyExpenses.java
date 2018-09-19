package ui;
import java.util.LinkedList;
import java.util.List;

public class MonthlyExpenses {
        private Date date;
        private int totalexpenses;
        private int budget;
        private List<Item> ListOfExpenses;

        // Create a MonthlyExpenses with a date and a budget
        public MonthlyExpenses(Date date, int budget) {
            this.date = date;
            this.budget = budget;
            ListOfExpenses = new LinkedList<>();
        }

        public void additem(Item item) {
            ListOfExpenses.add(item);
            totalexpenses += item.getPrice();
        }

        public void setbudget(int budget) { this.budget = budget; }
        public void setdate(Date date) { this.date = date; }
        public int getbudget() { return budget; }
        public Date getdate() { return date; }
        public int gettotalexpenses() { return totalexpenses; }
        public List<Item> getListOfExpenses() { return ListOfExpenses; }

    public static void main(String[] args) {
    }
}
