package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialTagTest {

    private static final String VALID_TUTORIAL_CODE_1 = "T1";
    private static final String VALID_TUTORIAL_CODE_2 = "TUT10";
    private static final String INVALID_TUTORIAL_CODE_EMPTY = "";
    private static final String INVALID_TUTORIAL_CODE_TOO_LONG = "TUT1234"; // >5 chars
    private static final String INVALID_TUTORIAL_CODE_WRONG_FORMAT = "t1"; // lowercase
    private static final String INVALID_TUTORIAL_CODE_NO_NUMBER = "T"; // no number
    private static final String INVALID_TUTORIAL_CODE_INVALID_PREFIX = "U1"; // wrong prefix
    private static final String INVALID_TUTORIAL_CODE_WITH_LETTER = "T1A"; // extra letter

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialTag(null));
    }

    @Test
    public void constructor_invalidTutorialCode_throwsIllegalArgumentException() {
        String invalidTutorialCode = "";
        assertThrows(IllegalArgumentException.class, () -> new TutorialTag(invalidTutorialCode));
    }

    @Test
    public void constructor_validTutorialCode_success() {
        TutorialTag tutorialTag = new TutorialTag(VALID_TUTORIAL_CODE_1);
        assertEquals(VALID_TUTORIAL_CODE_1, tutorialTag.tagName);
    }

    @Test
    public void isValidTutorialCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> TutorialTag.isValidTutorialCode(null));
    }

    @Test
    public void isValidCourseCode_validTutorialCodes_returnsTrue() {
        assertTrue(TutorialTag.isValidTutorialCode(VALID_TUTORIAL_CODE_1));
        assertTrue(TutorialTag.isValidTutorialCode(VALID_TUTORIAL_CODE_2));
        assertTrue(TutorialTag.isValidTutorialCode("T2"));
        assertTrue(TutorialTag.isValidTutorialCode("TUT5"));
        assertTrue(TutorialTag.isValidTutorialCode("T123"));
    }

    @Test
    public void isValidTutorialCode_invalidTutorialCodes_returnsFalse() {
        assertFalse(TutorialTag.isValidTutorialCode(INVALID_TUTORIAL_CODE_EMPTY));
        assertFalse(TutorialTag.isValidTutorialCode(INVALID_TUTORIAL_CODE_TOO_LONG));
        assertFalse(TutorialTag.isValidTutorialCode(INVALID_TUTORIAL_CODE_WRONG_FORMAT));
        assertFalse(TutorialTag.isValidTutorialCode(INVALID_TUTORIAL_CODE_NO_NUMBER));
        assertFalse(TutorialTag.isValidTutorialCode(INVALID_TUTORIAL_CODE_INVALID_PREFIX));
        assertFalse(TutorialTag.isValidTutorialCode(INVALID_TUTORIAL_CODE_WITH_LETTER));
        assertFalse(TutorialTag.isValidTutorialCode("T 1")); // Space
        assertFalse(TutorialTag.isValidTutorialCode("T1@")); // Special char
        assertFalse(TutorialTag.isValidTutorialCode("tut1")); // Lowercase
    }

    @Test
    public void getTagType_returnsTutorial() {
        TutorialTag tutorialTag = new TutorialTag(VALID_TUTORIAL_CODE_1);
        assertEquals(TagType.TUTORIAL, tutorialTag.getTagType());
    }

    @Test
    public void equals_sameTutorialCode_returnsTrue() {
        TutorialTag tutorialTag1 = new TutorialTag(VALID_TUTORIAL_CODE_1);
        TutorialTag tutorialTag2 = new TutorialTag(VALID_TUTORIAL_CODE_1);
        assertTrue(tutorialTag1.equals(tutorialTag2));
    }

    @Test
    public void equals_differentTutorialCode_returnsFalse() {
        TutorialTag tutorialTag1 = new TutorialTag(VALID_TUTORIAL_CODE_1);
        TutorialTag tutorialTag2 = new TutorialTag(VALID_TUTORIAL_CODE_2);
        assertFalse(tutorialTag1.equals(tutorialTag2));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        TutorialTag tutorialTag = new TutorialTag(VALID_TUTORIAL_CODE_1);
        Tag tag = new Tag(VALID_TUTORIAL_CODE_1);
        assertFalse(tutorialTag.equals(tag));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        TutorialTag tutorialTag = new TutorialTag(VALID_TUTORIAL_CODE_1);
        assertTrue(tutorialTag.equals(tutorialTag));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        TutorialTag tutorialTag = new TutorialTag(VALID_TUTORIAL_CODE_1);
        assertFalse(tutorialTag.equals(null));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        TutorialTag tutorialTag = new TutorialTag(VALID_TUTORIAL_CODE_1);
        assertFalse(tutorialTag.equals(VALID_TUTORIAL_CODE_1));
    }

    @Test
    public void hashCode_sameTutorialCode_sameHashCode() {
        TutorialTag tutorialTag1 = new TutorialTag(VALID_TUTORIAL_CODE_1);
        TutorialTag tutorialTag2 = new TutorialTag(VALID_TUTORIAL_CODE_1);
        assertEquals(tutorialTag1.hashCode(), tutorialTag2.hashCode());
    }

    @Test
    public void hashCode_differentTutorialCode_differentHashCode() {
        TutorialTag tutorialTag1 = new TutorialTag(VALID_TUTORIAL_CODE_1);
        TutorialTag tutorialTag2 = new TutorialTag(VALID_TUTORIAL_CODE_2);
        assertNotEquals(tutorialTag1.hashCode(), tutorialTag2.hashCode());
    }

    @Test
    public void toString_returnsFormattedString() {
        TutorialTag tutorialTag = new TutorialTag(VALID_TUTORIAL_CODE_1);
        assertEquals("[" + VALID_TUTORIAL_CODE_1 + "]", tutorialTag.toString());
    }
}
