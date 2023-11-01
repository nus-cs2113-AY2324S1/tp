package seedu.duke.flashcard;

import seedu.duke.flashcard.review.FlashcardReview;
import seedu.duke.flashcard.review.ReviewDifficulty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * storage for flashcards
 * One storage manages one file
 */
public class FlashcardStorage {
    // simply implemented for save & load first

    private static Logger flashlogger; // for logging
    protected String path;

    FlashcardStorageParser parser;

    public FlashcardStorage(String path){
        this.path = path;
        flashlogger = Logger.getLogger("flash");
    }


    public boolean isStorageAvailable(){
        File f = new File(this.path);
        return f.exists();
    }

    /**
     * load list of flashcards
     * from this.path
     * @return list of Flashcards
     * @throws FileNotFoundException
     */
    public FlashcardList loadFlashcards() throws FileNotFoundException{

        flashlogger.log(Level.INFO, "loading flashcard");

        FlashcardList flashcardList = new FlashcardList(new ArrayList<>());
        File f = new File (this.path);
        Scanner s = new Scanner(f);

        while(s.hasNext()){
            String[] flashTokens = s.nextLine().split(" \\| ");
            flashcardList.add(FlashcardStorageParser.loadFlashcard(flashTokens));
        }

        System.out.println(String.format(
                "    There are currently %d flashcards in the savefile",
                flashcardList.getSize()));

        return flashcardList;

    }

    public void saveFlashcards(ArrayList<Flashcard> flashcardList) {

        try {
            FileWriter fw = new FileWriter(path);

            for (int i = 0; i < flashcardList.size(); i++) {

                // get info from a flashcard
                Flashcard flashcard = flashcardList.get(i);

                int id = flashcard.getId();
                String frontText = flashcard.getFrontText();
                String backText = flashcard.getBackText();
                ArrayList<FlashcardReview> reviewList = flashcard.getReviews();

                String reviews = FlashcardStorageParser.reviewtoString(reviewList);

                fw.write(String.format("%d | %s | %s | - | %s | -\r\n",
                        id, frontText, backText, reviews));
            }
            fw.close();
        } catch (IOException e){
            //System.out.println("Failed to save.");
            flashlogger.log(Level.WARNING, "problem: failed to save");
        }
    }





}
