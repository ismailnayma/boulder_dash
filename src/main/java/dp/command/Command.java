package dp.command;

public interface Command {
    /**
     * Executes the specified command
     */
    void execute();

    /**
     * Undoes the specified command
     */
    void undo();

    /**
     * redoes the specified command
     */
    void redo();
}
