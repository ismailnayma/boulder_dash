


import controller.ConsoleController;
import model.Level;
import model.Model;
import view.ConsoleView;

/**
 * @author 55996
 */
public class App {


    public static void main(String[] args) {
        ConsoleController ctrl = new ConsoleController(new Model(new Level(ConsoleView.askLevel())));
        ctrl.start();
    }

}