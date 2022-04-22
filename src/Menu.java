import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static int command;


    public static void mainMenu(Game game) {
        do {
            System.out.println("Welcome to Alfredo Adventures v 1.0. Please make your choice and press Enter");
            System.out.println("1. Start new game");
            System.out.println("2. Options");
            System.out.println("3. Credits");
            System.out.println("4. Exit");

            command = scanner.nextInt();

            switch (command) {
                case 1:
                game.startGame(1, 1);
                    break;
                case 2:
                    break;
                case 3:
                    showCredits();
                    break;
                case 4:
                    break;
                default:
                    throw new IllegalArgumentException("Wrong symbol");
            }

        } while (command != 4);
    }

    public static int merchantMenu(){
        boolean correctInput = false;
        do {
            System.out.println("Greetings, traveler!. Please make your choice and press Enter");
            System.out.println("1. Double move(10)");
            System.out.println("2. Traps + 5 (10");
            System.out.println("3. Critical damage chance X2 (10)");
            System.out.println("4. +1 HP (10)");
            System.out.println("5. Exit");

            command = scanner.nextInt();


            if (command >= 1 && command <=5)
                correctInput = true;
        }while(!correctInput);
        return command;
    }

        public static void showCredits(){
            System.out.println("Made by Smetiukhov Serhii s23520");
        }

        public static int playerTurnMenu(){
            boolean correctInput = false;
            do {
                System.out.println("choose action");
                System.out.println("1. move up");
                System.out.println("2. move down");
                System.out.println("3. move left");
                System.out.println("4. move right");
                System.out.println("5. place trap");
                command = scanner.nextInt();
                if (command >= 1 && command <=5)
                    correctInput = true;
            }while(!correctInput);
            return command;
        }

        public static int placeTrapMenu(){
            boolean correctInput = false;
            do {
                System.out.println("choose action");
                System.out.println("1. place upwards");
                System.out.println("2. place downwards");
                System.out.println("3. place on the left");
                System.out.println("4. place ont the right");
                command = scanner.nextInt();
                if (command >= 1 && command <=4)
                    correctInput = true;
            }while(!correctInput);
            return command;
        }


}