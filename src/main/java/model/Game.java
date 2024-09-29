package model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Level level;
    private Board board;
    private Position posPlayer;
    private State state;
    private int actualRemaningDiamond;
    private int diamondPickedUp;
    private int totalDiamond;
    private int nbDiamondValue;
    private int actualScore;
    private int diamondToDisplayOutlet;
    private boolean accessibleOutlet;
    private boolean playerWin;
    private boolean playerAlive;

    /**
     * Constructor of Game
     * Create a game with the level entered in param
     * This what she does:
     * <ul>
     *   <li>Saves the level</li>
     *   <li>Create a Board with the level</li>
     *   <li>Saves the position of the player</li>
     *   <li>Initialise the state at Not Started</li>
     *   <li>Saves the number of total diamond</li>
     *   <li>Initialise diamondPickedUp to 0 </li>
     *   <li>Initialise actual Remaining Diamond with the total diamond</li>
     *   <li>Saves the value of a diamond </li>
     *   <li>Initialise the score to 0</li>
     *   <li>Saves the number of diamonds needed to open the exit</li
     *   <li>Initialise accessibleOutlet to false</li
     *   <li>Initialise playerWin to false</li>
     *   <li>Initialise playerAlive to true</li>
     * </ul>
     * Saves the board
     *
     * @param level
     */
    public Game(Level level) {
        this.level = level;
        this.board = new Board(this.level.getString());
        this.posPlayer = board.getPosPlayer();
        this.state = State.NOT_STARTED;
        this.totalDiamond = board.getDiamondPositions().size();
        this.diamondPickedUp = 0;
        this.actualRemaningDiamond = totalDiamond;
        this.nbDiamondValue = this.level.getDiamondValue();
        this.actualScore = 0;
        this.diamondToDisplayOutlet = this.level.getNbDiamondToDisplayOutlet();
        this.accessibleOutlet = false;

        this.playerWin = false;
        this.playerAlive = true;
    }

    /**
     * Copy constructor of Game
     *
     * @param game
     */
    public Game(Game game) {
        this.level = new Level(game.level.getLevelInt());
        this.board = new Board(game.board);
        this.posPlayer = new Position(game.posPlayer.getRow(), game.posPlayer.getColumn());
        this.state = game.getState();
        this.actualRemaningDiamond = game.actualRemaningDiamond;
        this.diamondPickedUp = game.diamondPickedUp;
        this.totalDiamond = game.totalDiamond;
        this.nbDiamondValue = game.nbDiamondValue;
        this.actualScore = game.actualScore;
        this.diamondToDisplayOutlet = game.diamondToDisplayOutlet;
        this.accessibleOutlet = game.accessibleOutlet;
        this.playerWin = game.playerWin;
        this.playerAlive = game.playerAlive;
    }

    /**
     * Move a player in a direction
     * Check if it's possible to move this way and if it's possible he moves the player and make the needed changes
     * and return true else he does nothing and return false
     *
     * @param direction
     * @return boolean if the player moved
     */
    public boolean move(Direction direction) {
        if (this.isInside(getPositionNextDirection(posPlayer, direction))) {
            if(checkCaseAndMove(getPositionNextDirection(posPlayer, direction), direction)){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Receive the position where we want to move and check if it's possible to move this way and
     * if it's possible he moves the player and make the needed changes
     * :
     * <li>Put the state at IN_GAME</li>
     * <li>Changes the position of the player and update the board</li>
     * and return true else he does nothing and return false
     *
     * @param position
     * @param direction
     * @return boolean if the player moved
     */
    public boolean checkCaseAndMove(Position position, Direction direction) {
        state = State.IN_GAME;
        Case caseToCheck = board.getCaseByPosition(position);
        boolean bool = false;
        switch (caseToCheck.getType()) {
            case VOID:
            case EARTH:
                board.changeCasePosition(posPlayer, new Case(TypeCase.VOID));
                board.changeCasePosition(position, new Case(TypeCase.PLAYER));
                posPlayer = position;
                bool = true;
                break;
            case STONE:
                Position checkAfterStone = getPositionNextDirection(position, direction);
                if (board.getCaseByPosition(checkAfterStone).getType() == TypeCase.VOID) {
                    board.changeCasePosition(posPlayer, new Case(TypeCase.VOID));
                    board.changeCasePosition(position, new Case(TypeCase.PLAYER));
                    board.changeCasePosition(checkAfterStone, new Case(TypeCase.STONE));
                    posPlayer = position;
                    int index = board.getIndexStoneFromPosition(position);
                    board.changePositionOfStoneList(index, checkAfterStone);
                    bool = true;
                } else {
                    bool = false;
                }
                break;
            case DIAMOND:
                board.changeCasePosition(posPlayer, new Case(TypeCase.VOID));
                board.changeCasePosition(position, new Case(TypeCase.PLAYER));
                board.removeDiamondOfList(position);
                posPlayer = position;
                actualRemaningDiamond--;
                diamondPickedUp++;
                actualScore += nbDiamondValue;
                if (diamondPickedUp >= diamondToDisplayOutlet) {
                    accessibleOutlet = true;
                }
                bool = true;
                break;
            case WALL:
                bool = false;
                break;
            case OUTLET:
                if (accessibleOutlet) {
                    board.changeCasePosition(posPlayer, new Case(TypeCase.VOID));
                    board.changeCasePosition(position, new Case(TypeCase.PLAYER));
                    posPlayer = position;
                    state = State.GAME_OVER;
                    playerWin = true;
                }
                break;
        }
        return bool;
    }

    /**
     * Receive a position and a direction, and return the entered position next to the direction
     *
     * @param position
     * @param direction
     * @return Position
     */
    public Position getPositionNextDirection(Position position, Direction direction) {
        switch (direction) {
            case RIGHT:
                return new Position(position.getRow(), position.getColumn() + 1);
            case LEFT:
                return new Position(position.getRow(), position.getColumn() - 1);
            case UP:
                return new Position(position.getRow() - 1, position.getColumn());
            case DOWN:
                return new Position(position.getRow() + 1, position.getColumn());
        }
        return null;
    }

    /**
     * Check if a position it's inside the board
     *
     * @param position
     * @return true if position is inside else false
     */
    public boolean isInside(Position position) {
        if ((position.getRow() < board.getWidth() && position.getRow() >= 0)
                && (position.getColumn() < board.getLength() && position.getRow() >= 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check for all stones and diamond if there is a way to fall
     * Makes them fall if there is a way
     *
     * @return true if a stone or a diamond has moved
     */
    public boolean checkStonesAndDiamonds() {
        boolean modify = false;
        List<Position> stonePositions = board.getStonePositions();
        List<Position> diamondsPositions = board.getDiamondPositions();
        for (int i = 0; i < stonePositions.size(); i++) {
            Position posToCheck = stonePositions.get(i);
            if (board.getCaseByPosition(getPositionNextDirection(stonePositions.get(i), Direction.DOWN)).getType()
                    == TypeCase.VOID) {
                posToCheck = stonePositions.get(i);
            } else if (board.getCaseByPosition(getPositionNextDirection(stonePositions.get(i), Direction.DOWN)).getType()
                    == TypeCase.STONE) {
                if (board.getCaseByPosition(getPositionNextDirection(stonePositions.get(i), Direction.RIGHT)).getType()
                        == TypeCase.VOID) {
                    posToCheck = getPositionNextDirection(stonePositions.get(i), Direction.RIGHT);
                } else if (board.getCaseByPosition(getPositionNextDirection(stonePositions.get(i), Direction.LEFT)).getType()
                        == TypeCase.VOID) {
                    posToCheck = getPositionNextDirection(stonePositions.get(i), Direction.LEFT);
                }
            }
            Position position = checkRockFall(posToCheck);
            if (position != null) {
                board.changeCasePosition(position, new Case(TypeCase.STONE));
                board.changeCasePosition(stonePositions.get(i), new Case(TypeCase.VOID));
                board.changePositionOfStoneList(i, position);
                modify = true;
            }
        }
        for (int i = 0; i < diamondsPositions.size(); i++) {
            Position posToCheck = diamondsPositions.get(i);
            if (board.getCaseByPosition(getPositionNextDirection(diamondsPositions.get(i), Direction.DOWN)).getType()
                    == TypeCase.VOID) {
                posToCheck = diamondsPositions.get(i);
            } else if (board.getCaseByPosition(getPositionNextDirection(diamondsPositions.get(i), Direction.DOWN)).getType()
                    == TypeCase.STONE) {
                if (board.getCaseByPosition(getPositionNextDirection(diamondsPositions.get(i), Direction.RIGHT)).getType()
                        == TypeCase.VOID) {
                    posToCheck = getPositionNextDirection(diamondsPositions.get(i), Direction.RIGHT);
                } else if (board.getCaseByPosition(getPositionNextDirection(diamondsPositions.get(i), Direction.LEFT)).getType()
                        == TypeCase.VOID) {
                    posToCheck = getPositionNextDirection(diamondsPositions.get(i), Direction.LEFT);
                }
            }

            Position position = checkRockFall(posToCheck);
            if (position != null) {
                board.changeCasePosition(position, new Case(TypeCase.DIAMOND));
                board.changeCasePosition(diamondsPositions.get(i), new Case(TypeCase.VOID));
                board.changePositionOfDiamondList(i, position);
                modify = true;
            }
        }
        return modify;
    }

    /**
     * Check for a diamond or a stone if there is no void space at the bottom
     * check if the rock(diamond or stone) can fall
     *
     * @return the position where the rock can fall
     */
    public Position checkRockFall(Position position) {
        Position posToReturn = null;
        Position nextTocheck = getPositionNextDirection(position, Direction.DOWN);
        if (board.getCaseByPosition(nextTocheck).getType() == TypeCase.PLAYER) {
            return null;
        } else if (board.getCaseByPosition(nextTocheck).getType() == TypeCase.VOID) {
            posToReturn = nextTocheck;
            nextTocheck = getPositionNextDirection(posToReturn, Direction.DOWN);
            while (board.getCaseByPosition(nextTocheck).getType() == TypeCase.VOID
                    || board.getCaseByPosition(nextTocheck).getType() == TypeCase.PLAYER) {
                if (board.getCaseByPosition(nextTocheck).getType() == TypeCase.VOID) {
                    posToReturn = nextTocheck;
                    nextTocheck = getPositionNextDirection(posToReturn, Direction.DOWN);
                } else if (board.getCaseByPosition(nextTocheck).getType() == TypeCase.PLAYER) {
                    state = State.GAME_OVER;
                    playerAlive = false;
                    return nextTocheck;
                }
            }
        }
        return posToReturn;

    }

    /**
     * Check if the player won
     *
     * @return boolean
     */
    public boolean isPlayerWin() {
        return playerWin;
    }

    /**
     * Getter of the game state
     *
     * @return State
     */
    public State getState() {
        return state;
    }

    /**
     * Set the state of Game of the state at param
     *
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Getter of the Game player position
     *
     * @return Position of player
     */
    public Position getPosPlayer() {
        return posPlayer;
    }

    /**
     * Check if the player is alive
     *
     * @return boolean
     */
    public boolean isPlayerAlive() {
        return playerAlive;
    }

    /**
     * Getter of Game Board
     *
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    public boolean checkIfWalkAway(){
        Position outlet = board.getOutletPosition();
        if((outlet.getRow() == posPlayer.getRow())){
            if(outlet.getColumn() == posPlayer.getColumn()
                    || outlet.getColumn() == posPlayer.getColumn()+1
                    || outlet.getColumn() == posPlayer.getColumn()-1){
                return true;

            }

        }else if(outlet.getColumn()==posPlayer.getColumn()){
            if(outlet.getRow() == posPlayer.getRow()
                    || outlet.getRow() == posPlayer.getRow()+1
                    || outlet.getRow() == posPlayer.getRow()-1){
                return true;

            }
        }
        return false;
    }

    public void walkAway(){
        Position outlet = board.getOutletPosition();
        // position direction +4
        Position top = new Position(outlet.getRow(), outlet.getColumn());
        Position down = new Position(outlet.getRow(), outlet.getColumn());
        Position right = new Position(outlet.getRow(), outlet.getColumn());
        Position left = new Position(outlet.getRow(), outlet.getColumn());
        for (int i = 0; i < 4; i++) {
            top = getPositionNextDirection(top, Direction.UP);
            down = getPositionNextDirection(down, Direction.DOWN);
            right = getPositionNextDirection(right, Direction.RIGHT);
            left = getPositionNextDirection(left, Direction.LEFT);
        }
        if(checkIfWalkAway()){
            if(isInside(down)){
                board.changeCasePosition(outlet, new Case(TypeCase.VOID));
                board.changeCasePosition(down, new Case(TypeCase.OUTLET));
                board.setOutletPosition(down);
            }else if(isInside(top)){
                board.changeCasePosition(outlet, new Case(TypeCase.VOID));
                board.changeCasePosition(top, new Case(TypeCase.OUTLET));
                board.setOutletPosition(top);

            }else if(isInside(left)){
                board.changeCasePosition(outlet, new Case(TypeCase.VOID));
                board.changeCasePosition(left, new Case(TypeCase.OUTLET));
                board.setOutletPosition(left);
            }else if(isInside(right)){
                board.changeCasePosition(outlet, new Case(TypeCase.VOID));
                board.changeCasePosition(right, new Case(TypeCase.OUTLET));
                board.setOutletPosition(right);

            }

        }
    }

    /**
     * Getter of Game Level
     *
     * @return
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Getter of the game total diamonds
     *
     * @return int
     */
    public int getTotalDiamond() {
        return totalDiamond;
    }

    /**
     * Getter of the game diamondToDisplayOutlet
     *
     * @return int
     */
    public int getDiamondToDisplayOutlet() {
        return diamondToDisplayOutlet;
    }

    /**
     * Getter of the game diamondPickedUp
     *
     * @return int
     */
    public int getDiamondPickedUp() {
        return diamondPickedUp;
    }

    /**
     * Getter of the game actualScore
     *
     * @return
     */
    public int getActualScore() {
        return actualScore;
    }
}
