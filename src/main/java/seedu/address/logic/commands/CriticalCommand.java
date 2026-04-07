package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Defines the behavior of critical command which requires user confirmation before execution.
 */
public interface CriticalCommand {

    /**
     * Verify if the command will execute without errors, this will be executed before requiring confirmation.
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException If an error will occur during command execution.
     */
    void preExecuteVerify(Model model) throws CommandException;
}
