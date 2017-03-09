package dto;

import java.time.LocalDateTime;

/**
 * TODO delete
 */
public class TestDto {

    private LocalDateTime submissionTime;
    private String employeeId;
    private LocalDateTime startTime;
    public TestDto() {
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "submissionTime=" + submissionTime +
                ", employeeId='" + employeeId + '\'' +
                ", startTime=" + startTime +
                '}';
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(LocalDateTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
