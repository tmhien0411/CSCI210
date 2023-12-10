import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Random;

/**
 * <h2>Main.java - Create 10 job object and assigned the job into a copier to print all the data.</h2>
 * <p>Description:</p>
 * <p style="margin-left: 25px;">Assigning 10 job into its queue and put the job into the copier and print. Every time printing, the copier can only print 10 pages</p>
 *
 * <p>Algorithm:</p>
 * <ol style="margin-left: 25px;">
 *   <li>Create a Menu array of the size 10</li>
 *   <li>Create an array StringOfName</li>
 *   <li>Create an ArrayList String random names </li>
 *   <li>Create an array of String customer info (name)</li>
 *   <li>Create an array int to count the number of drink sold a day for every drink on the menu (will find the favorite drink by customer)</li>
 *   <li>Create an int variable order to count the no. order</li>
 *   <li>Create an int variable count to count the number receipt need to be print</li>
 *   <li>Create a random int variable workerToday to get the number of worker work for today</li>
 *   <li>Create a random int variable numberOfOrderToday to get the number of order will be make (print) today</li>
 *   <li>Display the Menu list</li>
 *   <li>while order less than numberOfOrderToday</li>
 *      <ul>
 *          <li>Create a random int numberOfDrinkOrder to get the number of cups (sold) per order (receipt)</li>
 *          <li>Create a random int randomIndexForCustomerName to get the index of random customer name from the array String listOfName</li>
 *          <li>Create an index to get the index to put into the queue</li>
 *              <ul>
 *                  <li>To get the index use the method <code>determineQueueIndex</code></li>
 *              </ul>
 *          <li>Create a PrintOrder job to put the order no., customer name, numberOfDrinkOrder</li>
 *          <li>Then add the job just created into the PrintOrder</li>
 *          <li>Now put the customer name into the customerInfo array</li>
 *          <li>Print to state which queue order (receipt) need to add into the PrintReceiptQueue array</li>
 *          <li>Print the receipt</li>
 *              <ul>
 *                  <li>To print receipt use the <code>printReceipt</code> and <code>estimateTime</code> methods</li>
 *              </ul>
 *          <li>Create a for loop to loop through the PrintReceiptQueue array to remove the order job after printing the receipt for customer</li>
 *          <li>Print to state which queue order (receipt) just remove from the PrintReceiptQueue array.</li>
 *      </ul>
 *   <li>Repeat until step 11 is false.</li>
 * </ol>
 *
 * <p>Instance variables:</p>
 * <ul style="margin-left: 25px;">
 *     <li>displayMenu() - Print the Menu drink of the shop</li>
 *     <li>readUsingBufferedReader() - Read the text file</li>
 *     <li>printReceipt() - Print the receipt</li>
 *     <li>estimateTime() - Estimate the time the order will be ready for the customer to pick up (depends on the number of worker on each day)</li>
 *     <li>payCash() - Print a pay cash method in the receipt</li>
 *     <li>payCard() - Print a pay card method in the receipt</li>
 *     <li>quickSort()- quickSort to sort the customer name of today's order list</li>
 *     <li>summaryOfADay() - Summary of today's worker, customer info, top drink sold, total cup, income of today</li>
 *     <li>determineQueueIndex (int) - a number from 1 to 3</li>
 * </ul>
 *
 * @author Hien Tran
 * @version Module 99, Programming Project
 */

