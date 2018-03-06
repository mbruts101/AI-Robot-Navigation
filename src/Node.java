public class  Node{
    double row;
    double column;

    public Node(double row, double column){
        this.row = row;
        this.column = column;
    }

    public boolean equals(Node n){
    	if(this.row == n.row && this.column == n.column)
    		return true;
    	else
    		return false;
    }

    @Override
    public String toString(){
    	String loc = "(" + this.row + ", " + this.column + ")";
    	return loc;
    }
}
