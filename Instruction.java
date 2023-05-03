public class Instruction {
   private int id;
   int index = 0;
   private String[] ArrayOfBricks = new String[5000];
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