public class Main {
    public static final double TIP_PERCENT = 0.15;
    public static final double TAX = 0.0725;
    public static final int MAX_QUEUE = 3;
    public static void main(String[] args) throws Exception {
        try{
            Random rand = new Random();
            Income earnPerOrder = new Income();
            Menu drinkMenu[] = new Menu[10];
            int order = 0;                  //count my order
            int count = 0;                  //count receipt

            //Create an array of randomNames
            String [] listOfName = {"Ace", "Mikey", "Juan", "Jay", "Max", "Catherine", "Lillie", "Justin", "Ben", "Angie", "Evelyn", "Cassie", "Paula", "Mimi", "Tina"};

            //Create a random number of employee (1-3) of today
            int workerToday = (rand.nextInt(3) + 1);

            //Create a random number of order for a day
            int numberOfOrderToday = (rand.nextInt(20) + 1);

            //Create an ArrayList of random names of customer (listOfName)
            ArrayList<String> randomNames = new ArrayList<>();
            //Now add the array listOfName into the ArrayList randomNames
            for (int i = 0; i < listOfName.length; i++){
                randomNames.add(listOfName[i]);
            }

            //Create an array PrintReceiptQueue that have a PrintOrder data type
            PrintReceiptQueue<PrintOrder>[] queueOrder = new PrintReceiptQueue[MAX_QUEUE];
            for (int i = 0; i < MAX_QUEUE; i++){
                queueOrder[i] = new PrintReceiptQueue<>(i + 1);
            }

            //Array customer name (will be sorted later)
            String [] customerInfo = new String[numberOfOrderToday];

            //Array of count drink sold a day for every drink (will find the most order (favorite) drink by customer)
            int [] countOrderedByCustomerForEveryDrink = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

            //Display our boba menu
            displayMenu(drinkMenu);

            System.out.println("Number of Order Today is: " + numberOfOrderToday);

            while(order < numberOfOrderToday){

                //Create random number of drink (put into print order) between 1-20
                int numberOfDrinkOrder = (rand.nextInt(20) + 1);

                //Create random index to get a random customer's name
                int randomIndexForCustomerName = (rand.nextInt(14));

                int index = determineQueueIndex(numberOfDrinkOrder);            //find a random index to put into the queue

                //Create the PrintOrder to add into the queueOrder
                PrintOrder newOrder = new PrintOrder((order + 1), randomNames.get(randomIndexForCustomerName), numberOfDrinkOrder);
                queueOrder[index].add(newOrder);

                //Put the customer name into the customerInfo
                customerInfo[order] = randomNames.get(randomIndexForCustomerName);
                System.out.println();

                System.out.println("Queue " + (index + 1) + " has " + ++count +  " receipt need to be print." );

                //Print Receipt
                printReceipt(drinkMenu, (order + 1), numberOfDrinkOrder ,earnPerOrder, randomNames, randomIndexForCustomerName, countOrderedByCustomerForEveryDrink, queueOrder, workerToday);
//                estimateTime(numberOfDrinkOrder, queueOrder, workerToday);

                //After print the receipt remove the printOrder job out of the queue
                for (PrintReceiptQueue<PrintOrder> queue : queueOrder){
                    if (!queue.isEmpty()){
                        queue.pop();            //delete the order after print the receipt
                    }
                }
                System.out.println("Queue " + (index + 1) + " has " + --count +  " receipt need to be print." );
                order++;
            }

            System.out.println();
            //Print the summary of today
            summaryOfADay(drinkMenu, customerInfo, earnPerOrder, countOrderedByCustomerForEveryDrink, workerToday);              //summary of a day (include customer info, revenue of the day)
        }catch (FileNotFoundException e){
            e.getMessage();
        }

    }//main

