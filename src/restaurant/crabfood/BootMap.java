/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;
import org.newdawn.slick.opengl.Texture;
/**
 *
 * @author Fitri
 */
public class BootMap {

    public static final int WIDTH = 600, HEIGHT = 400;
    

    public BootMap() {
        
        BeginSession();

        int[][] map = {
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0},
            {0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
            {0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0},
            {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
        };
        
        TileGrid grid = new TileGrid(map);
        while (!Display.isCloseRequested()) {
                        
            grid.Draw();
            
            Display.update();
            Display.sync(60);

        }

        Display.destroy();
    }
}
