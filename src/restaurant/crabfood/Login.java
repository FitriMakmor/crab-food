/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant.crabfood;

/**
 *
 * @author Asfa
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static restaurant.crabfood.RestaurantCrabfood.Customer;

public class Login {
	
	JFrame logframe = new JFrame("Authorized User Log");
	JPanel logpanel = new JPanel();
	JLabel username= new JLabel("Username: ");
	JLabel password= new JLabel("Password: ");
	JTextField user= new JTextField("",20);
	JTextField pw= new JTextField("",20);

	JButton button = new JButton("Sign in");
	
	public Login() {
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if( user.getText().equals("Fa58") && pw.getText().equals("hehe12") ) {
                                        new LogFrame(Customer);
					//add logging panel
				}
				else {
					System.out.println("Access Denied");
					//add warning
				}

			}

		});
		logpanel.add(username);
		logpanel.add(user);
		logpanel.add(password);
		logpanel.add(pw);
		logpanel.add(button);
		logframe.add(logpanel);
		logframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		logframe.pack();
		logframe.setVisible(true);
		
		
	}
}
