import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AIRobotNavigation {
    static int boardSize;
    static ArrayList<Node> visitedNodes = new ArrayList<Node>();
    static List<Path> bestPath = new ArrayList<Path>();
    static Node initial = null;
    static Node goal = null;
    public static void main(String[] args)
    {
        //Variable Declarations
        String filename;

        filename = args[0];
        String[][] board = null;
        String[][] baseBoard = null;

        //Read in file and create the board
        try{
            String line;
            PrintWriter output = new PrintWriter("output.txt", "UTF-8");
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            int x = 0;
            while((line = br.readLine()) != null){
                if(boardSize == 0){
                    boardSize = Integer.parseInt(line.trim());
                    board = new String[boardSize][boardSize];
                    baseBoard = new String[boardSize][boardSize];
                }
                else {
                    String rowChars[] = line.split("");
                    for(int y = 0; y < boardSize; y++){
                        board[x-1][y] = rowChars[y];
                        rowChars[y] = rowChars[y].trim();
                        if(rowChars[y].equals("i")){
                            System.out.println("Reaching this!!");
                            initial = new Node(x-1, y);
                        }
                        if(rowChars[y].equals("g")){
                            goal = new Node(x-1,y);
                        }
                    }
                }
                x++;
            }
            for(int t = 0; t < boardSize; t++){
                for(int y = 0; y <boardSize; y++){
                    baseBoard[t][y] = board[t][y];
                }
            }
            visitedNodes.add(initial);
            bestPath.add(new Path((double)0, initial));
            //Run 4 times for each test
            for(int i = 0; i < 4; i++) {
                //While not at the goal
                while (!bestPath.get(0).paths.get(bestPath.get(0).paths.size() - 1).equals(goal)) {
                    Path currentBest = bestPath.remove(0);
                    Node currentNode = currentBest.paths.get(currentBest.paths.size() - 1);

                    Node newNode;
                    //Checking the left node
                    if (currentNode.row - 1 != -1 && !board[(int) (currentNode.row - 1)][(int) currentNode.column].equals( "+")) {
                        Path left = new Path(currentBest);
                        newNode = new Node(currentNode.row - 1, currentNode.column);
                        if (FindIfVisited(newNode) == false) {
                            visitedNodes.add(newNode);
                            bestPath.add(CalculateDistances(left, newNode, i));
                        }
                    }
                    //Check the right node
                    if (currentNode.row + 1 != boardSize && !board[(int) (currentNode.row + 1)][(int) currentNode.column].equals("+")) {
                        Path right = new Path(currentBest);
                        newNode = new Node(currentNode.row + 1, currentNode.column);
                        if (FindIfVisited(newNode) == false) {
                            visitedNodes.add(newNode);
                            bestPath.add(CalculateDistances(right, newNode, i));
                        }
                    }
                    //Check the above node
                    if(currentNode.column-1 != -1 && !board[(int) (currentNode.row)][(int) currentNode.column-1].equals("+")){
                        Path up = new Path(currentBest);
                        newNode = new Node(currentNode.row, currentNode.column-1);
                        if(FindIfVisited(newNode) == false){
                            visitedNodes.add(newNode);
                            bestPath.add(CalculateDistances(up, newNode, i));
                        }
                    }
                    //Check the below node
                    if(currentNode.column+1 != boardSize && !board[(int) (currentNode.row)][(int) currentNode.column+1].equals("+")){
                        Path down = new Path(currentBest);
                        newNode = new Node(currentNode.row, currentNode.column+1);
                        if(FindIfVisited(newNode) == false){
                            visitedNodes.add(newNode);
                            bestPath.add(CalculateDistances(down, newNode, i));
                        }
                    }


                }
                for(Node node: bestPath.get(0).paths){
                    if(node != initial && node != goal){
                        board[(int) node.row][(int) node.column] = "o";
                    }
                }
                for (int a = 0; a < boardSize; ++a) {
                    for (int j = 0; j < boardSize ; ++j) {
                        output.print(board[a][j]);
                    }
                    output.println();
                }

                output.println("\nNumber of steps taken: " + bestPath.get(0).pathCost);
                output.println("Number of nodes traversed: " + bestPath.size());
                output.println("\n\n");
                board = baseBoard;
                visitedNodes.clear();
                bestPath.clear();;
                bestPath.add(new Path((double)0, initial));
                visitedNodes.add(initial);
                for(int t = 0; t < boardSize; t++){
                    for(int y = 0; y <boardSize; y++){
                        board[t][y] = baseBoard[t][y];
                    }
                }
            }
            output.close();

        } catch (FileNotFoundException e) {
            System.out.println("That file doesn't exist!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("That isn't a valid number for your board size");
        }


    }

    public static double EuclidianDistance(Node current, Node next){
        return Math.sqrt(Math.pow((current.row - next.row), 2)  + Math.pow((current.column - next.column), 2));
    }
    public static double ManhattanDistance(Node current, Node next){
        return Math.abs(current.row - next.row) + Math.abs(current.column - next.column);
    }
    public static boolean FindIfVisited(Node pointToCheck){
        for(Node node : visitedNodes){
            if(pointToCheck.row == node.row && pointToCheck.column == node.column){
                return true;
            }
        }
        return false;
    }
    public static Path CalculateDistances(Path current, Node newNode, int mode) {
        current.pathCost++;
        switch (mode) {
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

