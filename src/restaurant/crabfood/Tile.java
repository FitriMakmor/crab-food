/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;
/**
 *
 * @author Fitri
 */
public class Tile {
    
    private float x, y, width, height;
    private Texture texture;
    private TileType type;

    
    public Tile(float x, float y, float width, float height, TileType type){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.texture = QuickLoad(type.textureName);
    }
    
    public void Draw(){
        DrawQuadTex(texture,x,y,width,height);
    }
    public Texture getTexture() {
        return texture;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public float getWidth() {
        return width;
    }

    public TileType getType() {
        return type;
    }

    public float getHeight() {
        return height;
    }
    
    
    
    
}
