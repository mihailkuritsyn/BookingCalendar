package domain;

import dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

/**

 */
public class BookingService {

    private static final Logger LOG = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    BookingCalendar bookingCalendar;

    /**
     * Treats booking request and updates booking calendar
     * <br>
     * Constraints / Notes<br>
     * •	No part of a meeting may fall outside office hours.<br>
     * •	Meetings may not overlap.<br>
     * •	The booking submission system only allows one submission at a time, so submission times are guaranteed to be unique.<br>
     * •	Bookings must be processed in the chronological order in which they were submitted.<br>
     * •	The ordering of booking submissions in the supplied input is not guaranteed.<br>
     * •	The current requirements make no provision for alerting users of failed bookings; it is up to the user to confirm that their booking was successful.<br>
     *
     * @param bookingRequest
     */
    public final void updateBookingCalendar(BookingRequest bookingRequest) {
        bookingRequest.getMeetingRequests().sort((o1, o2) -> o1.getSubmissionTime().compareTo(o2.getSubmissionTime()));

        for (MeetingRequest meetingRequest : bookingRequest.getMeetingRequests()) {
            try {
                LocalTime requestStartTime = meetingRequest.getStartTime().toLocalTime();
                LocalTime requestEndTime = meetingRequest.getStartTime().plusHours(meetingRequest.getDuration()).toLocalTime();
                LocalDate requestDate = meetingRequest.getStartTime().toLocalDate();

                if (requestStartTime.compareTo(bookingRequest.getOfficeHoursStart()) < 0) {
                    throw new BookingException("No part of a meeting may fall outside office hours.");
                }

                if (requestEndTime.compareTo(bookingRequest.getOfficeHoursEnd()) > 0) {
                    throw new BookingException("No part of a meeting may fall outside office hours.");
                }

                BookingDay day = bookingCalendar.getDays().get(requestDate);
                if (day != null) {
                    for (BookingRecord record : day.getRecords().values()) {
                        if (isOverlapped(requestStartTime, requestEndTime,
                                record.getMeetingStartTime(), record.getMeetingEndTime())) {
                            throw new BookingException("Meetings may not overlap.");
                        }
                    }
                } else {
                    day = new BookingDay();
                    bookingCalendar.getDays().put(requestDate, day);
                }

                BookingRecord newRecord = new BookingRecord();
                newRecord.setEmployeeId(meetingRequest.getEmployeeId());
                newRecord.setMeetingStartTime(requestStartTime);
                newRecord.setMeetingEndTime(requestEndTime);

                day.getRecords().put(requestStartTime, newRecord);
            } catch (BookingException e) {
                LOG.error(e.getMessage());
            }
        }
    }

    /**
     * @return boolean true in case the date ranges overlap.
     */
    private boolean isOverlapped(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) throws NullPointerException {
        if ((start1.isBefore(start2) && end1.isAfter(start2)) ||
                (start1.isBefore(end2) && end1.isAfter(end2)) ||
                (start1.isBefore(start2) && end1.isAfter(end2))) {
            return true;
        } else {
            return false;
        }
    }
}
