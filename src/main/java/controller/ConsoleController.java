package controller;

import dp.command.Command;
import dp.command.CommandManager;
import dp.command.Move;
import model.Direction;
import model.Model;
import view.ConsoleView;

public class ConsoleController {

    private Model model;
    private ConsoleView view;
    private Command command;
    private CommandManager cmdCtrl;

    public ConsoleController(Model model) {
        this.model = model;
        this.view = new ConsoleView(this.model);
        cmdCtrl = new CommandManager();
    }

    /**
     * Starts the Application and asks the user to enter a command.
     */
    public void start() {
        char c;
        do {
            view.displayScoreAndDiamond();
            view.displayBoard();
            c = view.askCommand(
                    "Enter command ['z'->(UP),'q'->(LEFT), 's'->(DOWN), 'd'->(RIGHT)," +
                            " 'u'->(UNDO), 'r'(REDO), 'e'->(exit)");
            switch (c) {
                case 'z' -> {
                    this.command = new Move(model, Direction.UP);
                    cmdCtrl.add(command);
                }
                case 'q' -> {
                    this.command = new Move(model, Direction.LEFT);
                    cmdCtrl.add(command);
                }
                case 's' -> {
                    this.command = new Move(model, Direction.DOWN);
                    cmdCtrl.add(command);
                }
                case 'd' -> {
                    this.command = new Move(model, Direction.RIGHT);
                    cmdCtrl.add(command);
                }
                case 'u' -> {
                    cmdCtrl.undo();
                }
                case 'r' -> {
                    cmdCtrl.redo();
                }
                case 'e' -> {
                    model.abandon();
                }
                default -> System.err.println("Incorrect command [please re-try]");
            }

        }
        while (!model.isOver());
        if (model.isWon()) {
            System.out.println("You have won");
        } else {
            System.out.println("You have lose");
        }
    }


}
