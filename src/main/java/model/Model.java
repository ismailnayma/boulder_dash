package model;

import dp.observers.Observable;
import dp.observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class Model correspond to the facade pattern
 */
public class Model implements Observable {

    private Game game;
    private List<Observer> observers = new ArrayList<>();

    /**
     * Constructor of the class Model (facade)
     * Allows to start a game
     *
     * @param level
     */
    public Model(Level level) {
        this.game = new Game(level);
    }


    /**
     * Check if the game is over
     * Verify if the state is GAME_OVER
     *
     * @return true if the game is over
     */
    public boolean isOver() {
        if (game.getState() == State.GAME_OVER) {
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the user won the game
     * Verify if the player won the game
     *
     * @return true if the player won
     */
    public boolean isWon() {
        if (game.isPlayerWin()) {
            notifyObservers();
            return true;
        } else if (!game.isPlayerAlive()) {
            return false;
        }
        return false;
    }


    /**
     * Move the player in a direction
     *
     * @param direction
     * @return true if the move was executed
     */
    public boolean playShot(Direction direction) {
        if (game.move(direction)) {
            while (game.checkStonesAndDiamonds()) {
                game.checkStonesAndDiamonds();
            }
            game.walkAway();
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Abandon the game
     * Put the state game at GAME OVER
     */
    public void abandon() {
        game.setState(State.GAME_OVER);
        notifyObservers();
    }

    /**
     * Getter of the Game
     *
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Set the game attribute with the param Game
     * thanks to a copy constructor
     *
     * @param game
     */
    public void setGame(Game game) {
        this.game = new Game(game);
        notifyObservers();
    }

    /**
     * Add an observer in the list of observers
     *
     * @param observer The observer to be added.
     */
    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
        System.out.println("Observer added !");
    }

    /**
     * Remove the observer from the list of observers
     *
     * @param observer The  observer to be removed.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer deleted !");
    }

    /**
     * Notify the observers that the observable has been changed
     * execute the update method for all observers
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
        System.out.println("Update observers");

    }
}
