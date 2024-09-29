package dp.command;

import model.Direction;
import model.Game;
import model.Model;

public class Move implements Command {

    private Model model;
    private Game oldGame;
    private Direction direction;

    public Move(Model model, Direction direction) {
        this.model = model;
        this.oldGame = new Game(model.getGame());
        this.direction = direction;
    }

    @Override
    public void execute() {
        if (!this.model.playShot(this.direction)) {
            System.err.println("You can't go this way");
        }
    }

    @Override
    public void undo() {
        this.model.setGame(oldGame);
    }

    @Override
    public void redo() {
        execute();
    }
}
