/**
 * <h2>Menu.java - Describes the menu.</h2>
 * <p>Description:</p>
 * <p style="margin-left: 25px;">Print the drink number, name and price .</p>
 *
 * <p>Instance variables:</p>
 * <ul style="margin-left: 25px;">
 *   <li>drinkNumber (int) number belongs to the drink.</li>
 *   <li>drinkName (String) name of the drink ordered.</li>
 *   <li>drinkPrice (double) price of the drink ordered</li>
 * </ul>
 *
 * <p>Methods:</p>
 * <ul style="margin-left: 25px;">
 *     <li>getDrinkNumber (double)- </li>
 *     <li>getDrinkName (double)- </li>
 *     <li>getDrinkPrice- </li>
 *     <li>setDrinkNumber- </li>
 *     <li>setDrinkName- </li>
 *     <li>setDrinkPrice- </li>
 *     <li>toString- </li>
 *     <li>equals- </li>
 * </ul>
 * @author Hien Tran
 * @version Module 99, Programming Project
 */
public class Menu {


    //Instance variable
    private int drinkNumber;
    private String drinkName;
    private double drinkPrice;

    /**
     * Full constructors- a Menu object with the drink name and price
     * @param drinkNumber the number of the drink
     * @param drinkName the name of the drink
     * @param drinkPrice the price of the drink
     */
    public Menu(int drinkNumber, String drinkName, double drinkPrice){
        this.drinkNumber = drinkNumber;
        this.drinkName = drinkName;
        this.drinkPrice = drinkPrice;
    }

    /**
     * Getter for the drink number
     * @return- the number of the drink
     */
    public int getDrinkNumber(){
        return drinkNumber;
    }

    /**
     * Getter for the drink name
     * @return- the name of the drink
     */
    public String getDrinkName(){
        return drinkName;
    }

    /**
     * Getter for the drink price
     * @return the price of the drink
     */
    public double getDrinkPrice(){
        return drinkPrice;
    }

    /**
     * Setter for the drink number
     * @param drinkNumber
     */
    public void setDrinkNumber(int drinkNumber){
        this.drinkNumber = drinkNumber;
    }

    /**
     * Setter for the drink name
     * @param drinkName
     */
    public void setDrinkName(String drinkName){
        this.drinkName = drinkName;
    }

    /**
     * Setter for the drink price
     * @param drinkPrice
     */
    public void setDrinkPrice(double drinkPrice){
        this.drinkPrice = drinkPrice;
    }

    /**
     * Shows all instance variables
     * @return <code>String</code> showing all instance variables for the menu
     */
    public String toString(){
        return "Drink number: " + getDrinkNumber() + "Drink name: " + getDrinkName() + "\tDrink price: "  + getDrinkPrice();
    }

    /**
     * Compares all fields
     * @param anObject anotherobject of the <code>Menu</code> class to be compared to this <code>Menu</code>
     * @return true of the parameter drink name and price are the same as those of another object
     */
    public boolean equals(Object anObject){
        if (anObject != null && getClass() == anObject.getClass()){
            Menu menu = (Menu) anObject;
            return drinkNumber == menu.getDrinkNumber() && drinkName.equals(menu.getDrinkName()) && drinkPrice == menu.getDrinkPrice();
        }
        return false;
    }

}
