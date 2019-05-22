/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import static restaurant.crabfood.OrderStatus.gif;
import static restaurant.crabfood.RMap.rmap;
import static restaurant.crabfood.Restaurant.astar;
import static restaurant.crabfood.Restaurant.innerMap;
import static restaurant.crabfood.Restaurant.status;
import static restaurant.crabfood.Restaurant.tile;
import static restaurant.crabfood.RestaurantCrabfood.customerX;
import static restaurant.crabfood.RestaurantCrabfood.customerY;

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

    private String restaurantName;
    private static int customerNo = 0;
    private static final HashMap dishTime = new HashMap();
    private static final HashMap dishPrice = new HashMap();
    private static Queue orderList = new Queue();
    private static Queue[] OrderLists = {new Queue(), new Queue(), new Queue(), new Queue(), new Queue()};
    private static int listIndex = 0;
    private boolean cookingState = false;
    private int totalTime;
    private int startTime;
    Timer timer;
    timeLog2 task;
    Customer customer;
    private ImageIcon deliveryImg;

    public RestaurantBranch(String name) {
        this.restaurantName = name;
    }

    public RestaurantBranch(String name, int time, Customer customer) {
        this.restaurantName = name;
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

    public void emptyList() {
        while (!isEmpty()) {
            orderList.remove();
        }
    }

    private void cook(String dishName, int time, Queue list, JTextArea chef) throws InterruptedException {
        for (int i = 1; i <= time; i++) {
            chef.setText("Preparing " + dishName + ": " + i + " second(s) has elapsed"); //TO BE DISPLAYED
            TimeUnit.SECONDS.sleep(1);
        }
        chef.setText(dishName + " has successfully been cooked!"); //TO BE DISPLAYED
        time = task.getTime() - startTime;
        if (list.isEmpty() && (time >= (totalTime - 1))) {
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public void startChef(Queue list, JTextArea chef) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cookingState = true;
                String dish;
                while (!list.isEmpty()) {
                    dish = (String) list.removeFirst();
                    try {
                        cook(dish, (int) dishTime.get(dish), list, chef);
                    } catch (InterruptedException | NullPointerException ex) {
                        Logger.getLogger(RestaurantBranch.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }).start();
    }

    @Override
    public void run() {
        System.out.println(restaurantName);
        switch (restaurantName) {
            case "Crusty Crab":
                deliveryImg = new ImageIcon(getClass().getResource("img/deliveryManKK.jpeg"));
                break;
            case "Phum Bucket":
                deliveryImg = new ImageIcon(getClass().getResource("img/deliveryManCB.jpeg"));
                break;
            case "Burger Krusty":
                deliveryImg = new ImageIcon(getClass().getResource("img/deliveryManWH.jpeg"));
                break;
        }
        Image dvimg = deliveryImg.getImage();
        Image newdvimg = dvimg.getScaledInstance(117, 66, java.awt.Image.SCALE_SMOOTH);
        deliveryImg = new ImageIcon(newdvimg);

        timer.schedule(task, startTime, 1000);
        cookingState = true;

        int thisCustomer = customerNo;
        int thisCustomerX = customerX;
        int thisCustomerY = customerY;

        ArrayList<Pair> astar = innerMap.BestBranch(restaurantName, thisCustomerX, thisCustomerY);

        totalTime = totalTime();
        while (!orderList.isEmpty()) {
            OrderLists[listIndex].addLast(orderList.removeFirst());
        }
        startChef(OrderLists[listIndex], OrderStatus.firstChef[thisCustomer - 1]);
        startChef(OrderLists[listIndex], OrderStatus.secondChef[thisCustomer - 1]);
        startChef(OrderLists[listIndex], OrderStatus.thirdChef[thisCustomer - 1]);
        URL url1 = getClass().getResource("img/cooking.gif");
        ImageIcon imageIcon1 = new ImageIcon(url1);
        gif[thisCustomer - 1].setIcon(imageIcon1);
        if (listIndex < 4) {
            listIndex++;
        } else {
            listIndex = 0;
        }

        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(RestaurantBranch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cookingState = false;
        URL url2 = getClass().getResource("img/finished.gif");
        ImageIcon imageIcon2 = new ImageIcon(url2);
        gif[thisCustomer - 1].setIcon(imageIcon2);
        status.append("\n" + task.getTime() + ": Order finished for Customer " + thisCustomer);

        Collections.reverse(astar);
        System.out.println(astar);
        int branchX = astar.get(1).getX();
        int branchY = astar.get(1).getY();
//        String branch = astar.get(listIndex);
        customer.setDeliveryTime(astar.get(2).getX() + 1);

        for (int i = 0; i < 4; i++) {
            astar.remove(0);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Icon icon;
                    while (!astar.isEmpty()) {
                        icon = tile[astar.get(0).getX()][astar.get(0).getY()].getIcon();
                        tile[astar.get(0).getX()][astar.get(0).getY()].setIcon(deliveryImg);
                        TimeUnit.SECONDS.sleep(rmap[astar.get(0).getX()][astar.get(0).getY()].getDifficulty());
                        tile[astar.get(0).getX()][astar.get(0).getY()].setIcon(icon);
                        astar.remove(0);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(RestaurantBranch.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        status.append("\n" + task.getTime() + ": Delivery from branch (" + branchX + "," + branchY + ") to location (" + thisCustomerX + "," + thisCustomerY + ") is now starting.");
        customer.setFinishedCookingTime(task.getTime());
        timer.cancel();
    }

}
