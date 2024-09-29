package model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    @org.junit.jupiter.api.Test
    void changeCasePosition() {
        String s =
                "wwwwwwwww\n" +
                        "weeeeeeve\n" +
                        "wespseeee\n" +
                        "weeeeeeee\n" +
                        "wsevveeee\n";

        Board board = new Board(s);
        Position posToChange = new Position(0, 3);
        TypeCase typeBefore = board.getCaseByPosition(posToChange).getType();
        board.changeCasePosition(new Position(0, 3), new Case(TypeCase.DIAMOND));
        TypeCase typeAfter = board.getCaseByPosition(posToChange).getType();
        if (typeAfter == typeBefore) {
            fail("The case didn't change");
        } else if (typeAfter != TypeCase.DIAMOND) {
            fail("The case didn't change");
        }
        board.changeCasePosition(new Position(0, 3), new Case(TypeCase.STONE));
        typeAfter = board.getCaseByPosition(posToChange).getType();
        if (typeAfter != TypeCase.STONE) {
            fail("The case didn't change");
        }


    }

    @org.junit.jupiter.api.Test
    void getCaseByPosition() {
        String s =
                "wwwwwwwww\n" +
                        "weeeeeeve\n" +
                        "wespdeeee\n" +
                        "woeeeeeee\n" +
                        "wsevveeee\n";

        Board board = new Board(s);
        Position pos1 = new Position(0, 3); //w first line
        Position pos2 = new Position(1, 8); //e last on second line
        Position pos3 = new Position(2, 4); //d on the middle
        Position pos4 = new Position(3, 1); //o
        Position pos5 = new Position(9, 9); // outside the board
        if(board.getCaseByPosition(pos1).getType()!=TypeCase.WALL){
            fail("The position don't correspond");
        }
        if(board.getCaseByPosition(pos2).getType()!=TypeCase.EARTH){
            fail("The position don't correspond");
        }
        if(board.getCaseByPosition(pos3).getType()!=TypeCase.DIAMOND){
            fail("The position don't correspond");
        }
        if(board.getCaseByPosition(pos4).getType()!=TypeCase.OUTLET){
            fail("The position don't correspond");
        }
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> board.getCaseByPosition(pos5).getType());




    }

    @org.junit.jupiter.api.Test
    void getStonePositions() {
        String s =
                "wwwsswwww\n" +
                        "seeeeeeve\n" +
                        "wespeeeee\n" +
                        "woeeeeeee\n" +
                        "weevveees\n";
        Board board = new Board(s);
        List<Position> listPos =  board.getStonePositions();
        List<Position> listToHave = new ArrayList<>();
        listToHave.add(new Position(0,3));
        assertEquals(listPos.get(0),listToHave.get(0));
        listToHave.add(new Position(0,4));
        assertEquals(listPos.get(1),listToHave.get(1));
        listToHave.add(new Position(1,0));
        assertEquals(listPos.get(2),listToHave.get(2));
        listToHave.add(new Position(2,2));
        assertEquals(listPos.get(3),listToHave.get(3));
        int expectedSize = listPos.size();
        assertEquals(expectedSize,5);


    }

    @org.junit.jupiter.api.Test
    void getDiamondPositions() {
        String s =
                "wwwddwwww\n" +
                        "deeeeeeve\n" +
                        "wedpeeeee\n" +
                        "woeeeeeee\n" +
                        "weevveees\n";
        Board board = new Board(s);
        List<Position> listPos =  board.getDiamondPositions();
        List<Position> listToHave = new ArrayList<>();
        listToHave.add(new Position(0,3));
        assertEquals(listPos.get(0),listToHave.get(0));
        listToHave.add(new Position(0,4));
        assertEquals(listPos.get(1),listToHave.get(1));
        listToHave.add(new Position(1,0));
        assertEquals(listPos.get(2),listToHave.get(2));
        listToHave.add(new Position(2,2));
        assertEquals(listPos.get(3),listToHave.get(3));
        int expectedSize = listPos.size();
        assertEquals(expectedSize,4);

    }

    @org.junit.jupiter.api.Test
    void getLength() {
        String s =
                "wwwddwwww\n" +
                        "deeeeeeve\n";
        Board board = new Board(s);
        int expectedLength = board.getLength();
        assertEquals(expectedLength,9);

    }

    @org.junit.jupiter.api.Test
    void getWidth() {
        String s =
                "wwwddwwww\n" +
                        "deeeeeeve\n";
        Board board = new Board(s);
        int expectedWidth = board.getWidth();
        assertEquals(expectedWidth,2);
    }

    @org.junit.jupiter.api.Test
    void getOutletPosition() {
        String s =
                "wwoddwwww\n" +
                        "deeeeeeve\n";
        Board board = new Board(s);
        Position expectedPosition = board.getOutletPosition();
        assertEquals(expectedPosition,new Position(0,2));
    }

    @org.junit.jupiter.api.Test
    void getPosPlayer() {
        String s =
                "wwoddwwww\n" +
                        "depeeeeve\n";
        Board board = new Board(s);
        Position expectedPosition = board.getPosPlayer();
        assertEquals(expectedPosition,new Position(1,2));
    }

    @org.junit.jupiter.api.Test
    void getTypeCaseByChar() {
        Board board = new Board("a");
        TypeCase typeExpected = TypeCase.VOID;
        TypeCase typeExpected2 = TypeCase.PLAYER;
        TypeCase typeExpected3 = TypeCase.DIAMOND;
        assertEquals(typeExpected,board.getTypeCaseByChar('v'));
        assertEquals(typeExpected2,board.getTypeCaseByChar('p'));
        assertEquals(typeExpected3,board.getTypeCaseByChar('d'));
        assertNull(board.getTypeCaseByChar('x'));
    }


    @org.junit.jupiter.api.Test
    void removeDiamondOfList() {
        String s =
                "wwwddwwww\n" +
                        "deeeeeeve\n" +
                        "wedpeeeee\n" +
                        "woeeeeeee\n" +
                        "weevveees\n";
        Board board = new Board(s);
        List<Position> listPos =  board.getDiamondPositions();
        assertEquals(listPos.size(),4);
        board.removeDiamondOfList(new Position(0,4));
        assertEquals(listPos.size(),3);
    }

    @org.junit.jupiter.api.Test
    void changePositionOfDiamondList() {
        String s =
                "wwwddwwww\n" +
                        "deeeeeeve\n" +
                        "wedpeeeee\n" +
                        "woeeeeeee\n" +
                        "weevveees\n";
        Board board = new Board(s);
        List<Position> listPos =  board.getDiamondPositions();
        Position before = listPos.get(0);
        assertEquals(before, new Position(0,3));
        board.changePositionOfDiamondList(0,new Position(1,8));
        Position after = listPos.get(0);
        assertEquals(after,new Position(1,8));
    }

    @org.junit.jupiter.api.Test
    void changePositionOfStoneList() {
        String s =
                "wswddwwww\n" +
                        "deeeeeeve\n" +
                        "wedpeeeee\n" +
                        "woeeeeeee\n" +
                        "weevveees\n";
        Board board = new Board(s);
        List<Position> listPos =  board.getStonePositions();
        Position before = listPos.get(0);
        assertEquals(before, new Position(0,1));
        board.changePositionOfStoneList(0,new Position(1,8));
        Position after = listPos.get(0);
        assertEquals(after,new Position(1,8));
    }

    @org.junit.jupiter.api.Test
    void getIndexStoneFromPosition() {
        String s =
                "wswddwwww\n" +
                        "seeeeeeve\n" +
                        "wsdpeeeee\n" +
                        "woeeeseee\n" +
                        "weevveees\n";
        Board board = new Board(s);
        List<Position> listPos =  board.getStonePositions();
        assertEquals(0,board.getIndexStoneFromPosition(new Position(0,1)));
        assertEquals(5,board.getStonePositions().size());
        assertEquals(new Position(1,0),listPos.get(1));
        assertEquals(1,board.getIndexStoneFromPosition(new Position(1,0)));
        assertEquals(2,board.getIndexStoneFromPosition(new Position(2,1)));

    }
}