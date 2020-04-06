import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileWordAnalyzer {
    FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List getWordsOrderedAlphabetically() {
        String[] returnList = getWordsFromSentence();
        Arrays.sort(returnList);
        return Arrays.asList(returnList);
    }

    public List getWordsContainingSubstring (String subString) {
        String[] tempList = getWordsFromSentence();

        ArrayList<String> returnList = new ArrayList<>();
        for (String word : tempList) {
            if (word.toLowerCase().contains(subString)) {
                returnList.add(word);
            }
        }
        return returnList;
    }

    public List getStringsWhichPalindromes () {
        String[] tempList = getWordsFromSentence();

        List<String> returnList = new ArrayList<>();
        for (String word : tempList) {
            StringBuilder newWord = new StringBuilder(word.toLowerCase());
            StringBuilder reverse = newWord.reverse();

            if (word.toLowerCase().equals(reverse.toString())) {
                returnList.add(word);
            }
        }

        return returnList;
    }

    private String[] getWordsFromSentence() {
        String sentence = filePartReader.readLines();
        return sentence.split(" ");
    }
}
