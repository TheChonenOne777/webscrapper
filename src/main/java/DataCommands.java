import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class contains methods for processing data, read from url content.
 */

public class DataCommands {
    Pattern pattern;
    Matcher matcher;

    /**
     * Counts number of occurences of the word in url content.
     * @param url - URL to read content from
     * @param keyWord - string word to search for in url content
     * @return int number of occurences of the keyWord in url
     * @throws IOException
     */
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

    /**
     * Counts number of charactes of whole url content.
     * @param url - url, which to be read from
     * @return int number of characters of url content
     * @throws IOException
     */
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

    /**
     * Collects sentences with containing word.
     * Firstly get all content to StringBuilder, then separates it into sentences and
     * returns only sentences with needed word.
     * As a sentence boundary is counted a dot, followed by space simbols.
     * TODO: improve sentence boundary regex.
     * @param url - url, which content will be read
     * @param word - keyword for search in url content
     * @return list of sentences with keyword
     * @throws IOException
     */
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
