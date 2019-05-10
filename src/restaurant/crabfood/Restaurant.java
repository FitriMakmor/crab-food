package restaurant.crabfood;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;



public class Restaurant extends JFrame{

	private ImageIcon image1;
	private JButton button1;
	
	private ImageIcon image2;
	private JButton button2;
	
	private ImageIcon image3;
	private JButton button3;
	
	private ImageIcon image4;
	private JButton button4;

	Restaurant(JFrame frame){
		
		setLayout(new FlowLayout());
		
		image1= new ImageIcon(getClass().getResource("cb.png"));
		button1 = new JButton(image1);
		event e = new event();

		button1.addActionListener(e);
		
		add(button1);
		
		image2= new ImageIcon(getClass().getResource("kk.png"));
		button2 = new JButton(image2);
		add(button2);
		
		image3= new ImageIcon(getClass().getResource("sb.png"));
		button3 = new JButton(image3);
		add(button3);
		
		image4= new ImageIcon(getClass().getResource("s.png"));
		button4 = new JButton(image4);
		add(button4);
		
		
	}
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e ) {
			ChumBucket food;
			try {
			
				food = new ChumBucket();
				food.create();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}
