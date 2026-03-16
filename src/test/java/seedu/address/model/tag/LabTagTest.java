package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LabTagTest {

    private static final String VALID_LAB_CODE_1 = "L1";
    private static final String VALID_LAB_CODE_2 = "LAB10";
    private static final String INVALID_LAB_CODE_EMPTY = "";
    private static final String INVALID_LAB_CODE_TOO_LONG = "LAB1234"; // >5 chars
    private static final String INVALID_LAB_CODE_WRONG_FORMAT = "l1"; // lowercase
    private static final String INVALID_LAB_CODE_NO_NUMBER = "L"; // no number
    private static final String INVALID_LAB_CODE_INVALID_PREFIX = "A1"; // wrong prefix
    private static final String INVALID_LAB_CODE_WITH_LETTER = "L1A"; // extra letter

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LabTag(null));
    }

    @Test
    public void constructor_invalidLabCode_throwsIllegalArgumentException() {
        String invalidLabCode = "";
        assertThrows(IllegalArgumentException.class, () -> new LabTag(invalidLabCode));
    }

    @Test
    public void constructor_validLabCode_success() {
        LabTag labTag = new LabTag(VALID_LAB_CODE_1);
        assertEquals(VALID_LAB_CODE_1, labTag.tagName);
    }

    @Test
    public void isValidLabCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> LabTag.isValidLabCode(null));
    }

    @Test
    public void isValidCourseCode_validLabCodes_returnsTrue() {
        assertTrue(LabTag.isValidLabCode(VALID_LAB_CODE_1));
        assertTrue(LabTag.isValidLabCode(VALID_LAB_CODE_2));
        assertTrue(LabTag.isValidLabCode("L2"));
        assertTrue(LabTag.isValidLabCode("LAB5"));
        assertTrue(LabTag.isValidLabCode("L123"));
    }

    @Test
    public void isValidLabCode_invalidLabCodes_returnsFalse() {
        assertFalse(LabTag.isValidLabCode(INVALID_LAB_CODE_EMPTY));
        assertFalse(LabTag.isValidLabCode(INVALID_LAB_CODE_TOO_LONG));
        assertFalse(LabTag.isValidLabCode(INVALID_LAB_CODE_WRONG_FORMAT));
        assertFalse(LabTag.isValidLabCode(INVALID_LAB_CODE_NO_NUMBER));
        assertFalse(LabTag.isValidLabCode(INVALID_LAB_CODE_INVALID_PREFIX));
        assertFalse(LabTag.isValidLabCode(INVALID_LAB_CODE_WITH_LETTER));
        assertFalse(LabTag.isValidLabCode("L 1")); // Space
        assertFalse(LabTag.isValidLabCode("L1@")); // Special char
        assertFalse(LabTag.isValidLabCode("lab1")); // Lowercase
    }

    @Test
    public void getTagType_returnsLab() {
        LabTag labTag = new LabTag(VALID_LAB_CODE_1);
        assertEquals(TagType.LAB, labTag.getTagType());
    }

    @Test
    public void equals_sameLabCode_returnsTrue() {
        LabTag labTag1 = new LabTag(VALID_LAB_CODE_1);
        LabTag labTag2 = new LabTag(VALID_LAB_CODE_1);
        assertTrue(labTag1.equals(labTag2));
    }

    @Test
    public void equals_differentLabCode_returnsFalse() {
        LabTag labTag1 = new LabTag(VALID_LAB_CODE_1);
        LabTag labTag2 = new LabTag(VALID_LAB_CODE_2);
        assertFalse(labTag1.equals(labTag2));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        LabTag labTag = new LabTag(VALID_LAB_CODE_1);
        Tag tag = new Tag(VALID_LAB_CODE_1);
        assertFalse(labTag.equals(tag));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        LabTag labTag = new LabTag(VALID_LAB_CODE_1);
        assertTrue(labTag.equals(labTag));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        LabTag labTag = new LabTag(VALID_LAB_CODE_1);
        assertFalse(labTag.equals(null));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        LabTag labTag = new LabTag(VALID_LAB_CODE_1);
        assertFalse(labTag.equals(VALID_LAB_CODE_1));
    }

    @Test
    public void hashCode_sameLabCode_sameHashCode() {
        LabTag labTag1 = new LabTag(VALID_LAB_CODE_1);
        LabTag labTag2 = new LabTag(VALID_LAB_CODE_1);
        assertEquals(labTag1.hashCode(), labTag2.hashCode());
    }

    @Test
    public void hashCode_differentLabCode_differentHashCode() {
        LabTag labTag1 = new LabTag(VALID_LAB_CODE_1);
        LabTag labTag2 = new LabTag(VALID_LAB_CODE_2);
        assertNotEquals(labTag1.hashCode(), labTag2.hashCode());
    }

    @Test
    public void toString_returnsFormattedString() {
        LabTag labTag = new LabTag(VALID_LAB_CODE_1);
        assertEquals("[" + VALID_LAB_CODE_1 + "]", labTag.toString());
    }
}
