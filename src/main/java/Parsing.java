import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Parsing {

    String getURLtoString(String str);
    String getPath(String str);
    List<String> getURLsToStringFromPath(String path) throws IOException;
    List<String> getKeywords(String str);
    String getCommands(String str);

}
