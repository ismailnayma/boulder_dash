package viewFX;

import javafx.scene.image.Image;

public class InstructionsImage {

    private Image instructions;
    private Image basDePage;

    public InstructionsImage() {
        instructions = new Image(InstructionsImage.class.getResourceAsStream("/images/instructions.png"));
        basDePage = new Image(InstructionsImage.class.getResourceAsStream("/images/basdepage.png"));
    }

    public Image getInstructions() {
        return instructions;
    }

    public Image getBasDePage() {
        return basDePage;
    }
}
