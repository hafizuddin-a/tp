package seedu.duke;


import java.util.ArrayList;

/**
 * A class to add a new expense
 */
public class Expense {
    private String payerName;
    private float totalAmount;
    private ArrayList<String> payees = new ArrayList<>();

    /**
     * Constructor to create new Expense
     * @param payer_name : The name of the user who paid for the Expense
     * @param total_amount : The total amount before being divided
     * @param payee_list : String array of people who owe the payer money (Index 0 is the payer and will not be added to the payee list)
     */
    Expense(String payer_name, String total_amount, String[] payee_list){
        total_amount = removeWhitespaces(total_amount);
        payer_name = removeWhitespaces(payer_name);
        for(int i = 1; i < payee_list.length; i++){
            payees.add(removeWhitespaces(payee_list[i]));
        }
        this.payerName = payer_name;
        this.totalAmount = Float.parseFloat(total_amount);
        System.out.printf("Added new expense %.2f owed to %s by:",this.totalAmount,this.payerName);
        for(String payee : payees){
            System.out.print(payee + ", ");
        }
        System.out.println();
    }


    private String removeWhitespaces(String item){
        String itemWithoutWhitespaces = item.replaceAll("\\s+", " ").trim();
        return itemWithoutWhitespaces;
    }
    public String getPayerName() {
        return payerName;
    }

    /**
     *
     * @return : float showing the total amount before division
     */
    public float getTotalAmount() {
        return totalAmount;
    }

    public ArrayList<String> getPayees() {
        return payees;
    }
}
