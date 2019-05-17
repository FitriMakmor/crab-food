/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import static helpers.Artist.*;

/**
 *
 * @author Fitri
 */
public class TileGrid {

    public Tile[][] map;

    public TileGrid() {
        map = new Tile[20][15];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Sand);
            }
        }
    }

    public TileGrid(int[][] newMap) {
        map = new Tile[20][15];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (newMap[j][i] == 0) {
                    map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Sand);
                }else{
                    map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Path);
                }
            }
        }
    }

    public void Draw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Tile t = map[i][j];
                DrawQuadTex(t.getTexture(), t.getX(), t.getY(), t.getWidth(), t.getHeight());
            }
        }
    }
}
