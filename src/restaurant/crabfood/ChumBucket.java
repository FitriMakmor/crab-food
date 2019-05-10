package restaurant.crabfood;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class ChumBucket extends JFrame {

	static private JFrame frame;
	static private JButton backButton, orderButton;
	static private JTextField textField;
	static private GridBagConstraints gbc;
	private JTable table;
	DefaultTableModel dtm;
	Double[] price;
	Double[] priceDrinks;
	Double[] priceDesserts;
	double totalPrice = 0;
	double p1, p2, p3, p4, p5, p6, p7, p8, p9;
	double d1, d2, d3, d4, d5;
	double de1, de2;

	private JSpinner[] numSpinner;
	static private JLabel[] foodLabel;
	static private ImageIcon[] foodImage;
	private String[] file;

	private JSpinner[] numSpinnerDrinks;
	static private JLabel[] drinkLabel;
	static private JLabel[] drinkImage;
	private String[] fileDrinks;

	private JSpinner[] numSpinnerDesserts;
	static private JLabel[] dessertLabel;
	static private JLabel[] dessertImage;
	private String[] fileDesserts;

	private static final int ELEMENTS = 3;
	private static final int DRINK_ELEMENTS = 5;
	private static final int DESSERT_ELEMENTS = 2;

	double total = 0;
	double food1, food2, food3, food4, food5, food6, food7, food8, food9;
	double drink1, drink2, drink3, drink4, drink5;
	double pr, pr1;

	double totalForFoods;
	double totalForDrinks;
	double totalForDesserts;
	JTabbedPane tabbedPane;
	private ImageIcon imageIcon;
	
	void create () throws IOException  {
		frame = new JFrame("Main Menu ");
		frame.setBounds(100, 100, 750, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JLabel lblFoodOrdered = new JLabel("Food Ordered");
		lblFoodOrdered.setBounds(529, 11, 81, 14);

		frame.getContentPane().add(lblFoodOrdered);

		table = new JTable();
		backButton = new JButton();
		orderButton = new JButton();
		dtm = new DefaultTableModel(0, 0);
		final String header[] = new String[] { "Item", "Qty", "Price", "Spinner" };
		dtm.setColumnIdentifiers(header);
		dtm.addRow(header);
		table = new JTable();
		table.setModel(dtm);
		table.setBounds(475, 31, 1, 1); // int x, int y, int width, int height
		table.setSize(245, 300); // width,height
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setMinWidth(0); // hide spinner
															// column
		table.getColumnModel().getColumn(3).setMaxWidth(0); // hide spinner
															// column
		table.setShowGrid(false); // remove cell boarder
		frame.getContentPane().add(table);
		JLabel lblTotal = new JLabel("Total  : ");
		lblTotal.setBounds(519, 340, 46, 14);
		frame.getContentPane().add(lblTotal);
		textField = new JTextField();
		textField.setBounds(585, 340, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		orderButton = new JButton("Order");
		orderButton.setBounds(500, 385, 89, 23);
		frame.getContentPane().add(orderButton);
		backButton = new JButton("Back");
		backButton.setBounds(610, 385, 89, 23);
		frame.getContentPane().add(backButton);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		addIt(tabbedPane, "Foods");
	
		tabbedPane.setBounds(18, 11, 450, 450);
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);
	
	}

	 void addIt (JTabbedPane tabbedPane, String text) throws IOException {
			gbc = new GridBagConstraints(); // getting constraints for the specified
											// component
			gbc.insets = new Insets(10, 0, 0, 0);
			foodImage = new ImageIcon[ELEMENTS];
			foodLabel = new JLabel[ELEMENTS];
			numSpinner = new JSpinner[ELEMENTS];
			file = new String[ELEMENTS];
			price = new Double[ELEMENTS];
			
				foodImage[0]=new ImageIcon(getClass().getResource("spaghetti.jpg"));
			

		
			foodLabel[0] = new JLabel("Salad");
			foodLabel[1] = new JLabel("Japanese Noodles");
			foodLabel[2] = new JLabel("Spaghetti");
			
			price[0] = 3.50;
			price[1] = 4.50;
			price[2] = 3.70;
			
			setVisible(true);
		
		}
		
	}

