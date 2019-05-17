package restaurant.crabfood;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

public class CrustyCrab extends JPanel {

    static private JPanel panel;
    JFrame frame;
    
    static private JButton backButton, orderButton;
    static private JTextField textTotal;
    static private JTextField textTime;

    

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

//	static private JLabel[] foodLabel;
//	static private ImageIcon[] foodImage;
    private JPanel mainPanel;
    private JPanel menu;
    private GridBagConstraints c;

//	private static final int ELEMENTS = 3;
    double total = 0;

    public CrustyCrab(JFrame frame, JPanel panel) {
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
        btn1.setIcon(new ImageIcon(getClass().getResource("img/krabbyPattyRS.jpg")));
        btn1.addActionListener(new eventKP());
        btn2.setIcon(new ImageIcon(getClass().getResource("img/crabbyMealRS.jpg")));
        btn2.addActionListener(new eventCM());
        btn3.setIcon(new ImageIcon(getClass().getResource("img/sailorsSurpriseRS.jpg")));
        btn3.addActionListener(new eventSS());
        

        menu.add(btn1);
        menu.add(btn2);
        menu.add(btn3);
                
        JLabel lblBranch = new JLabel("Restaurant Branch: "+RestaurantCrabfood.branchIndex);
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
            branch[0][branchIndex].emptyList();
    }
    }

    public class orderEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[0][branchIndex] = new RestaurantBranch(task.getTime(), customer);
            RestaurantCrabfood.order = new Thread(branch[0][branchIndex]);            
            customer.setOrderTime(task.getTime());
            RestaurantCrabfood.order.start();
            
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
            dtm.insertRow(rowNum++, new Object[]{"Crabby Patty", branch[0][branchIndex].getTime("Crabby Patty"), df.format(branch[0][branchIndex].getPrice("Crabby Patty"))});
            dtm.removeRow(dtm.getRowCount() - 1);
            totalPrice += branch[0][branchIndex].getPrice("Crabby Patty");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[0][branchIndex].totalTime()));
        }
    }

    public class eventCM implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[0][branchIndex].order("Crabby Meal");
            dtm.insertRow(rowNum++, new Object[]{"Crabby Meal", branch[0][branchIndex].getTime("Crabby Meal"), df.format(branch[0][branchIndex].getPrice("Crabby Meal"))});
            dtm.removeRow(dtm.getRowCount() - 1);
            totalPrice += branch[0][branchIndex].getPrice("Crabby Meal");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[0][branchIndex].totalTime()));
        }
    }

    public class eventSS implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            branch[0][branchIndex].order("Sailors Surprise");
            dtm.insertRow(rowNum++, new Object[]{"Sailors Surprise", branch[0][branchIndex].getTime("Sailors Surprise"), df.format(branch[0][branchIndex].getPrice("Sailors Surprise"))});
            dtm.removeRow(dtm.getRowCount() - 1);
            totalPrice += branch[0][branchIndex].getPrice("Sailors Surprise");
            textTotal.setText(String.valueOf(df.format(totalPrice)));
            textTime.setText(String.valueOf(branch[0][branchIndex].totalTime()));
        }
    }

}
