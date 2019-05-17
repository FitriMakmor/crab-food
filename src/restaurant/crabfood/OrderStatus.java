/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import static restaurant.crabfood.CrustyCrab.df;

/**
 *
 * @author Fitri
 */
public class OrderStatus extends JPanel{
    
    static private JPanel panel;
    JFrame frame;
    
    private JPanel mainPanel;
    private GridBagConstraints c;
    
    public static JTextArea firstChef = new JTextArea(1,50);
    public static JTextArea secondChef = new JTextArea(1,50);
    public static JTextArea thirdChef = new JTextArea(1,50);
    
    static private JButton backButton;
    
    public OrderStatus(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }
    
    void create() throws IOException {

        mainPanel = new JPanel();

        c = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        JLabel lblFoodOrdered = new JLabel("Dish Preparation Status");
//		lblFoodOrdered.setBounds(529, 11, 81, 14);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblFoodOrdered, c);
        mainPanel.validate();

        
        
//        firstChef = new JTextArea();
//        firstChef.setColumns(10);
//		firstChef.setBounds(585, 340, 86, 20);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 5;
        c.gridheight = 1;
        mainPanel.add(firstChef, c);
        
//        secondChef = new JTextArea();
//        secondChef.setColumns(10);
//		secondChef.setBounds(585, 340, 86, 20);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 5;
        c.gridheight = 1;
        mainPanel.add(secondChef, c);

//        thirdChef = new JTextArea();
//        thirdChef.setColumns(10);
//		secondChef.setBounds(585, 340, 86, 20);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 5;
        c.gridheight = 1;
        mainPanel.add(thirdChef, c);
        
        backButton = new JButton("Back");
//		backButton.setBounds(610, 385, 89, 23);

        c.gridx = 15;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(backButton, c);
        backEvent e = new backEvent();
        backButton.addActionListener(e);

        frame.getContentPane().add(mainPanel);

    }
    
    public class backEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setVisible(true);
            frame.getContentPane().remove(mainPanel);
            if(RestaurantCrabfood.branchIndex!=2){
            RestaurantCrabfood.branchIndex++;
            }else{
                RestaurantCrabfood.branchIndex=0;
            }
            CrustyCrab.totalPrice=0;
        }
    }
    
}
