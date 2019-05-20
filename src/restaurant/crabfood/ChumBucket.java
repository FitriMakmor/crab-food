package restaurant.crabfood;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static restaurant.crabfood.RestaurantCrabfood.branch;
import static restaurant.crabfood.RestaurantCrabfood.branchIndex;
import static restaurant.crabfood.RestaurantCrabfood.customer;
import static restaurant.crabfood.RestaurantCrabfood.customerNo;
import static restaurant.crabfood.RestaurantCrabfood.task;

public class ChumBucket extends JPanel {

    static private JPanel panel;
    JFrame frame;
    
    static private JButton backButton, orderButton;
    static private JTextField textTotal;
    static private JTextField textTime;

    private ImageIcon image1;
    private ImageIcon image2;
    private ImageIcon image3;

    static private JTable table;
    static private DefaultTableModel dtm;
    static private int rowNum = 0;

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

    public ChumBucket(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    void create() throws IOException {
        
        rowNum=0;

        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        mainPanel = new JPanel();

        c = new GridBagConstraints();
        mainPanel.setLayout(new GridBagLayout());

        menu = new JPanel();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 10;
        c.gridheight = 13;

        mainPanel.add(menu, c);
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        JButton btn3 = new JButton();
        
        image1 = new ImageIcon(getClass().getResource("img/phumBurger.jpg"));
        Image img1 = image1.getImage();
        Image newimg1 = img1.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        image1 = new ImageIcon(newimg1);
        btn1.setIcon(image1);
        btn1.addActionListener(new eventKP());
        
        image2 = new ImageIcon(getClass().getResource("img/phumFries.jpg"));
        Image img2 = image2.getImage();
        Image newimg2 = img2.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        image2 = new ImageIcon(newimg2);
        btn2.setIcon(image2);
        btn2.addActionListener(new eventCM());
        
        image3 = new ImageIcon(getClass().getResource("img/phumPie.png"));
        Image img3 = image3.getImage();
        Image newimg3 = img3.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        image3 = new ImageIcon(newimg3);
        btn3.setIcon(image3);
        btn3.addActionListener(new eventSS());
        

        menu.add(btn1);
        menu.add(btn2);
        menu.add(btn3);
                
        JLabel lblBranch = new JLabel("Restaurant Branch: "+RestaurantCrabfood.branchIndex+" - Customer No.: "+customerNo);
        c.gridx = 0;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblBranch, c);
        mainPanel.validate();

        JLabel lblFoodOrdered = new JLabel("Food Ordered");
//		lblFoodOrdered.setBounds(529, 11, 81, 14);

        c.gridx = 11;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblFoodOrdered, c);
        mainPanel.validate();

        dtm = new DefaultTableModel(10, 3);
        final String header[] = new String[]{"Item", "Time", "Price"};
//                Double[][] data = new Double[10][3];
        dtm.setColumnIdentifiers(header);
        table = new JTable(dtm);
//		table.setBounds(475, 31, 1, 1); // int x, int y, int width, int height
        table.setSize(245, 300); // width,height
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.setRowHeight(50);
//		table.setShowGrid(false); // remove cell border

        c.gridx = 11;
        c.gridy = 1;
        c.gridwidth = 5;
        c.gridheight = 1;
        mainPanel.add(table.getTableHeader(), c);

        c.gridx = 11;
        c.gridy = 2;
        c.gridwidth = 5;
        c.gridheight = 11;
        mainPanel.add(table, c);

        JLabel lblTotal = new JLabel("Total Price : ");
//		lblTotal.setBounds(519, 340, 46, 14);

        c.gridx = 11;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblTotal, c);
        
        JLabel lblTime = new JLabel("Total Time : ");
//		lblTime.setBounds(519, 340, 46, 14);

        c.gridx = 11;
        c.gridy = 15;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblTime, c);

        textTotal = new JTextField();
        textTotal.setColumns(10);
//		textTotal.setBounds(585, 340, 86, 20);

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
            branch[1][branchIndex].emptyList();
            totalPrice=0;
    }
    }

    public class orderEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[1][branchIndex] = new RestaurantBranch(task.getTime(), customer);
            RestaurantCrabfood.order = new Thread(branch[1][branchIndex]);            
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
            branch[1][branchIndex].order("Phum Burger");
            dtm.insertRow(rowNum++, new Object[]{"Phum Burger", branch[1][branchIndex].getTime("Phum Burger"), df.format(branch[1][branchIndex].getPrice("Phum Burger"))});
            dtm.removeRow(dtm.getRowCount() - 1);
            totalPrice += branch[1][branchIndex].getPrice("Phum Burger");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[1][branchIndex].totalTime()));
        }
    }

    public class eventCM implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[1][branchIndex].order("Phum Fries");
            dtm.insertRow(rowNum++, new Object[]{"Phum Fries", branch[1][branchIndex].getTime("Phum Fries"), df.format(branch[1][branchIndex].getPrice("Phum Fries"))});
            dtm.removeRow(dtm.getRowCount() - 1);
            totalPrice += branch[1][branchIndex].getPrice("Phum Fries");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[1][branchIndex].totalTime()));
        }
    }

    public class eventSS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[1][branchIndex].order("Phum Pie");
            dtm.insertRow(rowNum++, new Object[]{"Phum Pie", branch[1][branchIndex].getTime("Phum Pie"), df.format(branch[1][branchIndex].getPrice("Phum Pie"))});
            dtm.removeRow(dtm.getRowCount() - 1);
            totalPrice += branch[1][branchIndex].getPrice("Phum Pie");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[1][branchIndex].totalTime()));
        }
    }

}
