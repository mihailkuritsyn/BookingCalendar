package dto;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

/**
 * 2011-03-21
 * 09:00 11:00 EMP002
 * 2011-03-22
 * 14:00 16:00 EMP003
 * 16:00 17:00 EMP004
 */
public class BookingDay {

    private Map<LocalTime, BookingRecord> records = new TreeMap<>();

    public BookingDay() {
    }

    public Map<LocalTime, BookingRecord> getRecords() {
        return records;
    }

    public void setRecords(Map<LocalTime, BookingRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (BookingRecord bookingRecord : records.values()) {
            builder.append(bookingRecord).append("\n");
        }
        return "BookingDay{" +
                "records=" + builder.toString() +
                '}';
    }
}
