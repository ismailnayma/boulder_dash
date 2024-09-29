package viewFX;

import dp.observers.Observer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Board;
import model.Model;
import model.Position;
import model.TypeCase;


public class BoardFx extends GridPane {

    private Model model;
    private Image[][] boardImages;
    private Images lib;

    public BoardFx(Model model) {
        this.model = model;
        lib = new Images();
        this.createBoard(model.getGame().getBoard());
    }

    public void createBoard(Board board) {
        this.getChildren().clear();
        boardImages = new Image[board.getWidth()][board.getLength()];
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getLength(); j++) {
                TypeCase tc = board.getCaseByPosition(new Position(i, j)).getType();
                switch (tc) {
                    case WALL -> {
                        ImageView wall = new ImageView(lib.getWall());
                        wall.setFitHeight(35);
                        wall.setFitWidth(35);
                        this.add(wall, j, i);
                    }
                    case EARTH -> {
                        ImageView earth = new ImageView(lib.getEarth());
                        earth.setFitWidth(35);
                        earth.setFitHeight(35);
                        this.add(earth, j, i);
                    }
                    case STONE -> {
                        ImageView stone = new ImageView(lib.getStone());
                        stone.setFitWidth(35);
                        stone.setFitHeight(35);
                        this.add(stone, j, i);
                    }
                    case DIAMOND -> {
                        ImageView diamond = new ImageView(lib.getDiamond());
                        diamond.setFitWidth(35);
                        diamond.setFitHeight(35);
                        this.add(diamond, j, i);
                    }
                    case VOID -> {
                        ImageView none = new ImageView(lib.getNone());
                        none.setFitWidth(35);
                        none.setFitHeight(35);
                        this.add(none, j, i);
                    }
                    case PLAYER -> {
                        ImageView player = new ImageView(lib.getPlayer());
                        player.setFitWidth(35);
                        player.setFitHeight(35);
                        this.add(player, j, i);
                    }
                    case OUTLET -> {
                        ImageView outlet = new ImageView(lib.getOutlet());
                        outlet.setFitWidth(35);
                        outlet.setFitHeight(35);
                        this.add(outlet, j, i);
                    }
                }
            }
        }

    }

    /**
     * the board is empty, the utility is just have the leght of the board when you get it
     *
     * @return
     */
    public Image[][] getBoardImages() {
        return boardImages;
    }

    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }
}