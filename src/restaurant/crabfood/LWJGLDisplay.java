/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

/**
 *
 * @author Fitri
 */
import helpers.Artist;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class LWJGLDisplay {

    private volatile boolean isRunning = false;

    /*
     * The question asker seemed to desire that the JFrame be 800x600 and
     * that the Display be 300x300.  Regardless of the desired sizes,
     * I think the important thing is to set the Canvas and Display to the same sizes.
     */
    private int frameWidth = 800;
    private int frameHeight = 600;
    private int displayWidth = 300;
    private int displayHeight = 300;

    private Thread glThread;

    public static void main(String[] args) {
        new LWJGLDisplay().runTester();
    }

    private void runTester() {
        final JFrame frame = new JFrame("LWJGL in Swing");
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frame, "Do you want to quit the Application?");
                if (result == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    frame.dispose(); //canvas's removeNotify() will be called
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());

        JButton button = new JButton("BUTTON");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        Canvas canvas = new Canvas() {
            @Override
            public void addNotify() {
                super.addNotify();
                startGL();
            }

            @Override
            public void removeNotify() {
                stopGL();
                super.removeNotify();
            }
        };
        canvas.setPreferredSize(new Dimension(displayWidth, displayHeight));
        canvas.setIgnoreRepaint(true);

        try {
            Display.setParent(canvas);
        } catch (LWJGLException e) {
            //handle exception
            e.printStackTrace();
        }
        JPanel canvasPanel = new JPanel();
        canvasPanel.add(canvas);
        mainPanel.add(canvasPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);

        //frame.pack();
        frame.setVisible(true);
    }

    private void startGL() {
        glThread = new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                Artist.BeginSession();

                int[][] map = {
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                    {0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},};

                TileGrid grid = new TileGrid(map);

                // init OpenGL here
                while (isRunning) {
                    // render OpenGL here
                    grid.Draw();
                    System.out.println("yo");

                    Display.update();
                    Display.sync(60);
                }

                Display.destroy();
            }
        }, "LWJGL Thread");

        glThread.start();
    }

    private void stopGL() {
        isRunning = false;
        try {
            glThread.join();
        } catch (InterruptedException e) {
            //handle exception
            e.printStackTrace();
        }
    }

}
