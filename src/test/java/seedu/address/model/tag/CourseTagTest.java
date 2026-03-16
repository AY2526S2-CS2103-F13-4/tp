package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CourseTagTest {

    private static final String VALID_COURSE_CODE_1 = "CS2103";
    private static final String VALID_COURSE_CODE_2 = "MA1100";
    private static final String INVALID_COURSE_CODE_EMPTY = "";
    private static final String INVALID_COURSE_CODE_TOO_LONG = "CS2103T123"; // >10 chars
    private static final String INVALID_COURSE_CODE_WRONG_FORMAT = "cs2103"; // lowercase
    private static final String INVALID_COURSE_CODE_NO_NUMBER = "CSABC"; // no number
    private static final String INVALID_COURSE_CODE_START_NUMBER = "1CS2103"; // starts with number

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CourseTag(null));
    }

    @Test
    public void constructor_invalidCourseCode_throwsIllegalArgumentException() {
        String invalidCourseCode = "";
        assertThrows(IllegalArgumentException.class, () -> new CourseTag(invalidCourseCode));
    }

    @Test
    public void constructor_validCourseCode_success() {
        CourseTag courseTag = new CourseTag(VALID_COURSE_CODE_1);
        assertEquals(VALID_COURSE_CODE_1, courseTag.tagName);
    }

    @Test
    public void isValidCourseCode_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CourseTag.isValidCourseCode(null));
    }

    @Test
    public void isValidCourseCode_validCourseCodes_returnsTrue() {
        assertTrue(CourseTag.isValidCourseCode(VALID_COURSE_CODE_1));
        assertTrue(CourseTag.isValidCourseCode(VALID_COURSE_CODE_2));
        assertTrue(CourseTag.isValidCourseCode("CS2103T")); // With optional letter
        assertTrue(CourseTag.isValidCourseCode("MA1100"));
        assertTrue(CourseTag.isValidCourseCode("GEQ1000"));
    }

    @Test
    public void isValidCourseCode_invalidCourseCodes_returnsFalse() {
        assertFalse(CourseTag.isValidCourseCode(INVALID_COURSE_CODE_EMPTY));
        assertFalse(CourseTag.isValidCourseCode(INVALID_COURSE_CODE_TOO_LONG));
        assertFalse(CourseTag.isValidCourseCode(INVALID_COURSE_CODE_WRONG_FORMAT));
        assertFalse(CourseTag.isValidCourseCode(INVALID_COURSE_CODE_NO_NUMBER));
        assertFalse(CourseTag.isValidCourseCode(INVALID_COURSE_CODE_START_NUMBER));
        assertFalse(CourseTag.isValidCourseCode("CS2103 ")); // Trailing space
        assertFalse(CourseTag.isValidCourseCode(" CS2103")); // Leading space
        assertFalse(CourseTag.isValidCourseCode("cs2103")); // Lowercase
        assertFalse(CourseTag.isValidCourseCode("CS2103@")); // Special char
    }

    @Test
    public void getTagType_returnsCourse() {
        CourseTag courseTag = new CourseTag(VALID_COURSE_CODE_1);
        assertEquals(TagType.COURSE, courseTag.getTagType());
    }

    @Test
    public void equals_sameCourseCode_returnsTrue() {
        CourseTag courseTag1 = new CourseTag(VALID_COURSE_CODE_1);
        CourseTag courseTag2 = new CourseTag(VALID_COURSE_CODE_1);
        assertTrue(courseTag1.equals(courseTag2));
    }

    @Test
    public void equals_differentCourseCode_returnsFalse() {
        CourseTag courseTag1 = new CourseTag(VALID_COURSE_CODE_1);
        CourseTag courseTag2 = new CourseTag(VALID_COURSE_CODE_2);
        assertFalse(courseTag1.equals(courseTag2));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        CourseTag courseTag = new CourseTag(VALID_COURSE_CODE_1);
        Tag tag = new Tag(VALID_COURSE_CODE_1);
        assertFalse(courseTag.equals(tag));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        CourseTag courseTag = new CourseTag(VALID_COURSE_CODE_1);
        assertTrue(courseTag.equals(courseTag));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        CourseTag courseTag = new CourseTag(VALID_COURSE_CODE_1);
        assertFalse(courseTag.equals(null));
    }

    @Test
    public void equals_differentClass_returnsFalse() {
        CourseTag courseTag = new CourseTag(VALID_COURSE_CODE_1);
        assertFalse(courseTag.equals(VALID_COURSE_CODE_1));
    }

    @Test
    public void hashCode_sameCourseCode_sameHashCode() {
        CourseTag courseTag1 = new CourseTag(VALID_COURSE_CODE_1);
        CourseTag courseTag2 = new CourseTag(VALID_COURSE_CODE_1);
        assertEquals(courseTag1.hashCode(), courseTag2.hashCode());
    }

    @Test
    public void hashCode_differentCourseCode_differentHashCode() {
        CourseTag courseTag1 = new CourseTag(VALID_COURSE_CODE_1);
        CourseTag courseTag2 = new CourseTag(VALID_COURSE_CODE_2);
        assertNotEquals(courseTag1.hashCode(), courseTag2.hashCode());
    }

    @Test
    public void toString_returnsFormattedString() {
        CourseTag courseTag = new CourseTag(VALID_COURSE_CODE_1);
        assertEquals("[" + "Course:" + VALID_COURSE_CODE_1 + "]", courseTag.toString());
    }
}
