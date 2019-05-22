package restaurant.crabfood;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Tharin
 */
public class RMap {

    Random ran = new Random();
    public static Cell[][] rmap;
    private ArrayList<String> Name;
    private ArrayList<Pair> CoOrdin;

    public RMap(int[][] array) throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(new File("Input.txt"));
        rmap = new Cell[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                rmap[i][j] = new Cell();
                if (array[i][j] == 0) {
                    rmap[i][j].setIsImPassable(true);
                }
                if (array[i][j] == 1) {
                    if (ran.nextInt(3) == 2) { //33% chance of there being traffic
                        rmap[i][j].setDifficulty(ran.nextInt(4) + 1);
                    }else{
                        rmap[i][j].setDifficulty(1);
                    }
                }
            }
        }
        while (scan.hasNext()) {
            boolean isPair = true;
            String resName = scan.nextLine();
            //System.out.println("resName = "+resName);
            String holder = scan.nextLine();
            for (String n : holder.split(" ")) {
                if (!this.CanParseInt(n)) {
                    isPair = false;
                }
            }
            while (holder.split(" ").length < 3 & isPair == true) {
                //System.out.println("holder = "+holder);
                this.setRes(Integer.parseInt(holder.split(" ")[0]), Integer.parseInt(holder.split(" ")[1]), resName);//set restaurant
                holder = scan.nextLine();//reload
                for (String n : holder.split(" ")) {
                    if (!this.CanParseInt(n)) {
                        isPair = false;//check
                    }
                }
            }//end of the pairs
            while (holder.length() > 0) {
                if (scan.hasNext()) {
                    holder = scan.nextLine();
                } else {
                    break;
                }
            }
        }
    }

    public ArrayList<Pair> BestBranch(String restaurant, int Dx, int Dy) {
        ArrayList<Pair> holder = null;
        int i, j;
        boolean breaknow = false;
        for (i = 0; i < rmap.length; i++) {
            for (j = 0; j < rmap.length; j++) {
                if (rmap[i][j].getName().equals(restaurant)) {
                    holder = this.A_star(i, j, Dx, Dy);
                    breaknow = true;
                    break;
                }
            }
            if (breaknow) {
                break;
            }
        }
        for (i = 0; i < rmap.length; i++) {
            for (j = 0; j < rmap.length; j++) {
                //System.out.println(i+" "+j);
                if (rmap[i][j].getName().equals(restaurant)) {
//                        System.out.println("Alert");
                    if (holder.get(holder.size() - 3).getX() > this.A_star(i, j, Dx, Dy).get(this.A_star(i, j, Dx, Dy).size() - 3).getX()) {
                        holder = this.A_star(i, j, Dx, Dy);
                    }
                }
            }
        }
        return holder;
    }

    public int[][] throwMap() {
        int[][] nnn = new int[rmap.length][rmap.length];
        for (int i = 0; i < rmap.length; i++) {
            for (int j = 0; j < rmap.length; j++) {
                if (rmap[i][j].getIsImPassable()) {
                    nnn[i][j] = 0;
                } else {
                    nnn[i][j] = rmap[i][j].getDifficulty();
                }
            }
        }
        return nnn;
    }

    public void showmap(ArrayList<Pair> A_star) {//Show the map for individual A* path; X for restaurant, I for impassible, R for road, T for trail(designated by A* algo)
        //1. create an array of char
        char[][] VisMap = new char[rmap.length][rmap.length];
        //2.Assign 'R' to all the cell
        for (int i = 0; i < VisMap.length; i++) {
            for (int j = 0; j < VisMap.length; j++) {
                VisMap[i][j] = 'R';
                //3. Assign I to the impassible cell
                if (rmap[i][j].getIsImPassable()) {
                    VisMap[i][j] = 'I';
                }
            }
        } //5. Assign T from A*
        for (int i = 1; i < A_star.size() - 4; i++) {
            VisMap[A_star.get(i).getX()][A_star.get(i).getY()] = '#';
        }
        VisMap[A_star.get(A_star.size() - 1).getX()][A_star.get(A_star.size() - 1).getY()] = 'D';
        VisMap[A_star.get(A_star.size() - 2).getX()][A_star.get(A_star.size() - 2).getY()] = 'O';
        //4. Assign X from Restaurant coordinate
        for (int i = 0; i < VisMap.length; i++) {
            for (int j = 0; j < VisMap.length; j++) {
                System.out.print(VisMap[i][j] + " ");
            }
            System.out.println();
        }
    }
