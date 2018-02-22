import java.io.*;

public class AIRobotNavigation {

    public static  void main(String[] args)
    {
        String filename;
        filename = args[0];
        try{
            FileReader fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("That file doesn't exist!");
        }
    }

}
