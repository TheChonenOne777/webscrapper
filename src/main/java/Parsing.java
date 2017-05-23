import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * <p>This interface contains methods for separating an input string,
 * which is concatinated args array for sure, into parts like string version of URL,
 * keywords to be searched for, flags for commands to be executed.</p>
 *
 * <p>Implemented by {@link Parser}.</p>
 */

public interface Parsing {

    /**
     * Gets a string URL from an input string.
     * @param str - input string, could be anything in text format
     * @return a URL-like string
     */
    String getURLtoString(String str);

    /**
     * Gets a path to file from an input string.
     * @param str - input string
     * @return path-alike string
     */
    String getPath(String str);

    /**
     * Gets a list of string URLs from the file, located in local storage.
     * @param path - path to file
     * @return list of string URLs
     * @throws IOException if there are any problems with file path or file indeed
     */
    List<String> getURLsToStringFromPath(String path) throws IOException;

    /**
     * Gets keywords from input string. In other words, this method just separates
     * the input string by commas and add them to a Set, no duplicates allowed.
     * The input string to be parsed correctly, this method sould be used after url and commands are cut out.
     * @param str - input string
     * @return Set of string keywords
     */
    Set<String> getKeywords(String str);

    /**
     * Gets command flags as a string from input string. Converts value from -n -b -a to nba, for example.
     * Simply finds a sequence of space, dash and a letter at the end of the input string.
     * @param str - input string
     * @return string, contains sequence of letters
     */
    String getCommands(String str);

}
