package seedu.duke.commands;

import seedu.duke.Balance;
import seedu.duke.Group;
import seedu.duke.exceptions.ExpensesException;

import java.util.Optional;

public class BalanceCommand {
    public static void handleBalance(String argument) throws ExpensesException{
        // Checks if user is currently in a Group
        // named 'currentGroup1' to prevent conflict with previous declaration
        Optional<Group> currentGroup = Group.getCurrentGroup();
        if (currentGroup.isEmpty()) {
            String exceptionMessage = "Not signed in to a Group! Use 'create <name>' to create Group";
            throw new ExpensesException(exceptionMessage);
        }
        assert currentGroup.isPresent()  : "Group should be created and present";

        // Checks if user specified is in Current Group
        if (Group.isMember(argument)) {
            String exceptionMessage = argument + " is not in current Group!";
            throw new ExpensesException(exceptionMessage);
        }

        Balance balance = new Balance(argument, currentGroup.get());
        balance.printBalance();
    }
}
