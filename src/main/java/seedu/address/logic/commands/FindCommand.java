package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.*;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TagsContainsTagPredicate;
import seedu.address.model.tag.AbstractTag;

/**
 * Finds and lists all persons in address book whose name contains any of the
 * argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords and/or who have any of the specified tags. "
            + "Parameters: "
            + "[KEYWORD [MORE_KEYWORDS]...] "
            + "[" + PREFIX_TAG + "TAG [MORE_TAGS]...]\n"
            + "Note: At least one of KEYWORD or TAG must be provided.\n"
            + "Example: " + COMMAND_WORD + " alice bob " + PREFIX_TAG + "friends";

    private final NameContainsKeywordsPredicate namePredicate;
    private final TagsContainsTagPredicate tagPredicate;
    private final Predicate<Person> predicate;

    /**
     * Constructs a {@code FindCommand} to filter persons by name keywords only.
     *
     * @param namePredicate the predicate for matching persons by name keywords;
     *                      cannot be null
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate) {
        this(namePredicate, null);
    }

    /**
     * Constructs a {@code FindCommand} to filter persons by name keywords and/or tags.
     * Both predicates are combined using AND logic, so persons must match both conditions
     * (if both are provided).
     *
     * @param namePredicate the predicate for matching persons by name keywords;
     *                      cannot be null
     * @param tagPredicate  the predicate for matching persons by tags;
     *                      can be null to filter by name keywords only
     */
    public FindCommand(NameContainsKeywordsPredicate namePredicate, TagsContainsTagPredicate tagPredicate) {
        Predicate<Person> finalPredicate = x -> true;
        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
        if (namePredicate != null) {
            finalPredicate = finalPredicate.and(namePredicate);
        }
        if (tagPredicate != null) {
            finalPredicate = finalPredicate.and(tagPredicate);
        }
        this.predicate = finalPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return Objects.equals(namePredicate, otherFindCommand.namePredicate)
                && Objects.equals(tagPredicate, otherFindCommand.tagPredicate);

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("namePredicate", namePredicate)
                .add("tagPredicate", tagPredicate)
                .toString();
    }

    /**
     * Stores the details to find the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class FindPersonDescriptor {
        private Set<Name> name;
        private Set<Phone> phone;
        private Set<Email> email;
        private Set<AbstractTag> tags;

        public FindPersonDescriptor() {}


        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public FindPersonDescriptor(FindPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
        }

        public void setName(Set<Name> name) {
            this.name = name;
        }

        public Optional<Set<Name>> getName() {
            return (name != null) ? Optional.of(Collections.unmodifiableSet(name)) : Optional.empty();
        }

        public void setPhone(Set<Phone> phone) {
            this.phone = phone;
        }

        public Optional<Set<Phone>> getPhone() {
            return (phone != null) ? Optional.of(Collections.unmodifiableSet(phone)) : Optional.empty();
        }

        public void setEmail(Set<Email> email) {
            this.email = email;
        }

        public Optional<Set<Email>> getEmail() {
            return (email != null) ? Optional.of(Collections.unmodifiableSet(email)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public <T extends AbstractTag> void setTags(Set<T> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<AbstractTag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindPersonDescriptor otherFindPersonDescriptor)) {
                return false;
            }

            return Objects.equals(name, otherFindPersonDescriptor.name)
                    && Objects.equals(phone, otherFindPersonDescriptor.phone)
                    && Objects.equals(email, otherFindPersonDescriptor.email)
                    && Objects.equals(tags, otherFindPersonDescriptor.tags);
        }
        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("tags", tags)
                    .toString();
        }
    }
}
