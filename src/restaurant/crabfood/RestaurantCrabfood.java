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
     */
    public static void main(String[] args) throws InterruptedException {
        String[] dishDetails = new String[6];
        RestaurantBranch crustyCrab = new RestaurantBranch();
        RestaurantBranch phumBucket = new RestaurantBranch();
        RestaurantBranch burgerKrusty = new RestaurantBranch();
        Thread order;

        Timer timer = new Timer();
        timeLog task = new timeLog();
        int time = 0;

        Scanner input = new Scanner(System.in);
        int choice;

        Customer customer;
        ArrayList Customer = new ArrayList();

        try {
            Scanner sc = new Scanner(new FileInputStream("Input.txt"));
            int company = 1;
            while (sc.hasNextLine()) {
                for (int i = 0; i < 4; i++) {
                    sc.nextLine();
                }
                for (int i = 0; i < 6; i++) {
                    dishDetails[i] = sc.nextLine();
                }
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
                switch (company) {
                    case 1:
                        crustyCrab.setDishes(dishDetails);
                        break;
                    case 2:
                        phumBucket.setDishes(dishDetails);
                        break;
                    case 3:
                        burgerKrusty.setDishes(dishDetails);
                        break;
                }
                company++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        
        timer.schedule(task, time, 1000);
        
//START APPLICATION
        do {
            Customer.add(customer=new Customer(task.getTime()));
            System.out.println("Welcome to CrabFood Inc.! Choose your Restaurant\n(Display restaurants -(1) to continue)"); //TO BE REPLACED WITH BUTTON
            input.nextInt();
            String restaurant = "Crusty Crab"; //TO BE REPLACED WITH BUTTON
            System.out.println("You have chosen: " + restaurant + ".\n Place your orders now. (Press 1 to continue)"); //TO BE REPLACED WITH BUTTON
            input.nextInt();

            crustyCrab.order("Crabby Patty"); //TO BE REPLACED WITH BUTTON
            crustyCrab.order("Crabby Meal"); //TO BE REPLACED WITH BUTTON
            crustyCrab.order("Sailors Surprise"); //TO BE REPLACED WITH BUTTON
            crustyCrab.order("Crabby Patty"); //TO BE REPLACED WITH BUTTON
            
            switch (restaurant) {
                case "Crusty Crab":
                    System.out.println("Total time required to prepare dish: " + crustyCrab.totalTime());
                    break;
                case "Phum Bucket":
                    System.out.println("Total time required to prepare dish: " + phumBucket.totalTime());
                    break;
                case "Burger Krusty":
                    System.out.println("Total time required to prepare dish: " + burgerKrusty.totalTime());
                    break;
            }
            System.out.println("Branch (x,x) shall take the order."); //WITH THE HELP OF MAP

            System.out.println("Proceed? (1) to continue"); //TO BE REPLACED WITH BUTTON
            input.nextInt();
            customer.setOrderTime(task.getTime());
            switch (restaurant) {

                case "Crusty Crab":
                    crustyCrab = new RestaurantBranch(task.getTime(),customer);
                    order = new Thread(crustyCrab);
                    order.start();
                    break;
                case "Phum Bucket":
                    phumBucket = new RestaurantBranch(task.getTime(),customer);
                    order = new Thread(phumBucket);
                    order.start();
                    break;
                case "Burger Krusty":
                    burgerKrusty = new RestaurantBranch(task.getTime(),customer);
                    order = new Thread(burgerKrusty);
                    order.start();
                    break;
            }
            time = task.getTime();
            System.out.println("Press (1) to order again, press any other int value to exit");
            choice = input.nextInt(); //TO BE REPLACED WITH BUTTON
        } while (choice == 1);

        System.out.println("SYSTEM ENDS");

    }
}
