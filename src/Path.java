import java.util.ArrayList;

public class Path {
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
}
