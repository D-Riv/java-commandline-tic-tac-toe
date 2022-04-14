package io.drdev19;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner((System.in));

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tact Toe Command Line Edition!");
        System.out.println("Press 1 to start!");

        char marker = setPlayerMarker();

        char[][] board = {{' ', ' ', ' ',}, {' ', ' ', ' ',}, {' ', ' ', ' ',}};

        printBoard(board);

        runGame(board, marker);
    }

    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + " |" + board[0][1] + " | " + board[0][2]);
        System.out.println("--+--+--");
        System.out.println(board[1][0] + " |" + board[1][1] + " | " + board[1][2]);
        System.out.println("--+--+--");
        System.out.println(board[2][0] + " |" + board[2][1] + " | " + board[2][2]);
    }

    private static char setPlayerMarker() {
        char marker;
        while (true) {
            try {
                int userTurnPlacement = scanner.nextInt();

                if (userTurnPlacement == 1) {
                    marker = 'X';
                    break;
                } else if (userTurnPlacement == 2) {
                    marker = 'O';
                    break;
                } else {
                    System.out.println("Invalid number input! Try again...");

                }
            } catch (Exception e) {
                System.out.println("The value you have choose is not a number. Try again...");
                scanner.next();
            }
        }
        return marker;
    }

    private static void playerTurn(char[][] board, int userInput, char marker) {
        switch(userInput) {
            case 1:
                board[0][0] = marker;
                break;
            case 2:
                board[0][1] = marker;
                break;
            case 3:
                board[0][2] = marker;
                break;
            case 4:
                board[1][0] = marker;
                break;
            case 5:
                board[1][1] = marker;
                break;
            case 6:
                board[1][2] = marker;
                break;
            case 7:
                board[2][0] = marker;
                break;
            case 8:
                board[2][1] = marker;
                break;
            case 9:
                board[2][2] = marker;
                break;
        }
    }

    private static boolean isSlotAvailable(char[][] board, int slotPosition) {
        switch(slotPosition) {
            case 1:
                return (board[0][0] == ' ');
            case 2:
                return (board[0][1] == ' ');
            case 3:
                return (board[0][2] == ' ');
            case 4:
                return (board[1][0] == ' ');
            case 5:
                return (board[1][1] == ' ');
            case 6:
                return (board[1][2] == ' ');
            case 7:
                return (board[2][0] == ' ');
            case 8:
                return (board[2][1] == ' ');
            case 9:
                return (board[2][2] == ' ');
            default:
                return false;
        }

    }

    private static boolean isGameFinished(char[][] board, char marker) {
        if ((board[0][0] == marker && board[0][1] == marker && board[0][2] == marker) ||
            (board[1][0] == marker && board[1][1] == marker && board[1][2] == marker) ||
            (board[2][0] == marker && board[2][1] == marker && board[2][2] == marker) ||

            (board[0][0] == marker && board[1][0] == marker && board[2][0] == marker) ||
            (board[0][1] == marker && board[1][1] == marker && board[2][2] == marker) ||
            (board[0][2] == marker && board[1][2] == marker && board[2][2] == marker) ||

            (board[0][0] == marker && board[1][1] == marker && board[2][2] == marker) ||
            (board[0][2] == marker && board[1][1] == marker && board[2][0] == marker)) {
            System.out.println("Player: " + marker + " wins!");
            return true;
        }

        for (char[] row: board) {
            for (char slot : row) {
                if (slot == ' ') {
                    return false;
                }
            }
        }
        printBoard(board);
        System.out.println("The game ended in a tie");
        return true;
    }

    private static void runGame(char[][] board, char marker) {
        while (!isGameFinished(board, marker) && (!isGameFinished(board, 'O'))) {
            System.out.println("Where would you like to set your board marker? (1-9)");
            int userInput = scanner.nextInt();
            if (isSlotAvailable(board, userInput)) {
                playerTurn(board, userInput, marker);
                printBoard(board);


                while (true) {
                    Random randomInt = new Random();
                    int randomSlot = randomInt.nextInt(9) + 1;
                    System.out.println("Opponent selected: " + randomSlot);

                    if (isSlotAvailable(board, randomSlot)) {
                        playerTurn(board, randomSlot, 'O');
                        break;
                    }
                }
                printBoard(board);
            } else {
                System.out.println("Chosen slot is taken, try again...");
            }
        }
    }

    private static int findIndex( char[] slotArray, char boardSlot) {
        for (int i = 0; i < slotArray.length; i++) {
            if (slotArray[i] == boardSlot) {
                return i;
            }
        }
        return -1;
    }



}
