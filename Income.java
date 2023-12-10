/**
 * <h2>Income.java - Describes the income per order & day.</h2>
 * <p>Description:</p>
 * <p style="margin-left: 25px;">Update the money make per day.</p>
 *
 * <p>Instance variables:</p>
 * <ul style="margin-left: 25px;">
 *   <li>earnPerOrder (double) how much money earn per order.</li>
 *   <li>incomePerDay (double) how much money earn per day.</li>
 * </ul>
 *
 * <p>Methods:</p>
 * <ul style="margin-left: 25px;">
 *     <li>getEarnPerOrder (double)- </li>
 *     <li>getIncomePerDay (double)- </li>
 *     <li>updateEarnPerOrder- </li>
 *     <li>updateIncomePerDay- </li>
 *     <li>updateEarnPerOrder- </li>
 *     <li>resetIncomePerDay- </li>
 * </ul>
 * @author Hien Tran
 * @version Module 99, Programming Project
 */
public class Income {
    private double earnPerOrder;
    private double incomePerDay;

    public Income(){
        earnPerOrder = 0;
    }

    public Income(double earnPerOrder){
        this.earnPerOrder = earnPerOrder;
    }

    /**
     * Getter income for earn per order
     * @return earnPerOrder
     */
    public double getEarnPerOrder(){
        return earnPerOrder;
    }

    /**
     * Getter income earn per day
     * @return incomePerDay
     */
    public double getIncomePerDay(){
        return incomePerDay;
    }

    /**
     * Update income earn per order (add all price of each drink together)
     * @param earnPerOrder
     */
    public void updateEarnPerOrder(double earnPerOrder){
        this.earnPerOrder += earnPerOrder;
    }

    /**
     * Update income earn per day (add all earn per order together)
     * @param incomePerDay
     */
    public void updateIncomePerDay(double incomePerDay){
        this.incomePerDay += incomePerDay;
    }


    public void resetEarnPerOrder(){
        this.earnPerOrder = 0;
    }

    public void resetIncomePerDay(){
        this.incomePerDay = 0;
    }




}
