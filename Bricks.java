import java.util.*;

public class Bricks {

    static int bricksUsedInStageI = 0;
    static int bricksUsedInStageII = 0;
    static int bricksLeftInBox = 0;
    static int bricksMissingInInstructions = 0;
    static int buildingsBuilt = 0;
    static int buildingsNotBuilt = 0;

    static Set<Instruction> setOfInstructions = new HashSet<>();
    static List<Integer> numbersUsedInInstruction = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useDelimiter("\\s+");
        while (input.hasNext()) {
            String i = input.nextLine();
            SegmentAnalizer(i);
        }

        BuildingProcess();


        System.out.println(bricksUsedInStageI);
        System.out.println(bricksUsedInStageII);
        System.out.println(bricksLeftInBox);
        System.out.println(bricksMissingInInstructions); //
        System.out.println(contOfBuildingsPossible()[0]);
        System.out.println(contOfBuildingsPossible()[1]);


    }

    //ta funkcja zapisuje dane
    public static void SegmentAnalizer(String input) {
        //sprawdzenie ile cyfr ma liczba ktora reprezentuje instrukcje
        int colonIndex = input.indexOf(':');
        if (colonIndex == -1) {
            System.out.println("klops");
            return;
        }

        int number = Integer.parseInt(input.substring(0, colonIndex));
        String BricksLetters = input.substring(colonIndex + 1);

        if (number == 0) {
            bricksLeftInBox++;
            Box.getInstance().addBrick(BricksLetters);

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

            if (CreateNewList) {
                Instruction instruction = new Instruction(number);
                instruction.addBricks(BricksLetters);
                setOfInstructions.add(instruction);
                numbersUsedInInstruction.add(number);
            } else {
                for (Instruction instruction : setOfInstructions) {
                    if (instruction.getId() == number) {
                        instruction.addBricks(BricksLetters);
                        break;
                    }
                }
            }
        } else {
            System.out.println("klops");
        }
    }

    public static void BuildingProcess() {
        for (Instruction instruction : setOfInstructions) {
            if (instruction.getId() % 3 == 0) {

                System.out.println("sprawdzam kocki dla instrukcji numer " + instruction.getId() + "\n");

                bricksUsedInStageI += build(instruction);

            }
        }
        for (Instruction instruction : setOfInstructions) {
            if (!(instruction.getId() % 3 == 0)) {

                System.out.println("sprawdzam kocki dla instrukcji numer " + instruction.getId() + "\n");

                bricksUsedInStageII += build(instruction);

            }
        }

    }

    private static int build(Instruction instruction) {
        List<Integer> listOfIndexOfUsedBricks = new ArrayList<>();
        Box box = Box.getInstance();
        boolean allBricksFound = true;
        for (String brickInInstruction : instruction.ArrayOfBricks) {
            if (brickInInstruction == null) {
                break;
            }
            boolean brickFound = false;
            for (int i = 0; i <= box.index; i++) {
                if (box.getBrickArray()[i] != null && box.getBrickArray()[i].equals(brickInInstruction)) {
                    System.out.println("został znaleziony klocek " + brickInInstruction + "\n");
                    brickFound = true;
                    listOfIndexOfUsedBricks.add(i);
                    box.getBrickArray()[i]=null;



                    //TODO TUTAJ MOZE BYC PROBLEM Z TYM ZE JAK INSTRUKCJA SIE OKAZE NIEWYKONALNA TO I ATK POLICZY KLOCKI JAKO UZYTE
                    break;
                }
            }
            if (!brickFound) {
                allBricksFound = false;
                System.out.println("nie został znaleniony klocek" + brickInInstruction + " dla budowli " + instruction.getId() + "\n");
                break;
            }
        }
        instruction.itIsPossibleToBuild = allBricksFound;
        if (allBricksFound) {

            bricksLeftInBox-=instruction.getIndex();

            return 1;
        }else {


            bricksMissingInInstructions +=(instruction.getIndex()- listOfIndexOfUsedBricks.size());
        }

        return 0;
    }

    public static int[] contOfBuildingsPossible() {
        int counterPossible = 0;
        int counterNotPossible = 0;
        for (Instruction i : setOfInstructions) {
            if (i.itIsPossibleToBuild) {
                counterPossible++;
            } else counterNotPossible++;
        }
        return new int[]{counterPossible, counterNotPossible};
    }
}