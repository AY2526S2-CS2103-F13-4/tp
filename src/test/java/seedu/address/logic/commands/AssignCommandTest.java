package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.CourseTag;
import seedu.address.model.tag.LabTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TutorialTag;

public class AssignCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Set<Tag> groupTags = new HashSet<>();
        groupTags.add(new CourseTag("CS2103"));
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, groupTags));
    }

    @Test
    public void constructor_nullGroupTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_personAssignedToGroup_success() throws Exception {
        Person personToAssign = ALICE;
        Set<Tag> groupTags = new HashSet<>();
        groupTags.add(new CourseTag("CS2103"));
        groupTags.add(new TutorialTag("T01"));

        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, groupTags);
        ModelStubAcceptingPersonAssigned modelStub = new ModelStubAcceptingPersonAssigned(personToAssign);

        CommandResult commandResult = assignCommand.execute(modelStub);

        // Expected person should have original details plus new group tags
        Set<Tag> expectedTags = new HashSet<>(personToAssign.getTags());
        expectedTags.addAll(groupTags);
        Person expectedPerson = new Person(
                personToAssign.getName(),
                personToAssign.getPhone(),
                personToAssign.getEmail(),
                personToAssign.getUsername(),
                expectedTags
        );

        assertEquals(String.format(AssignCommand.MESSAGE_ASSIGN_GROUP_SUCCESS, personToAssign.getName(), groupTags),
                commandResult.getFeedbackToUser());
        assertEquals(expectedPerson, modelStub.personAssigned);
    }

    @Test
    public void execute_invalidPersonIndex_throwsCommandException() {
        Set<Tag> groupTags = new HashSet<>();
        groupTags.add(new CourseTag("CS2103"));
        AssignCommand assignCommand = new AssignCommand(Index.fromOneBased(10), groupTags);

        ModelStub modelStub = new ModelStubWithNoPerson();

        assertThrows(CommandException.class, () -> assignCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Set<Tag> courseTags = new HashSet<>();
        courseTags.add(new CourseTag("CS2103"));
        Set<Tag> labTags = new HashSet<>();
        labTags.add(new LabTag("L01"));

        AssignCommand assignCourseCommand = new AssignCommand(INDEX_FIRST_PERSON, courseTags);
        AssignCommand assignLabCommand = new AssignCommand(INDEX_FIRST_PERSON, labTags);

        // same object -> returns true
        assertTrue(assignCourseCommand.equals(assignCourseCommand));

        // same values -> returns true
        AssignCommand assignCourseCommandCopy = new AssignCommand(INDEX_FIRST_PERSON, courseTags);
        assertTrue(assignCourseCommand.equals(assignCourseCommandCopy));

        // different types -> returns false
        assertFalse(assignCourseCommand.equals(1));

        // null -> returns false
        assertFalse(assignCourseCommand.equals(null));

        // different index -> returns false
        assertFalse(assignCourseCommand.equals(new AssignCommand(Index.fromOneBased(2), courseTags)));

        // different tags -> returns false
        assertFalse(assignCourseCommand.equals(assignLabCommand));
    }

    @Test
    public void toStringMethod() {
        Set<Tag> groupTags = new HashSet<>();
        groupTags.add(new CourseTag("CS2103"));
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, groupTags);
        String expected = AssignCommand.class.getCanonicalName() + "{index=" + INDEX_FIRST_PERSON
                + ", groupTags=" + groupTags + "}";
        assertEquals(expected, assignCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains no person.
     */
    private class ModelStubWithNoPerson extends ModelStub {
        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.emptyObservableList();
        }
    }

    /**
     * A Model stub that always accept the person being assigned.
     */
    private class ModelStubAcceptingPersonAssigned extends ModelStub {
        public Person personAssigned;
        public Set<Tag> assignedTags;
        private final Person person;

        ModelStubAcceptingPersonAssigned(Person person) {
            this.person = person;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.singletonObservableList(person);
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            requireNonNull(target);
            requireNonNull(editedPerson);
            personAssigned = editedPerson;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            // Do nothing
        }
    }
}
