package restaurant.crabfood;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import static restaurant.crabfood.RestaurantCrabfood.Customer;
import static restaurant.crabfood.RestaurantCrabfood.customerNo;
import static restaurant.crabfood.RestaurantCrabfood.task;
import static restaurant.crabfood.RestaurantCrabfood.timer;

public class Restaurant extends JPanel {

    private JPanel panel = this;
    
    private JPanel topLeftPanel;
    private JPanel topRightPanel;
    private JPanel botLeftPanel;
    private JPanel botRightPanel;
//    private JPanel panel1;
//    private JPanel panel2;
//    private JPanel panel3;
    private ImageIcon image1;
    private JButton button1;

    private ImageIcon image2;
    private JButton button2;

    private ImageIcon image3;
    private JButton button3;

    private JButton endButton;

    private JFrame frame;
    
    public static JTextArea status;

    Restaurant(JFrame frame) {
        this.frame = frame;
        
        topLeftPanel = new JPanel();
        topRightPanel = new JPanel();
        botLeftPanel = new JPanel();
        botRightPanel = new JPanel();
        
        topLeftPanel.setLayout(new GridLayout(1,3));
//        panel1 = new JPanel();
//        panel2 = new JPanel();
//        panel3 = new JPanel();
//        
        setLayout(new GridLayout(2,2));

        image1 = new ImageIcon(getClass().getResource("img/kkRS.png"));
        Image img1 = image1.getImage();
        Image newimg1 = img1.getScaledInstance(320, 500, java.awt.Image.SCALE_SMOOTH);
        image1 = new ImageIcon(newimg1);
        
        button1 = new JButton(image1);
        openKK openKK = new openKK();
        button1.addActionListener(openKK);
        topLeftPanel.add(button1);

        image2 = new ImageIcon(getClass().getResource("img/cb.png"));
        Image img2 = image2.getImage();
        Image newimg2 = img2.getScaledInstance(320, 500, java.awt.Image.SCALE_SMOOTH);
        image2 = new ImageIcon(newimg2);
        
        button2 = new JButton(image2);
        topLeftPanel.add(button2);

        image3 = new ImageIcon(getClass().getResource("img/sb.png"));
        Image img3 = image3.getImage();
        Image newimg3 = img3.getScaledInstance(320, 500, java.awt.Image.SCALE_SMOOTH);
        image3 = new ImageIcon(newimg3);
        
        
        button3 = new JButton(image3);
        topLeftPanel.add(button3);
        
        add(topLeftPanel);
        
        add(topRightPanel);
        
        status = new JTextArea(">>>>> Dish Preparation Log <<<<<");
        botLeftPanel.setLayout(new GridLayout());
        botLeftPanel.add(status);
        add(botLeftPanel);
        
        endButton = new JButton("End Day");
        endDay end = new endDay();
        endButton.addActionListener(end);
        botRightPanel.setLayout(new GridLayout());
        botRightPanel.add(endButton);
        
        add(botRightPanel);

    }

    public class openKK implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CrustyCrab food;
            try {
                customerNo++;
                RestaurantCrabfood.Customer.add(RestaurantCrabfood.customer = new Customer(task.getTime()));
                food = new CrustyCrab(frame, panel);
                food.create();
                setVisible(false);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public class endDay implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SYSTEM ENDS");
            timer.cancel();

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new LogFrame(Customer);
                }
            });
        }

    }

}
