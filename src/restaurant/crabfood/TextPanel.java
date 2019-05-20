/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Fitri
 */
public class TextPanel extends JPanel {

    private JTable table;

    public TextPanel(ArrayList customer) {

        setLayout(new FlowLayout());
        String[] columnNames = {"Customer", "Arrival", "Order Time", "Finished Cooking Time", "Delivery Time", "Total Time"};
        
        String[][] data = new String[customer.size()][6];
        for(int i=0;i<customer.size();i++){
            Customer kastemer = (Customer)customer.get(i);
                data[i][0]=""+(i+1);
                data[i][1]=""+kastemer.getArrivalTime();
                data[i][2]=""+kastemer.getOrderTime();
                data[i][3]=""+kastemer.getFinishedCookingTime();
                data[i][4]=""+kastemer.getDeliveryTime();
                data[i][5]=""+kastemer.getTotalTime();
        }
        
        table = new JTable(data, columnNames);
        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);
        table.setPreferredScrollableViewportSize(new Dimension(800,300));
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
