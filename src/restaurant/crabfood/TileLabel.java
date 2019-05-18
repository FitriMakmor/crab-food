/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import javax.swing.JLabel;

/**
 *
 * @author Fitri
 */
public class TileLabel extends JLabel{
    private int property;
    
    public TileLabel(int num){
        property=num;
    }
    
    public int getProperty(){
        return property;
    }
}
