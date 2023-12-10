import java.util.LinkedList;
import java.util.Queue;

/**
 * <h2>PrintReceiptQueue.java - represents one of three print queues in the simulation.</h2>
 * <p>Description:</p>
 * <p style="margin-left: 25px;">A print job can be assigned into three queues depending on the number of cups need to be make</p>
 *
 * <p>Instance variables:</p>
 * <ul style="margin-left: 25px;">
 *   <li>queueNumber (int) - a number from 1 to 3</li>
 *   <li>maximumCups (int)- the number of cup order by the customer</li>
 * </ul>
 *
 * <p>Methods:</p>
 * <ul style="margin-left: 25px;">
 *   <li>getterQueueNumber (int) - a number from 1 to 3</li>
 *   <li>setterQueueNumber - set the queue number</li>
 *   <li>equals (boolean) - queue number equal to maximum cups</li>
 *   <li>toString - returning a message contains queue number and maximum cups</li>
 * </ul>
 *
 * @author Hien Tran
 * @version Module 99, Programming Project
 */
public class PrintReceiptQueue<PrintOrder> extends LinkedList<PrintOrder> implements Queue<PrintOrder> {
    private int queueNumber ;   //3 queues

    private int maximumCups;    //(queue 1 = 5; queue 2 = 10, queue 3 = 20)


    public PrintReceiptQueue(int queueNumber){
        this.queueNumber = queueNumber;

    }

    /**
     * @return queueNumber
     */
    public int getterQueueNumber(){
        return queueNumber;
    }

    /**
     * Set a value to queueNumber
     * @param queueNumber
     */
    public void settlerQueueNumber(int queueNumber){
        this.queueNumber = queueNumber;
    }

    /**
     * Compare queueNumber and maximumCups
     * @return
     */
    public boolean equals(){
        return queueNumber == maximumCups;
    }



    public String toString(){
        String stringToReturn = "Queue " + queueNumber;
        //if (this.size() == 0){
        if (this.size() == 0){
            stringToReturn += " is done printing";
        }else{
            stringToReturn += " has " + this.size() + " orders to be print";
        }
        return stringToReturn;
    }


}
