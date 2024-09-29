package dp.command;

import java.util.Stack;

public class CommandManager {

    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public CommandManager() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    /**
     * Add the command to the history of executed commands
     * when the command is executed.
     *
     * @param command Command to execute.
     */
    public void add(Command command) {
        undoStack.push(command);
        command.execute();
        redoStack.clear();
    }

    /**
     * Undoes the last command and keeps the trace of.
     */
    public void undo() {
        if (!undoStack.empty()) {
            undoStack.lastElement().undo();
            redoStack.push(undoStack.lastElement());
            undoStack.pop();
        } else {
            System.err.println("Undo error");
        }
    }

    /**
     * Redoes the last command only if the instance of the last command is not
     * Remove.
     */
    public void redo() {
        if (!redoStack.empty()) {
            redoStack.lastElement().execute();
            undoStack.push(redoStack.lastElement());
            redoStack.pop();
        } else {
            System.err.println("Redo error");
        }
    }
}