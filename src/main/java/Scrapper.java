import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Scrapper {

    public static void main(String[] args) throws IOException {

        Parsing parser = new Parser();
        StringBuilder input = new StringBuilder();
        List<String> keyWords;
        List<String> urls = new ArrayList<String>();
        URL link;
        char[] commands;
        DataCommands dataCommands = new DataCommands();
        int totalCharacters = 0;
        Map<String, Integer> keyWordsCounter = new HashMap<>();
//        Map<String, List<String>> sentencesWithKeyWords;


        for (int i = 0; i < args.length; i++) {
            input.append(args[i] + " ");
        }

        input.delete(input.length() - 1, input.length());

        String urlPath = parser.getURLtoString(input.toString());
        
        if("".equals(urlPath)){
            urlPath = parser.getPath(input.toString());
            urls = parser.getURLsToStringFromPath(urlPath);
        } else {
            urls.add(urlPath);
        }

        input.delete(0, urlPath.length() + 1);

        String commandsString = parser.getCommands(input.toString());

        input.delete(input.length() - commandsString.length(), input.length());

        commands = commandsString.replaceAll("\\s*-*", "").toCharArray();

        keyWords = parser.getKeywords(input.toString());

        for (String keyWord : keyWords) {
            keyWordsCounter.put(keyWord, 0);
        }


        for (String url : urls) {
            link = new URL(url);

            System.out.println("In " + url + " found: ");

            for (char command : commands) {
                switch (command) {
                    case 'w':

                        for (String keyWord : keyWords) {
                            Integer i = keyWordsCounter.get(keyWord);
                            Integer j = dataCommands.countWordOccurencesByURL(link, keyWord);
                            i += j;
                            keyWordsCounter.put(keyWord, i);
                            System.out.println(keyWord + ": " + j);
                        }
                        break;

                    case 'c':

                        int i = dataCommands.countNumberOfCharactesByURL(link);
                        System.out.println("Number of charactes: " + i);
                        totalCharacters += i;
                        break;

                    case 'e':

                        for (String keyWord : keyWords) {
                            System.out.println(keyWord + " is in sentences: ");
                            int k = 1;
                            for(String sentence : dataCommands.getSentencesContainingWord(link, keyWord)){
                                System.out.println(k + ") " + sentence);
                                k++;
                            };
                        }
                        break;
                }
            }
            System.out.println("--------------");

        }

        System.out.println("=========================");
        System.out.println("Total words occurences: ");

        for (Map.Entry<String, Integer> pair : keyWordsCounter.entrySet()){
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }

        System.out.println("Total number of characters: " + totalCharacters);

    }

}
