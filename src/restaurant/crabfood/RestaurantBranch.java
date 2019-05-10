/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fitri
 */
class timeLog2 extends TimerTask {

    private int time;

    public timeLog2(int startTime) {
        this.time = startTime;
    }

    @Override
    public void run() {
        time++;
    }

    public int getTime() {
        return time;
    }

}

public class RestaurantBranch implements Runnable {

    private static int customerNo = 0;
    private static final HashMap dishTime = new HashMap();
    private static final HashMap dishPrice = new HashMap();
    private static Queue orderList = new Queue();
    private boolean cookingState = false;
    private int totalTime;
    private int startTime;
    Timer timer;
    timeLog2 task;
    Customer customer;

    public RestaurantBranch() {
    }

    public RestaurantBranch(int time, Customer customer) {
        timer = new Timer();
        task = new timeLog2(time);
        customerNo++;
        startTime = time;
        this.customer = customer;
    }

    public void setDish(String dishName, int time, double price) {
        dishTime.put(dishName, time);
        dishPrice.put(dishName, price);
    }

    public int getTime(String dishName) {
        return (int) dishTime.get(dishName);
    }

    public double getPrice(String dishName) {
        return (double) dishPrice.get(dishName);
    }

    public int getStartTime() {
        return startTime;
    }

    public void setDishes(String[] dishText) {
        for (int i = 0; i < 6; i += 2) {
            String timePrice[] = dishText[i + 1].split(",");
            setDish(dishText[i], Integer.parseInt(timePrice[0]), Double.parseDouble(timePrice[1]));
        }
        totalTime = totalTime();
    }

    public boolean order(String dishName) {
        if (dishTime.containsKey(dishName) && cookingState == false) {
            orderList.add(dishName);
            System.out.println("Order successful!");
            return false;
        } else if (dishTime.containsKey(dishName) && cookingState == true) {
            System.out.println("Kitchen is Busy!");
            return true;
        } else {
            System.out.println("Dish not available in the menu!");
            return true;
        }
    }

    public int totalTime() {
        int[] chefs = {0, 0, 0};
        int time = 0;
        if (orderList.size() > 3) {
            for (int i = 0; i < 3; i++) {
                chefs[i] += (int) dishTime.get(orderList.get(i));
            }
            for (int i = 3; i < orderList.size(); i++) {
                if (chefs[0] <= chefs[1] && chefs[0] <= chefs[2]) {
                    chefs[0] += (int) dishTime.get(orderList.get(i));
                } else if (chefs[1] <= chefs[2]) {
                    chefs[1] += (int) dishTime.get(orderList.get(i));
                } else {
                    chefs[2] += (int) dishTime.get(orderList.get(i));
                }
            }
            if (chefs[0] >= chefs[1] && chefs[0] >= chefs[2]) {
                time = chefs[0];
            } else if (chefs[1] >= chefs[2]) {
                time = chefs[1];
            } else {
                time = chefs[2];
            }

        } else {
            for (int i = 0; i < orderList.size(); i++) {
                if (time < (int) dishTime.get(orderList.get(i))) {
                    time = (int) dishTime.get(orderList.get(i));
                }
            }
        }
        return time;
    }

    public boolean isCooking() {
        return cookingState;
    }

    public boolean isEmpty() {
        return orderList.isEmpty();
    }

    private void cook(String dishName, int time) throws InterruptedException {
        for (int i = 1; i <= time; i++) {
            System.out.println("Preparing " + dishName + ": " + i + " second(s) has elapsed"); //TO BE DISPLAYED
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(dishName + " has successfully been cooked!"); //TO BE DISPLAYED
        time = task.getTime() - startTime;
        if (orderList.isEmpty() && (time >= (totalTime - 1))) {
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public void startChef() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cookingState = true;
                String dish;
                while (!orderList.isEmpty()) {
                    dish = (String) orderList.removeFirst();
                    try {
                        cook(dish, (int) dishTime.get(dish));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RestaurantBranch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    @Override
    public void run() {
        timer.schedule(task, startTime, 1000);
        cookingState = true;
        int thisCustomer = customerNo;
        totalTime = totalTime();
        startChef();
        startChef();
        startChef();

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(RestaurantBranch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cookingState=false;
        System.out.println("Order finished for Customer " + thisCustomer + ", time is: " + (task.getTime()));
        System.out.println("Delivery from branch (x,x) to location (x,x) is now starting."); //WITH THE HELP OF MAP
        customer.setFinishedCookingTime(task.getTime());
        timer.cancel();
    }

}
