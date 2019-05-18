package restaurant.crabfood;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import static restaurant.crabfood.RestaurantCrabfood.Customer;
import static restaurant.crabfood.RestaurantCrabfood.customerNo;
import static restaurant.crabfood.RestaurantCrabfood.task;
import static restaurant.crabfood.RestaurantCrabfood.timer;

public class Restaurant extends JPanel {

    private JPanel panel = this;

    private JPanel topLeftPanel;
    private JPanel topRightPanel;
    private JPanel botLeftPanel;
    private JPanel botRightPanel;
//    private JPanel panel1;
//    private JPanel panel2;
//    private JPanel panel3;
    private ImageIcon image1;
    private JButton button1;

    private ImageIcon image2;
    private JButton button2;

    private ImageIcon image3;
    private JButton button3;
    
    private ImageIcon sandimg;
    private ImageIcon pathimg;
    private ImageIcon kkimg;
    private ImageIcon cbimg;
    private ImageIcon whimg;

    private ImageIcon endImage;
    private JButton endButton;

    private JFrame frame;

    public static JTextArea status;
    
    static final int ROW = 15;
    static final int COL = 15;
    
    static TileLabel[][] tile = new TileLabel[ROW][COL];

    Restaurant(JFrame frame) {
        this.frame = frame;

        topLeftPanel = new JPanel();
        topRightPanel = new JPanel();
        botLeftPanel = new JPanel();
        botRightPanel = new JPanel();

        topLeftPanel.setLayout(new GridLayout(1, 3));

        setLayout(new GridLayout(2, 2));

        image1 = new ImageIcon(getClass().getResource("img/kk.png"));
        Image img1 = image1.getImage();
        Image newimg1 = img1.getScaledInstance(320, 500, java.awt.Image.SCALE_SMOOTH);
        image1 = new ImageIcon(newimg1);

        button1 = new JButton(image1);
        openKK openKK = new openKK();
        button1.addActionListener(openKK);
        topLeftPanel.add(button1);

        image2 = new ImageIcon(getClass().getResource("img/cb.png"));
        Image img2 = image2.getImage();
        Image newimg2 = img2.getScaledInstance(320, 500, java.awt.Image.SCALE_SMOOTH);
        image2 = new ImageIcon(newimg2);

        button2 = new JButton(image2);
        topLeftPanel.add(button2);

        image3 = new ImageIcon(getClass().getResource("img/wh.jpeg"));
        Image img3 = image3.getImage();
        Image newimg3 = img3.getScaledInstance(320, 500, java.awt.Image.SCALE_SMOOTH);
        image3 = new ImageIcon(newimg3);

        button3 = new JButton(image3);
        topLeftPanel.add(button3);

        add(topLeftPanel);        
        
        sandimg = new ImageIcon(getClass().getResource("img/sandTile.png"));
        Image sandimg1 = sandimg.getImage();
        Image sandImg = sandimg1.getScaledInstance(64, 33, java.awt.Image.SCALE_SMOOTH);
        sandimg = new ImageIcon(sandImg);
        
        pathimg = new ImageIcon(getClass().getResource("img/pathTile.png"));
        Image pathimg1 = pathimg.getImage();
        Image pathImg = pathimg1.getScaledInstance(64, 33, java.awt.Image.SCALE_SMOOTH);
        pathimg = new ImageIcon(pathImg);
        
        kkimg = new ImageIcon(getClass().getResource("img/kkTile.jpeg"));
        Image kkimg1 = kkimg.getImage();
        Image kkImg = kkimg1.getScaledInstance(64, 33, java.awt.Image.SCALE_SMOOTH);
        kkimg = new ImageIcon(kkImg);
        
        cbimg = new ImageIcon(getClass().getResource("img/cbTile.jpeg"));
        Image cbimg1 = cbimg.getImage();
        Image cbImg = cbimg1.getScaledInstance(64, 33, java.awt.Image.SCALE_SMOOTH);
        cbimg = new ImageIcon(cbImg);
        
        whimg = new ImageIcon(getClass().getResource("img/whTile.jpeg"));
        Image whimg1 = whimg.getImage();
        Image whImg = whimg1.getScaledInstance(64, 33, java.awt.Image.SCALE_SMOOTH);
        whimg = new ImageIcon(whImg);
        
        int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,1,1,1,1,1,1,1,0,1},
            {0,1,1,1,0,0,1,0,1,0,0,3,1,0,1},
            {0,1,4,1,1,1,1,0,1,0,0,0,1,0,1},
            {0,1,0,0,0,1,0,0,1,0,0,0,1,4,1},
            {0,1,0,0,0,1,0,4,1,0,0,1,1,1,1},
            {1,1,0,0,0,1,1,1,1,1,1,1,0,0,0},
            {1,0,0,1,1,1,0,0,1,2,0,1,1,0,0},
            {1,0,0,1,0,0,0,0,1,1,0,0,1,1,1},
            {1,0,0,1,2,0,0,0,0,1,1,0,0,0,1},
            {1,3,0,1,0,0,0,0,0,0,1,0,0,0,1},
            {1,1,1,1,1,1,1,3,0,0,1,0,0,1,1},
            {0,1,0,0,0,0,1,1,0,0,1,2,1,1,0},
            {0,1,0,0,0,0,0,1,1,1,1,1,1,0,0},
            {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };
        
        topRightPanel.setLayout(new GridLayout(ROW, COL));
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                switch (map[i][j]) {
                    case 1:
                        tile[i][j] = new TileLabel(1);
                        tile[i][j].setIcon(pathimg);
                        break;
                    case 2:
                        tile[i][j] = new TileLabel(2);
                        tile[i][j].setIcon(kkimg);
                        break;
                    case 3:
                        tile[i][j] = new TileLabel(3);
                        tile[i][j].setIcon(cbimg);
                        break;
                    case 4:
                        tile[i][j] = new TileLabel(4);
                        tile[i][j].setIcon(whimg);
                        break;
                    default:
                        tile[i][j] = new TileLabel(0);
                        tile[i][j].setIcon(sandimg);
                }
                tile[i][j].setBorder(new LineBorder(Color.BLACK));
                tile[i][j].setOpaque(true);
                topRightPanel.add(tile[i][j]);
            }
        }

        add(topRightPanel);

        status = new JTextArea(">>>>> Dish Preparation Log <<<<<");
        botLeftPanel.setLayout(new GridLayout());
        botLeftPanel.add(status);
        add(botLeftPanel);

        
        
        endImage = new ImageIcon(getClass().getResource("img/endDay.jpg"));
        Image endi = endImage.getImage();
        Image endimg = endi.getScaledInstance(960, 500, java.awt.Image.SCALE_SMOOTH);
        endImage = new ImageIcon(endimg);
        endButton = new JButton(endImage);
        
        endDay end = new endDay();
        endButton.addActionListener(end);
        botRightPanel.setLayout(new GridLayout());
        botRightPanel.add(endButton);
        
        add(botRightPanel);
        
    }

    public class openKK implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            CrustyCrab food;
            try {
                customerNo++;
                RestaurantCrabfood.Customer.add(RestaurantCrabfood.customer = new Customer(task.getTime()));
                food = new CrustyCrab(frame, panel);
                food.create();
                setVisible(false);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public class endDay implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("SYSTEM ENDS");
            timer.cancel();

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new LogFrame(Customer);
                }
            });
        }

    }

}
