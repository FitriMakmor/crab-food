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

    static final int RESTAURANT_COMPANIES = 3;
    static final int RESTAURANT_BRANCHES = 4;
    static Coords[][] coords = new Coords[RESTAURANT_COMPANIES][RESTAURANT_BRANCHES];
    static String[] restName = new String[RESTAURANT_COMPANIES];

    static ArrayList Customer = new ArrayList();
    public static int customerX, customerY;

    static Timer timer = new Timer();

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        String[] dishDetails = new String[6];


        try {
            Scanner sc;

            sc = new Scanner(new FileInputStream("Input.txt"));
            int company = 1;
            while (sc.hasNextLine()) {
                for (int i = 0; i < RESTAURANT_COMPANIES; i++) {
                    restName[i] = sc.nextLine();
                    for(int j=0;j<RESTAURANT_BRANCHES;j++){
                    coords[i][j] = new Coords();
                    coords[i][j].setX(Integer.parseInt(sc.next()));
                    coords[i][j].setY(Integer.parseInt(sc.next()));
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
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }

        timer.schedule(task, 0, 1000); //starts the timer at an interval of 1 second
        
        Main gui = new Main();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLocationRelativeTo(null);
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gui.setVisible(true);
        gui.setTitle("Crab Food Inc. - Crab Crave for more");
    }

}
