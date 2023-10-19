package seedu.duke.flashcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * storage for flashcards
 * One storage manages one file
 */
public class FlashcardStorage {
    // simply implemented for save & load first

    protected String path;

    public FlashcardStorage(String path){
        this.path = path;
    }


    public boolean isStorageAvailable(){
        File f = new File(this.path);
        return f.exists();
    }
    
    /**
     * load a flash card from certain format
     * Tokens includes attributes of Flashcard
     * @param tokens
     * @return Flashcard object
     */
    private Flashcard loadFlashcard(String[] tokens){

        assert tokens.length == 5: "Token length should be 5";

        String frontText = tokens[0].trim();
        String backText = tokens[1].trim();
        String[] tags = tokens[2].trim().split("/");
        String[] reviews = tokens[3].trim().split("/");
        String nextReviewOn = tokens[4].trim();


        Flashcard flashcard = new Flashcard(frontText, backText);

        for(String tag:tags){
            if (tag.trim().equals("-")) {
                break;
            } else{
                System.out.println("tags are not for v1");
            }
        }

        for(String review: reviews){
            if (review.trim().equals("-")) {
                break;
            } else{
                System.out.println("reviews are not for v1");
            }
        }

        if(!nextReviewOn.equals("-")){
            //LocalDateTime.parse(nextReviewOn);
            System.out.println("reviews are not for v1");
        }

        return flashcard;
    }

    /**
     * load list of flashcards
     * from this.path
     * @return list of Flashcards
     * @throws FileNotFoundException
     */
    public FlashcardList loadFlashcards() throws FileNotFoundException{
        FlashcardList flashcardList = new FlashcardList(new ArrayList<>());
        File f = new File (this.path);
        Scanner s = new Scanner(f);

        while(s.hasNext()){
            String[] flashTokens = s.nextLine().split(" \\| ");
            flashcardList.add(loadFlashcard(flashTokens));
        }

        System.out.println(String.format("    There are currently %d flashcards in the savefile", flashcardList.getSize()));

        return flashcardList;

    }

    public void saveFlashcards(ArrayList<Flashcard> flashcardList) {

        try {
            FileWriter fw = new FileWriter(path);

            for (int i = 0; i < flashcardList.size(); i++) {
                Flashcard flashcard = flashcardList.get(i);
                fw.write(String.format("%s | %s | - | - | -\r\n",
                        flashcard.getFrontText(), flashcard.getBackText()));
            }
            fw.close();
        } catch (IOException e){
            System.out.println("Failed to save.");
        }
    }





}
