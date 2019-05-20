/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tharin
 */
public class CrabFood {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        RMap test = new RMap();
        ArrayList<Pair> xxx=test.A_star(0, 0, 5, 5);
        test.showmap(xxx);
        }
        // TODO code application logic here
    }
    

