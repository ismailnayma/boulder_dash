package model;

/**
 * This class represents a Case in the board
 */
public class Case {

    private TypeCase type;
    private boolean isVisible;

    /**
     * Constructor for a Case
     * All the cases are visible at the creation except the outlet Case
     *
     * @param type
     */
    public Case(TypeCase type) {
        this.type = type;
    }

    /**
     * Get the type of the Case
     *
     * @return TypeCase
     */
    public TypeCase getType() {
        return type;
    }


}
