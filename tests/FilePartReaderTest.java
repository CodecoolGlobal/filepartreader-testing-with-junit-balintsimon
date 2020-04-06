import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {

    @Test
    void testIsExceptionOnFromLineLargerThanToLine() {
        FilePartReader filePartReader = new FilePartReader();

        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup("d:/cc/filepartreader-testing-with-junit-balintsimon/test/test.txt", 3, 1));
    }

    @Test
    void testIsExceptionOnFromLineZero() {
        FilePartReader filePartReader = new FilePartReader();

        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup("d:/cc/filepartreader-testing-with-junit-balintsimon/test/test.txt", 0, 1));
    }

    @Test
    void testIsExceptionOnFromLineMinus() {
        FilePartReader filePartReader = new FilePartReader();

        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup("d:/cc/filepartreader-testing-with-junit-balintsimon/test/test.txt", -1, 1));
    }

    @Test
    void testIsSetupGetValues() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("d:/cc/filepartreader-testing-with-junit-balintsimon/test/test.txt", 1, 2);
        assertTrue("d:/cc/filepartreader-testing-with-junit-balintsimon/test/test.txt" == filePartReader.getFilePath());
        assertEquals(1, filePartReader.getFromLine());
        assertEquals(2, filePartReader.getToLine());
    }

    @Test
    void testOneLineRead() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("d:/cc/filepartreader-testing-with-junit-balintsimon/test/test1Line.txt", 1,1);
        String returnLine = filePartReader.read();
        assertTrue("1 line" == returnLine);
    }

    @Test
    void testMultipleLineRead() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("d:/cc/filepartreader-testing-with-junit-balintsimon/test/test2Line.txt", 1,2);
        String returnLine = filePartReader.read();
        assertTrue("1 line 2 line" == returnLine);
    }

    @Test
    void testIsFileNotFoundErrorThrown() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("d:/cc/filepartreader-testing-with-junit-balintsimon/test/testIT.txt", 1,1);
        assertThrows(FileNotFoundException.class, () -> filePartReader.read());
    }

    @Test
    void read() {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("d:/cc/filepartreader-testing-with-junit-balintsimon/test/testIT.txt", 1,1);
        assertThrows(FileNotFoundException.class, () -> filePartReader.read());

    }
}