package restaurant.crabfood;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import static restaurant.crabfood.RestaurantCrabfood.Customer;
import static restaurant.crabfood.RestaurantCrabfood.RESTAURANT_BRANCHES;
import static restaurant.crabfood.RestaurantCrabfood.RESTAURANT_COMPANIES;
import static restaurant.crabfood.RestaurantCrabfood.coords;
import static restaurant.crabfood.RestaurantCrabfood.customerNo;
import static restaurant.crabfood.RestaurantCrabfood.task;
import static restaurant.crabfood.RestaurantCrabfood.timer;

public class Restaurant extends JPanel {

    private JPanel panel = this;

    private JPanel firstPanel;
    private JPanel secondPanel;
    private JPanel thirdPanel;
    private JPanel fourthPanel;
    private JPanel sidePanel;

    private ImageIcon image1;
    private JButton button1;

    private ImageIcon image2;
    private JButton button2;

    private ImageIcon image3;
    private JButton button3;

    private ImageIcon imagea;
    private JButton buttona;

    private ImageIcon imageb;
    private JButton buttonb;

    private ImageIcon imagec;
    private JButton buttonc;

    private ImageIcon imaged;
    private JButton buttond;

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

    private GridBagConstraints c;

    static TileLabel[][] tile = new TileLabel[ROW][COL];

    Restaurant(JFrame frame) {
        this.frame = frame;

        sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(4, 1));
        setLayout(new GridBagLayout());

        final int TAB_BTN_WIDTH = 250;
        final int TAB_BTN_HEIGHT = 250;
        imagea = new ImageIcon(getClass().getResource("img/restaurantTab.jpeg"));
        Image imga = imagea.getImage();
        Image newimga = imga.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        imagea = new ImageIcon(newimga);

        buttona = new JButton(imagea);
        buttona.setPreferredSize(new Dimension(TAB_BTN_WIDTH, TAB_BTN_HEIGHT));
        tabRestaurant tabRest = new tabRestaurant();
        buttona.addActionListener(tabRest);
        sidePanel.add(buttona);

        imageb = new ImageIcon(getClass().getResource("img/mapTab.jpeg"));
        Image imgb = imageb.getImage();
        Image newimgb = imgb.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        imageb = new ImageIcon(newimgb);

        buttonb = new JButton(imageb);
        buttonb.setPreferredSize(new Dimension(TAB_BTN_WIDTH, TAB_BTN_HEIGHT));
        tabMap tabM = new tabMap();
        buttonb.addActionListener(tabM);
        sidePanel.add(buttonb);

        imagec = new ImageIcon(getClass().getResource("img/statusTab.jpeg"));
        Image imgc = imagec.getImage();
        Image newimgc = imgc.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        imagec = new ImageIcon(newimgc);

        buttonc = new JButton(imagec);
        buttonc.setPreferredSize(new Dimension(TAB_BTN_WIDTH, TAB_BTN_HEIGHT));
        tabStatus tabStat = new tabStatus();
        buttonc.addActionListener(tabStat);
        sidePanel.add(buttonc);

        imaged = new ImageIcon(getClass().getResource("img/endDayTab.jpeg"));
        Image imgd = imaged.getImage();
        Image newimgd = imgd.getScaledInstance(TAB_BTN_WIDTH, TAB_BTN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        imaged = new ImageIcon(newimgd);

        buttond = new JButton(imaged);
        buttond.setPreferredSize(new Dimension(TAB_BTN_WIDTH, TAB_BTN_HEIGHT));
        tabEndDay tabEnd = new tabEndDay();
        buttond.addActionListener(tabEnd);
        sidePanel.add(buttond);

        c = new GridBagConstraints();

        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 4;

        sidePanel.setPreferredSize(new Dimension(250, 1001));
        sidePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));

        add(sidePanel, c);

        firstPanel = new JPanel();
        secondPanel = new JPanel();
        thirdPanel = new JPanel();
        fourthPanel = new JPanel();

        firstPanel.setLayout(new GridLayout(1, 3));

        image1 = new ImageIcon(getClass().getResource("img/kk.jpeg"));
        Image img1 = image1.getImage();
        Image newimg1 = img1.getScaledInstance(590, 1001, java.awt.Image.SCALE_SMOOTH);
        image1 = new ImageIcon(newimg1);

        button1 = new JButton(image1);
        openKK openKK = new openKK();
        button1.addActionListener(openKK);
        firstPanel.add(button1);

        image2 = new ImageIcon(getClass().getResource("img/cb.jpeg"));
        Image img2 = image2.getImage();
        Image newimg2 = img2.getScaledInstance(590, 1001, java.awt.Image.SCALE_SMOOTH);
        image2 = new ImageIcon(newimg2);

        button2 = new JButton(image2);
        openCB openCB = new openCB();
        button2.addActionListener(openCB);
        firstPanel.add(button2);

        image3 = new ImageIcon(getClass().getResource("img/wh.jpeg"));
        Image img3 = image3.getImage();
        Image newimg3 = img3.getScaledInstance(590, 1001, java.awt.Image.SCALE_SMOOTH);
        image3 = new ImageIcon(newimg3);

        button3 = new JButton(image3);
        openBK openBK = new openBK();
        button3.addActionListener(openBK);
        firstPanel.add(button3);
        firstPanel.setPreferredSize(new Dimension(1670, 1001));
        firstPanel.setVisible(true);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 10;
        c.gridheight = 4;

        firstPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
        add(firstPanel, c);

        sandimg = new ImageIcon(getClass().getResource("img/sandTile.png"));
        Image sandimg1 = sandimg.getImage();
        Image sandImg = sandimg1.getScaledInstance(117, 66, java.awt.Image.SCALE_SMOOTH);
        sandimg = new ImageIcon(sandImg);

        pathimg = new ImageIcon(getClass().getResource("img/pathTile.png"));
        Image pathimg1 = pathimg.getImage();
        Image pathImg = pathimg1.getScaledInstance(117, 66, java.awt.Image.SCALE_SMOOTH);
        pathimg = new ImageIcon(pathImg);

        kkimg = new ImageIcon(getClass().getResource("img/kkTile.jpeg"));
        Image kkimg1 = kkimg.getImage();
        Image kkImg = kkimg1.getScaledInstance(117, 66, java.awt.Image.SCALE_SMOOTH);
        kkimg = new ImageIcon(kkImg);

        cbimg = new ImageIcon(getClass().getResource("img/cbTile.jpeg"));
        Image cbimg1 = cbimg.getImage();
        Image cbImg = cbimg1.getScaledInstance(117, 66, java.awt.Image.SCALE_SMOOTH);
        cbimg = new ImageIcon(cbImg);

        whimg = new ImageIcon(getClass().getResource("img/whTile.jpeg"));
        Image whimg1 = whimg.getImage();
        Image whImg = whimg1.getScaledInstance(117, 66, java.awt.Image.SCALE_SMOOTH);
        whimg = new ImageIcon(whImg);

        int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1},
            {0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1},
            {0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1},
            {1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};
        
