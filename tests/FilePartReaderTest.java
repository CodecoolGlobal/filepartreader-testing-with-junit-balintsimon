import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {

    @Test
    void testIsExceptionOnFromLineLargerThanToLine() {
        FilePartReader filePartReader = new FilePartReader();

        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup("../test/test.txt", 3, 1));
    }

    @Test
    void testIsExceptionOnFromLineZero() {
        FilePartReader filePartReader = new FilePartReader();

        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", 0, 1));
    }

    @Test
    void testIsExceptionOnFromLineMinus() {
        FilePartReader filePartReader = new FilePartReader();

        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", -1, 1));
    }

    @Test
    void testIsSetupGetValues() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", 1, 2);
        assertTrue("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt" == filePartReader.getFilePath());
        assertEquals(1, filePartReader.getFromLine());
        assertEquals(2, filePartReader.getToLine());
    }

    @Test
    void testOneLineRead() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test1Line.txt", 1,1);
        String returnLine = filePartReader.read();
        assertTrue(returnLine.equals("1 line "));
    }

    @Test
    void testMultipleLineRead() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test2Line.txt", 1,2);
        String returnLine = filePartReader.read();
        assertTrue(returnLine.equals("1 line 2 line "));
    }

    @Test
    void testIsReadLineStartAndEndOnFromLineToLine() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", 4,5);
        String returnLine = filePartReader.readLines();
        assertTrue(returnLine.equals("4th line 5th A B C D E F G "));
    }

    @Test
    public void testIsWordsAreSortedAlphabetically() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", 4,5);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        List<String> actual = fileWordAnalyzer.getWordsOrderedAlphabetically();
        List<String> expected = Arrays.asList("4th", "5th", "A", "B", "C", "D", "E", "F", "G", "line");
        assertTrue(actual.equals(expected));
    }

    @Test
    public void testIsExistingSubstringFound() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", 6,6);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        List<String> actual = fileWordAnalyzer.getWordsContainingSubstring("an");
        List<String> expected = Arrays.asList("ANNA", "manna");
        assertTrue(actual.equals(expected));
    }

    @Test
    public void testIsNonExistingSubstringNotFound() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", 6,6);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        List<String> actual = fileWordAnalyzer.getWordsContainingSubstring("ban");
        List<String> expected = Arrays.asList();
        assertTrue(actual.equals(expected));
    }

    @Test
    public void testIsExistingPalindromeFound() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", 1,6);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        List<String> actual = fileWordAnalyzer.getStringsWhichPalindromes();
        List<String> expected = Arrays.asList("Kayak", "ANNA", "leveL");
        System.out.println(actual.toString());
        System.out.println(expected.toString());
        assertTrue(actual.equals(expected));
    }

    @Test
    public void testIsNonExistingPalindromeNotFound() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test/test.txt", 1,5);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        List<String> actual = fileWordAnalyzer.getStringsWhichPalindromes();
        List<String> expected = Arrays.asList();
        assertTrue(actual.equals(expected));
    }
}