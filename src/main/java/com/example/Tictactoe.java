package com.example;
public class Tictactoe {

    static char[][] board = new char[3][3];
    static char currentPlayer;

    public static void main(String[] args) {
        initializeBoard();
        tossToDecideFirstPlayer();
        printBoard();
    }

    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    static void printBoard() {
        System.out.println("---------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("---------------");
        }
    }

    static void tossToDecideFirstPlayer() {
        if (Math.random() < 0.5) {
            currentPlayer = 'X';
        } else {
            currentPlayer = 'O';
        }
        System.out.println("Player " + currentPlayer + " will start the game.");
    }
}