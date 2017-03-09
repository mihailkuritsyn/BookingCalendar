package dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The first line of the input text represents the company office hours, in 24 hour clock format,
 * and the remainder of the input represents individual booking requests. Each booking request is in the following format.
 * <p>
 * [request submission time, in the format YYYY-MM-DD HH:MM:SS] [employee id]
 * [meeting start time, in the format YYYY-MM-DD HH:MM] [meeting duration in hours]
 * A sample input text:
 * 0900 1730
 * 2011-03-17 10:17:06 EMP001
 * 2011-03-21 09:00 2
 * 2011-03-16 12:34:56 EMP002
 * 2011-03-21 09:00 2
 * 2011-03-16 09:28:23 EMP003
 * 2011-03-22 14:00 2
 * 2011-03-17 11:23:45 EMP004
 * 2011-03-22 16:00 1
 * 2011-03-15 17:29:12 EMP005
 * 2011-03-21 16:00 3
 */
public class BookingRequest {


    private LocalTime officeHoursStart;
    private LocalTime officeHoursEnd;
    private List<MeetingRequest> meetingRequests = new ArrayList<>();

    @JsonCreator
    public BookingRequest() {
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (MeetingRequest meetingRequest : meetingRequests) {
            builder.append(meetingRequest).append("\n");
        }

        return "dto.BookingRequest{" +
                "officeHoursStart=" + officeHoursStart +
                ", officeHoursEnd=" + officeHoursEnd +
                ", meetingRequests=" + builder.toString() +
                '}';
    }

    public LocalTime getOfficeHoursStart() {
        return officeHoursStart;
    }

    public void setOfficeHoursStart(LocalTime officeHoursStart) {
        this.officeHoursStart = officeHoursStart;
    }

    public LocalTime getOfficeHoursEnd() {
        return officeHoursEnd;
    }

    public void setOfficeHoursEnd(LocalTime officeHoursEnd) {
        this.officeHoursEnd = officeHoursEnd;
    }

    public List<MeetingRequest> getMeetingRequests() {
        return meetingRequests;
    }

    public void setMeetingRequests(List<MeetingRequest> meetingRequests) {
        this.meetingRequests = meetingRequests;
    }

}