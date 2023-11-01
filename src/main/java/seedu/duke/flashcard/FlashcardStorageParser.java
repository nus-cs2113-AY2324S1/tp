package seedu.duke.flashcard;

import seedu.duke.flashcard.review.FlashcardReview;
import seedu.duke.flashcard.review.ReviewDifficulty;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * FlashcardStorageParser class
 * Utility class for parsing
 */
public final class FlashcardStorageParser {

    /**
     * load a flash card from certain format
     * Tokens includes attributes of Flashcard
     * @param tokens
     * @return Flashcard object
     */
    public static Flashcard loadFlashcard(String[] tokens){

        assert tokens.length == 6 : "Token length should be 5";

        // flashlogger.log(Level.INFO, "token length is", tokens.length);
        // System.out.println(tokens[0]);

        int id = Integer.parseInt(tokens[0].trim());
        String frontText = tokens[1].trim();
        String backText = tokens[2].trim();
        String[] tags = tokens[3].trim().split("/");
        String[] reviews = tokens[4].trim().split("/");
        String nextReviewOn = tokens[5].trim();


        Flashcard flashcard = new Flashcard(frontText, backText);

        flashcard.setId(id);

        //flashlogger.log(Level.INFO, "added flashcard");

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
                String[] reviewTokens = review.split("#");
                LocalDateTime reviewDate = LocalDateTime.parse(reviewTokens[0].trim());
                ReviewDifficulty reviewDifficulty = ReviewDifficulty.valueOf(reviewTokens[1].trim());

                FlashcardReview flashcardReview = new FlashcardReview(reviewDate, reviewDifficulty);
                flashcard.addReview(flashcardReview);

                flashcard.setLastReviewOn(reviewDate);

            }
        }

        if(!nextReviewOn.equals("-")){
            //LocalDateTime.parse(nextReviewOn);
            System.out.println("reviews are not for v1");
        }

        return flashcard;
    }

    /**
     * reviews to String methods
     * make String from reviews list
     * @param reviewList
     * @return String of review
     */
    public static String reviewtoString(ArrayList<FlashcardReview> reviewList){
        StringBuilder reviews;

        if(reviewList.isEmpty()){
            reviews = new StringBuilder("-");
        } else{
            reviews = new StringBuilder();
            for(FlashcardReview review: reviewList){
                if(reviews.length() > 0){
                    reviews.append("/");
                }
                reviews.append(review.getReviewDate().toString());
                // identifier between date and difficulty is #
                reviews.append(" # ");
                reviews.append(review.getReviewDifficulty());

            }
        }

        return reviews.toString();
    }
}
