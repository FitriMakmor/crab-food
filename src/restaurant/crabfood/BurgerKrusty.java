package restaurant.crabfood;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.Icon;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import static restaurant.crabfood.OrderStatus.cust;
import static restaurant.crabfood.Restaurant.innerMap;
import static restaurant.crabfood.Restaurant.map;
import static restaurant.crabfood.Restaurant.tile;
import static restaurant.crabfood.RestaurantCrabfood.branch;
import static restaurant.crabfood.RestaurantCrabfood.branchIndex;
import static restaurant.crabfood.RestaurantCrabfood.customer;
import static restaurant.crabfood.RestaurantCrabfood.customerNo;
import static restaurant.crabfood.RestaurantCrabfood.customerX;
import static restaurant.crabfood.RestaurantCrabfood.customerY;
import static restaurant.crabfood.RestaurantCrabfood.task;

public class BurgerKrusty extends JPanel {

    static private JPanel panel;
    JFrame frame;

    static private JButton backButton, orderButton;
    static private JTextField textTotal;
    static private JTextField textTime;

    static private JTable table;
    static private DefaultTableModel dtm;
    static private JScrollPane pane;

    private ImageIcon image1;
    private JLabel label1;

    private ImageIcon image2;
    private JLabel label2;

    private ImageIcon image3;
    private JLabel label3;

    private ImageIcon image4;
    private JButton button1;

    private ImageIcon image5;
    private JButton button2;

    private ImageIcon image6;
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

    public BurgerKrusty(JFrame frame, JPanel panel) {
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
        menu.setLayout(new GridLayout(2, 3));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 10;
        c.gridheight = 13;

        final int TAB_BTN_WIDTH = 250;
        final int TAB_BTN_HEIGHT = 250;

        image1 = new ImageIcon(getClass().getResource("img/theKlogger.jpg"));
        Image img1 = image1.getImage();
        Image newimg1 = img1.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image1 = new ImageIcon(newimg1);

        label1 = new JLabel(image1);
        menu.add(label1);

        image2 = new ImageIcon(getClass().getResource("img/fishSandwich.jpg"));
        Image img2 = image2.getImage();
        Image newimg2 = img2.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image2 = new ImageIcon(newimg2);

        label2 = new JLabel(image2);
        menu.add(label2);

        image3 = new ImageIcon(getClass().getResource("img/twistyLard.jpg"));
        Image img3 = image3.getImage();
        Image newimg3 = img3.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image3 = new ImageIcon(newimg3);

        label3 = new JLabel(image3);
        menu.add(label3);

        image4 = new ImageIcon(getClass().getResource("img/theKloggerText.jpeg"));
        Image img4 = image4.getImage();
        Image newimg4 = img4.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image4 = new ImageIcon(newimg4);

        button1 = new JButton(image4);
        button1.addActionListener(new eventTK());
        menu.add(button1);

        image5 = new ImageIcon(getClass().getResource("img/fishSandwichText.jpeg"));
        Image img5 = image5.getImage();
        Image newimg5 = img5.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image5 = new ImageIcon(newimg5);

        button2 = new JButton(image5);
        button2.addActionListener(new eventFS());
        menu.add(button2);

        image6 = new ImageIcon(getClass().getResource("img/twistyLardText.jpeg"));
        Image img6 = image6.getImage();
        Image newimg6 = img6.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        image6 = new ImageIcon(newimg6);

        button3 = new JButton(image6);
        button3.addActionListener(new eventTL());
        menu.add(button3);

        menu.setPreferredSize(new Dimension(968, 740));

        mainPanel.add(menu, c);

        c.weightx = 1.0;
        c.weighty = 1.0;

        JLabel lblBranch = new JLabel("Restaurant Branch: " + RestaurantCrabfood.branchIndex);
        lblBranch.setBackground(Color.WHITE);
        lblBranch.setOpaque(true);
        c.gridx = 0;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblBranch, c);
        mainPanel.validate();

