package com.example;

import java.util.Random;
import java.util.Scanner;

public class Tictactoe {

    static char[][] board = new char[3][3];
    static char currentPlayer;
    static char computerSymbol = 'O';
    static char humanSymbol;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        tossToDecideFirstPlayer();
        humanSymbol = (computerSymbol == 'X') ? 'O' : 'X';
        
        boolean gameRunning = true;

        while (gameRunning) {
            printBoard();
            
            if (currentPlayer == computerSymbol) {
                computerMove();
            } else {
                int selectedSlot = getUserInput();
                int[] indices = convertSlotToIndices(selectedSlot);
                if (isValidMove(indices[0], indices[1])) {
                    placeMove(indices[0], indices[1], currentPlayer);
                } else {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            }

            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameRunning = false;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("No moves left. It is a draw!");
                gameRunning = false;
            } else {
                switchPlayer();
            }
        }
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
        System.out.println("Toss won! Player " + currentPlayer + " starts.");
    }

    static int getUserInput() {
        System.out.print("Player " + currentPlayer + ", enter a slot (1-9): ");
        return scanner.nextInt();
    }

    static int[] convertSlotToIndices(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }

    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        return board[row][col] == '-';
    }

    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    static void computerMove() {
        Random random = new Random();
        while (true) {
            int slot = random.nextInt(9) + 1;
            int[] indices = convertSlotToIndices(slot);
            if (isValidMove(indices[0], indices[1])) {
                placeMove(indices[0], indices[1], computerSymbol);
                System.out.println("Computer placed " + computerSymbol + " in slot: " + slot);
                break;
            }
        }
    }

    static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    static boolean isBoardFull() {
        boolean full = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    full = false;
                }
            }
        }
        return full;
    }

    static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        
        return false;
    }
}