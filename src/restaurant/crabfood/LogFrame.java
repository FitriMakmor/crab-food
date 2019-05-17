/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Fitri
 */
public class LogFrame extends JFrame {

    private TextPanel textPanel;
    private JButton btn;
    
    public LogFrame(ArrayList customer) {
        super("Customer Log");

        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.getHSBColor(176, 97, 95));
        
        textPanel = new TextPanel(customer);
        add(textPanel, BorderLayout.CENTER);
        
        add(textPanel, BorderLayout.CENTER);
        
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
