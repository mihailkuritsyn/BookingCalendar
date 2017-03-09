package serialization;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class FORMATTER {

    public static final DateTimeFormatter LOCAL_DATE = ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter LOCAL_TIME = ofPattern("HH:mm:ss");
    public static final DateTimeFormatter LOCAL_DATE_TIME = ofPattern("yyyy-MM-dd HH:mm:ss");

    private FORMATTER() {
    }
}
