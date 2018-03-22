package persistence.textfile;

import java.io.IOException;

public class TextFilePersistenceException extends RuntimeException {

    String fileName;
    int lineNumber;
    String line;

    public TextFilePersistenceException(String fileName, int lineNumber, String line, Throwable cause) {
        super(cause);
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.line = line;
    }

    public String getFileName() {
        return fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getLine() {
        return line;
    }
}
