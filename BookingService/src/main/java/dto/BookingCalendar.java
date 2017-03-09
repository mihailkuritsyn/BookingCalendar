package dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class BookingCalendar {

    private Map<LocalDate, BookingDay> days = new TreeMap<>();

    public BookingCalendar() {
    }

    public Map<LocalDate, BookingDay> getDays() {
        return days;
    }

    public void setDays(Map<LocalDate, BookingDay> days) {
        this.days = days;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (BookingDay bookingDay : days.values()) {
            builder.append(bookingDay).append("\n");
        }
        return "BookingCalendar{" +
                "days=" + builder.toString() +
                '}';
    }
}
