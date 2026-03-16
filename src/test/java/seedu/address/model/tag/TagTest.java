package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    private static final String VALID_TAG_NAME_1 = "friend";
    private static final String VALID_TAG_NAME_2 = "colleague";
    private static final String INVALID_TAG_NAME_EMPTY = "";
    private static final String INVALID_TAG_NAME_SPACES = "tag with spaces";
    private static final String INVALID_TAG_NAME_SPECIAL_CHARS = "tag@friend";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void constructor_validTagName_success() {
        Tag tag = new Tag(VALID_TAG_NAME_1);
        assertEquals(VALID_TAG_NAME_1, tag.tagName);
    }

    @Test
    public void isValidTagName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isValidTagName_validTagNames_returnsTrue() {
        assertTrue(Tag.isValidTagName(VALID_TAG_NAME_1));
        assertTrue(Tag.isValidTagName(VALID_TAG_NAME_2));
        assertTrue(Tag.isValidTagName("a")); // Single character
        assertTrue(Tag.isValidTagName("1")); // Single number
        assertTrue(Tag.isValidTagName("tag123")); // Alphanumeric
    }

    @Test
    public void isValidTagName_invalidTagNames_returnsFalse() {
        assertFalse(Tag.isValidTagName(INVALID_TAG_NAME_EMPTY));
        assertFalse(Tag.isValidTagName(INVALID_TAG_NAME_SPACES));
        assertFalse(Tag.isValidTagName(INVALID_TAG_NAME_SPECIAL_CHARS));
        assertFalse(Tag.isValidTagName("tag with spaces"));
        assertFalse(Tag.isValidTagName("tag@special"));
    }

    @Test
    public void getTagType_returnsTag() {
        Tag tag = new Tag(VALID_TAG_NAME_1);
        assertEquals(TagType.TAG, tag.getTagType());
    }

    @Test
    public void equals_sameTagName_returnsTrue() {
        Tag tag1 = new Tag(VALID_TAG_NAME_1);
        Tag tag2 = new Tag(VALID_TAG_NAME_1);
        assertTrue(tag1.equals(tag2));
    }

    @Test
    public void equals_differentTagName_returnsFalse() {
        Tag tag1 = new Tag(VALID_TAG_NAME_1);
        Tag tag2 = new Tag(VALID_TAG_NAME_2);
        assertFalse(tag1.equals(tag2));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        Tag tag = new Tag(VALID_TAG_NAME_1);
        CourseTag courseTag = new CourseTag(VALID_TAG_NAME_1);
        assertFalse(tag.equals(courseTag));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Tag tag = new Tag(VALID_TAG_NAME_1);
        assertTrue(tag.equals(tag));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        Tag tag = new Tag(VALID_TAG_NAME_1);
        assertFalse(tag.equals(null));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        Tag tag = new Tag(VALID_TAG_NAME_1);
        assertFalse(tag.equals(VALID_TAG_NAME_1));
    }

    @Test
    public void hashCode_sameTagName_sameHashCode() {
        Tag tag1 = new Tag(VALID_TAG_NAME_1);
        Tag tag2 = new Tag(VALID_TAG_NAME_1);
        assertEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    public void hashCode_differentTagName_differentHashCode() {
        Tag tag1 = new Tag(VALID_TAG_NAME_1);
        Tag tag2 = new Tag(VALID_TAG_NAME_2);
        assertNotEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    public void toString_returnsFormattedString() {
        Tag tag = new Tag(VALID_TAG_NAME_1);
        assertEquals("[" + VALID_TAG_NAME_1 + "]", tag.toString());
    }
}
