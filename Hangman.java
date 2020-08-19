package tictactoe_v2;


import java.util.Scanner;


public class Hangman {
    static char[] THEWORD;
    static String guessedWords = "";
    static int HEALTH = 5;
    static int HIT = 0;
    
    public static void main(String[] args) {
        System.out.print("Első játékos, adjon meg egy szót: ");
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        hideTheWord(word);
        drawTheHUD(HEALTH, THEWORD);
        char tip;
        
        while(true){
            System.out.print("Tipp: ");
            tip = sc.next().charAt(0);
            while(guessedWords.contains(String.valueOf(tip)) || check(tip)){
                System.out.println("Ezt a betűt már próbáltad!");
                System.out.print("Új tipp: ");
                tip = sc.next().charAt(0);
            }
            if(!guess(tip, word)) HEALTH--;
            drawTheHUD(HEALTH, THEWORD);
            if(HEALTH == 0) {
                System.out.println("Ops! Vesztettél.");
                break;
            }
            if(HIT == word.length()){
                System.out.println("Gratulálok! Nyertél.");
                break;
            }
        }
        
    }
    
    public static boolean check(char tip){
        for(char x : THEWORD) {
            if(x == tip) return true;
        }
        return false;       
    }
    
    
    public static void hideTheWord(String word){
        THEWORD = new char[word.length()];
        for(int i = 0; i < word.length(); i++){
            THEWORD[i] = '_';
        }   
    }
    
    public static boolean guess(char tip, String word){
        boolean hit = false;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == tip){
                THEWORD[i] = tip;
                HIT++;
                hit = true;
            }
        }
        if(!hit) {
            if(guessedWords.length() > 0) guessedWords += ", ";
            guessedWords += tip;
        }
        return hit;
    }
    

    
    public static void drawTheHUD(int health, char[] theWord){
        System.out.println("Élet: " + health + "    Hibázott szavak: " + guessedWords);
        for(char x: theWord){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
}
