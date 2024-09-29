package model;

/**
 * This class represent the type of Case
 */
public enum TypeCase {

    WALL,
    EARTH,
    STONE,
    DIAMOND,
    VOID,
    PLAYER,
    OUTLET;

    /**
     * Getter of a char who correspond to the Typecase
     *
     * @return char
     */
    public char getChar() {
        switch (this) {
            case WALL -> {
                return 'w';
            }
            case EARTH -> {
                return 'e';
            }
            case STONE -> {
                return 's';
            }
            case DIAMOND -> {
                return 'd';
            }
            case VOID -> {
                return 'v';
            }
            case PLAYER -> {
                return 'p';
            }
            case OUTLET -> {
                return 'o';
            }
        }
        return 0;
    }

}

