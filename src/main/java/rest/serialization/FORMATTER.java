package rest.serialization;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public final class FORMATTER {

    public static final DateTimeFormatter LOCAL_DATE = ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter LOCAL_TIME = ofPattern("HH:mm:ss");

    private FORMATTER() {
    }
}
