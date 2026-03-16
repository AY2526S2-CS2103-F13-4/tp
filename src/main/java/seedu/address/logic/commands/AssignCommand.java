package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Assign a person to a group.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";
    public static final String MESSAGE_USAGE = """
        %1$s: Assign a person to a group by the person name, group prefix, and group name.
        Existing values will be overwritten by the input values.
        
        Parameters: NAME [%2$sCOURSE] [%3$sTUTORIAL] [%4$sLAB]
        
        Example: %1$s %2$sCS2103
        """.formatted(COMMAND_WORD, PREFIX_COURSE, PREFIX_TUTORIAL, PREFIX_LAB);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
