package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a Board of Cases
 */
public class Board {

    private Case[][] cases;
    private List<Position> stonePositions = new ArrayList<>();
    private List<Position> diamondPositions = new ArrayList<>();
    private Position outletPosition;
    private Position posPlayer;
    private int length;
    private int width;

    /**
     * Constructor of Board , create a new Case[][]
     * It browses the string received as parameter and for each char of the string it creates a new Case, and
     * it's placed on the Case[][] board
     * It saves the on the stonePositions and diamondPositions the position on the board when
     * a stone or a diamond is created
     * It saves the outletPosition and posPlayer Position
     *
     * @param string
     */
    public Board(String string) {
        String[] stringBoard = string.split("\n");
        length = stringBoard[0].length();
        width = stringBoard.length;
        cases = new Case[width][length];
        for (int i = 0; i < stringBoard.length; i++) {
            String line = stringBoard[i];
            for (int j = 0; j < line.length(); j++) {
                TypeCase type = getTypeCaseByChar(line.charAt(j));
                if (type == TypeCase.STONE) {
                    stonePositions.add(new Position(i, j));
                } else if (type == TypeCase.DIAMOND) {
                    diamondPositions.add(new Position(i, j));
                } else if (type == TypeCase.OUTLET) {
                    outletPosition = new Position(i, j);
                } else if (type == TypeCase.PLAYER) {
                    posPlayer = new Position(i, j);
                }
                cases[i][j] = new Case(type);
            }
        }
    }

    /**
     * Copy constructor of Board
     *
     * @param board
     */
    public Board(Board board) {
        this.cases = getCasesArray(board.cases);
        this.stonePositions = getCopyofList(board.stonePositions);
        this.diamondPositions = getCopyofList(board.diamondPositions);
        this.outletPosition = board.getOutletPosition();
        this.posPlayer = new Position(board.posPlayer.getRow(), board.posPlayer.getColumn());
        this.length = board.length;
        this.width = board.width;
    }

    /**
     * Change the value of the position enter in param by the value Case entered
     *
     * @param position position to change
     * @param case1    the new value to put at the param position
     */
    public void changeCasePosition(Position position, Case case1) {
        cases[position.getRow()][position.getColumn()] = case1;
    }

    /**
     * Get the Case at the position entered
     *
     * @param position
     * @return Case
     */
    public Case getCaseByPosition(Position position) {
        return cases[position.getRow()][position.getColumn()];
    }

    /**
     * Getter of the list of stonePositions
     *
     * @return a list of stonePositions
     */
    public List<Position> getStonePositions() {
        return stonePositions;
    }

    /**
     * Getter of the list of diamondPositions
     *
     * @return list of diamondPositions
     */
    public List<Position> getDiamondPositions() {
        return diamondPositions;
    }


    /**
     * Getter of the board length
     * Correspond to the number of column
     *
     * @return int
     */
    public int getLength() {
        return length;
    }

    /**
     * Getter of the board width
     * Correspond to the number of row
     *
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter of the outlet Position
     *
     * @return outlet Position
     */
    public Position getOutletPosition() {
        return outletPosition;
    }


    /**
     * Getter of the player Position
     *
     * @return player Position
     */
    public Position getPosPlayer() {
        return posPlayer;
    }

    public void setOutletPosition(Position outletPosition) {
        this.outletPosition = outletPosition;
    }

    /**
     * Getter of the TypeCase with char entered in param
     *
     * @param c
     * @return TypeCase
     */
    public TypeCase getTypeCaseByChar(char c) {
        switch (c) {
            case 'w' -> {
                return TypeCase.WALL;
            }
            case 'e' -> {
                return TypeCase.EARTH;
            }
            case 's' -> {
                return TypeCase.STONE;
            }
            case 'd' -> {
                return TypeCase.DIAMOND;
            }
            case 'v' -> {
                return TypeCase.VOID;
            }
            case 'p' -> {
                return TypeCase.PLAYER;
            }
            case 'o' -> {
                return TypeCase.OUTLET;
            }
        }
        return null;
    }

    /**
     * Override of the board
     * We display at each box of the board the character that corresponds to this,
     * and we go to the line at each new row
     *
     * @return String of board
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                builder.append(cases[i][j].getType().getChar()).append("\s");
            }
            builder.append("\n");
        }
        return builder.toString();
    }


    /**
     * Find the position entered on param in the list diamondPositions and remove it from there
     *
     * @param position
     */
    public void removeDiamondOfList(Position position) {
        diamondPositions.remove(position);
    }

    /**
     * Change the value at the index of the list diamondPositions
     *
     * @param index
     * @param position
     */
    public void changePositionOfDiamondList(int index, Position position) {
        diamondPositions.set(index, position);
    }

    /**
     * Change the value at the index of the list stonePositions
     *
     * @param index
     * @param position
     */
    public void changePositionOfStoneList(int index, Position position) {
        stonePositions.set(index, position);
    }

    /**
     * Find the position entered on param in the list stonePositions and return the index
     *
     * @param position
     * @return int of the index
     */
    public int getIndexStoneFromPosition(Position position) {
        for (int i = 0; i < stonePositions.size(); i++) {
            if (stonePositions.get(i).equals(position)) {
                return i;
            }
        }
        return 3;
    }

    /**
     * Copy constructor of a list
     *
     * @param list
     * @return list of Positions
     */
    private List<Position> getCopyofList(List<Position> list) {
        List<Position> listToReturn = new ArrayList<>();
        for (Position p : list) {
            listToReturn.add(new Position(p.getRow(), p.getColumn()));
        }
        return listToReturn;
    }

    /**
     * Getter of the array 2D of Cases
     *
     * @param cases
     * @return Case[][]
     */
    private Case[][] getCasesArray(Case[][] cases) {
        Case[][] casesToReturn = new Case[cases.length][cases[0].length];
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                casesToReturn[i][j] = new Case(cases[i][j].getType());
            }
        }
        return casesToReturn;
    }


}
