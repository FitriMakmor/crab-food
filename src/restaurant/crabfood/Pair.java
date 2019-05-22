/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

/**
 *
 * @author Tharin
 */
public class Pair {
    private int x,y,weight;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
        this.weight=weight;
    }
    
    public int[] toArray() {
        int[] temp = new int[2];
        temp[0] = this.x;
        temp[1] = this.y;
        return temp;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWeight() {
        return weight;
    }
    
    @Override
    public String toString(){
        return "["+x+","+y+"]"+" - "+weight;
        
    }
    
}
