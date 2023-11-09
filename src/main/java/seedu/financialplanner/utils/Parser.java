package seedu.financialplanner.utils;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.CommandManager;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Parser {
    public static Command parseCommand(String input) throws FinancialPlannerException {
        RawCommand rawCommand = parseRawCommand(input);
        return parseCommand(rawCommand);
    }

    public static Command parseCommand(RawCommand rawCommand) throws FinancialPlannerException {
        CommandManager commandManager = CommandManager.getInstance();
        Class<? extends Command> commandClass;

        try {
            commandClass = commandManager.getCommandClass(rawCommand.getCommandName().toLowerCase());
        } catch (NoSuchElementException e) {
            throw new FinancialPlannerException("Unknown command. Type help for help.");
        } catch (Exception e) {
            throw new FinancialPlannerException(e.getMessage());
        }

        Constructor<? extends Command> constructorWithRawCommand;
        Constructor<? extends Command> constructorWithNothing;

        try {
            constructorWithRawCommand = commandClass.getConstructor(RawCommand.class);
            return constructorWithRawCommand.newInstance(rawCommand);
        } catch (NoSuchMethodException noConstructorWithRawCommandException) {
            try {
                constructorWithNothing = commandClass.getConstructor();
                return constructorWithNothing.newInstance();
            } catch (InvocationTargetException e) {
                throw new FinancialPlannerException(e.getCause().getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (InvocationTargetException e) {
            throw new FinancialPlannerException(e.getCause().getMessage());
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static RawCommand parseRawCommand(String input) throws IllegalArgumentException {
        Iterator<String> iterator = Arrays.stream(input.split(" ")).iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }
        String commandName = iterator.next();
        List<String> args = new ArrayList<>();
        Map<String, String> extraArgs = new HashMap<>();

        List<String> extraArgumentContentBuffer = new ArrayList<>();
        String currentExtraArgumentName = null;

        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.startsWith("/")) {
                // Save previous extra argument when next extra argument is found
                if (currentExtraArgumentName != null) {
                    savePreviousExtraArgument(extraArgs, currentExtraArgumentName, extraArgumentContentBuffer);
                }
                if (next.length() == 1) {
                    throw new IllegalArgumentException("Extra argument name cannot be empty");
                }

                currentExtraArgumentName = next.substring(1);

            } else {
                if (currentExtraArgumentName == null) {
                    args.add(next);
                } else {
                    extraArgumentContentBuffer.add(next);
                }
            }
        }
        // Save previous extra argument at the very end
        if (currentExtraArgumentName != null) {
            savePreviousExtraArgument(extraArgs, currentExtraArgumentName, extraArgumentContentBuffer);
        }

        return new RawCommand(commandName, args, extraArgs);
    }

    private static void savePreviousExtraArgument(Map<String, String> extraArgs
            , String currentExtraArgumentName, List<String> extraArgumentContentBuffer) {
        if (extraArgs.containsKey(currentExtraArgumentName)) {
            throw new IllegalArgumentException(
                    String.format("Duplicate extra argument name: %s", currentExtraArgumentName));
        } else {
            extraArgs.put(currentExtraArgumentName, String.join(" ", extraArgumentContentBuffer));
            extraArgumentContentBuffer.clear();
        }
    }
}
