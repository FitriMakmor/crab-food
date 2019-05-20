package restaurant.crabfood;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static restaurant.crabfood.RestaurantCrabfood.branch;
import static restaurant.crabfood.RestaurantCrabfood.branchIndex;
import static restaurant.crabfood.RestaurantCrabfood.customer;
import static restaurant.crabfood.RestaurantCrabfood.customerNo;
import static restaurant.crabfood.RestaurantCrabfood.task;

public class KrustyKrab extends JPanel {

    static private JPanel panel;
    JFrame frame;
    
    static private JButton backButton, orderButton;
    static private JTextField textTotal;
    static private JTextField textTime;
    
    static private JTable table;
    static private DefaultTableModel dtm;

    private ImageIcon image1;
    private JButton button1;
    
    private ImageIcon image2;
    private JButton button2;
    
    private ImageIcon image3;
    private JButton button3;
    static DecimalFormat df = new DecimalFormat();

    Double[] price;
    Double[] priceDrinks;
    Double[] priceDesserts;
    static double totalPrice = 0;
    double p1, p2, p3, p4, p5, p6, p7, p8, p9;
    double d1, d2, d3, d4, d5;
    double de1, de2;

    private JPanel mainPanel;
    private JPanel menu;
    private GridBagConstraints c;

    double total = 0;

    public KrustyKrab(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    void create() throws IOException {

        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        mainPanel = new JPanel();

        c = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        menu = new JPanel();
        menu.setLayout(new GridLayout(2,3));
        
        c.fill= GridBagConstraints.BOTH;
        c.weightx=1.0;
        c.weighty=1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 10;
        c.gridheight = 13;
        
        final int TAB_BTN_WIDTH=250;
        final int TAB_BTN_HEIGHT=250;
        
        image1 = new ImageIcon(getClass().getResource("img/krabbyPatty.jpg"));
        Image img1 = image1.getImage();
        Image newimg1 = img1.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image1 = new ImageIcon(newimg1);
        
        button1 = new JButton(image1);
        button1.addActionListener(new eventKP());
        menu.add(button1);
        
        image2 = new ImageIcon(getClass().getResource("img/crabbyMeal.jpg"));
        Image img2 = image2.getImage();
        Image newimg2 = img2.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image2 = new ImageIcon(newimg2);
        
        button2 = new JButton(image2);
        button2.addActionListener(new eventCM());
        menu.add(button2);
        
        image3 = new ImageIcon(getClass().getResource("img/sailorsSurprise.jpg"));
        Image img3 = image3.getImage();
        Image newimg3 = img3.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image3 = new ImageIcon(newimg3);
        
        button3 = new JButton(image3);
        button3.addActionListener(new eventSS());
        menu.add(button3);
        
        JButton a1 = new JButton("Crabby Patty");
        
        menu.add(a1);
        
        JButton a2 = new JButton("Crabby Meal");
        menu.add(a2);
        
        JButton a3 = new JButton("Sailors Surprise");;
        menu.add(a3);
        
        mainPanel.add(menu, c);
        
        
        JLabel lblBranch = new JLabel("Restaurant Branch: "+RestaurantCrabfood.branchIndex);
        lblBranch.setBackground(Color.WHITE);
        lblBranch.setOpaque(true);
        c.gridx = 0;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblBranch, c);
        mainPanel.validate();

        JLabel lblCustNo = new JLabel("Customer No.: "+customerNo);
        lblCustNo.setBackground(Color.LIGHT_GRAY);
        lblCustNo.setOpaque(true);
        c.gridx = 0;
        c.gridy = 15;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblCustNo, c);
        mainPanel.validate();
        
        JLabel lblFoodOrdered = new JLabel("Food Ordered");

        c.gridx = 11;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblFoodOrdered, c);
        mainPanel.validate();

        dtm = new DefaultTableModel(0, 3);
        final String header[] = new String[]{"Item", "Time", "Price"};
        dtm.setColumnIdentifiers(header);
        table = new JTable(dtm);
        table.setSize(245, 300); // width,height
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.setRowHeight(50);

        c.gridx = 11;
        c.gridy = 1;
        c.gridwidth = 5;
        c.gridheight = 1;
        mainPanel.add(table.getTableHeader(), c);

        c.gridx = 11;
        c.gridy = 2;
        c.gridwidth = 5;
        c.gridheight = 11;
        
        JScrollPane pane = new JScrollPane(table);
        mainPanel.add(pane, c);

        JLabel lblTotal = new JLabel("Total Price : ");
        
        c.gridx = 11;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblTotal, c);
        
        JLabel lblTime = new JLabel("Total Time : ");

        c.gridx = 11;
        c.gridy = 15;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblTime, c);

        textTotal = new JTextField();
        textTotal.setColumns(10);

        c.gridx = 12;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(textTotal, c);
        
        textTime = new JTextField();
        textTime.setColumns(10);
//		textTime.setBounds(585, 340, 86, 20);

        c.gridx = 12;
        c.gridy = 15;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(textTime, c);

        orderButton = new JButton("Order");
//		orderButton.setBounds(500, 385, 89, 23);

        c.gridx = 14;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 2;
        mainPanel.add(orderButton, c);
        orderEvent oe = new orderEvent();
        orderButton.addActionListener(oe);

        backButton = new JButton("Back");
//		backButton.setBounds(610, 385, 89, 23);

        c.gridx = 15;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 2;
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
            RestaurantCrabfood.Customer.remove(--customerNo);
            branch[0][branchIndex].emptyList();
            totalPrice=0;
    }
    }

    public class orderEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[0][branchIndex] = new RestaurantBranch(task.getTime(), customer);
            RestaurantCrabfood.order = new Thread(branch[0][branchIndex]);            
            customer.setOrderTime(task.getTime());
            RestaurantCrabfood.order.start();
            totalPrice=0;
            
            OrderStatus status;
			try {
				status = new OrderStatus(frame,panel);
				status.create();
                                mainPanel.setVisible(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    public class eventKP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[0][branchIndex].order("Crabby Patty");
            dtm.addRow(new Object[]{"Crabby Patty", branch[0][branchIndex].getTime("Crabby Patty"), df.format(branch[0][branchIndex].getPrice("Crabby Patty"))});
            totalPrice += branch[0][branchIndex].getPrice("Crabby Patty");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[0][branchIndex].totalTime()));
        }
    }

    public class eventCM implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[0][branchIndex].order("Crabby Meal");
            dtm.addRow(new Object[]{"Crabby Meal", branch[0][branchIndex].getTime("Crabby Meal"), df.format(branch[0][branchIndex].getPrice("Crabby Meal"))});
            totalPrice += branch[0][branchIndex].getPrice("Crabby Meal");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[0][branchIndex].totalTime()));
        }
    }

    public class eventSS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[0][branchIndex].order("Sailors Surprise");
            dtm.addRow(new Object[]{"Sailors Surprise", branch[0][branchIndex].getTime("Sailors Surprise"), df.format(branch[0][branchIndex].getPrice("Sailors Surprise"))});
            totalPrice += branch[0][branchIndex].getPrice("Sailors Surprise");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[0][branchIndex].totalTime()));
        }
    }

}
