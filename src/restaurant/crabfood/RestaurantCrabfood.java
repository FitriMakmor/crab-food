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
class timeLog extends TimerTask{

    public static int time=0;
    
    @Override
    public void run() {
        System.out.println("Time: "+ ++time);
    }
    
    public int getTime(){
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
        
        Timer timer = new Timer();
        timeLog task = new timeLog();
        timer.schedule(task,0,1000);

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
                if(sc.hasNextLine()){
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
        
        crustyCrab.order("Crabby Patty");
        crustyCrab.order("Crabby Meal");
        crustyCrab.order("Sailors Surprise");
        crustyCrab.order("Sailors Surprise");
        crustyCrab.order("Crabby Meal");
        
        
        System.out.println("Total time required to prepare dish: "+crustyCrab.totalTime());
        
        Thread chef1 = new Thread(crustyCrab);
        chef1.start();
        Thread chef2 = new Thread(crustyCrab);
        chef2.start();
        Thread chef3 = new Thread(crustyCrab);
        chef3.start();
    }

}
