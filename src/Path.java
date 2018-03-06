import java.util.ArrayList;

public class Path implements  Comparable<Path>{
    double length;
    double pathCost;
    ArrayList<Node> paths = new ArrayList<Node>();

    Path(Double length, Node node){
        this.length = length;
        paths.add(node);
    }
    Path(Path newPath){
        this.length = newPath.length;
        this.paths = (ArrayList<Node>) newPath.paths.clone();
        this.pathCost = newPath.pathCost;
    }

    @Override
    public int compareTo(Path o) {
        if(this.length > o.length){
            return 1;
        }
        else if(this.length == o.length){
            return 0;
        }
        else {
            return 1;
        }
    }
}
