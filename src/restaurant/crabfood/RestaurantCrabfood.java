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

    static RestaurantBranch[][] branch = {
        {new RestaurantBranch("Crusty Crab"), new RestaurantBranch("Crusty Crab"), new RestaurantBranch("Crusty Crab")},
        {new RestaurantBranch("Phum Bucket"), new RestaurantBranch("Phum Bucket"), new RestaurantBranch("Phum Bucket")},
        {new RestaurantBranch("Burger Krusty"), new RestaurantBranch("Burger Krusty"), new RestaurantBranch("Burger Krusty")},};

    static int branchIndex = 0;

    static Thread order;

    static timeLog task = new timeLog();

    static Customer customer;
    static int customerNo = 0;

    static ArrayList Customer = new ArrayList();

    static Timer timer = new Timer();

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

//        new BootMap();
        String[] dishDetails = new String[6];

        boolean busyKitchen;

        Scanner input = new Scanner(System.in);
        int choice = 1;

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
                            branch[0][i].setDishes(dishDetails);
                            break;
                        case 2:
                            branch[1][i].setDishes(dishDetails);
                            break;
                        case 3:
                            branch[2][i].setDishes(dishDetails);
                            break;
                    }
                    company++;
                }
                sc.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }

        timer.schedule(task, 0, 1000); //starts the timer at an interval of 1 second
        
        Main gui = new Main();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
        gui.setTitle("CRABFOOD");

//START APPLICATION
        do {

//            customerNo++;
//            Customer.add(customer = new Customer(task.getTime()));                    
//            String restaurant = "Crusty Crab"; //TO BE REPLACED WITH BUTTON
//            System.out.println("You have chosen: " + restaurant + ".\n Place your orders now. (Press 1 to continue)"); //TO BE REPLACED WITH BUTTON
//            input.nextInt();
//            busyKitchen = branch[0][branchIndex].order("Crabby Patty"); //TO BE REPLACED WITH BUTTON
//            if (busyKitchen) {
//                Customer.remove(--customerNo);
//                continue;
//            }
//
//            switch (restaurant) {
//                case "Crusty Crab":
//                    System.out.println("Total time required to prepare dish: " + branch[0][branchIndex].totalTime());
//                    break;
//                case "Phum Bucket":
//                    System.out.println("Total time required to prepare dish: " + branch[1][branchIndex].totalTime());
//                    break;
//                case "Burger Krusty":
//                    System.out.println("Total time required to prepare dish: " + branch[2][branchIndex].totalTime());
//                    break;
//            }
//            System.out.println("Branch "+branchIndex+" shall take the order."); //WITH THE HELP OF MAP
//            System.out.println("Proceed? (1) to continue"); //TO BE REPLACED WITH BUTTON
//            input.nextInt();
//            customer.setOrderTime(task.getTime());
//            switch (restaurant) {
//
//                case "Crusty Crab":
//                    branch[0][branchIndex] = new RestaurantBranch(task.getTime(), customer);
//                    order = new Thread(branch[0][branchIndex]);
//                    order.start();
//                    break;
//                case "Phum Bucket":
//                    branch[1][branchIndex] = new RestaurantBranch(task.getTime(), customer);
//                    order = new Thread(branch[1][branchIndex]);
//                    order.start();
//                    break;
//                case "Burger Krusty":
//                    branch[2][branchIndex] = new RestaurantBranch(task.getTime(), customer);
//                    order = new Thread(branch[2][branchIndex]);
//                    order.start();
//                    break;
//            }
//            System.out.println("Press (1) to order again, press any other int value to exit");
//            choice = input.nextInt(); //TO BE REPLACED WITH BUTTON
//            if(branchIndex!=2){
//            branchIndex++;
//            }else{
//                branchIndex=0;
//            }
        } while (choice == 1);

        System.out.println("SYSTEM ENDS");
        timer.cancel();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogFrame(Customer);
            }
        });

    }

    public void stopTimer() {
        timer.cancel();
    }

}
