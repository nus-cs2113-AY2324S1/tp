package fittrack.command;

import fittrack.UserProfile;
import fittrack.data.Height;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CheckWeightRangeTest {

    private UserProfile userProfile;
    private final Height height = new Height(180);

    @BeforeEach
    public void setUp() {
        userProfile  = new UserProfile();
        userProfile.setHeight(height);
    }
    @Test
    public void testHeight(){
        double weightUserProfile = userProfile.getHeight().calculateIdealWeight();
        double weightCalculated = 50 + (0.91 * (height.value - 152.4));
        assertEquals(weightUserProfile, weightCalculated);
    }
}
