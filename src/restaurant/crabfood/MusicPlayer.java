/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.io.*;

import javax.sound.sampled.*;

/**
 *
 * @author Fitri
 */
public class MusicPlayer {

    public static void sfx(String sfx) {
        try {
            File audioFile = new File("src/restaurant/crabfood/muz/" + sfx + ".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
