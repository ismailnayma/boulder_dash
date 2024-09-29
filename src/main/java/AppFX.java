import controllerFX.FxController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Level;
import model.Model;
import view.ConsoleView;

public class AppFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FxController controller = new FxController(new Model(new Level(ConsoleView.askLevel())));
        controller.start();
    }
}
