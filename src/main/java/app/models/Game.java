package app.models;

import java.util.UUID;

public class Game {

    public UUID uuid;
    public int countXWins;
    public int countOWins;

    public char[] grid;

    public Game(String gameUuid){
        uuid = UUID.fromString(gameUuid);
        grid = new char[9];
        countOWins = 0;
        countXWins = 0;
    }

    public String SetSymbol(String cellNumber, String symbol){
        grid[Integer.parseInt(cellNumber) - 1] = symbol.charAt(0);
        return CheckWin();
    }

    public String CheckWin(){
        if ((grid[0] == 'X' && grid[1] == 'X' && grid[2] == 'X') ||
        (grid[3] == 'X' && grid[4] == 'X' && grid[5] == 'X') ||
        (grid[6] == 'X' && grid[7] == 'X' && grid[8] == 'X') ||
        (grid[0] == 'X' && grid[3] == 'X' && grid[6] == 'X') ||
        (grid[1] == 'X' && grid[4] == 'X' && grid[7] == 'X') ||
        (grid[2] == 'X' && grid[5] == 'X' && grid[8] == 'X') ||
        (grid[0] == 'X' && grid[4] == 'X' && grid[8] == 'X') ||
        (grid[2] == 'X' && grid[4] == 'X' && grid[6] == 'X')){
            grid = new char[9];
            countXWins += 1;
            return "X";
        } else if ((grid[0] == 'O' && grid[1] == 'O' && grid[2] == 'O') ||
        (grid[3] == 'O' && grid[4] == 'O' && grid[5] == 'O') ||
        (grid[6] == 'O' && grid[7] == 'O' && grid[8] == 'O') ||
        (grid[0] == 'O' && grid[3] == 'O' && grid[6] == 'O') ||
        (grid[1] == 'O' && grid[4] == 'O' && grid[7] == 'O') ||
        (grid[2] == 'O' && grid[5] == 'O' && grid[8] == 'O') ||
        (grid[0] == 'O' && grid[4] == 'O' && grid[8] == 'O') ||
        (grid[2] == 'O' && grid[4] == 'O' && grid[6] == 'O')){
            grid = new char[9];
            countOWins += 1;
            return "O";
        } else if(grid[0] != '\u0000' && grid[1] != '\u0000' && grid[2] != '\u0000'
                && grid[3] != '\u0000' && grid[4] != '\u0000' && grid[5] != '\u0000'
                && grid[6] != '\u0000' && grid[7] != '\u0000' && grid[8] != '\u0000') {
            grid = new char[9];
            return "T";
        } else {
            return "";
        }
    }

    public void ResetGame(){
        countXWins = 0;
        countOWins = 0;
        grid = new char[9];
    }

    public String GetScore(){
        return "X: " + countXWins + ", O: " + countOWins;
    }
}