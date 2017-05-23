import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * This interface contains methods for separating an input string,
 * which is concatinated args array for sure, into parts like string version of URL,
 * keywords to be searched for, flags for commands to be executed.
 */

public interface Parsing {

    String getURLtoString(String str);
    String getPath(String str);
    List<String> getURLsToStringFromPath(String path) throws IOException;
    List<String> getKeywords(String str);
    String getCommands(String str);

}
