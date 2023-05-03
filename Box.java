import java.sql.Array;

public class Box {
    static int index = 0;
    private static String[] BrickArray  = new String[10000000];

    public void addBrick(String brickName){
        BrickArray[index++] = brickName;
    }

    public String[] getBrickArray(){
        return BrickArray;
    }
}
