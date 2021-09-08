package com.company;
import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    final char SIGN_X = 'x';
    final char SIGN_O = 'o';
    final char SIGN_EMPTY = '.';
    char[][] table;
    Random random;
    Scanner scanner;

    public static void main(String[] args) {
        new TicTacToe().game();
    }

    TicTacToe() {
        // конструктор: инициализация полей
        random = new Random();
        scanner = new Scanner(System.in);
        table = new char[3][3];
    }


    // дополнительные методы

    //заполнение таблицы пустыми клетками
    void initTable() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                table[row][col] = SIGN_EMPTY;
    }

    //отображените текущего состояния таблицы
    void printTable() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
                System.out.print(table[row][col] + " ");
            System.out.println();
        }
    }

    void game() {
        // игровая логика
        String a;
        initTable();
        while (true) {
            turnHuman();
            if (checkWin(SIGN_X)) {
                a = "YOU WIN!";
                printTable();
                break;
            }
            if (isTableFull()) {
                a = "Sorry, DRAW!";
                printTable();
                break;
            }
            turnAI();
            printTable();
            if (checkWin(SIGN_O)) {
                a = "AI WIN!";
                break;
            }
            if (isTableFull()) {
                a = "Sorry, DRAW!";
                break;
            }

        }
        System.out.println(a);
        System.out.println("GAME OVER.");

    }

    //ход человека
    void turnHuman() {
        int x, y;
        do {
            System.out.println("Enter X and Y (1..3):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_X;
    }

    //проверка валидности хода
    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3)
            return false;
        return table[y][x] == SIGN_EMPTY;
    }

    //ход AI
    void turnAI() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_O;
    }

    //проверка исхода игры
    boolean checkWin(char dot) {
        for (int i = 0; i < 3; i++)
            if ((table[i][0] == dot && table[i][1] == dot &&
                    table[i][2] == dot) ||
                    (table[0][i] == dot && table[1][i] == dot &&
                            table[2][i] == dot))
                return true;
        if ((table[0][0] == dot && table[1][1] == dot &&
                table[2][2] == dot) ||
                (table[2][0] == dot && table[1][1] == dot &&
                        table[0][2] == dot))
            return true;
        return false;
    }

    //
    boolean isTableFull() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (table[row][col] == SIGN_EMPTY)
                    return false;
        return true;
    }
}