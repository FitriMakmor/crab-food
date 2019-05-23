/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author Fitri
 */
public class music {

    private static AudioPlayer MGP = AudioPlayer.player;
    private static AudioStream SFX;
    private static AudioStream BGM;

    public music() {
        AudioPlayer.player.stop(BGM);
        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("src/restaurant/crabfood/muz/loungeMusic.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
    }

    

    public static void sfx(String sfx) {
        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("src/restaurant/crabfood/muz/" + sfx + ".wav");
            SFX = new AudioStream(test);
            AudioPlayer.player.start(SFX);
        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
    }
}
