import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FilePartReader {
    public String getFilePath() {
        return filePath;
    }

    public Integer getFromLine() {
        return fromLine;
    }

    public Integer getToLine() {
        return toLine;
    }

    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "open.txt";
        this.fromLine = 0;
        this.toLine = 0;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        if (toLine < fromLine || fromLine < 1) throw new IllegalArgumentException();
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    public String read() {
        String fileData = "";
        try {
            File file = new File(this.filePath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                fileData += myReader.nextLine() + " ";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileData;
    }

    public String readLines() {
        String fileData = "";
        Integer currentLinePosition = 1;
        try {
            File file = new File(this.filePath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                if (currentLinePosition >= this.fromLine && currentLinePosition <= this.toLine) {
                    fileData += myReader.nextLine() + " ";
                } else {
                    myReader.nextLine();
                }
                currentLinePosition++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return fileData;
    }
}
