package viewFX;

import controllerFX.FxController;
import dp.observers.*;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Direction;
import model.Level;
import model.Model;
import view.ConsoleView;

public class FinalViewFx implements Observer {

    private FxController controller;
    private BoardFx board;
    private ZoomBoardFx zoomBoard;
    private InstructionsImage ins;
    private LabelGameStatusFx lb;
    private Stage stage;
    private Scene scene;

    public FinalViewFx(FxController controller) {
        this.controller = controller;
        board = new BoardFx(controller.getModel());
        zoomBoard = new ZoomBoardFx(board, controller.getModel().getGame().getPosPlayer());
        ins = new InstructionsImage();
        lb = new LabelGameStatusFx(controller.getModel().getGame());
        this.stage = new Stage();
    }


    public void start() {
        board.createBoard(controller.getModel().getGame().getBoard());
        zoomBoard.createZoomBoard(board, controller.getModel().getGame().getPosPlayer());
        stage.setTitle("BoulderDash");
        HBox main = new HBox();
        VBox stateInstructions = new VBox();
        VBox boardAndInfo = new VBox();
        ImageView imageIns = new ImageView(ins.getInstructions());
        ImageView imageInfo = new ImageView(ins.getBasDePage());
        stateInstructions.getChildren().addAll(lb, imageIns);
        boardAndInfo.getChildren().addAll(zoomBoard, imageInfo);

        main.setSpacing(10);
        main.getChildren().addAll(boardAndInfo, stateInstructions);

        scene = new Scene(main);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    controller.makeMove(Direction.UP);
                }
                case DOWN -> {
                    controller.makeMove(Direction.DOWN);
                }
                case RIGHT -> {
                    controller.makeMove(Direction.RIGHT);
                }
                case LEFT -> {
                    controller.makeMove(Direction.LEFT);
                }
                case BACK_SPACE -> {
                    controller.undo();
                }
                case ENTER -> {
                    controller.redo();
                }
                case E -> {
                    controller.abandon();
                }
            }
            if (controller.isOver()) {
                if (controller.getModel().isWon()) {
                    System.out.println("You have won");
                    controller.getModel().removeObserver(this);
                    stage.close();
                    if (controller.getModel().getGame().getLevel().getLevelInt() >= 3) {
                        controller = new FxController(new Model(new Level(ConsoleView.askLevel())));
                        System.out.println(
                                "Level " + controller.getModel().getGame().getLevel().getLevelInt() + " charged");
                        controller.start();
                    } else {
                        controller = new FxController(new Model(
                                new Level(controller.getModel().getGame().getLevel().getLevelInt() + 1)));
                        System.out.println(
                                "Level " + controller.getModel().getGame().getLevel().getLevelInt() + " charged");
                        controller.start();
                    }
                } else {
                    System.out.println("You have lose");
                    stage.close();
                    controller.getModel().removeObserver(this);
                    controller = new FxController(new Model(controller.getModel().getGame().getLevel()));
                    controller.start();
                }
            }

        });

        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void update() {
        board.createBoard(controller.getModel().getGame().getBoard());
        zoomBoard.createZoomBoard(board, controller.getModel().getGame().getPosPlayer());
        lb.update(controller.getModel().getGame());
    }
}