        JLabel lblCustNo = new JLabel("Customer No.: " + customerNo);
        lblCustNo.setBackground(Color.LIGHT_GRAY);
        lblCustNo.setOpaque(true);
        c.gridx = 0;
        c.gridy = 15;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(lblCustNo, c);
        mainPanel.validate();

        JLabel lblFoodOrdered = new JLabel("Food Ordered", SwingConstants.CENTER);
        lblFoodOrdered.setFont(new Font("Times New Roman", Font.BOLD, 40));

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
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);
        table.setOpaque(true);

        c.gridx = 11;
        c.gridy = 1;
        c.gridwidth = 5;
        c.gridheight = 1;
        mainPanel.add(table.getTableHeader(), c);

        c.gridx = 11;
        c.gridy = 2;
        c.gridwidth = 5;
        c.gridheight = 11;

        pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(950, 558));

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

        c.gridx = 12;
        c.gridy = 15;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPanel.add(textTime, c);

        orderButton = new JButton("Order");

        c.gridx = 14;
        c.gridy = 14;
        c.gridwidth = 1;
        c.gridheight = 2;
        mainPanel.add(orderButton, c);
        orderEvent oe = new orderEvent();
        orderButton.addActionListener(oe);

        backButton = new JButton("Back");

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
            branch[2][branchIndex].emptyList();
            totalPrice = 0;
        }
    }

    public class orderEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Random r = new Random(); // Spawns the of customer inside map
            
            while (true) {
                customerX = r.nextInt(15);
                customerY = r.nextInt(15);
                if (map[customerX][customerY] == 1) {
                    tile[customerX][customerY].setIcon(getCustImg());
                    break;
                }
            }
            
            branch[2][branchIndex] = new RestaurantBranch("Burger Krusty",task.getTime(), customer);
            RestaurantCrabfood.order = new Thread(branch[2][branchIndex]);
            customer.setOrderTime(task.getTime());
            RestaurantCrabfood.order.start();
            totalPrice = 0;
            cust[customerNo - 1].setIcon(getCustImg());

            

            OrderStatus status;
            try {
                status = new OrderStatus(frame, panel);
                status.create();
                mainPanel.setVisible(false);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public class eventTK implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[2][branchIndex].order("The Klogger");
            dtm.addRow(new Object[]{"The Klogger", branch[2][branchIndex].getTime("The Klogger"), df.format(branch[2][branchIndex].getPrice("The Klogger"))});
            totalPrice += branch[2][branchIndex].getPrice("The Klogger");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[2][branchIndex].totalTime()));
        }
    }

    public class eventFS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[2][branchIndex].order("Fish Sandwich");
            dtm.addRow(new Object[]{"Fish Sandwich", branch[2][branchIndex].getTime("Fish Sandwich"), df.format(branch[2][branchIndex].getPrice("Fish Sandwich"))});
            totalPrice += branch[2][branchIndex].getPrice("Fish Sandwich");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[2][branchIndex].totalTime()));
        }
    }

    public class eventTL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[2][branchIndex].order("Twisty Lard");
            dtm.addRow(new Object[]{"Twisty Lard", branch[2][branchIndex].getTime("Twisty Lard"), df.format(branch[2][branchIndex].getPrice("Twisty Lard"))});
            totalPrice += branch[2][branchIndex].getPrice("Twisty Lard");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[2][branchIndex].totalTime()));
        }
    }

    public ImageIcon getCustImg() {

        int customer = customerNo % 5;
        String text = "img/";
        switch (customer) {
            case 0:
                text += "cust1.jpeg";
                break;
            case 1:
                text += "cust2.jpeg";
                break;
            case 2:
                text += "cust3.jpeg";
                break;
            case 3:
                text += "cust4.jpeg";
                break;
            case 4:
                text += "cust5.jpeg";
                break;
            default:
                text += "cust1.jpeg";
        }
        ImageIcon tempImg = new ImageIcon(getClass().getResource(text));
        Image tempI = tempImg.getImage();
        Image tempimg = tempI.getScaledInstance(117, 66, java.awt.Image.SCALE_SMOOTH);
        tempImg = new ImageIcon(tempimg);
        return tempImg;
    }

}
