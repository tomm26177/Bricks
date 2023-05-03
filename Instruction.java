import java.util.Objects;

public class Instruction {
   private final int id;
   public boolean itIsPossibleToBuild;
   int index = 0;
   public  String[] ArrayOfBricks = new String[5000];
    Instruction(int number){
        id=number;

    }



    public void addBricks(String s){
        ArrayOfBricks[index++]=s;
    }
    public int getId(){
        return id;
    }

}
