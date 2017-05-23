import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>This class implements interface Parsing.</p>
 * <p>It has common for all methods Pattern and Matcher. Hence uses reqular expressions
 * to exclude data from input string</p>
 */

public class Parser implements Parsing {

    Pattern pattern;
    Matcher matcher;
    String output = "";

    public String getURLtoString(String str) {
        output = "";
        pattern = Pattern.compile("https?:\\/\\/[-\\w\\.]+\\.([a-z]{2,6})(\\/[-\\w\\.]*)*\\/?");
        matcher = pattern.matcher(str);

        if(matcher.find()){
            output = matcher.group();
        }

        return output;
    }

    public String getPath(String str) {
        output = "";
        pattern = Pattern.compile("^([A-z]:\\\\\\\\)?([-.\\w]+\\\\)*[-.\\w]+\\.[\\w]+");
        matcher = pattern.matcher(str);

        if(matcher.find()){
            output = matcher.group();
        }

        if("".equals(output)){
            pattern = Pattern.compile("^([A-z]:\\/)?([-.\\w]+\\/)*[-.\\w]+\\.[\\w]+");
            matcher = pattern.matcher(str);
            if(matcher.find()){
                output = matcher.group();
            }
        }

        return output;
    }

    public List<String> getURLsToStringFromPath(String path) throws FileNotFoundException{
        List<String> urlList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            while (br.ready()){
                urlList.add(getURLtoString(br.readLine()));
            }

        } catch (IOException e){
            e.printStackTrace();
        };

        urlList.removeAll(Arrays.asList("", null));

        return urlList;
    }

    public Set<String> getKeywords(String str) {
        Set<String> outputList = new HashSet<String>(Arrays.asList(str.split("\\s*,\\s*")));

        outputList.removeAll(Arrays.asList("", null));

        return outputList;
    }

    public String getCommands(String str) {
        output = "";
        pattern = Pattern.compile("(\\s-[a-z])*\\s?$");
        matcher = pattern.matcher(str);

        while(matcher.find()){
            output += matcher.group();
        }

        return output;
    }
}
