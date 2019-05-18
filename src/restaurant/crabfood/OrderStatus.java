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
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Fitri
 */
public class OrderStatus extends JPanel {

    static private JPanel panel;
    JFrame frame;

    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private GridBagConstraints c;
    
    

    public static JTextArea[] firstChef = {new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50)};
    public static JTextArea[] secondChef = {new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50)};
    public static JTextArea[] thirdChef = {new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50), new JTextArea(1, 50)};

    private static final URL URL = OrderStatus.class.getResource("img/standby.gif");
    private static final ImageIcon IMAGE_ICON = new ImageIcon(URL);
    public static JLabel[] gif = {new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),new JLabel(IMAGE_ICON),};

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
        

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        mainPanel.add(lblFoodOrdered, c);
        
        c.fill = GridBagConstraints.VERTICAL;
        for (int i = 0, j = 1; i < 10; i++, j += 3) {

            
            c.gridx = 0;
            c.gridy = j;
            c.gridwidth = 1;
            c.gridheight = 3;
            mainPanel.add(gif[i], c);

            c.gridx = 1;
            c.gridy = j;
            c.gridwidth = 1;
            c.gridheight = 1;
            mainPanel.add(firstChef[i], c);

            c.gridx = 1;
            c.gridy = j + 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            mainPanel.add(secondChef[i], c);

            c.gridx = 1;
            c.gridy = j + 2;
            c.gridwidth = 1;
            c.gridheight = 1;
            mainPanel.add(thirdChef[i], c);
        }
        
        c.fill = GridBagConstraints.HORIZONTAL;
        
        backButton = new JButton("Back");

        c.gridx = 0;
        c.gridy = 31;
        c.gridwidth = 2;
        c.gridheight = 1;
        mainPanel.add(backButton, c);
        backEvent e = new backEvent();
        backButton.addActionListener(e);

        scrollPane = new JScrollPane(mainPanel);
        frame.getContentPane().add(scrollPane);
        frame.validate();
        

    }

    public class backEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.setVisible(true);
            frame.getContentPane().remove(scrollPane);
            if (RestaurantCrabfood.branchIndex != 2) {
                RestaurantCrabfood.branchIndex++;
            } else {
                RestaurantCrabfood.branchIndex = 0;
            }
            CrustyCrab.totalPrice = 0;
        }
    }

}
