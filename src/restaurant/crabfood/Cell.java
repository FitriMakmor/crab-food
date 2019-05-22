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
public class Cell {
    private boolean isRestoraunt, isImPassable;
    private int CoX,CoY;
    private int difficulty;
    private String name="";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public Cell() {
        this.setIsImPassable(false);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    

    public boolean getIsImPassable() {
        return isImPassable;
    }

//    public void setHeuCost(int heuCost) {
//        this.heuCost = heuCost;
//    }

    public void setIsImPassable(boolean isImPassable) {
        this.isImPassable = isImPassable;
    }

    public int getCoX() {
        return CoX;
    }

    public int getCoY() {
        return CoY;
    }

//    public void setRestoraunt(boolean isRestoraunt,String name) {
//        this.isImPassable=true;
//        this.isRestoraunt = isRestoraunt;
//        this.NameRes = name;
//    }

    public void setCoX(int CoX) {
        this.CoX = CoX;
    }

    public void setCoY(int CoY) {
        this.CoY = CoY;
    }
    
    
    
}
