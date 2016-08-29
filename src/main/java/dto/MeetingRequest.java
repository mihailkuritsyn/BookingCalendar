package dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;


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
public class MeetingRequest {
    private LocalDateTime submissionTime;
    private String employeeId;
    private LocalDateTime startTime;
    private int duration;

    @JsonCreator
    public MeetingRequest() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "dto.MeetingRequest{" +
                "submissionTime=" + submissionTime +
                ", employeeId=" + employeeId +
                ", startTime=" + startTime +
                ", duration=" + duration +
                '}';
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(LocalDateTime submissionTime) {
        this.submissionTime = submissionTime;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
