import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIRobotNavigation {
    static ArrayList<Node>VisitedNodes = new ArrayList<Node>();
    static List<Path> bestPath = new ArrayList<Path>();
    static Node initial = null;
    static Node goal = null;
    public static void main(String[] args)
    {
        //Variable Declarations

        int boardSize = 0;
        String filename;

        filename = args[0];
        String[][] board = null;

        //Read in file and create the board
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
            bestPath.add(new Path((double)0, initial));
            //Run 4 times for each test
            for(int i = 0; i < 4; i++) {
                //While not at the goal
                while (bestPath.get(0).paths.get(bestPath.get(0).paths.size() - 1) != goal) {
                    Path currentBest = bestPath.remove(0);
                    Node currentNode = currentBest.paths.get(currentBest.paths.size() - 1);

                    Node newNode;
                    //Checking the left node
                    if (currentNode.row - 1 != -1 && board[(int) (currentNode.row - 1)][(int) currentNode.column] != "+") {
                        Path left = new Path(currentBest);
                        newNode = new Node(currentNode.row - 1, currentNode.column);
                        if (FindIfVisited(newNode) == false) {
                            VisitedNodes.add(newNode);
                            bestPath.add(CalculateDistances(left, newNode, i));
                        }
                    }
                    //Check the right node
                    if (currentNode.row + 1 != boardSize && board[(int) (currentNode.row + 1)][(int) currentNode.column] != "+") {
                        Path right = new Path(currentBest);
                        newNode = new Node(currentNode.row + 1, currentNode.column);
                        if (FindIfVisited(newNode) == false) {
                            VisitedNodes.add(newNode);
                            bestPath.add(CalculateDistances(right, newNode, i));
                        }
                    }
                    //Check the above node
                    if(currentNode.column-1 != -1 && board[(int) (currentNode.row)][(int) currentNode.column-1] != "+"){
                        Path up = new Path(currentBest);
                        newNode = new Node(currentNode.row, currentNode.column-1);
                        if(FindIfVisited(newNode) == false){
                            VisitedNodes.add(newNode);
                            bestPath.add(CalculateDistances(up, newNode, i));
                        }
                    }
                    //Check the below node
                    if(currentNode.column+1 != boardSize && board[(int) (currentNode.row)][(int) currentNode.column+1] != "+"){
                        Path down = new Path(currentBest);
                        newNode = new Node(currentNode.row, currentNode.column+1);
                        if(FindIfVisited(newNode) == false){
                            VisitedNodes.add(newNode);
                            bestPath.add(CalculateDistances(down, newNode, i));
                        }
                    }


                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("That file doesn't exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static double EuclidianDistance(Node current, Node next){
        return Math.sqrt(Math.pow((current.row - next.row), 2)  + Math.pow((current.column - next.column), 2));
    }
    public static double ManhattanDistance(Node current, Node next){
        return Math.abs(current.row - next.row) + Math.abs(current.column - next.column);
    }
    public static boolean FindIfVisited(Node pointToCheck){
        for(Node node : VisitedNodes){
            if(pointToCheck.row == node.row && pointToCheck.column == node.column){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    public static Path CalculateDistances(Path current, Node newNode, int mode){
        current.pathCost++;
        switch (mode){
            case 1:
                //Euclidian Distance
                current.length = EuclidianDistance(newNode, goal);
                break;
            case 2:
                //Manhattan Distance
                current.length = ManhattanDistance(newNode, goal);
                break;
            case 3:
                current.length = current.pathCost + EuclidianDistance(newNode, goal);
                break;
            case 4:
                current.length = current.pathCost + ManhattanDistance(newNode, goal);
                break;
        }
        current.paths.add(newNode);
        Collections.sort(bestPath);
        return current;
    }


}

