import java.util.*;

public class Bricks {
    private static Box box = new Box();

    static Set<Instruction> setOfInstructions = new HashSet<>();
    static List<Integer> numbersUsedInInstruction = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useDelimiter("\\s+");
        while (input.hasNext()) {
            String i = input.nextLine();
            SegmentAnalizer(i);
        }
    }

    //ta funkcja zapisuje dane
    public static void SegmentAnalizer(String input) {
        //sprawdzenie ile cyfr ma liczba ktora reprezentuje instrukcje
        int colonIndex = input.indexOf(':');
        if (colonIndex == -1) {
            System.out.println("Niepoprawny format wejściowy");
            return;
        }

        int number = Integer.parseInt(input.substring(0, colonIndex));
        String BricksLetters = input.substring(colonIndex + 1);

        if (number == 0) {
            box.addBrick(BricksLetters);
        } else if (number > 0 && number <= 1000) {
            //jesli jest juz utworzona dana instrukcja to dodaje do niej nowe klocki, jesli
            // nie ma nowej instrukcji to tworzę ją dodaje klocki i dodaje do setu z instrukcjami
            boolean CreateNewList = true;

            for (int value : numbersUsedInInstruction) {
                if (value == number) {
                    CreateNewList = false;
                    break;
                }
            }

            if(CreateNewList) {
                Instruction instruction = new Instruction(number);
                instruction.addBricks(BricksLetters);
                setOfInstructions.add(instruction);
                numbersUsedInInstruction.add(number);
            }else{
                for (Instruction instruction : setOfInstructions) {
                    if (instruction.getId() == number) {
                        instruction.addBricks(BricksLetters);
                        break;
                    }
                }
            }
        }else{
            System.out.println("nieprawidłowa liczba");
        }
    }

    public static void BuildingProcess(){

    }
}
