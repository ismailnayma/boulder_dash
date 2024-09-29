package viewFX;

import controllerFX.FxController;
import dp.observers.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DiamondCollected implements Observer {

    private FxController controller;
    private Stage stage;
    private int nbDiamond;
    private Label diamondCollected;

    public DiamondCollected(FxController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    public void startBigView(){
        stage.setTitle("Diamond Collected");
        diamondCollected = new Label();
        diamondCollected.setFont(Font.font("New Times Roman", 20));
        diamondCollected.setText("DiamondCollected : "+nbDiamond);
        Scene scene = new Scene(diamondCollected, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update() {
        nbDiamond = controller.getModel().getGame().getDiamondPickedUp();
        diamondCollected.setText("DiamondCollected : "+nbDiamond);

    }
}
