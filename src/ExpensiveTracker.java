import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ExpensiveTracker {
  static List<Expense> expenses = new ArrayList<>();
  static Scanner scanner = new Scanner(System.in);
  static double budget = 0;


  public static void addExpense(Expense expense) {
    expenses.add(expense);
  }

  public static void updateExpense(int id) {
    for (Expense expense : expenses) {
      if (expense.getId() == id) {

        System.out.println("Update Description: ");
        String description = scanner.nextLine();

        double amount = -1;
        while (true) {
          System.out.println("Enter Amount - ");
          if (scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            if (amount >= 0) {
              break;
            } else {
              System.out.println("Amount cannot be negative.");
            }
          } else {
            System.out.println("Invalid amount. Please enter a numeric value.");
          }
          scanner.nextLine();
        }

        scanner.nextLine();
        System.out.println("Update Category: ");
        String category = scanner.nextLine();

        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setCategory(category);
        System.out.println("Successfully updated expense");
        return;
      }
    }
    System.out.println("ID does not exist");
  }

  public static void delete(int id) {
    Iterator<Expense> deletedExpenses = expenses.iterator();
    while(deletedExpenses.hasNext()){
      Expense expense = deletedExpenses.next();
      if(expense.getId() == id){
        deletedExpenses.remove();
        System.out.println("Expense deleted successfully");
      }
    }
  }

  public static double budget(double num){
    return num + budget;
  }

  public static void viewSummary() {
    double total = 0;
    double mostExpensive = 0;
    double averageSpending = 0;

    double max = Integer.MIN_VALUE;

    for (Expense expense : expenses) {
      double amount = expense.getAmount();
      total += expense.getAmount();
      averageSpending = total / expenses.size();

      if(amount > max){
        max = amount;
      }
      mostExpensive = max;

    }
    System.out.println("Total Expenses = $" + total + " \n " +
                        "Most Expense Purchase = $" + mostExpensive + " \n " +
                        "Average Spending = $" + averageSpending + " \n " +
                        "Budget Comparison "  + (budget <= total ?
                        "You exceeded your budget by $" + (total-budget) :
                        "You are within your budget. Your remaining budget: $" + (budget - total)) + " \n " +
                        "_____________________________");
  }

  /*
  "Expense: #" + id + " \n " +
        "amount = " + amount + " \n " +
        "description = " + description + " \n" +
        " date = " + date + " \n " +
        "category = " + category + " \n" +
        "_____________________________";
   */

  public static void displayExpense() {
    if(expenses.isEmpty()){
      System.out.println("++++++++++++++++NO EXPENSES AVAILABLE++++++++++++++++");
    } else {
      for (Expense expense : expenses) {
        System.out.println(expense.toString());
      }
    }
  }

  public static void main(String[] args) {
    while (true) {
      System.out.println("\nExpense Tracker - Choose an option");
      System.out.println("1. Add Expense\n2. Update Expense\n3. Delete Expense\n4. View Expenses\n5. View Summary\n6. Set Budget\n7. Export to CSV\n8. Exit");
      System.out.println("Choose a choice 1-8: ");

      if (!scanner.hasNextInt()) {
        System.out.println("Please choose a number 1-8");
        scanner.next();
        continue;
      }

      int choice = scanner.nextInt();

      if (choice < 1 || choice > 8) {
        System.out.println("Please choose a number 1-8");
        continue;
      }
      scanner.nextLine();


      switch (choice) {
        case 1:
          System.out.println("Enter Description - ");
          String description = scanner.nextLine();
          double amount = -1;

          while (true) {
            System.out.println("Enter Amount - ");
            if (scanner.hasNextDouble()) {
              amount = scanner.nextDouble();
              if (amount >= 0) {
                break;
              } else {
                System.out.println("Amount cannot be negative.");
              }
            } else {
              System.out.println("Invalid amount. Please enter a numeric value.");
            }
            scanner.nextLine();
          }

          scanner.nextLine();
          System.out.println("Enter Category - ");
          String category = scanner.nextLine();

          Expense expense = new Expense(description, amount, category);
          addExpense(expense);
          System.out.println("Expense successfully added.");
          break;

        case 2:
          System.out.println("Update expense by choosing the ID: ");
          if (scanner.hasNextInt()) {
            int chosenId = scanner.nextInt();
            scanner.nextLine();
            updateExpense(chosenId);
          } else {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.next();
          }
          break;

        case 3:
          System.out.println("Enter Expense ID to delete");
          if(scanner.hasNextInt()){
            int deletedId = scanner.nextInt();
            scanner.nextLine();
            delete(deletedId);
          } else {
            System.out.println("Invalid. numeric values only.");
            scanner.next();
          }
          break;

        case 4:
          displayExpense();
          break;

        case 5:
          System.out.println("++++++++++++++++SUMMARY++++++++++++++++");
          viewSummary();
          break;

        case 6:
          System.out.println("Set a budget: ");
          budget = scanner.nextDouble();
          scanner.nextLine();
          budget(budget);
          System.out.println("Budget - $" + budget);
          break;

        case 7:
          System.out.println("Under construction");
          break;

        case 8:
          System.out.println("Goodbye....");
          return;

        default:
          System.out.println("Invalid choice. Please choose a number between 1 and 8.");
          break;
      }
    }
  }
}
