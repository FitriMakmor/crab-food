package restaurant.crabfood;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame {
	private ImageIcon image1;
	private JLabel label1;
	private JButton button1;
	
	JMenuBar menubar;
	JMenu Crab;
	JMenuItem exit;

	
	
	Main(){
		setLayout(new FlowLayout());
		
		image1= new ImageIcon(getClass().getResource("Sale.png"));
		button1 = new JButton(image1);
		event e = new event();

		button1.addActionListener(e);
		add(button1);
		
		
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		Crab= new JMenu("CRABFOOD");
		menubar.add(Crab);
		
		
		exit = new JMenuItem("Exit");
		Crab.add(exit);
		
		exit.addActionListener(e);
		
		
	}
	
	public class event implements ActionListener {
		public void actionPerformed(ActionEvent e ) {
			
			Restaurant gui= new Restaurant(Main.this);
			
			gui.setSize(1500,1000);
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gui.setTitle("CHOOSE A RESTAURANT");
			gui.setVisible(true);
		}
	}
	
	public static void main (String args[]) {
		Main gui = new Main();
		
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.pack();
		gui.setTitle("CRABFOOD");
	}
	

}

