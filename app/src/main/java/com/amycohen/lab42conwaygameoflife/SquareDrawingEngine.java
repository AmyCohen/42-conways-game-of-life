package com.amycohen.lab42conwaygameoflife;

import java.util.Set;

public class SquareDrawingEngine {
    private Set<Square> squares;
    private boolean[][] gameBoard;

    private boolean hasSquare;

    //Not sure on these yet. Still reasoning how to accomplish the laws and how to call them in the program
    private boolean hasTooFewToLive;
    private boolean hasEnoughToLive;
    private boolean hasTooManyToLive;
    private boolean hasEnoughToBeBorn;

    public void drawGameBoard(){

        gameBoard = new boolean[][]{
            {false, false, true, false, false, false, false, false, false, false},
            {false, true, false, false, false, false, false, true, true, false},
            {false, true, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false},
            {false, false, false, false, false, false, true, false, false, false},
            {false, true, true, false, false, false, true, false, true, true},
            {false, true, true, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, true},
        };
    }
}
