package view;

import model.Direction;
import model.Model;

import java.util.Scanner;

public class ConsoleView {

    private Model model;

    public ConsoleView(Model model) {
        this.model = model;
    }

    /**
     * Display the board
     */
    public void displayBoard() {
        System.out.println(model.getGame().getBoard());
    }

    /**
     * Ask a command to the user
     *
     * @return Array of String with every word of the command.
     */
    public char askCommand(String msg) {
        String[] inputs;
        Scanner keyboard = new Scanner(System.in);
        System.out.println(msg);

        String input = keyboard.nextLine().toLowerCase();
        if (input.length() != 1) {
            System.err.println("The command entered is incorrect");
        }
        return input.charAt(0);
    }

    public static int askLevel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a level between 1 and 3  :");
        int str = sc.nextInt();
        while (!(str >= 1 && str <= 3)) {
            System.out.println("Choose a level between 1 and 3  :");
            str = sc.nextInt();
        }
        return str;
    }

    public void displayScoreAndDiamond() {
        System.out.println("TotalD: " + model.getGame().getTotalDiamond()
                + " - DToOpen: " + model.getGame().getDiamondToDisplayOutlet()
                + " - CollectedD: " + model.getGame().getDiamondPickedUp()
                + " - Score: " + model.getGame().getActualScore());
    }

}