//    public void testmap() throws FileNotFoundException, IOException {//create a testmap which must
//        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("TestMap.txt"));
//        
//    }

    private void setRes(int i, int j, String Name) {
        rmap[i][j].setIsImPassable(true);
        rmap[i][j].setName(Name);
    }

    private ArrayList<Pair> A_star(int Ox, int Oy, int Dx, int Dy) {
        int Dxx = Dx;
        int Dyy = Dy;
        rmap[Dx][Dy].setIsImPassable(false);
        int Ox1 = Ox;
        int Oy1 = Oy;
        boolean done = false;
        int[][][] Amap = Heu(Dx, Dy);
        ArrayList<Pair> tobeReturn = new ArrayList<>();
        class CellCom implements Comparator<Cell> {

            @Override
            public int compare(Cell o1, Cell o2) {
                int CoX1 = o1.getCoX();
                int CoY1 = o1.getCoY();
                int CoX2 = o2.getCoX();
                int CoY2 = o2.getCoY();
                if (Amap[CoX1][CoY1][0] < Amap[CoX2][CoY2][0]) {
                    return -1;
                } else if (Amap[CoX1][CoY1][0] == Amap[CoX2][CoY2][0]) {
                    if (Math.sqrt((CoX1 - Dxx) * (CoX1 - Dxx) + (CoY1 - Dyy) * (CoY1 - Dyy)) < Math.sqrt((CoX2 - Dxx) * (CoX2 - Dxx) + (CoY2 - Dyy) * (CoY2 - Dyy))) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        }
        PriorityQueue<Cell> ONode = new PriorityQueue<>(1, new CellCom());
        ArrayList<Cell> CNode = new ArrayList<>();
        while (!done) {
            //System.out.println(Ox+" Picked "+Oy);
            //System.out.println("Parent node : .................................................."+Amap[Ox][Oy][1]+","+Amap[Ox][Oy][2]);
            CNode.add(rmap[Ox][Oy]);
            ONode.poll();
            int CostSoFar = Amap[Ox][Oy][3];
            //System.out.println("Cost so far = "+CostSoFar);
            if (Ox + 1 < rmap.length) {
                if (!rmap[Ox + 1][Oy].getIsImPassable() & !CNode.contains(rmap[Ox + 1][Oy]) & !ONode.contains(rmap[Ox + 1][Oy])) {
                    Amap[Ox + 1][Oy][3] = CostSoFar + rmap[Ox + 1][Oy].getDifficulty();
                    Amap[Ox + 1][Oy][0] += Amap[Ox + 1][Oy][3];
                    ONode.add(rmap[Ox + 1][Oy]);
                    Amap[Ox + 1][Oy][1] = Ox;//Right hand
                    Amap[Ox + 1][Oy][2] = Oy;
//                    System.out.println("R");
//                    System.out.println("Cell to the right = "+(Ox+1)+","+Oy);
//                    System.out.println("XNXN"+Amap[Ox+1][Oy][0]);
                }
            }
            if (Oy + 1 < rmap.length) {
                if (!rmap[Ox][Oy + 1].getIsImPassable() & !CNode.contains(rmap[Ox][Oy + 1]) & !ONode.contains(rmap[Ox][Oy + 1])) {
                    Amap[Ox][Oy + 1][3] = CostSoFar + rmap[Ox][Oy + 1].getDifficulty();
                    Amap[Ox][Oy + 1][0] += Amap[Ox][Oy + 1][3];
                    ONode.add(rmap[Ox][Oy + 1]);
                    Amap[Ox][Oy + 1][1] = Ox;
                    Amap[Ox][Oy + 1][2] = Oy;
//                    System.out.println("D");
//                    System.out.println("Cell under = "+Ox+","+(Oy+1));
//                    System.out.println("XNXN"+Amap[Ox][Oy+1][0]);
                }
            }
            if (Ox - 1 >= 0) {
                if (!rmap[Ox - 1][Oy].getIsImPassable() & !CNode.contains(rmap[Ox - 1][Oy]) & !ONode.contains(rmap[Ox - 1][Oy])) {
                    Amap[Ox - 1][Oy][3] = CostSoFar + rmap[Ox - 1][Oy].getDifficulty();
                    Amap[Ox - 1][Oy][0] += Amap[Ox - 1][Oy][3];
                    ONode.add(rmap[Ox - 1][Oy]);
                    Amap[Ox - 1][Oy][1] = Ox;
                    Amap[Ox - 1][Oy][2] = Oy;
//                    System.out.println("L");
//                    System.out.println("Cell to the left = "+(Ox-1)+","+Oy);
//                    System.out.println("XNXN"+Amap[Ox-1][Oy][0]);
                }
            }
            if (Oy - 1 >= 0) {
                if (!rmap[Ox][Oy - 1].getIsImPassable() & !CNode.contains(rmap[Ox][Oy - 1]) & !ONode.contains(rmap[Ox][Oy - 1])) {
                    Amap[Ox][Oy - 1][3] = CostSoFar + rmap[Ox][Oy - 1].getDifficulty();
                    Amap[Ox][Oy - 1][0] += Amap[Ox][Oy - 1][3];
                    ONode.add(rmap[Ox][Oy - 1]);
                    Amap[Ox][Oy - 1][1] = Ox;
                    Amap[Ox][Oy - 1][2] = Oy;
//                    System.out.println("U");
//                    System.out.println("Cell to the top = "+Ox+","+(Oy-1));
//                    System.out.println("XNXN"+Amap[Ox][Oy-1][0]);
                }
            }//Add all open nodes into ONode
            Ox = ONode.peek().getCoX();
            Oy = ONode.peek().getCoY();
            if (Ox == Dx & Oy == Dy) {//Reach the destination; Trace back
                //System.out.println("Peeking the destination");
                ONode.poll();
                //System.out.println("Sum: "+CostSoFar);
                //System.out.println("Heuristic of next node : "+Amap[ONode.peek().getCoX()][ONode.peek().getCoY()][0]);
                if (CostSoFar <= Amap[ONode.peek().getCoX()][ONode.peek().getCoY()][0]) {//If it really is the way then pack up things and return 
                    //System.out.println(".........................");
                    ArrayList<Pair> PlanRev = new ArrayList<>();
                    while (Dx != -1 & Dy != -1) {
                        PlanRev.add(new Pair(Dx, Dy));
                        int Dx2 = Dx;
//                        System.out.println("This is ...........................Dx :"+Dx+" And Dy: "+Dy);
                        Dx = (int) Amap[Dx][Dy][1];
                        Dy = (int) Amap[Dx2][Dy][2];
                    }
                    Pair sumFinal = new Pair(CostSoFar, 0);
                    Pair Origin = new Pair(Ox1, Oy1);
                    Pair Destination = new Pair(Dxx, Dyy);
                    PlanRev.add(sumFinal);
                    PlanRev.add(Origin);
                    PlanRev.add(Destination);
//                    for(int i=0;i<PlanRev.size();i++) System.out.println(PlanRev.get(i).getX()+","+PlanRev.get(i).getY());
                    tobeReturn = PlanRev;
                    done = true;
                }
            }
        }
        return tobeReturn;
    }

    private int[][][] Heu(int Dx, int Dy) {//heuristic mode
        int[][][] heu = new int[this.rmap.length][this.rmap.length][4];
        for (int i = 0; i < heu.length; i++) {
            for (int j = 0; j < heu.length; j++) {
                rmap[i][j].setCoX(i);
                rmap[i][j].setCoY(j);
                heu[i][j][0] = Math.abs(i - Dx) + Math.abs(j - Dy);
                heu[i][j][1] = -1;
                heu[i][j][2] = -1;
                heu[i][j][3] = 0;
            }
        }
        return heu;
    }

    private boolean CanParseInt(String test) {
        for (int i = 0; i < test.length(); i++) {
            if (!Character.isDigit(test.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    
}
