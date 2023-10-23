package cashleh.budget;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgressTest {
    Progress progress;

    @Test
    void getProgress_progressBetweenOneAndZero() {
        progress = new Progress(8, 12);
        assertEquals(progress.getProgress(), ((double) 8 /12));
    }

    @Test
    void getProgress_progressBelowZero() {
        progress = new Progress(-8, 12);
        assertEquals(progress.getProgress(), 0);
    }

    @Test
    void getProgress_progressAboveOne() {
        progress = new Progress(80, 12);
        assertEquals(progress.getProgress(), 1);
    }

    @Test
    void displayProgressBar_progressBetweenOneAndZero() {
        progress = new Progress(8, 12);
        assertEquals(progress.displayProgressBar(),
                "[********************----------] 66.67%");
    }

    @Test
    void displayProgressBar_progressBelowZero() {
        progress = new Progress(-8, 12);
        assertEquals(progress.displayProgressBar(),
                "[------------------------------] 0.00%");
    }

    @Test
    void displayProgressBar_progressAboveOne() {
        progress = new Progress(80, 12);
        assertEquals(progress.displayProgressBar(),
                "[******************************] 100.00%");
    }
}
