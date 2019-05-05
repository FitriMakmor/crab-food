/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fitri
 */
public class RestaurantBranch implements Runnable {

    private final HashMap dishTime = new HashMap();
    private final HashMap dishPrice = new HashMap();
    private final Queue orderList = new Queue();
    private static boolean cookingState=false;

    public RestaurantBranch() {
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

    public void setDishes(String[] dishText) {
        for (int i = 0; i < 6; i += 2) {
            String timePrice[] = dishText[i + 1].split(",");
            setDish(dishText[i], Integer.parseInt(timePrice[0]), Double.parseDouble(timePrice[1]));
        }
    }

    public void order(String dishName) {
        if (dishTime.containsKey(dishName)) {
            orderList.add(dishName);
            System.out.println("Order successful!");
        } else {
            System.out.println("Dish not available in the menu!");
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
    
    public boolean isCooking(){
        return cookingState;
    }
    public boolean isEmpty(){
        return orderList.isEmpty();
    }

    private void cook(String dishName, int time) throws InterruptedException {
        for (int i = 1; i <= time; i++) {
            System.out.println("Preparing " + dishName + ": " + i + " second(s) has elapsed"); //TO BE DISPLAYED
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(dishName + " has successfully been cooked!"); //TO BE DISPLAYED
        if(orderList.isEmpty()){
            cookingState=false;
        }
    }
    
    @Override
    public void run() {
        String dish;
        while (!orderList.isEmpty()) {
            dish = (String) orderList.removeFirst();
            try {
                cook(dish, (int) dishTime.get(dish));
                cookingState=true;
            } catch (InterruptedException ex) {
                Logger.getLogger(RestaurantBranch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
