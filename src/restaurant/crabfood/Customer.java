/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

/**
 *
 * @author Fitri
 */
public class Customer {

    private final int arrivalTime;
    private int orderTime;
    private int finishedCookingTime;
    private int deliveryTime;

    public Customer(int arrive) {
        arrivalTime = arrive;
    }
    
    public void setOrderTime(int time){
        orderTime=time;
    }
    
    public void setFinishedCookingTime(int time){
        finishedCookingTime=time;
    }
    
    public void setDeliveryTime(int time){
        deliveryTime=time;
    }
    
    public int getOrderTime(){
        return orderTime;
    }
    
    public int getFinishedCookingTime(){
        return finishedCookingTime;
    }
    
    public int getDeliveryTime(){
        return deliveryTime;
    }
    
    public int getTotalTime(){
        return finishedCookingTime+deliveryTime-arrivalTime;
    }
    
    @Override
    public String toString(){
        return "Arrival time: "+arrivalTime+"\nOrder time: "+orderTime+"\nFinished cooking time: "+finishedCookingTime+"\nDelivery time: "+deliveryTime+"\nTotal time: "+getTotalTime()+"\n";
    }
}
