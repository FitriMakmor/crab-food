/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Fitri
 */
class timeLog extends TimerTask {

    public static int time = 0;

    @Override
    public void run() {
        System.out.println("Time: " + ++time); //TO BE DISPLAYED
    }

    public int getTime() {
        return time;
    }

}

public class RestaurantCrabfood {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        String[] dishDetails = new String[6];
        RestaurantBranch[] crustyCrab = {new RestaurantBranch(), new RestaurantBranch(), new RestaurantBranch()};
        RestaurantBranch[] phumBucket = {new RestaurantBranch(), new RestaurantBranch(), new RestaurantBranch()};
        RestaurantBranch[] burgerKrusty = {new RestaurantBranch(), new RestaurantBranch(), new RestaurantBranch()};
        boolean busyKitchen;
        int branchIndex=0;
        Thread order;

        Timer timer = new Timer();
        timeLog task = new timeLog();
        int time = 0;

        Scanner input = new Scanner(System.in);
        int choice = 1;

        int customerNo = 0;
        Customer customer;
        ArrayList Customer = new ArrayList();

        try {
            Scanner sc;
            for (int i = 0; i < 3; i++) {
                 sc = new Scanner(new FileInputStream("Input.txt"));
                int company = 1;
                while (sc.hasNextLine()) {
                    for (int j = 0; j < 4; j++) {
                        sc.nextLine();
                    }
                    for (int j = 0; j < 6; j++) {
                        dishDetails[j] = sc.nextLine();
                    }
                    if (sc.hasNextLine()) {
                        sc.nextLine();
                    }
                    switch (company) {
                        case 1:
                            crustyCrab[i].setDishes(dishDetails);
                            break;
                        case 2:
                            phumBucket[i].setDishes(dishDetails);
                            break;
                        case 3:
                            burgerKrusty[i].setDishes(dishDetails);
                            break;
                    }
                    company++;
                }
            sc.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }

        timer.schedule(task, time, 1000);

//START APPLICATION
        do {
            
            customerNo++;
            Customer.add(customer = new Customer(task.getTime()));
            System.out.println("Welcome to CrabFood Inc.! Choose your Restaurant\n(Display restaurants -(1) to continue)"); //TO BE REPLACED WITH BUTTON
            input.nextInt();
            String restaurant = "Crusty Crab"; //TO BE REPLACED WITH BUTTON
            System.out.println("You have chosen: " + restaurant + ".\n Place your orders now. (Press 1 to continue)"); //TO BE REPLACED WITH BUTTON
            input.nextInt();

            crustyCrab[branchIndex].order("Crabby Patty"); //TO BE REPLACED WITH BUTTON
            crustyCrab[branchIndex].order("Crabby Meal"); //TO BE REPLACED WITH BUTTON
            crustyCrab[branchIndex].order("Sailors Surprise"); //TO BE REPLACED WITH BUTTON
            busyKitchen = crustyCrab[branchIndex].order("Crabby Patty"); //TO BE REPLACED WITH BUTTON
            if (busyKitchen) {
                Customer.remove(--customerNo);
                continue;
            }

            switch (restaurant) {
                case "Crusty Crab":
                    System.out.println("Total time required to prepare dish: " + crustyCrab[branchIndex].totalTime());
                    break;
                case "Phum Bucket":
                    System.out.println("Total time required to prepare dish: " + phumBucket[branchIndex].totalTime());
                    break;
                case "Burger Krusty":
                    System.out.println("Total time required to prepare dish: " + burgerKrusty[branchIndex].totalTime());
                    break;
            }
            System.out.println("Branch "+branchIndex+" shall take the order."); //WITH THE HELP OF MAP

            System.out.println("Proceed? (1) to continue"); //TO BE REPLACED WITH BUTTON
            input.nextInt();
            customer.setOrderTime(task.getTime());
            switch (restaurant) {

                case "Crusty Crab":
                    crustyCrab[branchIndex] = new RestaurantBranch(task.getTime(), customer);
                    order = new Thread(crustyCrab[branchIndex]);
                    order.start();
                    break;
                case "Phum Bucket":
                    phumBucket[branchIndex] = new RestaurantBranch(task.getTime(), customer);
                    order = new Thread(phumBucket[branchIndex]);
                    order.start();
                    break;
                case "Burger Krusty":
                    burgerKrusty[branchIndex] = new RestaurantBranch(task.getTime(), customer);
                    order = new Thread(burgerKrusty[branchIndex]);
                    order.start();
                    break;
            }
            time = task.getTime();
            System.out.println("Press (1) to order again, press any other int value to exit");
            choice = input.nextInt(); //TO BE REPLACED WITH BUTTON
            if(branchIndex!=2){
            branchIndex++;
            }else{
                branchIndex=0;
            }
        } while (choice == 1);

        System.out.println("SYSTEM ENDS");
        timer.cancel();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame(Customer);
            }

        });

    }
}
