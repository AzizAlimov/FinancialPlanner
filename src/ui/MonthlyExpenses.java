package ui;
import java.util.LinkedList;
import java.util.List;

public class MonthlyExpenses {
        private String date;
        private int totalexpenses;
        private int budget;
        private List<Item> ListOfExpenses;
        private Item CurrentItem;

        // Create a MonthlyExpenses with a date and a budget
        public MonthlyExpenses(String date, int budget) {
            date = this.date;
            budget = this.budget;
            ListOfExpenses = new LinkedList<>();
            CurrentItem = null;
        }
        // Calculates the total monthly expenses
        public void calctotalexpenses() {
            totalexpenses = 0;
            for (Item c : ListOfExpenses) {
                totalexpenses += CurrentItem.getPrice();
            }
        }

        public void additem(Item item) {
            ListOfExpenses.add(item);
        }

        public void setbudget(int budget) { this.budget = budget; }
        public void setdate(String date) { this.date = date; }
        public int getbudget() { return budget; }
        public String getdate() { return date; }
        public int gettotalexpenses() { return totalexpenses; }
        public List<Item> getListOfExpenses() { return ListOfExpenses; }

    public static void main(String[] args) {
    }
}
