/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import static restaurant.crabfood.BootMap.HEIGHT;
import static restaurant.crabfood.BootMap.WIDTH;

/**
 *
 * @author Fitri
 */
public class Artist {

    public static final int WIDTH = 1280, HEIGHT = 960;

    public static void BeginSession() {
        Display.setTitle("Restaurant map");
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        } catch (LWJGLException ex) {
            ex.printStackTrace();
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1); //Set up camera for the screen, parameters from the first, left, right, top, bottom, 3d
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D); //Allows drawing of textures
    }

    public static void DrawQuad(float x, float y, float width, float height) {
        glBegin(GL_QUADS);
        glVertex2f(x, y); //Top left
        glVertex2f(x + width, y); //Top right
        glVertex2f(x + width, y + width); //Bottom right
        glVertex2f(x, y + width); //Bottom left
        glEnd();
    }

    public static void DrawQuadTex(Texture tex, float x, float y, float width, float height) {
        tex.bind();
        glTranslatef(x, y, 0); //0 because 2D
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        glTexCoord2f(1, 0);
        glVertex2f(width, 0);
        glTexCoord2f(1, 1);
        glVertex2f(width, height);
        glTexCoord2f(0, 1);
        glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }

    public static Texture LoadTexture(String path, String fileType) {
        Texture tex = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture(fileType, in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tex;
    }
    
    public static Texture QuickLoad(String name){
        Texture tex = null;
        tex = LoadTexture("res/" +name+".png","PNG");
        return tex;
    }
}
