package seedu.address.model.tag.restricted;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TutorialTagSchemaTest {
    private TutorialTagSchema schema = new TutorialTagSchema();

    public static final String VALID_TUTORIAL_TAG = "D24";
    public static final String INVALID_TUTORIAL_TAG = "d24";

    @Test
    public void testValidTags() {
        assertTrue(schema.isTagValid(VALID_TUTORIAL_TAG));
        assertTrue(schema.isTagValid("8"));
        assertTrue(schema.isTagValid("10"));
        assertTrue(schema.isTagValid("9"));
        assertTrue(schema.isTagValid("A12"));
    }

    @Test
    public void testInvalidTags() {
        assertFalse(schema.isTagValid(INVALID_TUTORIAL_TAG)); // lowercase not allowed
        assertFalse(schema.isTagValid("D")); // too short without digit
        assertFalse(schema.isTagValid("D245")); // more than 2 digits
        assertFalse(schema.isTagValid("100")); // 3 digits
        assertFalse(schema.isTagValid(""));
        assertFalse(schema.isTagValid("AB24")); // two letters not allowed
    }

    @Test
    public void testGetConstraintViolationMessage() {
        assertEquals(
                "Tutorial tag expects format of an optional uppercase letter followed by maximum 2 numbers. Valid: 'D24' and '8'",
                schema.getConstraintViolationMessage());
    }

    @Test
    public void getVariant() {
        assertEquals("tut", schema.getVariant());
    }
}