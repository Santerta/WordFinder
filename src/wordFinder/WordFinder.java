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
    
    // program is given a list of 9 different letters
    // program goes through an english dictionary and checks for every word that if it can be written with those letters
    //              if the word can be written, program adds it to a collection
    // lastly the program prints all the words that can be written from 9 letters to 3 letters
    int rowCount;
    ArrayList<List<String>> validWords = new ArrayList<>();
    
    String wordsFilePath = "filtered_words.txt";
    
    
    /**
     * @param howManyLetters asd
     */
    public WordFinder(int howManyLetters) {
        this.rowCount = howManyLetters;
        this.validWords = new ArrayList<>();
        for (int i = 0; i < howManyLetters ; i++ ) {
            this.validWords.add(new ArrayList<>());
        }
    }
    
    /**
     * @param args not in use
     */
    public static void main(String[] args) {
        WordFinder helper = new WordFinder(9);
        
        String testLetters = "sginadtse";
        helper.findAllTheWords(testLetters);
        
        for (int i = 0 ; i < helper.validWords.size() ; i++) {
            int howManyWords = helper.validWords.get(i).size();
            System.out.println("Number of " + (i+1) + " letter words: " + howManyWords);
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
        String filePath = "./words_alpha.txt"; // Replace with the actual file path
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
