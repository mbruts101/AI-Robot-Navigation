import java.io.*;
import java.util.ArrayList;

public class AIRobotNavigation {

    public static  void main(String[] args)
    {
        ArrayList<Node>VisitedNodes = new ArrayList<Node>();
        int boardSize = 0;
        String filename;
        Node initial = null;
        Node goal = null;
        filename = args[0];
        String[][] board = null;
        try{
            String line;
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            int x = 0;
            while((line = br.readLine()) != null){
                if(boardSize == 0){
                    boardSize = Integer.parseInt(line);
                    System.out.println(boardSize);
                    board = new String[boardSize][boardSize];
                }
                else {
                    String rowChars[] = line.split("");
                    for(int y = 0; y < boardSize; y++){
                        board[x-1][y] = rowChars[y];
                        if(rowChars[y] == "i"){
                            initial = new Node(x-1, y);
                        }
                        if(rowChars[y] == "g"){
                            goal = new Node(x-1,y);
                        }
                    }
                }
                x++;
            }
            VisitedNodes.add(initial);

        } catch (FileNotFoundException e) {
            System.out.println("That file doesn't exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public double EuclidianDistance(Node current, Node next){
        return Math.sqrt(Math.pow((current.row - next.row), 2)  + Math.pow((current.column - next.column), 2));
    }
    public double ManhattanDistance(Node current, Node next){
        return Math.abs(current.row - next.row) + Math.abs(current.column - next.column);
    }

}

