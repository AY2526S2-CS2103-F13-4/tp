package seedu.address.model.tag;

/**
 * Represents a Tutorial as a group label.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTutorialCode(String)}
 */
public class TutorialTag extends Tag {

    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial codes should be T/TUT+numbers and no more than 5 characters";
    private static final String TUTORIAL_CODE_VALIDATION_REGEX = "^(T|TUT)[0-9]+$";

    /**
     * Constructs a {@code TutorialTag}.
     *
     * @param tutorialCode A valid tutorial code.
     */
    public TutorialTag(String tutorialCode) {
        super(tutorialCode);
    }

    @Override
    public TagType getTagType() {
        return TagType.TUTORIAL;
    }

    /**
     * Returns true if a given string is a valid course code.
     */
    public static boolean isValidTutorialCode(String test) {
        return test.length() <= 5 && test.matches(TUTORIAL_CODE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "[Tutorial:%s]".formatted(tagName);
    }
}
