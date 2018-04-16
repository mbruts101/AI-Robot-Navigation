import java.util.ArrayList;
import java.util.List;

public class Path implements  Comparable<Path>{
    double length;
    double pathCost;
    List<Node> nodes = new ArrayList<Node>();

    Path(Double length, Node node){
        this.length = length;
        nodes.add(node);
    }
    Path(Path newPath){
        this.length = newPath.length;
        for(Node node: newPath.nodes){
            this.nodes.add(node);
        }
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
            return -1;
        }
    }
}
