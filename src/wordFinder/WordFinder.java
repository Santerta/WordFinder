package wordFinder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Santeri Tammisto
 * @version 17.6.2023
 *
 */
public class WordFinder {
    
    private ArrayList<List<String>> validWords = new ArrayList<>();
    
    private String wordsFilePath = "filtered_words.txt";
    
    private static Scanner userInput = new Scanner(System.in);
    
    /**
     * @param howManyLetters asd
     */
    public WordFinder(int howManyLetters) {
        this.validWords = new ArrayList<>();
        for (int i = 0; i < howManyLetters ; i++ ) {
            this.validWords.add(new ArrayList<>());
        }
    }
    
    /**
     * @param args not in use
     */
    public static void main(String[] args) {
        /*
        WordFinder finder = new WordFinder(9);
        
        String testLetters = "sginadtse";
        finder.findAllTheWords(testLetters);
        finder.printFoundWords(3, 9, 10);
        
        
        for (int i = 2 ; i < finder.validWords.size() ; i++) {
            int howManyWords = finder.validWords.get(i).size();
            System.out.println("Number of " + (i+1) + " letter words: " + howManyWords);
        }
        */
        
        startProgram();
        
    }
    
    private static void startProgram() {
        WordFinder finder = new WordFinder(9);
        finder.startMenu();
        
    }
    
    
    private void startMenu() {
        System.out.println("WORDFINDER");
        System.out.println("==================");
        System.out.println("Which round do you need help with?\n"
                + "1. Letters round\n"
                + "2. Numbers round\n"
                + "3. Conundrum\n"
                + "4. Exit program"
                );
        
        int pressedButton = userInput.nextInt();
        
        if (pressedButton == 1) {
            startLettersRound();
        } else if (pressedButton == 2) {
            startNumbersRound();
        } else if (pressedButton == 3) {
            startConundrumRound();
        } else if (pressedButton == 4) {
            System.out.println("Exiting program\n");
        } else {
            System.out.println("Please provide a number between 1-4.\n");
            startMenu();
        }
    }
    
    
    private void startConundrumRound() {
        System.out.println("Conundrum round helper still under construction.");
        System.out.println("1. Back to start menu\n"
                + "2. Exit program");
        int pressedButton = userInput.nextInt();
        if (pressedButton == 1) {
            startMenu();
        } else if (pressedButton == 2) {
            System.out.println("Bye bye");
        } else {
            System.out.println("Please provide a number between 1-2.");
            startConundrumRound();
        }
        

    }
    
    
    private void startNumbersRound() {
        System.out.println("Numbers round helper still under construction.");
        System.out.println("1. Back to start menu\n"
                + "2. Exit program");
        int pressedButton = userInput.nextInt();
        if (pressedButton == 1) {
            startMenu();
        } else if (pressedButton == 2) {
            System.out.println("Bye bye");
        } else {
            System.out.println("Please provide a number between 1-2.");
            startNumbersRound();
        }
    }
    
    
    private void printFoundWords(int minimumLetters, int maximumLetters, int maxWords) {
        
        for (int i = maximumLetters ; i > minimumLetters ; i--) {
            int currentMax = maxWords;
            if ( this.validWords.get(i-1).size() < maxWords) currentMax = this.validWords.get(i-1).size();
            
            System.out.println("-----------------------");
            System.out.println((i) + " letter words:");
            for (int j = 0  ; j < currentMax ; j++) {
                System.out.println(this.validWords.get(i-1).get(j));
            }
        }
        System.out.println("-----------------------");
        
    }
    
    
    private void startLettersRound() {
        int minimum = 3;
        int maximum = 9;
        int howManyWords = 10;
        String examinedLetters;
        
        System.out.println("LETTERS ROUND HELPER\n-------------------\n");
        System.out.println("Please enter the nine letters:");
        
        examinedLetters = userInput.nextLine();
        
        if (examinedLetters.length() != 9) {
            System.out.println("Please give exactly 9 letters.");
            startLettersRound();
        }
        
        findAllTheWords(examinedLetters);
        printFoundWords(minimum, maximum, howManyWords);
        
        System.out.println("1. Start again\n" + "2. Exit program");
        int pressedButton = userInput.nextInt();
        
        if (pressedButton == 1) {
            startMenu();
        }
    }
    
    
    private void findAllTheWords(String givenLetters) {

        String letters = givenLetters.toLowerCase();
        
        try (Scanner fi = new Scanner(new FileInputStream(this.wordsFilePath))) {
            
            while ( fi.hasNext() ) {
                
                String word = fi.nextLine();
                int wordLength = word.length();
                StringBuilder lettersSB = new StringBuilder(letters);
                boolean isValid = true;
                
                for ( int i = 0 ; i < wordLength ; i++ ) {
                    int indexSB = lettersSB.indexOf(String.valueOf(word.charAt(i)));
                    if ( indexSB > -1 ) {
                        lettersSB.deleteCharAt(indexSB);
                    } else {
                        isValid = false;
                        break;
                    }
                }
                
                if ( isValid ) {
                    this.validWords.get(wordLength-1).add(word);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
    @SuppressWarnings("unused")
    private void filterWordsToAFile() {
        String filePath = "./words_alpha.txt";
        String outputFile = "filtered_words.txt";
        
        try ( Scanner fi = new Scanner(new FileInputStream(filePath));
             FileWriter writer = new FileWriter(outputFile) ) {
            
                while ( fi.hasNext() ) {
                    String currentWord = fi.nextLine();
                    
                    if ( currentWord.length() < 3 || currentWord.length() > 9 ) {
                        continue;
                    }
                    writer.write(currentWord + "\n");
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
}
