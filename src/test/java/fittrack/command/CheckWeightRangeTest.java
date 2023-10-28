package fittrack.command;

import fittrack.UserProfile;
import fittrack.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CheckWeightRangeTest {

    private final Storage storage = new Storage();
    private UserProfile userProfile;

    @BeforeEach
    public void setUp() throws Storage.StorageOperationException {
        userProfile  = storage.profileLoad();
    }
    @Test
    public void testHeight(){
        double height = userProfile.getHeight().value;
        double weightUserProfile = userProfile.getHeight().calculateIdealWeight();

        double weightCalculated = 50 + (0.91 * (height - 152.4));
        assertEquals(weightUserProfile, weightCalculated);
    }
}
