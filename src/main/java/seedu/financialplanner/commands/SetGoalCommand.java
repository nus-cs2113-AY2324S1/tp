package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.goal.WishList;

@SuppressWarnings("unused")
public class SetGoalCommand extends Command {
    public static final String NAME = "set";

    public static final String USAGE =
            "set goal </g AMOUNT> </l LABEL>";
    public static final String EXAMPLE =
            "set goal /g 5000 /l car";
    private final String label;
    private final int amount;

    /**
     * Constructor for the command to set a goal.
     *
     * @param rawCommand The input from the user.
     * @throws IllegalArgumentException if erroneous inputs are detected.
     */
    public SetGoalCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String labelString = String.join(" ", rawCommand.args);
        if (!rawCommand.extraArgs.containsKey("g")) {
            throw new IllegalArgumentException("Goal must have an amount");
        }

        String amountString = rawCommand.extraArgs.get("g");
        if (amountString.trim().isEmpty()) {
            throw new IllegalArgumentException("Amount must be specified");
        }
        try {
            amount = Integer.parseInt(amountString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Amount must be a valid integer");
        }

        if (amount<= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        rawCommand.extraArgs.remove("g");
        if (!rawCommand.extraArgs.containsKey("l")) {
            throw new IllegalArgumentException("Please specify the content of the goal");
        }
        label = rawCommand.extraArgs.get("l");

        if (label.trim().isEmpty()) {
            throw new IllegalArgumentException("Please specify the content of the goal");
        }
        rawCommand.extraArgs.remove("l");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to set a goal.
     */
    @Override
    public void execute() {
        assert amount > 0;
        WishList.getInstance().addGoal(label, amount);
    }
}
