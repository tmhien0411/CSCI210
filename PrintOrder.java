/**
 * <h2>PrintOrder.java - Describes a single print order.</h2>
 * <p>Description:</p>
 * <p style="margin-left: 25px;">Print orders are added to one of three queues, and then find the order to print receipt.</p>
 * <p style="margin-left: 25px;">When finished printing the receipt, remove from the queue.</p>
 *
 * <p>Instance variables:</p>
 * <ul style="margin-left: 25px;">
 *   <li>orderNumber (int) order number of the day (how many order).</li>
 *   <li>customerName (String) the customer name of the order.</li>
 *   <li>totalDrink (int) total drink (cup) order per order receipt.</li>
 * </ul>
 *
 * <p>Methods:</p>
 * <ul style="margin-left: 25px;">
 *     <li>getOrderNumber (int)- order number of the day</li>
 *     <li>getCustomerName (String)- customer name of the order</li>
 *     <li>getTotalDrink (int)- how many drink order by the customer</li>
 *     <li>setOrderNumber- set the order number</li>
 *     <li>setCustomerName- set the customer name</li>
 *     <li>setTotalDrink- set the total drink</li>
 * </ul>
 * @author Hien Tran
 * @version Module 99, Programming Project
 */
public class PrintOrder {

    private int orderNumber;        //order number of the day (how many order)
    private String customerName;      //customer's name

    private int totalDrink;           //total cup per order

    public PrintOrder(int orderNumber, String customerName, int totalDrink){
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.totalDrink = totalDrink;
    }

    /**
     * Getter for the order number assigned to Print Order
     * @return the order number assigned to this print order
     */
    public int getOrderNumber(){
        return orderNumber;
    }

    /**
     * Getter for the customer name for the order
     * @return the customer name assigned to this print order
     */
    public String getCustomerName(){
        return customerName;
    }

    /**
     * Getter for the total number of drinks this print order requires
     * @return the total drinks to print on this print order
     */
    public int getTotalDrink(){
        return totalDrink;
    }

    /**
     * Setter for the order number assigned to this order (Starting at order 1)
     * @param orderNumber the number to be assigned to this order
     */
    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    /**
     * Setter for the order name assigned to this order
     * @param customerName the name to be assigned to this order
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /**
     * Setter for the total drinks this order will require
     * @param totalDrink the total drinks to be made in this order
     */
    public void setTotalDrink(int totalDrink) {
        this.totalDrink = totalDrink;
    }


    public String toString(){
        return "Order Number: " + orderNumber + " for " + customerName +  " has " + totalDrink + " drink(s)";
    }
} //print order