for(int i=0;i<RESTAURANT_COMPANIES;i++){
    for(int j=0;j<RESTAURANT_BRANCHES;j++){
        switch(i){
            case 0: map[coords[i][j].getX()][coords[i][j].getY()]=5;
            break;
            case 1: map[coords[i][j].getX()][coords[i][j].getY()]=6;
            break;
            case 2: map[coords[i][j].getX()][coords[i][j].getY()]=7;
            break;
            default: System.out.println("Error in inserting restaurants in map!");
        }
    }
}
        secondPanel.setLayout(new GridLayout(ROW, COL));
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                switch (map[i][j]) {
                    case 1:
                        tile[i][j] = new TileLabel(1);
                        tile[i][j].setIcon(pathimg);
                        break;
                    case 5:
                        tile[i][j] = new TileLabel(2);
                        tile[i][j].setIcon(kkimg);
                        break;
                    case 6:
                        tile[i][j] = new TileLabel(3);
                        tile[i][j].setIcon(cbimg);
                        break;
                    case 7:
                        tile[i][j] = new TileLabel(4);
                        tile[i][j].setIcon(whimg);
                        break;
                    default:
                        tile[i][j] = new TileLabel(0);
                        tile[i][j].setIcon(sandimg);
                }
                tile[i][j].setBorder(new LineBorder(Color.BLACK));
                tile[i][j].setOpaque(true);
                secondPanel.add(tile[i][j]);
            }
        }

        secondPanel.setPreferredSize(new Dimension(1670, 1001));
        secondPanel.setVisible(false);

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 10;
        c.gridheight = 4;

        secondPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
        add(secondPanel, c);

        status = new JTextArea(">>>>> Dish Preparation Log <<<<<");
        thirdPanel.setLayout(new GridLayout());
        thirdPanel.add(status);

        thirdPanel.setVisible(false);

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 10;
        c.gridheight = 4;

        thirdPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
        add(thirdPanel, c);

        endImage = new ImageIcon(getClass().getResource("img/endDay.jpg"));
        Image endi = endImage.getImage();
        Image endimg = endi.getScaledInstance(1670, 1001, java.awt.Image.SCALE_SMOOTH);
        endImage = new ImageIcon(endimg);
        endButton = new JButton(endImage);

        endDay end = new endDay();
        endButton.addActionListener(end);
        fourthPanel.setLayout(new GridLayout());
        fourthPanel.add(endButton);

        fourthPanel.setPreferredSize(new Dimension(1670, 1001));
        fourthPanel.setVisible(false);

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 10;
        c.gridheight = 4;

        fourthPanel.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
        add(fourthPanel, c);

    }

    public class tabRestaurant implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            firstPanel.setVisible(true);
            secondPanel.setVisible(false);
            thirdPanel.setVisible(false);
            fourthPanel.setVisible(false);
        }
    }

    public class tabMap implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            firstPanel.setVisible(false);
            secondPanel.setVisible(true);
            thirdPanel.setVisible(false);
            fourthPanel.setVisible(false);
        }
    }

    public class tabStatus implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            firstPanel.setVisible(false);
            secondPanel.setVisible(false);
            thirdPanel.setVisible(true);
            fourthPanel.setVisible(false);
        }
    }

    public class tabEndDay implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            firstPanel.setVisible(false);
            secondPanel.setVisible(false);
            thirdPanel.setVisible(false);
            fourthPanel.setVisible(true);
        }
    }

    public class openKK implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            KrustyKrab food;
            try {
                customerNo++;
                RestaurantCrabfood.Customer.add(RestaurantCrabfood.customer = new Customer(task.getTime()));
                food = new KrustyKrab(frame, panel);
                food.create();
                setVisible(false);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public class openCB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ChumBucket food;
            try {
                customerNo++;
                RestaurantCrabfood.Customer.add(RestaurantCrabfood.customer = new Customer(task.getTime()));
                food = new ChumBucket(frame, panel);
                food.create();
                setVisible(false);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public class openBK implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BurgerKrusty food;
            try {
                customerNo++;
                RestaurantCrabfood.Customer.add(RestaurantCrabfood.customer = new Customer(task.getTime()));
                food = new BurgerKrusty(frame, panel);
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
