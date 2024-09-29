package viewFX;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import model.Position;

public class ZoomBoardFx extends GridPane {

    private BoardFx boardFx;
    private Position posplayer;

    public ZoomBoardFx(BoardFx boardFx, Position posPlayer) {

        this.boardFx = boardFx;
        this.posplayer = posPlayer;

    }

    public void createZoomBoard(BoardFx boardFx, Position posPlayer) {
        this.boardFx = boardFx;
        this.posplayer = posPlayer;
        this.getChildren().clear();
        int middleRow = boardFx.getBoardImages().length / 2;
        int middleColumn = boardFx.getBoardImages()[0].length / 2;
        if (posplayer.getColumn() < middleColumn) {
            if (posplayer.getRow() < middleRow) {
                // left top
                for (int i = 0; i < middleRow + 5; i++) {
                    for (int j = 0; j < middleColumn + 5; j++) {
                        this.add(boardFx.getNodeByRowColumnIndex(i, j, boardFx), j, i);
                    }
                }

            } else {
                //left down
                for (int i = middleRow - 5; i < boardFx.getBoardImages().length; i++) {
                    for (int j = 0; j < middleColumn + 5; j++) {
                        this.add(boardFx.getNodeByRowColumnIndex(i, j, boardFx), j, i);
                    }
                }

            }
        } else {
            if (posplayer.getRow() < middleRow) {
                // right top
                for (int i = 0; i < middleRow + 5; i++) {
                    for (int j = middleColumn - 5; j < boardFx.getBoardImages()[0].length; j++) {
                        this.add(boardFx.getNodeByRowColumnIndex(i, j, boardFx), j, i);
                    }
                }


            } else {
                // right down
                for (int i = middleRow - 5; i < boardFx.getBoardImages().length; i++) {
                    for (int j = middleColumn - 5; j < boardFx.getBoardImages()[0].length; j++) {
                        this.add(boardFx.getNodeByRowColumnIndex(i, j, boardFx), j, i);
                    }
                }
            }

        }

    }
}
