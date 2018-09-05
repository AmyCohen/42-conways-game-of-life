package com.amycohen.lab42conwaygameoflife;

import java.util.Set;

public class SquareDrawingEngine {
    private Set<Square> squares;
    private boolean[][] gameBoard;

    public boolean hasSquare;

    //Not sure on these yet. Still reasoning how to accomplish the laws and how to call them in the program

    /*
    hasTooFewToLive: Any live cell with fewer than two live neighbors dies, as if by under population.
    hasEnoughToLive: Any live cell with two or three live neighbors lives on to the next generation.
    hasTooManyToLive: Any live cell with more than three live neighbors dies, as if by overpopulation.
    hasEnoughToBeBorn: Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     */
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

        for(int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length; col++) {
                if (gameBoard[row][col] == true) {
                    //add location of square to squares Set
                    //draw a square at a location on the canvas
                }
            }
        }
    }

    // Copied from my whiteboard day04:
    // one method that does all bound-checking.
    // returns the actual value at the row col,
    // or returns 0 if the row col is out of bounds.
    private static boolean getCellValue(boolean[][] aa, int row, int col) {
        // check all bounds to make sure it doesn't run off
        // the top, the left, the bottom or the right.
        if (row < 0 || col < 0 || row >= aa.length || col >= aa[row].length) {
            return false;
        }

        // must be a safe access! return the actual value in the array.
        return aa[row][col];
    }

    // Copied from my whiteboard day04:
    // returns the whether a cell has as neighbor with a square.
    // only checks in four of eight directions, as the rest of the
    // directions will be checked as the outer for loops move through
    // all the cells.
    //
    // xxx  c - the center cell
    // xcy  x - no this direction is not checked from the center cell
    // yyy  y - yes this direction is checked from the center cell
    private int checkNeighbor(boolean[][] aa, int row, int col) {
        boolean cell = aa[row][col];
        int count = 0;

        // check right, bottom right, bottom, bottom left

        hasSquare = getCellValue(aa, row, col + 1);
        System.out.println(cell + " x " + getCellValue(aa, row, col + 1) + " equals " + hasSquare);

        if (hasSquare) {
            count++;
        }

        hasSquare = getCellValue(aa, row + 1, col + 1);
        System.out.println(cell + " x " + getCellValue(aa, row + 1, col + 1) + " equals " + hasSquare);

        if (hasSquare) {
            count++;
        }

        hasSquare = getCellValue(aa, row + 1, col);
        System.out.println(cell + " x " + getCellValue(aa, row + 1, col) + " equals " + hasSquare);

        if (hasSquare) {
            count++;
        }

        hasSquare = getCellValue(aa, row + 1, col - 1);
        System.out.println(cell + " x " + getCellValue(aa, row + 1, col - 1) + " equals " + hasSquare);

        if (hasSquare) {
            count++;
        }

        System.out.println();
        return count;
    }

    public void doesHaveNeighbors(boolean[][] aa) {
        for (int row = 0; row < aa.length; row++) {
            for (int col = 0; col < aa[row].length; col++) {
                int neighbors = checkNeighbor(aa, row, col);
                if (neighbors < 2) {
                    hasTooFewToLive = true;
                } else if (neighbors == 2 || neighbors == 3) {
                    hasEnoughToLive = true;
                } else if (neighbors > 3) {
                    hasTooManyToLive = true;
                } else if (neighbors == 3) {
                    hasEnoughToBeBorn = true;
                }
                System.out.println(neighbors);
            }
        }
    }
}