    public static void displayMenu(Menu drinkMenu[])throws Exception{

        System.out.println("        Welcome to Hien's Boba Shop");
        System.out.println("            Here is the Menu:");

        System.out.printf("%-8s %-33s %s %n","Number", "Drink Name", "Drink Price");
        System.out.println("------   ----------                        -----------");
        readUsingBufferedReader(drinkMenu);         //read the txt file

    }
    public static void readUsingBufferedReader(Menu[] drinkMenu) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader("src/MyBobaMenu.txt"));
        int i = 0;
        String line;
        reader.readLine(); // this will read the first line
        while((line = reader.readLine()) != null){
            StringTokenizer tokenizer = new StringTokenizer(line, "-");     //delimiter -
            while(tokenizer.hasMoreTokens()){
                int drinkNumber = Integer.parseInt(tokenizer.nextToken());              //read drink no.
                String drinkName = tokenizer.nextToken("-");                      //read drink name
                double drinkPrice = Double.parseDouble(tokenizer.nextToken());          //read drink price
                drinkMenu[i] = new Menu(drinkNumber,drinkName, drinkPrice);             //add the Menu class to drink menu array
            }
            System.out.println(drinkMenu[i].getDrinkNumber() + "\t\t" + drinkMenu[i].getDrinkName() + "\t" + "$" + drinkMenu[i].getDrinkPrice());
            i++;
        }
        reader.close();
    }

    public static void printReceipt(Menu []drinkMenu, int order, int numberOfDrinkOrder, Income earnPerOrder, ArrayList<String> names, int randomIndexForCustomerName, int [] countOrderedByCustomerForEveryDrink,
                                    PrintReceiptQueue<PrintOrder>[] queueOrder, int workerToday){
        Random rand = new Random();
        System.out.printf("%s%n", "---------------------------------------------");          //45
        System.out.printf("|%30s%14s%n", "Hien's Boba Shop", " |");
        System.out.printf("|%25s%-5d%14s%n", "Order: ", order, " |");
        System.out.printf("|%7s%-20s%17s%n", "Name: ", names.get(randomIndexForCustomerName), " |");
        System.out.printf("|%7s%35s%2s%n", "Order", "Price", " |");

        //Print drink name
        String orderedDrinkName[] = new String[numberOfDrinkOrder];             //create an array to hold drink name
        double orderedDrinkPrice[] = new double[numberOfDrinkOrder];            //create an array to hold drink price
        double totalPrice = 0;
        for (int i = 0; i < numberOfDrinkOrder; i++){
            int randomNoDrink = (rand.nextInt(10));                                     // random number of the drink on menu
            countOrderedByCustomerForEveryDrink[randomNoDrink] += 1;
            orderedDrinkName[i] = drinkMenu[randomNoDrink].getDrinkName();                    // create random drink name
            orderedDrinkPrice[i] = drinkMenu[randomNoDrink].getDrinkPrice();                  // create random drink price
            totalPrice += drinkMenu[randomNoDrink].getDrinkPrice();                           // add up the drink price
            System.out.printf("|%s%s%.2f%3s%n", orderedDrinkName[i], "$", orderedDrinkPrice[i], " |");
        }
        //System.out.println("Total: " + numberOfDrinkOrder);
        System.out.printf("|%44s%n|%8s%-5d%31s%n", "|", "Total: ", numberOfDrinkOrder, " |");
        System.out.printf("|%44s%n|%12s%25s%-6.2f%s%n", " |", "Subtotal ", "$", totalPrice, "|");
        double tip = totalPrice * TIP_PERCENT;                                                  //find tip from the total price
        double tax = totalPrice * TAX;                                                          //find tax from the total price
        System.out.printf("|%11s%26s%-6.2f%s%n", "Tip 15 %", "$", tip, "|");
        System.out.printf("|%19s%18s%-6.2f%s%n", "Sales tax 7.25 %", "$", tax, "|");
        totalPrice += (tip + tax);                                                              //find total price plus tip and tax
        System.out.printf("|%8s%29s%-6.2f%s%n", "Total", "$", totalPrice, "|");
        earnPerOrder.updateEarnPerOrder(totalPrice);          //update the total price per order plus tip and tax
        earnPerOrder.updateIncomePerDay(totalPrice);          //update the total price per day plus tip and tax
        boolean isCash = rand.nextBoolean();            //random way to pay
        if (isCash){
            payCash(totalPrice);                        //paying by cash
        }else{
            payCard(totalPrice);                        //paying by card
        }
        System.out.printf("|%26s%18s%n", "Thank You!", " |");
        estimateTime(numberOfDrinkOrder, queueOrder, workerToday);
    }

    public static void payCash(double totalPrice){
        Random rand = new Random();
        int roundPrice = (int) Math.round(totalPrice);
        int cash = (rand.nextInt(roundPrice + 20) + roundPrice);                    //Create random bill
        double change = cash - totalPrice;                                                 //Get change
        System.out.printf("|%44s%n|%7s%29s%-3d.00%s%n", "|", "Cash", "$", cash, " |");
        System.out.printf("|%9s%27s%-6.2f%2s%n", "Change", "$", change, " |");
    }
    public static void payCard(double totalPrice){
        System.out.printf("|%44s%n|%7s%30s%-6.2f%s%n", "|", "Card", "$", totalPrice, "|");
    }
    public static void estimateTime(int numberOfDrinkOrder, PrintReceiptQueue<PrintOrder>[] queues, int workerToday){
        double findTime = 0;
        int time = 0;
        //Each drink take 2 minutes to make
        while(numberOfDrinkOrder > 0){
            findTime += 2;
            numberOfDrinkOrder--;
        }
        time = (int) Math.round(findTime/workerToday);
        if (time < 2){
            time = 2;
        }
        System.out.printf("|%42s%s%n", "Your order will be ready to pick up in ", " |");
        System.out.printf("|%17d%s%18s%n", time, " minutes.", " |");
        System.out.printf("%s%n", "---------------------------------------------");          //45
    }

    private static <E extends Comparable<E>> void quickSort(E[] theArray) {
        // Sort the array using the quick sort algorithm
        Quicksort algorithm = new Quicksort() ;
        algorithm.sort(theArray) ;                  //quick sort
        for (E item: theArray) {
            System.out.print(item + " ") ;
        }
        System.out.println() ;
    }

    public static void summaryOfADay(Menu [] menu, String [] customerInfo, Income earnPerOrder, int [] countOrderedByCustomerForEveryDrink, int workerToday){
        int totalCupSoldToday = 0;
        for (int i = 0; i < countOrderedByCustomerForEveryDrink.length; i++){
            totalCupSoldToday += countOrderedByCustomerForEveryDrink[i];                //find the totalCupSoldToday by adding all the drink order by every customer (receipt)
        }

        //Search for the most ordered drink of today by customer
        int largest = countOrderedByCustomerForEveryDrink[0];
        int indexHolder = 0;
        for (int i = 1; i < 10; i++){
            if (largest < countOrderedByCustomerForEveryDrink[i]){
                largest = countOrderedByCustomerForEveryDrink[i];
                indexHolder = i;
            }
        }

        System.out.println("\nSummary Revenue of Today");
        System.out.println("________________________\n");
        System.out.println("Worker today: " + workerToday);
        System.out.print("Customer Info (list in alphabetically: ");                        //Customer info (name in alphabetically)
        quickSort(customerInfo);
        System.out.println("Top drink of today (most order drink): " + menu[indexHolder].getDrinkName());   //use quick sort to sort the customer name
        System.out.println("Total cup sold today: " + totalCupSoldToday);
        System.out.printf("%s%.2f%n", "Income per day: ", earnPerOrder.getIncomePerDay());  //Total income per day
    }

    public static int determineQueueIndex(int numberOfDrinkOrder)
    {
        if (numberOfDrinkOrder >= 1 && numberOfDrinkOrder <= 5) {           //total drinks between 1 and 5 added to queue 1
            return 0;
        } else if (numberOfDrinkOrder >= 6 && numberOfDrinkOrder <= 10) {   //total drinks between 6 and 10 added to queue 2
            return 1;
        } else {                                            //total drinks greater than 11 added to queue 3
            return 2;
        }

    }

}//class