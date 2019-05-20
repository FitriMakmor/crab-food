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
    private int x,y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
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
    
}
