package cashleh.parser;

import cashleh.exceptions.CashLehParsingException;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Represents a string tokenizer that tokenizes a string based on a list of prefixes.
 */
public class StringTokenizer {

    /*
     * Tokenizes a string based on a list of prefixes.
     * @param input String to be tokenized
     * @param prefixes List of prefixes to tokenize the string with - the order of the prefixes does not matter
     * Prefixes can be marked as optional by appending ":optional" to the prefix
     * @return HashMap of prefixes and their corresponding values
     * Values are trimmed before being added to the HashMap
     * Values return null if the prefix is not present in the input string
     * @throws CashLehParsingException if any of the prefixes are not present in the input string
     */
    public static HashMap<String, String> tokenize(String input, String[] prefixes) throws CashLehParsingException {
        checkAllPrefixesPresent(input, prefixes);
        ArrayList<PrefixWithPosition> prefixIndices = getPrefixIndices(input, prefixes);
        return getArgumentsMap(prefixIndices, input);
    }

    private static void checkAllPrefixesPresent(String input, String[] prefixes) throws CashLehParsingException {
        for (int i = 0; i < prefixes.length; i++) {
            if (prefixes[i].contains(":optional")) {
                prefixes[i] = prefixes[i].replace(":optional", "");
                return;
            }
            if (!input.contains(prefixes[i])) {
                throw new CashLehParsingException("Aiyoh! Your input blur like sotong... " +
                        "You never enter " + prefixes[i] + " leh!");
            }
        }
    }

    /*
     * Represents a prefix and its position in the input string.
     */
    private static class PrefixWithPosition {
        private final String prefix;
        private final int position;

        public PrefixWithPosition(String prefix, int position) {
            this.prefix = prefix;
            this.position = position;
        }

        public String getPrefix() {
            return prefix;
        }

        public int getPosition() {
            return position;
        }
    }

    private static ArrayList<PrefixWithPosition> getPrefixIndices(String input, String[] prefixes) {
        ArrayList<PrefixWithPosition> prefixIndices = new ArrayList<>();
        for (String prefix : prefixes) {
            int index = input.indexOf(prefix);
            // Ignore optional prefixes that are not present in the input string
            if (index == -1) {
                continue;
            }
            prefixIndices.add(new PrefixWithPosition(prefix, input.indexOf(prefix)));
        }
        return prefixIndices;
    }

    private static HashMap<String, String> getArgumentsMap(ArrayList<PrefixWithPosition> prefixes, String inputString) {

        // Sort map by prefix position
        prefixes.sort((prefixes1, prefixes2) -> prefixes1.getPosition() - prefixes2.getPosition());

        // Add dummy prefixes to represent start and end
        prefixes.add(0, new PrefixWithPosition("", 0));
        prefixes.add(new PrefixWithPosition("", inputString.length()));


        HashMap<String, String> tokenizedMap = new HashMap<>();
        for (int i = 0; i < prefixes.size() - 1; i++) {
            String prefixString = prefixes.get(i).getPrefix();
            // Get the string between the current prefix and the next prefix
            String parsedValue = inputString.substring(prefixes.get(i).getPosition() + prefixString.length(),
                    prefixes.get(i + 1).getPosition());
            tokenizedMap.put(prefixString, parsedValue.trim());
        }
        return tokenizedMap;
    }

}
