package seedu.address.model.tag;

/**
 * Represents a Course as a group label.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCourseCode(String)}
 */
public class CourseTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS =
            "Courses codes should be letters+numbers+letters and no more than 10 characters";
    private static final String COURSE_CODE_VALIDATION_REGEX = "[A-Z]+[0-9]+[A-Z]*$";

    /**
     * Constructs a {@code CourseTag}.
     *
     * @param courseCode A valid course code.
     */
    public CourseTag(String courseCode) {
        super(courseCode);
    }

    @Override
    public TagType getTagType() {
        return TagType.COURSE;
    }

    /**
     * Returns true if a given string is a valid course code.
     */
    public static boolean isValidCourseCode(String test) {
        return test.length() <= 10 && test.matches(COURSE_CODE_VALIDATION_REGEX);
    }
}
