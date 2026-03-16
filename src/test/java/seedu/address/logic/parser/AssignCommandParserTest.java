package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_EMPTY_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.model.tag.CourseTag;
import seedu.address.model.tag.LabTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TutorialTag;

public class AssignCommandParserTest {

    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Set<Tag> expectedTags = new HashSet<>();
        expectedTags.add(new CourseTag("CS2103"));
        expectedTags.add(new TutorialTag("T01"));
        expectedTags.add(new LabTag("L01"));

        // All prefixes present
        assertParseSuccess(parser, "1 " + PREFIX_COURSE + "CS2103 " + PREFIX_TUTORIAL + "T01 " + PREFIX_LAB + "L01",
                new AssignCommand(INDEX_FIRST_PERSON, expectedTags));

        // Only course
        Set<Tag> courseOnly = new HashSet<>();
        courseOnly.add(new CourseTag("CS2103"));
        assertParseSuccess(parser, "1 " + PREFIX_COURSE + "CS2103",
                new AssignCommand(INDEX_FIRST_PERSON, courseOnly));

        // Only tutorial
        Set<Tag> tutorialOnly = new HashSet<>();
        tutorialOnly.add(new TutorialTag("T01"));
        assertParseSuccess(parser, "1 " + PREFIX_TUTORIAL + "T01",
                new AssignCommand(INDEX_FIRST_PERSON, tutorialOnly));

        // Only lab
        Set<Tag> labOnly = new HashSet<>();
        labOnly.add(new LabTag("L01"));
        assertParseSuccess(parser, "1 " + PREFIX_LAB + "L01",
                new AssignCommand(INDEX_FIRST_PERSON, labOnly));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE);

        // Missing index
        assertParseFailure(parser, PREFIX_COURSE + "CS2103", expectedMessage);

        // No groups
        assertParseFailure(parser, "1", MESSAGE_EMPTY_GROUP);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid index
        assertParseFailure(parser, "a " + PREFIX_COURSE + "CS2103",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));

        // Invalid course code
        assertParseFailure(parser, "1 " + PREFIX_COURSE + "2103T", CourseTag.MESSAGE_CONSTRAINTS);

        // Invalid tutorial name
        assertParseFailure(parser, "1 " + PREFIX_TUTORIAL + "Tut1", TutorialTag.MESSAGE_CONSTRAINTS);

        // Invalid lab name
        assertParseFailure(parser, "1 " + PREFIX_LAB + "Lab1", LabTag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_duplicatePrefixes_failure() {
        // Duplicate course
        assertParseFailure(parser, "1 " + PREFIX_COURSE + "CS2103 " + PREFIX_COURSE + "CS2106",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COURSE));

        // Duplicate tutorial
        assertParseFailure(parser, "1 " + PREFIX_TUTORIAL + "T01 " + PREFIX_TUTORIAL + "T02",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TUTORIAL));

        // Duplicate lab
        assertParseFailure(parser, "1 " + PREFIX_LAB + "L01 " + PREFIX_LAB + "L02",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LAB));
    }
}
