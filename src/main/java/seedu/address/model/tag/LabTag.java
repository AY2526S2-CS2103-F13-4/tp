package seedu.address.model.tag;

/**
 * Represents a Lab as a group label.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLabCode(String)}
 */
public class LabTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS =
            "Lab codes should be L/LAB+numbers and no more than 5 characters";
    private static final String LAB_CODE_VALIDATION_REGEX = "^(L|LAB)[0-9]+$";

    /**
     * Constructs a {@code LabTag}.
     *
     * @param labCode A valid lab code.
     */
    public LabTag(String labCode) {
        super(labCode);
    }

    @Override
    public TagType getTagType() {
        return TagType.LAB;
    }

    /**
     * Returns true if a given string is a valid course code.
     */
    public static boolean isValidLabCode(String test) {
        return test.length() <= 5 && test.matches(LAB_CODE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "[Lab:%s]".formatted(tagName);
    }
}
