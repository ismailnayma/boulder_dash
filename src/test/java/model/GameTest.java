package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void move() {
        /*
        top on the level 1 to visualise
        wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
        weeeeeeveedesveeeeeseseeeeeeeveedeseeeew
        wespseeeeeeveeeeeeeeesdeeseeeeveeeeeveew
        weeeeeeeeeeveeveeeeeseseeseeeeeeeeseeeew
        wsevveeeeeeeeeseeeeeeseeseeeeeseeseeeeew
         */
        Game game = new Game(new Level(1));
        assertFalse(game.move(Direction.RIGHT)); // there is a stone at the left
        Position posExpected = new Position(2, 3);
        Position posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        assertTrue(game.move(Direction.DOWN)); // go down
        posExpected = new Position(3, 3);
        posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        assertTrue(game.move(Direction.LEFT)); //go left
        posExpected = new Position(3, 2);
        posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        game.move(Direction.DOWN); // do down and die
        while (game.checkStonesAndDiamonds()) {
            game.checkStonesAndDiamonds();
        }
        assertFalse(game.isPlayerAlive());
        game = new Game(new Level(1)); // restart a game
        assertTrue(game.move(Direction.UP)); // go up
        posExpected = new Position(1, 3);
        posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
    }

    @Test
    void checkCaseAndMove() {
        /*
        top on the level 1 to visualise
        wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
        weeeeeeveedesveeeeeseseeeeeeeveedeseeeew
        wespseeeeeeveeeeeeeeesdeeseeeeveeeeeveew
        weeeeeeeeeeveeveeeeeseseeseeeeeeeeseeeew
        wsevveeeeeeeeeseeeeeeseeseeeeeseeseeeeew
         */
        Game game = new Game(new Level(1));
        // there is a stone at the left
        assertFalse(game.checkCaseAndMove(
                game.getPositionNextDirection(game.getPosPlayer(), Direction.RIGHT), Direction.RIGHT));
        Position posExpected = new Position(2, 3);
        Position posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        // go down
        assertTrue(game.checkCaseAndMove(
                game.getPositionNextDirection(game.getPosPlayer(), Direction.DOWN), Direction.DOWN));
        posExpected = new Position(3, 3);
        posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        //go left
        assertTrue(game.checkCaseAndMove(
                game.getPositionNextDirection(game.getPosPlayer(), Direction.LEFT), Direction.LEFT));
        posExpected = new Position(3, 2);
        posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        // do down and die
        game.checkCaseAndMove(game.getPositionNextDirection(game.getPosPlayer(), Direction.DOWN), Direction.DOWN);
        while (game.checkStonesAndDiamonds()) {
            game.checkStonesAndDiamonds();
        }
        assertFalse(game.isPlayerAlive());
        // restart a game
        game = new Game(new Level(1));
        // go up
        assertTrue(game.checkCaseAndMove(game.getPositionNextDirection(game.getPosPlayer(), Direction.UP), Direction.UP));
        posExpected = new Position(1, 3);
        posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);


    }

    @Test
    void getPositionNextDirection() {
        Game game = new Game(new Level(1));
        Position posBasic = new Position(3, 3);
        Position posToCheck = game.getPositionNextDirection(posBasic, Direction.RIGHT);
        Position posExpected = new Position(3, 4);
        assertEquals(posExpected, posToCheck);
        posToCheck = game.getPositionNextDirection(posBasic, Direction.UP);
        posExpected = new Position(2, 3);
        assertEquals(posExpected, posToCheck);
        posToCheck = game.getPositionNextDirection(posBasic, Direction.DOWN);
        posExpected = new Position(4, 3);
        assertEquals(posExpected, posToCheck);
        posToCheck = game.getPositionNextDirection(posBasic, Direction.LEFT);
        posExpected = new Position(3, 2);
        assertEquals(posExpected, posToCheck);

    }

    @Test
    void isInside() {
        Game game = new Game(new Level(1));
        assertTrue(game.isInside(new Position(2, 5)));
        assertFalse(game.isInside(new Position(-1, 5)));
    }


    @Test
    void checkStonesAndDiamonds() {
        /*
        top on the level 1 to visualise
        wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
        weeeeeeveedesveeeeeseseeeeeeeveedeseeeew
        wespseeeeeeveeeeeeeeesdeeseeeeveeeeeveew
        weeeeeeeeeeveeveeeeeseseeseeeeeeeeseeeew
        wsevveeeeeeeeeseeeeeeseeseeeeeseeseeeeew
        */
        Game game = new Game(new Level(1));
        game.move(Direction.DOWN);
        game.move(Direction.LEFT);
        TypeCase typeBefore = game.getBoard().getCaseByPosition(new Position(3, 2)).getType();
        assertEquals(TypeCase.PLAYER, typeBefore);
        game.move(Direction.RIGHT);
        while (game.checkStonesAndDiamonds()) {
            game.checkStonesAndDiamonds();
        }
        TypeCase typeAfter = game.getBoard().getCaseByPosition(new Position(3, 2)).getType();
        assertEquals(TypeCase.STONE, typeAfter);

    }

    @Test
    void checkRockFall() {
         /*
        top on the level 1 to visualise
        wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww
        weeeeeeveedesveeeeeseseeeeeeeveedeseeeew
        wespseeeeeeveeeeeeeeesdeeseeeeveeeeeveew
        weeeeeeeeeeveeveeeeeseseeseeeeeeeeseeeew
        wsevveeeeeeeeeseeeeeeseeseeeeeseeseeeeew
        */
        Game game = new Game(new Level(1));
        game.move(Direction.DOWN);
        game.move(Direction.LEFT);
        TypeCase typeBefore = game.getBoard().getCaseByPosition(new Position(3, 2)).getType();
        assertEquals(TypeCase.PLAYER, typeBefore);
        assertNull(game.checkRockFall(new Position(2, 2)));
        game.move(Direction.RIGHT);
        assertEquals(new Position(3, 2), game.checkRockFall(new Position(2, 2)));

    }

    @Test
    void isPlayerWin() {
        Game game = new Game(new Level(1));
        assertFalse(game.isPlayerWin());
    }

    @Test
    void getState() {
        Game game = new Game(new Level(1));
        assertEquals(State.NOT_STARTED, game.getState());
        game.move(Direction.RIGHT);
        assertEquals(State.IN_GAME,game.getState());
    }

    @Test
    void setState() {
        Game game = new Game(new Level(1));
        State check = game.getState();
        assertEquals(State.NOT_STARTED, check);
        game.setState(State.GAME_OVER);
        check = game.getState();
        assertEquals(State.GAME_OVER, check);
    }


    @Test
    void isPlayerAlive() {
        Game game = new Game(new Level(1));
        assertTrue(game.isPlayerAlive());
        // there is a stone at the left
        assertFalse(game.checkCaseAndMove(
                game.getPositionNextDirection(game.getPosPlayer(), Direction.RIGHT), Direction.RIGHT));
        Position posExpected = new Position(2, 3);
        Position posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        // go down
        assertTrue(game.checkCaseAndMove(
                game.getPositionNextDirection(game.getPosPlayer(), Direction.DOWN), Direction.DOWN));
        posExpected = new Position(3, 3);
        posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        //go left
        assertTrue(game.checkCaseAndMove(
                game.getPositionNextDirection(game.getPosPlayer(), Direction.LEFT), Direction.LEFT));
        posExpected = new Position(3, 2);
        posPlayer = game.getPosPlayer();
        assertEquals(posExpected, posPlayer);
        // do down and die
        game.checkCaseAndMove(game.getPositionNextDirection(game.getPosPlayer(), Direction.DOWN), Direction.DOWN);
        while (game.checkStonesAndDiamonds()) {
            game.checkStonesAndDiamonds();
        }
        assertFalse(game.isPlayerAlive());

    }

}