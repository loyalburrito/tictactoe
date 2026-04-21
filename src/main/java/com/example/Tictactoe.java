package com.example;
import java.util.Scanner;

public class Tictactoe {

    static char[][] board = new char[3][3];
    static char currentPlayer;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        tossToDecideFirstPlayer();
        printBoard();
        
        int selectedSlot = getUserInput();
        int[] indices = convertSlotToIndices(selectedSlot);
        System.out.println("Mapped to -> Row: " + indices[0] + ", Column: " + indices[1]);
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
        System.out.println("Toss won! Player " + currentPlayer + " will start the game.");
    }

    static int getUserInput() {
        System.out.print("Player " + currentPlayer + ", enter a slot number (1-9): ");
        return scanner.nextInt();
    }

    static int[] convertSlotToIndices(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }
}