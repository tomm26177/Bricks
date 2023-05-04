import java.util.*;

public class Bricks {


    static Set<Instruction> setOfInstructions = new HashSet<>();
    static List<Integer> numbersUsedInInstruction = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in).useDelimiter("\\s+");
        while (input.hasNext()) {
            String i = input.nextLine();
            SegmentAnalizer(i);
        }
        BuildingProcess();

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
            System.out.println("nieprawidłowa liczba");
        }
    }

    public static void BuildingProcess() {
        for (Instruction instruction : setOfInstructions) {
            if (instruction.getId() % 3 == 0) {

                System.out.println("sprawdzam kocki dla instrukcji numer " + instruction.getId() + "\n");

                build(instruction);

            }
        }
        for (Instruction instruction : setOfInstructions) {
            if (!(instruction.getId() % 3 == 0)) {

                System.out.println("sprawdzam kocki dla instrukcji numer " + instruction.getId() + "\n");

                build(instruction);

            }
        }

    }

    private static void build(Instruction instruction) {
        Box box = Box.getInstance();
        boolean allBricksFound = true;
        for (String brickInInstruction : instruction.ArrayOfBricks) {
            if (brickInInstruction == null) {
                break;
            }
            boolean brickFound = false;
            for (int i = 0; i < box.index; i++) {
                if (box.getBrickArray()[i] != null && box.getBrickArray()[i].equals(brickInInstruction)) {
                    System.out.println("został znaleziony klocek " + brickInInstruction + "\n");
                    brickFound = true;
                    box.getBrickArray()[i] = null;
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
    }

    public int[] contOfBuildingsPossible() {
        int counterPossible = 0;
        int counterNotPossible = 0;
        for (Instruction i : setOfInstructions) {
            if (i.itIsPossibleToBuild) {
                counterPossible++;
            } else counterNotPossible++;
        }
        return new int[]{counterPossible, counterNotPossible};
    }

    public int countingOfUsedBlocksInFirstStep() {
        int counter=0;
        for (Instruction i : setOfInstructions) {
            if(i.getId()%3==0 && i.itIsPossibleToBuild){
               counter += i.ArrayOfBricks.length;
               //TODO MOZE PRZELICZAC TEZ NULL DO SPRAWDZEIA
            }
        }
        return counter;
    }

    public int countingOfUsedBlocksInSecondStep() {
        int counter=0;
        for (Instruction i : setOfInstructions) {
            if(!(i.getId()%3==0) && i.itIsPossibleToBuild){
                counter += i.ArrayOfBricks.length;
                //TODO MOZE PRZELICZAC TEZ NULL DO SPRAWDZEIA
            }
        }
        return counter;
    }

    public int countingOfReminderBlocksInBox(){
        int blockReminderInbox=0;
        int AllBlockInBox = 0;
         Box box = Box.getInstance();


         for(String BricksInBox : box.getBrickArray() ){

             if(BricksInBox!=null){
                 AllBlockInBox++;
             }


         }

         for(String BrickFromBox: box.getBrickArray()) {

             if (BrickFromBox != null) {

                 for (Instruction i : setOfInstructions) {

                     if (i.itIsPossibleToBuild) {


                         for (String BrickFromInstruction : i.ArrayOfBricks) {

                             if(BrickFromInstruction!=null) {


                                 if (BrickFromBox == BrickFromInstruction) {

                                     blockReminderInbox++;

                                 }
                             }
                         }

                     }

                 }

             }
         }

         return AllBlockInBox - blockReminderInbox;

    }


    public int countingOfLackingBlocks(){

        Box box = Box.getInstance();

        for(String BrickFromBox: box.getBrickArray()) {

            if (BrickFromBox != null) {

                for (Instruction i : setOfInstructions) {

                    if (!(i.itIsPossibleToBuild)) {
                        //todo tutaj zrobic ostatni punkt


                        for (String BrickFromInstruction : i.ArrayOfBricks) {

                            if(BrickFromInstruction!=null) {


                                if (BrickFromBox == BrickFromInstruction) {

                                    blockReminderInbox++;

                                }
                            }
                        }
                        //todo a tu koniec przerobekl

                    }

                }

            }
        }
    }

    //todo Łączną liczbę klocków, których brakowało
    // w pudełku podczas realizacji poszczególnych instrukcji




}