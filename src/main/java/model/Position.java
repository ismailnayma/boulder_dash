package model;

import java.util.Objects;

/**
 * This class correspond to a Position on a Board
 */
public class Position {
    private int row;
    private int column;

    /**
     * Constructor for a Position
     *
     * @param row    The row of the position
     * @param column The column of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Give the row of position
     *
     * @return the row of position
     */
    public int getRow() {
        return row;
    }

    /**
     * Give the column of position
     *
     * @return the column of position
     */
    public int getColumn() {
        return column;
    }

    /**
     * Verify the position is equals thanks to the attribute of the positions
     *
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
