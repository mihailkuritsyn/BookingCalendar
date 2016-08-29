package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.BookingCalendar;
import dto.BookingRequest;
import dto.MeetingRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerTest.class);

    private JacksonTester<BookingCalendar> json;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void test() throws URISyntaxException, IOException {
        BookingRequest bookingRequest = mockBookingRequest();

        String url = "http://localhost:8080/booking_calendar";
        restTemplate.put(new URI(url), bookingRequest);

        HttpEntity<BookingRequest> entity = new HttpEntity<>(bookingRequest);

        ResponseEntity<BookingCalendar> response = restTemplate.exchange(url, HttpMethod.GET, entity, BookingCalendar.class);
        BookingCalendar bookingCalendar = response.getBody();

        LOG.info("app.ControllerTest.test" + json.write(bookingCalendar));

        assertThat(this.json.write(bookingCalendar)).isEqualToJson("../result.json");
    }

    private BookingRequest mockBookingRequest() {
        //        2011-03-17 10:17:06 EMP001
        //        2011-03-21 09:00 2
        MeetingRequest meetingRequest = new MeetingRequest();
        meetingRequest.setEmployeeId("EMP001");
        meetingRequest.setSubmissionTime(LocalDateTime.of(2011, 03, 17, 10, 17, 06));
        meetingRequest.setStartTime(LocalDateTime.of(2011, 03, 21, 9, 0));
        meetingRequest.setDuration(2);


        //        2011-03-16 12:34:56 EMP002
        //        2011-03-21 09:00 2
        MeetingRequest meetingRequest2 = new MeetingRequest();
        meetingRequest2.setEmployeeId("EMP002");
        meetingRequest2.setSubmissionTime(LocalDateTime.of(2011, 03, 16, 12, 34, 56));
        meetingRequest2.setStartTime(LocalDateTime.of(2011, 03, 21, 9, 0));
        meetingRequest2.setDuration(2);

        //        2011-03-16 09:28:23 EMP003
        //        2011-03-22 14:00 2
        MeetingRequest meetingRequest3 = new MeetingRequest();
        meetingRequest3.setEmployeeId("EMP003");
        meetingRequest3.setSubmissionTime(LocalDateTime.of(2011, 03, 16, 9, 28, 23));
        meetingRequest3.setStartTime(LocalDateTime.of(2011, 03, 22, 14, 0));
        meetingRequest3.setDuration(2);

        //        2011-03-17 11:23:45 EMP004
        //        2011-03-22 16:00 1
        MeetingRequest meetingRequest4 = new MeetingRequest();
        meetingRequest4.setEmployeeId("EMP004");
        meetingRequest4.setSubmissionTime(LocalDateTime.of(2011, 03, 17, 11, 23, 45));
        meetingRequest4.setStartTime(LocalDateTime.of(2011, 03, 22, 16, 0));
        meetingRequest4.setDuration(1);

        //        2011-03-15 17:29:12 EMP005
        //        2011-03-21 16:00 3
        MeetingRequest meetingRequest5 = new MeetingRequest();
        meetingRequest5.setEmployeeId("EMP005");
        meetingRequest5.setSubmissionTime(LocalDateTime.of(2011, 03, 15, 17, 29, 12));
        meetingRequest5.setStartTime(LocalDateTime.of(2011, 03, 21, 16, 0));
        meetingRequest5.setDuration(3);

        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setOfficeHoursStart(LocalTime.of(9, 0));
        bookingRequest.setOfficeHoursEnd(LocalTime.of(17, 30));
        bookingRequest.setMeetingRequests(
                Arrays.asList(meetingRequest, meetingRequest2, meetingRequest3, meetingRequest4, meetingRequest5));
        return bookingRequest;
    }

}
