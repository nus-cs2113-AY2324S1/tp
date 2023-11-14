package seedu.financialplanner.commands.utils;

import org.reflections.Reflections;
import org.slf4j.simple.SimpleLogger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class CommandManager {

    public static final String COMMAND_CLASS_USAGE_FIELD_NAME = "USAGE";

    public static final String COMMAND_CLASS_EXAMPLE_FIELD_NAME = "EXAMPLE";

    public static final String COMMAND_CLASS_NAME_FIELD_NAME = "NAME";
    private static final String COMMAND_PACKAGE_NAME = "seedu.financialplanner.commands";

    private static final CommandManager instance = new CommandManager();
    private final Map<String, Class<? extends Command>> nameCommandMap = new HashMap<>();

    private CommandManager() {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
        Reflections reflections = new Reflections(COMMAND_PACKAGE_NAME);
        Set<Class<? extends Command>> commandClasses = reflections.getSubTypesOf(Command.class);
        for (Class<? extends Command> c : commandClasses) {
            if (!Command.class.isAssignableFrom(c)) {
                continue;
            }
            try {
                String commandName = (String) c.getField(COMMAND_CLASS_NAME_FIELD_NAME).get(null);
                if (nameCommandMap.containsKey(commandName)) {
                    throw new RuntimeException("Adding command with duplicate name: " + commandName);
                }
                nameCommandMap.put(commandName, c);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static CommandManager getInstance() {
        return instance;
    }

    @SuppressWarnings("unused")
    public Class<? extends Command> getCommandClass(String commandName) throws NoSuchElementException {
        if (!nameCommandMap.containsKey(commandName)) {
            throw new NoSuchElementException("Command not found!");
        }
        return nameCommandMap.get(commandName);
    }

    @SuppressWarnings("unused")
    public String getCommandUsage(Class<? extends Command> commandClass) throws NoSuchElementException {
        try {
            Field usageField = commandClass.getField(COMMAND_CLASS_USAGE_FIELD_NAME);
            return (String) usageField.get(null);
        } catch (ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Cannot get command usage. Is there a bug?");
        }
    }


    @SuppressWarnings("unused")
    public String getCommandUsage(String commandName) throws NoSuchElementException {
        try {
            return getCommandUsage(getCommandClass(commandName));
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    public String getCommandExample(Class<? extends Command> commandClass) throws NoSuchElementException {
        try {
            Field exampleField = commandClass.getField(COMMAND_CLASS_EXAMPLE_FIELD_NAME);
            return (String) exampleField.get(null);
        } catch (ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Cannot get command example. Is there a bug?");
        }
    }

    @SuppressWarnings("unused")
    public String getCommandExample(String commandName) {
        try {
            return getCommandExample(getCommandClass(commandName));
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @SuppressWarnings("unused")
    public Set<String> getCommandNames() {
        return nameCommandMap.keySet();
    }

    @SuppressWarnings("unused")
    public Set<Class<? extends Command>> getCommandClasses() {
        return new HashSet<>(nameCommandMap.values());
    }

}
