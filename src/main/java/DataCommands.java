import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCommands {
    Pattern pattern;
    Matcher matcher;

    public int countWordOccurencesByURL(URL url, String keyWord) throws IOException {
        int occurenceNumber = 0;
        pattern = Pattern.compile(keyWord);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){

            while (br.ready()){
                String inputLine = br.readLine();
                matcher = pattern.matcher(inputLine);
                while (matcher.find()) occurenceNumber++;
            }

        };
        return occurenceNumber;
    }

    public int countNumberOfCharactesByURL(URL url) throws IOException {
        int charactersNumber = 0;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){

            while (br.ready()){
                String input = br.readLine();
                charactersNumber += input.length();
            }

        };

        return charactersNumber;
    }

    public List<String> getSentencesContainingWord(URL url, String word) throws IOException {

        List<String> listOfSentences = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))){

            while (br.ready()){
                sb.append(br.readLine());
            }

        };

        pattern = Pattern.compile("\\.\\s+");

        for(String sentence : pattern.split(sb)){
            if(sentence.contains(word)){
                listOfSentences.add(sentence);
            }
        }
        return listOfSentences;
    }

}
