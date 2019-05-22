package restaurant.crabfood;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main extends JFrame {

    private ImageIcon image1;
    private JButton button1;

    JMenuBar menubar;
    JMenu Crab;
    JMenuItem exit;

    public Main() {
        JPanel front = new JPanel();

        front.setLayout(new FlowLayout());

        image1 = new ImageIcon(getClass().getResource("img/Sale.png"));
        button1 = new JButton(image1);
        event e = new event(this);

        button1.addActionListener(e);
        front.add(button1);
        getContentPane().add(front);
    }

    public class event implements ActionListener {

        JFrame frame;

        public event(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                getContentPane().removeAll();
                getContentPane().invalidate();
                Restaurant choose = new Restaurant(frame);
                choose.setVisible(true);
                getContentPane().add(choose);
                getContentPane().revalidate();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
