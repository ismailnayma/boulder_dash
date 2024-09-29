package viewFX;


import javafx.scene.image.Image;


public class Images {

    private final Image wall;
    private final Image earth;
    private final Image stone;
    private final Image diamond;
    private final Image none;
    private final Image player;
    private final Image outlet;

    public Images() {
        wall = new Image(Images.class.getResourceAsStream("/images/wall.jpg"));
        earth = new Image(Images.class.getResourceAsStream("/images/earth.jpg"));
        stone = new Image(Images.class.getResourceAsStream("/images/stone.jpg"));
        diamond = new Image(Images.class.getResourceAsStream("/images/diamond.jpg"));
        none = new Image(Images.class.getResourceAsStream("/images/void.jpg"));
        outlet = new Image(Images.class.getResourceAsStream("/images/door.jpg"));
        player = new Image(Images.class.getResourceAsStream("/images/player2.jpg"));
    }

    public Image getWall() {
        return wall;
    }

    public Image getEarth() {
        return earth;
    }

    public Image getStone() {
        return stone;
    }

    public Image getDiamond() {
        return diamond;
    }

    public Image getNone() {
        return none;
    }

    public Image getPlayer() {
        return player;
    }

    public Image getOutlet() {
        return outlet;
    }
}
