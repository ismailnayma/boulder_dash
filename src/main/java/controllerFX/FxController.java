package controllerFX;

import dp.command.Command;
import dp.command.CommandManager;
import dp.command.Move;
import javafx.stage.Stage;
import model.Direction;
import model.Level;
import model.Model;
import viewFX.DiamondCollected;
import viewFX.FinalViewFx;

public class FxController {

    private Model model;
    private FinalViewFx view;
    private Command command;
    private CommandManager cmdCtrl;
    private DiamondCollected diamondCollected;

    public FxController(Model model) {
        this.model = model;
        this.view = new FinalViewFx(this);
        this.model.addObserver(this.view);
        diamondCollected = new DiamondCollected(this,new Stage());
        this.model.addObserver(this.diamondCollected);
        cmdCtrl = new CommandManager();
    }


    public void start() {
        view.start();
        diamondCollected.startBigView();
    }


    public Model getModel() {
        return model;
    }

    public void makeMove(Direction direction) {
        this.command = new Move(model, direction);
        cmdCtrl.add(command);
    }

    public void undo() {
        cmdCtrl.undo();
    }

    public void redo() {
        cmdCtrl.redo();
    }

    public void abandon() {
        model.abandon();
    }

    public boolean isOver() {
        return model.isOver();
    }
}
