/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        Thread chef1;
        Thread chef2;
        Thread chef3;

        Timer timer = new Timer();
        timeLog task = new timeLog();
        int time = 0;

        Scanner input = new Scanner(System.in);
        int choice;

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
//START APPLICATION
        do {
            System.out.println("Welcome to CrabFood Inc.! Choose your Restaurant\n(Display restaurants -(1) to continue)"); //TO BE REPLACED WITH BUTTON
            input.nextInt();
            String restaurant = "Crusty Crab"; //TO BE REPLACED WITH BUTTON
            System.out.println("You have chosen: "+restaurant+".\n Place your orders now. (Press 1 to continue)"); //TO BE REPLACED WITH BUTTON
            input.nextInt();

            crustyCrab.order("Crabby Patty"); //TO BE REPLACED WITH BUTTON
            crustyCrab.order("Crabby Meal"); //TO BE REPLACED WITH BUTTON
            crustyCrab.order("Sailors Surprise"); //TO BE REPLACED WITH BUTTON
            crustyCrab.order("Crabby Patty"); //TO BE REPLACED WITH BUTTON
            crustyCrab.order("Crabby Meal"); //TO BE REPLACED WITH BUTTON

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
            System.out.println("Proceed? (1) to continue"); //TO BE REPLACED WITH BUTTON
            input.nextInt();
            timer.schedule(task, time, 1000);
            switch (restaurant) {

                case "Crusty Crab":
                    chef1 = new Thread(crustyCrab);
                    chef1.start();
                    chef2 = new Thread(crustyCrab);
                    chef2.start();
                    chef3 = new Thread(crustyCrab);
                    chef3.start();
                    chef1.join();
                    chef2.join();
                    chef3.join();
                    break;
                case "Phum Bucket":
                    chef1 = new Thread(phumBucket);
                    chef1.start();
                    chef2 = new Thread(phumBucket);
                    chef2.start();
                    chef3 = new Thread(phumBucket);
                    chef3.start();
                    chef1.join();
                    chef2.join();
                    chef3.join();
                    break;
                case "Burger Krusty":
                    chef1 = new Thread(burgerKrusty);
                    chef1.start();
                    chef2 = new Thread(burgerKrusty);
                    chef2.start();
                    chef3 = new Thread(burgerKrusty);
                    chef3.start();
                    chef1.join();
                    chef2.join();
                    chef3.join();
                    break;
            }
            time = task.getTime();
            System.out.println("ALL FINISHED! CURRENT TIME IS: " + time);
            timer.cancel();
            timer = new Timer();
            task = new timeLog();
            System.out.println("Press (1) to order again, press any other int value to exit");
            choice = input.nextInt(); //TO BE REPLACED WITH BUTTON
        } while (choice == 1);

    }
}
