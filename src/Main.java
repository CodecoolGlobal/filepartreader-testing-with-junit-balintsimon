import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("/home/balladin/cc_git/filepartreader-testing-with-junit-balintsimon/src/test.txt", 1,1);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        String string = filePartReader.readLines();
        System.out.println(string);

    }
}
