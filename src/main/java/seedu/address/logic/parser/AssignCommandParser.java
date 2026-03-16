package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_EMPTY_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagType;

public class AssignCommandParser implements Parser<AssignCommand> {

    @Override
    public AssignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COURSE, PREFIX_TUTORIAL, PREFIX_LAB);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COURSE, PREFIX_TUTORIAL, PREFIX_LAB);

        Set<Tag> groupTags = new HashSet<>();
        if (argMultimap.getValue(PREFIX_COURSE).isPresent()) {
            groupTags.add(ParserUtil
                    .parseGroupTag(argMultimap.getValue(PREFIX_COURSE).get(), TagType.COURSE));
        }
        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            groupTags.add(ParserUtil
                    .parseGroupTag(argMultimap.getValue(PREFIX_TUTORIAL).get(), TagType.TUTORIAL));
        }
        if (argMultimap.getValue(PREFIX_LAB).isPresent()) {
            groupTags.add(ParserUtil
                    .parseGroupTag(argMultimap.getValue(PREFIX_LAB).get(), TagType.LAB));
        }

        if (groupTags.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_GROUP);
        }

        return new AssignCommand(index, groupTags);
    }
}
