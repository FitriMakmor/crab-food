/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Tharin
 */
public class CrabFood {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,1,1,1,1,1,1,1,0,1},
            {0,1,1,1,0,0,1,0,1,0,0,6,1,0,1},
            {0,1,7,1,1,1,1,0,1,0,0,0,1,0,1},
            {0,1,0,0,0,1,0,0,1,0,0,0,1,7,1},
            {0,1,0,0,0,1,0,7,1,0,0,1,1,1,1},
            {1,1,0,0,0,1,1,1,1,1,1,1,0,0,0},
            {1,0,0,1,1,1,0,0,1,5,0,1,1,0,0},
            {1,0,0,1,0,0,0,0,1,1,0,0,1,1,1},
            {1,0,0,1,5,0,0,0,0,1,1,0,0,0,1},
            {1,6,0,1,0,0,0,0,0,0,1,0,0,0,1},
            {1,1,1,1,1,1,1,6,0,0,1,0,0,1,1},
            {0,1,0,0,0,0,1,1,0,0,1,5,1,1,0},
            {0,1,0,0,0,0,0,1,1,1,1,1,1,0,0},
            {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };
        RMap nn = new RMap(map);
        int[][] test = nn.throwMap();
//        for(int i=0;i<test.length;i++) {
//            for(int j=0;j<test.length;j++) System.out.print(test[i][j]+" ");
//            System.out.println("");
//        }
        System.out.println("");
        ArrayList<Pair> astar = nn.BestBranch("Crusty Crab",1 , 1);
        
        nn.showmap(astar);
        Collections.reverse(astar);
        System.out.println(astar);
//        for(int i=0;i<astar.size();i++) {
//            System.out.println("Dx"+astar.get(i).getX()+" Dy"+astar.get(i).getY());
//        }
        }
        // TODO code application logic here
    }
    

