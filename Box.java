import java.sql.Array;

public class Box {
    private static final Box INSTANCE = new Box();
     int index = 0;
    private String[] brickArray  = new String[10000000];

    private Box() {}

    public static Box getInstance() {
        return INSTANCE;
    }

    public void addBrick(String brickName){
        brickArray[index++] = brickName;
    }

    public String[] getBrickArray(){
        return brickArray;
    }
}
