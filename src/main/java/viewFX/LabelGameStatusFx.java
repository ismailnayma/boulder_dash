package viewFX;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import model.Game;


public class LabelGameStatusFx extends VBox {
    private Label totalDiamond;
    private Label diamondToOpenExit;
    private Label diamondCollected;
    private Label score;

    public LabelGameStatusFx(Game game) {
        super();
        totalDiamond = new Label();
        totalDiamond.setText("TotalDiamond : " + game.getTotalDiamond());
        totalDiamond.setFont(Font.font("Brush Script MT", FontWeight.BLACK, FontPosture.REGULAR, 28));
        diamondToOpenExit = new Label();
        diamondToOpenExit.setText("DiamondToExit : " + game.getDiamondToDisplayOutlet());
        diamondToOpenExit.setFont(Font.font("Brush Script MT", FontWeight.BLACK, FontPosture.REGULAR, 28));
        diamondCollected = new Label();
        diamondCollected.setText("DiamondCollected : " + game.getDiamondPickedUp());
        diamondCollected.setFont(Font.font("Brush Script MT", FontWeight.BLACK, FontPosture.REGULAR, 28));
        score = new Label();
        score.setText("Score : " + game.getActualScore());
        score.setFont(Font.font("Brush Script MT", FontWeight.BLACK, FontPosture.REGULAR, 28));
        this.getChildren().addAll(totalDiamond, diamondToOpenExit, diamondCollected, score);
    }

    public void update(Game game) {
        totalDiamond.setText("TotalDiamond : " + game.getTotalDiamond());
        diamondToOpenExit.setText("DiamondToExit : : " + game.getDiamondToDisplayOutlet());
        diamondCollected.setText("DiamondCollected : " + game.getDiamondPickedUp());
        score.setText("Score : " + game.getActualScore());
    }

}
