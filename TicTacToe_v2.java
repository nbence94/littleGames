package tictactoe_v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class TicTacToe_v2 {
    static ArrayList<Integer> playerPos = new ArrayList<>();
    static ArrayList<Integer> cpuPos = new ArrayList<> ();
    

    public static void main(String[] args) {
        char[][] gameBoard = { {' ', '|', ' ', '|', ' '}, 
                               {'-', '+', '-', '+', '-'},
                               {' ', '|', ' ', '|', ' '},
                               {'-', '+', '-', '+', '-'},
                               {' ', '|', ' ', '|', ' '}};  
        drawTheBoard(gameBoard);
        Random r = new Random(); 
        Scanner sc = new Scanner(System.in);
        String result = "";
        while(true){
            System.out.print("Hova tennéd az X-ed?: ");  
            int pos = sc.nextInt();
            while(playerPos.contains(pos) || cpuPos.contains(pos)){
                System.out.println("Ez a hely foglalt! Válassz másikat!");  
                System.out.print("Add meg a pozíciót: ");
                pos = sc.nextInt();
            }
            placePiece(gameBoard, pos, "player"); 
            result = winnerCheck();
            if(result.length() > 0) {
                drawTheBoard(gameBoard);
                System.out.println(result);
                break;
            }
            pos = r.nextInt(9) + 1;
            while(playerPos.contains(pos) || cpuPos.contains(pos)){
                pos = r.nextInt(9) + 1;
            }
            placePiece(gameBoard, pos, "cpu");
            drawTheBoard(gameBoard);
            result = winnerCheck();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }      
        }
    }
    public static void placePiece(char[][] gameBoard, int pos, String user){
        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPos.add(pos);
        } else if(user.equals("cpu")) {
            symbol = 'O';      
            cpuPos.add(pos);
        }
        switch(pos){
            case 1: gameBoard[0][0] = symbol; break;
            case 2: gameBoard[0][2] = symbol; break;
            case 3: gameBoard[0][4] = symbol; break;
            case 4: gameBoard[2][0] = symbol; break;
            case 5: gameBoard[2][2] = symbol; break;
            case 6: gameBoard[2][4] = symbol; break;
            case 7: gameBoard[4][0] = symbol; break;
            case 8: gameBoard[4][2] = symbol; break;
            case 9: gameBoard[4][4] = symbol; break;
            default: System.out.println("A választható slotok: 1 - 9");
        }        
    }
    
    public static String winnerCheck() {
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rigCol = Arrays.asList(3,6,9);
        
        List leftCross = Arrays.asList(1,5,9);
        List rightCross = Arrays.asList(7,5,3);
        
        List<List> winning = new ArrayList<> ();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rigCol);
        winning.add(leftCross);
        winning.add(rightCross);
        for(List l: winning) {
            if(playerPos.containsAll(l)) {
                return "Nyertél!";
            }
            else if(cpuPos.containsAll(l)) {
                return "A gép nyert!";
            }
            else if(playerPos.size() + cpuPos.size() == 9) {
                return "Döntetlen!";
            } 
        }
        return "";
    }
    
    public static void drawTheBoard(char[][] gameBoard){
        for(char[] row : gameBoard){
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println("");
        }
    }
}
    

