package dto;


import java.time.LocalTime;

public class BookingRecord {
    private String employeeId;
    private LocalTime meetingStartTime;
    private LocalTime meetingEndTime;

    public BookingRecord() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalTime getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(LocalTime meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public LocalTime getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(LocalTime meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    @Override
    public String toString() {
        return "BookingRecord{" +
                "employeeId='" + employeeId + '\'' +
                ", meetingStartTime=" + meetingStartTime +
                ", meetingEndTime=" + meetingEndTime +
                '}';
    }
}
