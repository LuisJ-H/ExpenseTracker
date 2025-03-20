import java.util.*;

/*
TODO
 add expenseID to id list. increment the expenseID everytime a new expense object is added.
 fix getId(), grab id from the list to compare to users input ID.
 grab the expenseID from the id list to display it in toString().
 */

public class Expense {
  private static int idCounter = 1;
//  private List<Integer> id;
  private int id;
  private String description;
  private double amount;
  private String category;
  private Date date;

  Expense(String description, double amount, String category){
    this.id = idCounter++;
    this.description = description;
    this.amount = amount;
    this.category = category;
    this.date = new Date();
  }
  public Date getDate() {
    return date;
  }

  public void setDate (Date date){
    this.date = date;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category){
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description){
    this.description = description;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) { this.id = id; }

  @Override
  public String toString() {
    return
        "Expense: #" + id + " \n " +
        "amount = " + amount + " \n " +
        "description = " + description + " \n" +
        " date = " + date + " \n " +
        "category = " + category + " \n" +
        "_____________________________";
  }




}
