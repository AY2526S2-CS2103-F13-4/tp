package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Assign a person to a group.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";
    public static final String MESSAGE_USAGE = """
        %1$s: Assign a person by the by the index number used in the displayed person list, \
        to a group by the group prefix and group name.
        Existing values will be overwritten by the input values.
        
        Parameters: INDEX [%2$sCOURSE] [%3$sTUTORIAL] [%4$sLAB]
        
        Example: %1$s 1 %2$sCS2103
        """.formatted(COMMAND_WORD, PREFIX_COURSE, PREFIX_TUTORIAL, PREFIX_LAB);
    public static final String MESSAGE_EMPTY_GROUP = "At least one group is required.";
    public static final String MESSAGE_ASSIGN_GROUP_SUCCESS = "Assigned %1$s to the group(s): %2$s";

    private final Index index;
    private final Set<Tag> groupTags;
    public AssignCommand(Index index, Set<Tag> groupTags) {
        requireNonNull(index);
        requireNonNull(groupTags);

        this.index = index;
        this.groupTags = groupTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAssign = lastShownList.get(index.getZeroBased());

        Set<Tag> newTags = personToAssign.getTags().stream()
                .filter(tag -> !groupTags.contains(tag))
                .collect(Collectors.toSet());
        newTags.addAll(groupTags);

        Person personAssigned = new Person(
                personToAssign.getName(),
                personToAssign.getPhone(),
                personToAssign.getEmail(),
                personToAssign.getUsername(),
                newTags
        );

        model.setPerson(personToAssign, personAssigned);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ASSIGN_GROUP_SUCCESS, personAssigned.getName(), groupTags));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AssignCommand)) {
            return false;
        }

        AssignCommand other = (AssignCommand) obj;
        return index.equals(other.index) && groupTags.equals(other.groupTags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("groupTags", groupTags)
                .toString();
    }
}
